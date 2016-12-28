package com.xbc.xframe.db;

import android.content.Context;
import android.os.Environment;

import com.litesuits.orm.LiteOrm;
import com.xbc.xframe.model.bean.AccountBean;
import com.xbc.xframe.util.LogUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobo.cui on 2016/9/26.
 */
public class RxDBManager implements IRxDBManager {
    public static final String SD_CARD = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 名字里包含路径符号"/"则将数据库建立到该路径下，可以使用sd卡路径。
     * 不包含则在系统默认路径下创建DB文件。
     */
    public static final String DB_NAME = SD_CARD + "/Test/amapdb.db";

    public static LiteOrm mLiteOrm;

    private static RxDBManager INSTANCE = new RxDBManager();


    public static RxDBManager getInstance() {
        return INSTANCE;
    }

    private RxDBManager() {

    }

    public static void init(Context context) {
        if (mLiteOrm == null) {
            mLiteOrm = LiteOrm.newSingleInstance(context, DB_NAME);
        }
    }

    @Override
    public Observable<List<AccountBean>> queryAllAccount() {


        return Observable.create(new Observable.OnSubscribe<List<AccountBean>>() {
            @Override
            public void call(Subscriber<? super List<AccountBean>> subscriber) {
                List<AccountBean> list = mLiteOrm.query(AccountBean.class);
                subscriber.onNext(list);
                subscriber.onCompleted();
                LogUtil.i(Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io());
    }
}
