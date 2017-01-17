package com.xbc.xframe.app.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by xiaobo.cui on 2016/9/22.
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private int layoutResId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResId, container, false);
        ButterKnife.bind(this,view);
        initArguments();
        initView();
        initListener();
        initData();
        return view;
    }

    protected void setContentView(int layoutResId){
        this.layoutResId=layoutResId;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.M)//api 23
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @SuppressWarnings("deprecation")// api <23
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mContext=activity;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initArguments();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();


}
