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

    public <T> void performRequest(final String method, final Request request, final Class<T> clazz, final Callback<T> callback) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Response response = null;
                T t = null;
                try {
                    response = HttpEngine.getInstance().execute(method, request);
                    Gson gson = new Gson();
                    t = gson.fromJson(response.result, clazz);
                    if (response.isSuccess()) {
                        subscriber.onNext(t);
                    } else {
                        LogUtil.i("onFailure");
                        callback.onFailure(null, t);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure(e, t);
                }
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onStart() {
                        LogUtil.i("onStart");
                        callback.onPreCall();
                    }

                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                        callback.onEndCall();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtil.i("onError");
                        callback.onFailure(e, null);
                        callback.onEndCall();

                    }

                    @Override
                    public void onNext(T response) {
                        LogUtil.i("onNext " + response);
                        try {
                            callback.onSuccess(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    public <T> void performGetRequest(final Request request, final Class<T> clazz, final Callback<T> callback) {
        performRequest(HttpEngine.GET, request, clazz, callback);
    }

    public <T> void performPostRequest(final Request request, final Class<T> clazz, final Callback<T> callback) {
        performRequest(HttpEngine.POST, request, clazz, callback);
    }
}
