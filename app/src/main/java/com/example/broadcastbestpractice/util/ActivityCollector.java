package com.example.broadcastbestpractice.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 安维 on 2017/4/28.
 */

public class ActivityCollector {
    public static List<Activity> activityList= new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public static void  removeAll(){
        for(Activity activity:activityList){
            if(!activity.isFinishing())
            activity.finish();
        }
        activityList.clear();
    }

}
