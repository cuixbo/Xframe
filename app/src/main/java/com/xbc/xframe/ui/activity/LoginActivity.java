package com.xbc.xframe.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;
import com.xbc.xframe.db.RxDBManager;
import com.xbc.xframe.model.bean.AccountBean;
import com.xbc.xframe.model.bean.WeatherResponseBean;
import com.xbc.xframe.net.ApiService;
import com.xbc.xframe.net.BaseRequest;
import com.xbc.xframe.net.Callback;
import com.xbc.xframe.ui.dialog.BottomDialog;
import com.xbc.xframe.util.LogUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
//        doQueryAllStudents();
//        doCallbackTest();
//        doRxJavaOnStartTest();
//        doApiServiceTest();

    }


    private void doBottomDialogTest() {
        BottomDialog dialog = new BottomDialog(mContext);
//        dialog.setDescription("退出账号将中断当前未发送完的内容")
//                .addItemHighlight("退出",null);
        dialog
//                .setDescription("退出账号将中断当前未发送完的内容")
                .addItem("刷新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItem("用手机浏览器打开", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItem("返回首页", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        dialog.show();
    }

    private void initView() {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBottomDialogTest();
            }
        });
    }


    private void doApiServiceTest() {
        BaseRequest request = new BaseRequest();
        request.url = "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=E4805d16520de693a3fe707cdc962045";
//        request.param.put("type","1");
//        request.param.put("id","xbc");
//        request.param.put("pw","1234");
//        request.param.put("serno","2");
        ApiService.getInstance().performGetRequest(request, WeatherResponseBean.class, new Callback<WeatherResponseBean>() {
            @Override
            public void onSuccess(WeatherResponseBean response) {
                LogUtil.i("doApiServiceTest:" + response.weatherInfo.city);
            }

        });

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

    /**
     * LiteOrm 查询Sample
     */
    private void doQueryAllAccount() {
        RxDBManager.getInstance()
                .queryAllAccount()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AccountBean>>() {
                    @Override
                    public void call(List<AccountBean> list) {
                        LogUtil.i(Thread.currentThread().getName());
                        for (int i = 0; i < list.size(); i++) {
                            LogUtil.i(list.get(i).username);
                        }
                    }
                });
    }

    /**
     * LiteOrm 查询Sample
     */
    private void doCallbackTest() {


    }


}
