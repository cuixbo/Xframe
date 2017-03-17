package com.xbc.xframe.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xbc.lib.common.util.LogUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;
import com.xbc.xframe.model.bean.WeatherResponseBean;
import com.xbc.xframe.net.ApiService;
import com.xbc.xframe.net.BaseRequest;
import com.xbc.xframe.net.Callback;
import com.xbc.xframe.ui.dialog.BottomDialog;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {
        mBtnLogin.setText("login");
    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doBottomDialogTest();
                doApiServiceTest2();
            }
        });
    }

    @Override
    protected void initData() {

    }


    private void doApiServiceTest2() {
        BaseRequest request = new BaseRequest();
        request.url = "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=301b490141089aa88d82f47c4e1959b3";
        new ApiService.Builder()
                .setRequest(request)
                .setResponseClass(WeatherResponseBean.class)
                .setCallback(new Callback<WeatherResponseBean>() {
                    @Override
                    public void onSuccess(WeatherResponseBean response) {
                        LogUtil.i("doApiServiceTest2:" + response.status);
                    }
                })
                .execute();
    }


    public void doBottomDialogTest() {
        BottomDialog dialog = new BottomDialog(mContext);
        dialog.addItem("刷新", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).addItem("用手机浏览器打开", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).addItem("返回首页", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    private void doRxJavaOnStartTest() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                LogUtil.i(Thread.currentThread().getName());

                int i = 0;
                for (; i < 200; i++) {
//                    LogUtil.i(i+"");
                }
                subscriber.onNext(i + "");
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        LogUtil.i("onStart" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i("onNext" + Thread.currentThread().getName());
                    }
                });
    }


}
