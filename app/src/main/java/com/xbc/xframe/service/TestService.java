package com.xbc.xframe.service;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ListView;

import com.xbc.xframe.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xiaobo.cui on 2017/3/27.
 */

public class TestService extends Service {

    public static int ACTION = AccessibilityNodeInfo.ACTION_SCROLL_FORWARD;
    public static boolean FLAG = true;
    public static SparseArray<String> IDS;
    public static Handler mHandler = new Handler(new Handler.Callback() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    View view = null;
                    ListView listView = null;
                    SwipeRefreshLayout swipeRefreshLayout = null;
                    Activity activity = getCurrentActivity();
                    if (activity != null) {
                        try {
//                            view = activity.findViewById(R.id.btn_test_shape_selector);
                            listView = (ListView) activity.findViewById(R.id.list_view);
                            swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_refresh_layout);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        if (view != null) {
//                            view.performClick();
//                        }

                        if (swipeRefreshLayout != null) {
                         /*

                         if (listView.getFirstVisiblePosition() == 0) {
                                scrollFromBottom(swipeRefreshLayout.getWidth(), swipeRefreshLayout.getHeight());
                                ACTION = AccessibilityNodeInfo.ACTION_SCROLL_FORWARD;
                            } else if (listView.getLastVisiblePosition() == listView.getCount() - 1) {
                                scrollFromTop(swipeRefreshLayout.getWidth(), swipeRefreshLayout.getHeight());
                                ACTION = AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD;
                            }

                            if (ACTION == AccessibilityNodeInfo.ACTION_SCROLL_FORWARD) {
                                scrollFromBottom(swipeRefreshLayout.getWidth(), swipeRefreshLayout.getHeight());
                            } else {
                                scrollFromTop(swipeRefreshLayout.getWidth(), swipeRefreshLayout.getHeight());

                            }*/

                            ViewGroup rootView = getRootView(activity);
                            if (FLAG) {
                                FLAG = false;
                                IDS=getRIdMembers();
                                printInfo(rootView);
                            }

                            Log.e("xbc", "----------->");

//                            scrollFromTop(swipeRefreshLayout.getWidth(),swipeRefreshLayout.getHeight());


//                            if (listView.getFirstVisiblePosition() == 0) {
//                                ACTION = AccessibilityNodeInfo.ACTION_SCROLL_FORWARD;
//                            } else if (listView.getLastVisiblePosition() == listView.getCount() - 1) {
//                                ACTION = AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD;
//                            }
//                            listView.performAccessibilityAction(ACTION, null);
//                            listView.smoothScrollToPositionFromTop(listView.getAdapter().getCount(),20,1000);
                        }

                    }
                    break;
            }
            return false;
        }
    });

    public static Timer mTimer = new Timer();

    public TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            Log.e("xbc", "mTimerTask.run()-->");
            mHandler.sendEmptyMessage(1);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        doTest();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void doTest() {
        mTimer.schedule(mTimerTask, 5000, 5000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ViewGroup getRootView(Activity context) {
        return (ViewGroup) ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    private static void printInfo(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    View child = viewGroup.getChildAt(i);
                    printInfo(child);
                }
                //printInfo()
            }
        }
        String type = view.getClass().getSimpleName();
        String id = String.valueOf(view.getId());
        String ids = "";
        if (IDS != null && IDS.size() > 0) {
            ids = IDS.get(view.getId());
        }
        Log.e("xbc", type + "," + id + "," + ids);
        return;
    }

    public static SparseArray<String> getRIdMembers() {
        SparseArray<String> map = null;
        try {
            map = new SparseArray<String>();
            Class clazz = Class.forName(R.id.class.getName());
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                String name = f.getName();
                Object obj = f.get(name);
                if (obj instanceof Integer) {
                    Integer value = ((Integer) obj);
                    map.put(value, name);
                    Log.e("xbc integer", name + "," + value);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void scrollFromTop(final int w, final int h) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation inst = new Instrumentation();
                int x = w / 2;
                int y = h / 2;

                long dowTime = SystemClock.uptimeMillis();
                inst.sendPointerSync(MotionEvent.obtain(dowTime, dowTime,
                        MotionEvent.ACTION_DOWN, x, y, 0));
                for (int i = 0; i < 10; i++) {
                    inst.sendPointerSync(
                            MotionEvent.obtain(
                                    dowTime, dowTime + 10 * i, MotionEvent.ACTION_MOVE, x, y + 40 * i, 0
                            )
                    );
                }
                inst.sendPointerSync(MotionEvent.obtain(dowTime, dowTime + 9 * 10,
                        MotionEvent.ACTION_UP, x, y + 9 * 40, 0));
            }
        }).start();
    }


    private static void scrollFromBottom(final int w, final int h) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation inst = new Instrumentation();
                int x = w / 2;
                int y = h / 2;

                long dowTime = SystemClock.uptimeMillis();
                inst.sendPointerSync(MotionEvent.obtain(dowTime, dowTime,
                        MotionEvent.ACTION_DOWN, x, y, 0));
                for (int i = 0; i < 10; i++) {
                    inst.sendPointerSync(
                            MotionEvent.obtain(
                                    dowTime, dowTime + 10 * i, MotionEvent.ACTION_MOVE, x, y - 40 * i, 0
                            )
                    );
                }
                inst.sendPointerSync(MotionEvent.obtain(dowTime, dowTime + 9 * 10,
                        MotionEvent.ACTION_UP, x, y - 9 * 40, 0));
            }
        }).start();
    }

}
