package com.xbc.xframe;

import android.os.Environment;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;

/**
 * Created by xiaobo.cui on 2017/2/24.
 */

public class FirstUiautomatorTest extends UiAutomatorTestCase {

    String mScreenShotDir= Environment.getExternalStorageDirectory()+"/Xframe/ScreenShots";
    int imgName=0;
    public void testDemo() throws UiObjectNotFoundException {
        File file=new File(mScreenShotDir);
        if (!file.exists()) {
            file.mkdirs();
        }


        // 模拟 HOME 键点击事件
        getUiDevice().pressHome();

        // 现在打开了主屏应用，模拟点击所有应用按钮操作来启动所有应用界面。
        // 如果你使用了uiautomatorviewer来查看主屏，则可以发现“所有应用”按钮的
        // content-description 属性为“Apps”。可以使用该属性来找到该按钮。
        UiObject allAppsButton = new UiObject(new UiSelector().text("Xframe"));

        // 模拟点击所有应用按钮，并等待所有应用界面起来
        allAppsButton.clickAndWaitForNewWindow();
        sleep(1000);
        UiDevice.getInstance().takeScreenshot(new File(mScreenShotDir+"/"+(++imgName)+".png"));

        UiObject object = findUntilExist("btn_test_toast");

        UiCollection obj = new UiCollection(new UiSelector().className(LinearLayout.class));
        int count = obj.getChildCount(new UiSelector().className(Button.class));
        for (int i = 0; i < count; i++) {
            UiObject btn = obj.getChildByInstance(new UiSelector().className(Button.class), i);
            btn.clickAndWaitForNewWindow();
            sleep(1000);
            UiDevice.getInstance().takeScreenshot(new File(mScreenShotDir+"/"+(++imgName)+".png"));
            sleep(1000);
            back(object);
        }


//        findUntilExist("btn_test_toast").clickAndWaitForNewWindow();
//        sleep(1000);
//        findUntilExist("tv_test_toast").click();
        sleep(20000);
    }


    private UiObject find(String id) {
        UiObject obj = new UiObject(new UiSelector().resourceId("com.xbc.xframe:id/" + id));
        return obj;
    }

    private UiObject findUntilExist(String id) {
        UiObject obj = find(id);
        boolean waiting = true;
        while (waiting) {
            if (obj != null && obj.exists()) {
                waiting = false;
            }
        }
        return obj;
    }

    private boolean exist(String id) {
        boolean ret = false;
        try {
            UiObject obj = find(id);
            if (obj != null && obj.exists()) {
                ret = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void back(UiObject obj) {
        if (obj != null) {
            while (!obj.exists()) {
                getUiDevice().pressBack();
            }
        }
    }

}
