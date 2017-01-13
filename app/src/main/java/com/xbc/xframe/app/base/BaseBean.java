package com.xbc.xframe.app.base;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class BaseBean implements Serializable {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int _id;
}
