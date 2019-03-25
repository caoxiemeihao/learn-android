package com.atom.activityatom;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        ActivityCollector.activities.add(activity);
    }
    public static void removeActivity(Activity activity) {
        ActivityCollector.activities.remove(activity);
    }
    public static void finishAll() {
        for (Activity activity: ActivityCollector.activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        // android.os.Process.killProcess(android.os.Process.myPid());
    }
}
