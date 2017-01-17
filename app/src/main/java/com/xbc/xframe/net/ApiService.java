package com.xbc.xframe.net;

import com.google.gson.Gson;
import com.xbc.xframe.util.LogUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobo.cui on 2016/9/29.
 */
public class ApiService {

    public static ApiService INSTANCE = new ApiService();

    private ApiService() {

    }

    public static ApiService getInstance() {
        return INSTANCE;
    }


    private <T> void performRequest(final String method, final Request request, final Class<T> clazz, final Callback<T> callback) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Response response = null;
                T t = null;
                try {
                    response = HttpEngine.execute(method, request);
                    Gson gson = new Gson();
                    t = gson.fromJson(response.result, clazz);
                    if (t instanceof Response) {
                        Response castResp = (Response) t;
                        castResp.request = response.request;
                        castResp.result = response.result;
                        castResp.statusCode = response.statusCode;
                    }
                    if (response.isSuccess()) {
                        subscriber.onNext(t);
                    } else {
                        LogUtil.e("onFailure:http status:" + response.getStatusCode());
                        callback.onFailure(null, t);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    LogUtil.e("onFailure");
//                    callback.onFailure(e, t);
                } finally {
                    subscriber.onCompleted();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onStart() {
//                        LogUtil.i("onStart");
                        callback.onPreCall();
                    }

                    @Override
                    public void onCompleted() {
//                        LogUtil.i("onCompleted");
                        callback.onEndCall();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtil.e("onError:" + e.getLocalizedMessage());
                        callback.onFailure(e, null);
                        callback.onEndCall();
                    }

                    @Override
                    public void onNext(T response) {
//                        LogUtil.i("onNext " + response);
                        callback.onSuccess(response);
                    }
                });

    }

    private <T> void performGetRequest(final Request request, final Class<T> clazz, final Callback<T> callback) {
        performRequest(HttpEngine.GET, request, clazz, callback);
    }

    private <T> void performPostRequest(final Request request, final Class<T> clazz, final Callback<T> callback) {
        performRequest(HttpEngine.POST, request, clazz, callback);
    }

    public static class Builder {
        public Request request;
        public Class clazz;
        public Callback callback;

        public Builder setRequest(Request request) {
            this.request = request;
            return this;
        }

        public Builder setResponseClass(Class clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder setCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public Builder execute() {
            INSTANCE.performPostRequest(request, clazz, callback);
            return this;
        }

        public Builder execute(Callback callback) {
            this.callback = callback;
            INSTANCE.performPostRequest(request, clazz, callback);
            return this;
        }

        public Builder executeGet() {
            INSTANCE.performGetRequest(request, clazz, callback);
            return this;
        }

        public Builder executePost() {
            INSTANCE.performPostRequest(request, clazz, callback);
            return this;
        }
    }
}
