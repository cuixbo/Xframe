package com.xbc.xframe;

import android.test.InstrumentationTestCase;

import com.xbc.xframe.ui.activity.LoginActivity;
import com.xbc.xframe.ui.activity.TestActivity;

/**
 * Created by xiaobo.cui on 2017/2/23.
 */

public class TestSubject extends InstrumentationTestCase {

    public void testStartActivity() throws Exception {
        launchActivity("com.xbc.xframe", TestActivity.class,null);
    }

    public void testStartActivity2() throws Exception {
        launchActivity("com.xbc.xframe", LoginActivity.class,null);
    }
}
