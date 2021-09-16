package com.hhh.dingdingtask;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/**
 * 开机广播接收器类
 * https://blog.csdn.net/hou09tian/article/details/80389054
 * https://blog.csdn.net/qq_19004627/article/details/103911316
 */
public class BootBroadcastReceiver extends android.content.BroadcastReceiver {
    //android.intent.action.BOOT_COMPLETED是已开机广播对应的字符串。
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        //在onReceive()方法中，首先通过收到的intent判断广播类型。getAction()方法的作用是获取接收到的Intent的动作。ACTION是定义的字符串。
        if (intent.getAction().equals(ACTION)) {
            Intent myIntent = new Intent(context, MainActivity.class);
            //我们通过广播来启动Activity的时候如果不设置intent的FLAG_ACTIVITY_NEW_TASK属性，就会报这个异常：
            //就是说在activity上下文之外（即除了activity调用的之外）调用startActivity需要FLAG_ACTIVITY_NEW_TASK属性。
            //https://www.pianshen.com/article/6822897031/
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//ALG_ACTIVITY_NEW_TASK表示创建一个新任务来启动该主活动
            context.startActivity(myIntent);
        }
    }
}
