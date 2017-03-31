package com.xbc.xframe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.btn_test_liteorm)
    Button mBtnTestLiteOrm;
    @BindView(R.id.btn_test_shape_selector)
    Button mBtnTestShapeSelector;
    @BindView(R.id.btn_test_toast)
    Button mBtnTestToast;
    @BindView(R.id.btn_test_photo_preview)
    Button mBtnTestPhotoPreview;
    @BindView(R.id.btn_test_date_picker)
    Button mBtnTestDatePicker;
    @BindView(R.id.btn_test_listview)
    Button mBtnTestListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    protected void initIntent() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        mBtnTestLiteOrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLiteOrmTest();
            }
        });

        mBtnTestShapeSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShapeSelectorTest();
            }
        });

        mBtnTestToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToastTest();
            }
        });

        mBtnTestPhotoPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPhotoPreviewTest();
            }
        });

        mBtnTestDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDatePickerTest();
            }
        });

        mBtnTestListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDateListView();
            }
        });
    }

    @Override
    protected void initData() {

    }

    public void doLiteOrmTest() {
        Intent intent = new Intent(mContext, TestFragmentActivity.class);
        intent.putExtra("testId", 0);
        startActivity(intent);
    }

    public void doShapeSelectorTest() {
        Intent intent = new Intent(mContext, TestFragmentActivity.class);
        intent.putExtra("testId", 1);
        startActivity(intent);
    }

    public void doToastTest() {
        Intent intent = new Intent(mContext, TestFragmentActivity.class);
        intent.putExtra("testId", 2);
        startActivity(intent);
    }

    public void doPhotoPreviewTest() {
        Intent intent = new Intent(mContext, PhotoPreviewActivity.class);
        intent.putExtra("imgIds", new int[]{R.mipmap.ic_launcher, R.drawable.icon_success, R.drawable.icon_warn});
        intent.putExtra("index", 0);
        String[] urls = new String[]{
                "http://dwz.cn/57ZdXt",
                "http://dwz.cn/57ZkVq",
                "http://dwz.cn/57ZlQh",
                "http://dwz.cn/57ZzuA",
                "http://dwz.cn/57ZmPg",
                "http://dwz.cn/57ZmYC",
                "http://dwz.cn/57ZnBL",
                "http://dwz.cn/57ZoEY",
                "http://dwz.cn/57ZoL5",
                "http://dwz.cn/57Zp5Z",
                "http://dwz.cn/57Zpx3",
                "http://dwz.cn/57ZpR0",
                "http://dwz.cn/57ZqzP",
                "http://dwz.cn/57ZqWw",
                "http://dwz.cn/57ZtF9",
                "http://dwz.cn/57ZtYB",
                "http://dwz.cn/57ZlKd"
        };
        intent.putExtra("urls", urls);
//        intent.putExtra("paths",new String[]{"/mnt/sdcard/pic.jpg"});
        startActivity(intent);
    }

    public void doDatePickerTest() {
        Intent intent = new Intent(mContext, TestFragmentActivity.class);
        intent.putExtra("testId", 3);
        startActivity(intent);
    }

    public void doDateListView() {
        Intent intent = new Intent(mContext, TestFragmentActivity.class);
        intent.putExtra("testId", 4);
        startActivity(intent);
    }


}
