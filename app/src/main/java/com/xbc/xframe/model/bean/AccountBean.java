package com.xbc.xframe.model.bean;


import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;
import com.xbc.xframe.app.base.BaseBean;

/**
 * Created by xiaobo.cui on 2016/9/26.
 */

@Table("Account")
public class AccountBean extends BaseBean {

    public String webgisid;

    @NotNull
    public String username;

    public String name;

    public String organs;

    public String phone;

}
