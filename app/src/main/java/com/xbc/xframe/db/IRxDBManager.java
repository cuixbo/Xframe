package com.xbc.xframe.db;


import java.util.List;

import rx.Observable;

/**
 * Created by xiaobo.cui on 2016/9/26.
 */
public interface IRxDBManager {

    <T> Observable<Long> insert(T t);

    <T> Observable<Long> insert(List<T> list);

    <T> Observable<Long> save(T t);

    <T> Observable<Long> save(List<T> list);

    <T> Observable<List<T>> queryAll(Class<T> clazz);

    <T> Observable<List<T>> queryAllByEqual(Class<T> clazz, String col, Object val);

    <T> Observable<List<T>> queryAllByWhere(Class<T> clazz, String where, Object[] objArgs);

    <T> Observable<Long> updateAll(List<T> list);

}
