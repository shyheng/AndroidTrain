package com.hhh.dingdingtask;

import static android.content.ContentValues.TAG;
import static android.content.Context.KEYGUARD_SERVICE;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import com.hhh.dingdingtask.dao.AlarmDao;
import com.hhh.dingdingtask.entity.Alarm;
import com.hhh.dingdingtask.tools.AlarmManagerUtil;
import com.hhh.dingdingtask.tools.NotificationManagerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 接受定时闹钟启动时的广播
 * https://blog.csdn.net/lyl0530/article/details/81105365
 * https://blog.csdn.net/lyl0530/article/details/81105365
 */
public class AlarmBroadcastReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getBundleExtra("timer");
        int hour = bundle.getInt("hour");
        int minute = bundle.getInt("minute");
        int id = bundle.getInt("id");//闹钟id
        int oldrequestCode = bundle.getInt("requestCode");
        AlarmDao alarmDao = new AlarmDao(context);
        Alarm o = alarmDao.QueryOne(id);
        //传递数据
        String clockType = o.getWorktype();//签到类型
        if (clockType.equals(MyApplication.GO_WORK)) {//打卡类型 上班打卡
            MyApplication.getInstance().setGoWork();
        } else {//下班打卡
            MyApplication.getInstance().setOffWork();
        }
        //重置
        MyApplication.getInstance().setSteps(new boolean[3]);
        //高版本重复闹钟方法  https://zhuanlan.zhihu.com/p/129613784
        AlarmManagerUtil.repeatAlarm(context, oldrequestCode, hour, minute, id);
        Log.e("dingding", "闹钟广播接收消息");

        //模拟真实点随机延迟执行
        //new Handler().postDelayed(new Runnable(){
        //    public void run(){
        //execute the task
        //TimerTask task = new TimerTask() {
        //@Override
        //public void run() {
        //要执行的操作
        Log.e("dingding", "延时亮屏、发送广播、启动钉钉成功");
        //唤醒屏幕 解锁
        //if (!isScreenOn(context)) {
            wakeUpAndUnlock(context);
        //}
        //发送广播通知
        NotificationManagerUtil.simpleNoticeWithTime(context, "钉钉小助手", "闹钟启动成功");
        //启动钉钉应用
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        //重点是加这个 启动钉钉
        ComponentName cn = new ComponentName("com.alibaba.android.rimet", "com.alibaba.android.rimet.biz.LaunchHomeActivity");
        i.setComponent(cn);
        context.startActivity(i);
        //}
        //};
        //Timer timer = new Timer();
        //timer.schedule(task, new Random().nextInt(3000+60*1000));//产生3秒到1分之间的随机时间
        // }
        // },new Random().nextInt(60*1000)); //产生0到1分之间的随机时间
    }

    /**
     * 点亮并解锁屏幕
     *
     * @param context
     */
    public static void wakeUpAndUnlock(Context context) {

        //获取电源管理器对象
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright:");
        //点亮屏幕
        wl.acquire(10000);
        //释放
        wl.release();
        //屏锁管理器
        KeyguardManager km = (KeyguardManager) context.getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
//        for (int i = 0; i < 20000; i++) {
//            Log.e(TAG, "等待解锁....！");
//        }
        if (!km.isKeyguardLocked()) {
            Log.e(TAG, "解锁成功....！");
        }
    }

    /**
     * 熄屏 好像不行
     *
     * @param
     */
    public static void checkScreenOff(Context context) {
        DevicePolicyManager policyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        //if(policyManager.isAdminActive()){
        policyManager.lockNow();
        // }else{
        // }
    }


    /**
     * 屏幕是否点亮
     *
     * @param context
     * @return
     */
    public boolean isScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.isInteractive();
    }

    /**
     * 通过包名打卡应用
     * // 打开钉钉
     * String packageName = "com.alibaba.android.rimet";
     * openApplication(packageName);
     * <p>
     * //返回自动打卡app
     * String packageNameNew = "com.example.dingding";
     * openApplication(packageNameNew);
     *
     * @param packageName 包名
     */
    public static void openApplication(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
        }
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);
        List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent, 0);
        ResolveInfo resolveInfo = apps.iterator().next();
        if (resolveInfo != null) {
            String className = resolveInfo.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

}
