package com.hhh.dingdingtask.tools;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

/**
 * https://www.jianshu.com/p/6be84993d2f7
 * <p>
 * https://blog.csdn.net/kingyc123456789/article/details/107179126/
 * <p>
 * https://cloud.tencent.com/developer/article/1739647
 */
public class AlarmManagerUtil {

    public static final long TIME_INTERVAL = 1000 * 60 * 60 * 24;//闹钟执行任务的时间间隔 为1天  //1000 * 20 ;//
    private static AlarmManager alarmManager;
    //闹钟意图 重复/取消闹钟根据意图重复/取消  通过这个来判断应用是否启动
    public static HashMap<String, PendingIntent> pendingIntents = new HashMap<String, PendingIntent>();

    //初始化
    private static void init(Context activity) {
        //获得AlarmManager实例对象
        if (alarmManager == null) {
            alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        }
    }

    /**
     * 添加闹钟
     *
     * @param activity
     * @param hour
     * @param minute
     * @param id
     */
    public static void addAlarm(Context activity, int hour, int minute, int id) {
        PendingIntent pendingIntent = buildPendingIntent(activity, hour, minute, id, false);
        //先取消 再添加
        //alarmManager.cancel(pendingIntent);
        //时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 00);
        long TaskTimer = calendar.getTimeInMillis();//第一次执行的时间
        long autoTime = calendar.getTimeInMillis() - System.currentTimeMillis();
        if (autoTime < 0) {//1天后的这个时刻
            TaskTimer += 1000 * 60 * 60 * 24;
        }
        //AlarmManager.ELAPSED_REALTIME：使用相对时间，可以通过SystemClock.elapsedRealtime() 获取（从开机到现在的毫秒数，包括手机的睡眠时间），设备休眠时并不会唤醒设备。
        //AlarmManager.ELAPSED_REALTIME_WAKEUP：与ELAPSED_REALTIME基本功能一样，只是会在设备休眠时唤醒设备。
        //AlarmManager.RTC：使用绝对时间，可以通过 System.currentTimeMillis()获取，设备休眠时并不会唤醒设备。
        //AlarmManager.RTC_WAKEUP: 与RTC基本功能一样，只是会在设备休眠时唤醒设备。
        //设置重复执行的定时任务
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//  6.0之后的版本，SDK API >= 23
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, TaskTimer, pendingIntent);
            Log.e(AlarmManagerUtil.class.getSimpleName(), "6.0+版本闹钟定时广播消息成功");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//  4.4之后，6.0之前， SDK API >= 19, SDK API < 23
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, TaskTimer, pendingIntent);
            Log.e(AlarmManagerUtil.class.getSimpleName(), "4.0-6.0版本闹钟定时广播消息成功");
        } else {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TaskTimer, TIME_INTERVAL, pendingIntent);
            Log.e(AlarmManagerUtil.class.getSimpleName(), "4.0-版本闹钟定时广播消息成功");
        }
    }

    /**
     * @param activity
     * @param hour
     * @param minute
     * @param id
     * @param requestCodeIsRandom 为fals则requestCode是id的值，true是随机数
     * @return
     */
    public static PendingIntent buildPendingIntent(Context activity, int hour, int minute, int id, boolean requestCodeIsRandom) {
        init(activity);
        //消息传递
        int requestCode = 0;
        if (!requestCodeIsRandom) {
            requestCode = id;
        } else {
            requestCode = 10 + new Random().nextInt(1000);
        }
        Intent alarmIntent = new Intent("dingding");
        Bundle bundle = new Bundle();
        bundle.putInt("hour", hour);
        bundle.putInt("minute", minute);
        bundle.putInt("id", id);
        bundle.putInt("requestCode", requestCode);

        alarmIntent.putExtra("timer", bundle);
        alarmIntent.setPackage(activity.getPackageName());

        //延迟执行的 Intent 闹钟定时发送广播信息
        //将第二个参数设置成自增变量，保证每次不同，将第四个参数设置成及时更新。只有这两个参数都设置才有用。数据不变，更新了也没用，数据变了，不更新也没用。
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, requestCode, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //激昂意图
        //pendingIntents.put(String.valueOf(requestCode),pendingIntent);
        return pendingIntent;
    }

    /**
     * 高版本重复设置闹钟达到低版本中setRepeating相同效果
     *
     * @param oldrequestCode
     * @param hour
     * @param minute
     * @param id
     */
    public static void repeatAlarm(Context context, int oldrequestCode, int hour, int minute, int id) {
        //取消原来的
        cancelAlarm(context, oldrequestCode, hour, minute, id);
        PendingIntent pendingIntent = buildPendingIntent(context, hour, minute, id, false);
        //高版本重复设置闹钟达到低版本中setRepeating相同效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// 6.0及以上
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + TIME_INTERVAL, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4及以上
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + TIME_INTERVAL, pendingIntent);
        }
    }

    /**
     * @param activity
     * @param requestCode
     * @param hour
     * @param minute
     * @param id
     */
    public static void cancelAlarm(Context activity, int requestCode, int hour, int minute, int id) {
        //消息传递
        Intent alarmIntent = new Intent("dingding");
        Bundle bundle = new Bundle();
        bundle.putInt("hour", hour);
        bundle.putInt("minute", minute);
        bundle.putInt("id", id);
        bundle.putInt("requestCode", requestCode);

        alarmIntent.putExtra("timer", bundle);
        alarmIntent.setPackage(activity.getPackageName());

        //延迟执行的 Intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, requestCode, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //取消闹钟
        alarmManager.cancel(pendingIntent);
    }

    public static void cancelAlarmWithRequestCodeIsId(Context activity, int hour, int minute, int id) {
        cancelAlarm(activity, id, hour, minute, id);
    }

    public static boolean hasAlarm(Context activity, int requestCode, int hour, int minute, int id) {
        Intent alarmIntent = new Intent("dingding");
        Bundle bundle = new Bundle();
        bundle.putInt("hour", hour);
        bundle.putInt("minute", minute);
        bundle.putInt("id", id);
        bundle.putInt("requestCode", requestCode);

        alarmIntent.putExtra("timer", bundle);
        alarmIntent.setPackage(activity.getPackageName());

        //延迟执行的 Intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, requestCode, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return (PendingIntent.getBroadcast(activity, requestCode, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT) != null);
    }

    public static boolean hasAlarmWithRequestCodeIsId(Context activity, int hour, int minute, int id) {

        return hasAlarm(activity, id, hour, minute, id);
    }
}
