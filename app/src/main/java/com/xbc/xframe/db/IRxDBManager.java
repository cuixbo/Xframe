package com.xbc.xframe.db;


import com.xbc.xframe.model.bean.AccountBean;

import java.util.List;

import rx.Observable;

/**
 * Created by xiaobo.cui on 2016/9/26.
 */
public interface IRxDBManager {

    Observable<List<AccountBean>> queryAllAccount();
}
