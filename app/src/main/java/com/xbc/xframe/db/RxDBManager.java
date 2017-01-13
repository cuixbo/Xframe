package com.xbc.xframe.db;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by xiaobo.cui on 2016/9/26.
 */
public class RxDBManager implements IRxDBManager {


    private static RxDBManager INSTANCE = new RxDBManager();


    public static RxDBManager getInstance() {
        return INSTANCE;
    }

    private RxDBManager() {

    }

    @Override
    public <T> Observable<Long> insert(final T t) {
        return Observable
                .create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        Long result = DBManager.getInstance().insert(t);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<Long> insert(final List<T> list) {
        return Observable
                .create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        Long result = DBManager.getInstance().insert(list);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<Long> save(final T t) {
        return Observable
                .create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        Long result = DBManager.getInstance().save(t);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<Long> save(final List<T> list) {
        return Observable
                .create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        Long result = DBManager.getInstance().save(list);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<List<T>> queryAll(final Class<T> clazz) {

        return Observable
                .create(new Observable.OnSubscribe<List<T>>() {
                    @Override
                    public void call(Subscriber<? super List<T>> subscriber) {
                        List<T> list = DBManager.getInstance().queryAll(clazz);
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<List<T>> queryAllByEqual(final Class<T> clazz, final String col, final Object val) {
        return Observable
                .create(new Observable.OnSubscribe<List<T>>() {
                    @Override
                    public void call(Subscriber<? super List<T>> subscriber) {
                        List<T> list = DBManager.getInstance().queryAllByEqual(clazz, col, val);
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<List<T>> queryAllByWhere(final Class<T> clazz, final String where, final Object[] objArgs) {
        return Observable
                .create(new Observable.OnSubscribe<List<T>>() {
                    @Override
                    public void call(Subscriber<? super List<T>> subscriber) {
                        List<T> list = DBManager.getInstance().queryAllByWhere(clazz, where, objArgs);
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable<Long> updateAll(final List<T> list) {
        return Observable
                .create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        Long result = DBManager.getInstance().updateAll(list);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
