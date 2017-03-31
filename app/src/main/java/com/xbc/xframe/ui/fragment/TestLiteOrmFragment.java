package com.xbc.xframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xbc.lib.common.util.LogUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseFragment;
import com.xbc.xframe.db.DBManager;
import com.xbc.xframe.db.RxDBManager;
import com.xbc.xframe.model.bean.AccountBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestLiteOrmFragment extends BaseFragment {

    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_query)
    Button btnQuery;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_liteorm);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initArguments() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInsert();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDelete();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdate();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doQuery();
            }
        });
    }

    @Override
    protected void initData() {

    }


    public void doInsert() {
        int i = new Random().nextInt(10);
        AccountBean account = new AccountBean();
        account.name = "xbc" + i;
        account.username = "xbc" + i;
        account.webgisid = "060534106" + i;
        account.organs = "dajie" + i;
        account.phone = "186" + i;
        RxDBManager.getInstance()
                .insert(account)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long result) {
                        LogUtil.i(String.valueOf(result));
                    }
                });
    }

    public void doSave() {
        int i = new Random().nextInt(10);
        AccountBean account = new AccountBean();
        account.name = "xbc" + i;
        account.username = "xbc" + i;
        account.webgisid = "060534106" + i;
        account.organs = "dajie" + i;
        account.phone = "186" + i;
        RxDBManager.getInstance()
                .save(account)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long result) {
                        LogUtil.i(String.valueOf(result));
                    }
                });
    }

    public void doDelete() {
        int i = new Random().nextInt(10);
        AccountBean account = new AccountBean();
        account.name = "xbc" + i;
        account.username = "xbc" + i;
        account.webgisid = "060534106" + i;
        account.organs = "dajie" + i;
        account.phone = "186" + i;
        DBManager.getInstance().delete(account);

    }

    public void doUpdate() {
        AccountBean account = DBManager.getInstance().queryByEqual(AccountBean.class, "name", "xbc1");
        if (account != null) {
            account.name = "xbc13";
            DBManager.getInstance().update(account);
        }
    }

    public void doQuery() {
        RxDBManager.getInstance()
                .queryAll(AccountBean.class)
                .subscribe(new Action1<List<AccountBean>>() {
                    @Override
                    public void call(List<AccountBean> list) {
                        List<String> names = new ArrayList<String>();
                        for (int i = 0; i < list.size(); i++) {
                            names.add(list.get(i).name);
                        }
                        LogUtil.i(names.toString());
                    }
                });
    }


}
