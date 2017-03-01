package com.xbc.xframe;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.xbc.xframe.ui.activity.LoginActivity;

/**
 * Created by xiaobo.cui on 2017/2/23.
 */

public class ActivityUnitTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    Context mContext;

    public ActivityUnitTest() {
        super(LoginActivity.class);
    }


    public ActivityUnitTest(Class<LoginActivity> activityClass) {
        super(activityClass);
    }

    public ActivityUnitTest(String pkg, Class<LoginActivity> activityClass, Context mContext) {
        super(pkg, activityClass);
        this.mContext = mContext;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext=getActivity().getApplicationContext();
    }

    public void testStart(){
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
