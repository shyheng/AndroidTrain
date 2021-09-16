package com.hhh.dingdingtask.tools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.hhh.dingdingtask.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationManagerUtil {
    public static Notification BuilNotification(Context context, String title, String content) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //此Builder为android.support.v4.app.NotificationCompat.Builder中的，下同。
        //创建Notification，传入Context和channelId
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //这里的第一个参数要和下面的channelId一样
            NotificationChannel notificationChannel =
                    new NotificationChannel("1", "dingdingtask", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(notificationChannel);
            //创建一个通知
            Notification notification = new NotificationCompat.Builder(context, "1")
                    .setContentTitle(title)
                    .setContentText(content)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .build();
            //创建你一个通知管理器 使用NotificationManager将通知推送出去
            return notification;
        } else {
            //创建一个通知
            Notification notification = new NotificationCompat.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .build();
            //创建你一个通知管理器 使用NotificationManager将通知推送出去
            return notification;
        }
    }

    public static void simpleNotice(Context context, String title, String content) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //此Builder为android.support.v4.app.NotificationCompat.Builder中的，下同。
        //创建Notification，传入Context和channelId
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //这里的第一个参数要和下面的channelId一样
            NotificationChannel notificationChannel =
                    new NotificationChannel("1", "dingdingtask", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(notificationChannel);
            //创建一个通知
            Notification notification = new NotificationCompat.Builder(context, "1")
                    .setContentTitle(title)
                    .setContentText(content)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .build();
            //创建你一个通知管理器 使用NotificationManager将通知推送出去
            nm.notify(1, notification);
        } else {
            //创建一个通知
            Notification notification = new NotificationCompat.Builder(context)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .build();
            //创建你一个通知管理器 使用NotificationManager将通知推送出去
            nm.notify(1, notification);
        }
        Log.e("dingdingnotice", "发送通知栏信息成功");
    }

    /**
     * 发送通知带有时间
     *
     * @param context
     * @param title
     * @param content
     */
    public static void simpleNoticeWithTime(Context context, String title, String content) {
        //发送广播通知
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        simpleNotice(context, title, content + simpleDateFormat.format(date));
    }
}
