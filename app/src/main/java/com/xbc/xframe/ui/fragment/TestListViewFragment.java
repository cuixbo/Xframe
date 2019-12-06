package com.xbc.xframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xbc.lib.common.util.RandomUtil;
import com.xbc.lib.common.util.ToastUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaobo.cui on 2017/1/12.
 */

public class TestListViewFragment extends BaseFragment {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.list_view)
    ListView mListView;
    List<String> mData = new ArrayList<String>();
    MyAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_listview);
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
//        mListView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add((i + 1) + "  " + RandomUtil.getRandomLetters(9));
        }
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_myadapter, parent, false);

            TextView tvItem = (TextView) convertView.findViewById(R.id.tv_item);
            Button btnItem = (Button) convertView.findViewById(R.id.btn_item);
            String text = mData.get(position);
            tvItem.setText(text);
            btnItem.setText((position + 1) + "");
            final String btnString = (position + 1) + "";
            btnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(btnString);
                }
            });
            return convertView;
        }
    }

}
