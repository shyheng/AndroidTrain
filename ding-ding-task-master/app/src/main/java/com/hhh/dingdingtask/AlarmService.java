package com.hhh.dingdingtask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hhh.dingdingtask.dao.AlarmDao;
import com.hhh.dingdingtask.entity.Alarm;
import com.hhh.dingdingtask.tools.AlarmManagerUtil;
import com.hhh.dingdingtask.tools.NotificationManagerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/a030c4c6f650
 * https://blog.csdn.net/weixin_37577039/article/details/80041624
 * https://blog.csdn.net/u013095264/article/details/91880916
 * 定时器/保活服务
 */
public class AlarmService extends Service {
    private static final String TAG = AlarmService.class.getSimpleName();
    public static final int NOTIFICATION_FLAG = 0X12;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //https://www.cnblogs.com/renhui/p/8575299.html
    public int onStartCommand(Intent intent, int flags, int startId) {
        //用于传递数据到activity
        Intent intent2 = new Intent();
        intent2.setAction("dingding.get.data");//action与接收器相同

        StringBuilder sb = new StringBuilder();
        //查询闹钟
        //https://blog.csdn.net/qiuqiu_qiuqiu123/article/details/55190222
        //查询数据库
        AlarmDao alarmDao = new AlarmDao(getApplicationContext());
        sb.append("1、查询数据库闹钟配置数" + alarmDao.total() + "\n");
        //添加闹钟  初始化数据
//        Alarm o1 = alarmDao.QueryOne(7, 45);
//        if (o1 == null) {
//            alarmDao.add(7, 45, MyApplication.GO_WORK);
//        }
//        Alarm o2 = alarmDao.QueryOne(11, 55);
//        if (o2 == null) {
//            alarmDao.add(11, 55, MyApplication.OFF_WORK);
//        }
//        Alarm o3 = alarmDao.QueryOne(12, 45);
//        if (o3 == null) {
//            alarmDao.add(12, 45, MyApplication.GO_WORK);
//        }
//        Alarm o4 = alarmDao.QueryOne(17, 30);
//        if (o4 == null) {
//            alarmDao.add(17, 30, MyApplication.OFF_WORK);
//        }
        //设置闹钟
        sb.append("2、设置闹钟" + "\n");
        //从数据库查询所有闹钟设置
        List<Alarm> objs = alarmDao.QueryAll();
//        List<String> nz = new ArrayList<>();
        for (Alarm o : objs) {
            //if (!AlarmManagerUtil.pendingIntents.containsKey(String.valueOf(o.getId()))) {//不包含表示没有此闹钟
            Log.e(TAG, o.getHour() + "h:" + o.getMinute() + "m");
            AlarmManagerUtil.addAlarm(getApplicationContext(), o.getHour(), o.getMinute(), o.getId());
            //}
            sb.append(o.getId() + "." + o.getHour() + ":" + o.getMinute() + "  类型：" + o.getWorktype() + "\n");
//            textView1.setText(o.getHour()+"h:"+o.getMinute()+"m"+o.getWorktype());
        }


        List<String> time = new ArrayList<>();
        List<String> type = new ArrayList<>();
        for (int i = 0; i < objs.size(); i++) {
            time.add(objs.get(i).getHour()+":"+objs.get(i).getMinute());
            type.add(objs.get(i).getWorktype());
        }




        //测试
        //AlarmManagerUtil.addAlarm(getApplicationContext(), 18, 38, 4);
        sb.append("3、" + "打卡闹钟设置成功");
        //发送广播 把数据传递到activity
        intent2.putExtra("msg", sb.toString());
        intent2.putStringArrayListExtra("time", (ArrayList<String>) time);
        intent2.putStringArrayListExtra("type", (ArrayList<String>) type);
        sendBroadcast(intent2);
        // 启动前台服务
        // 参数一：唯一的通知标识；参数二：通知消息。
        startForeground(NOTIFICATION_FLAG, NotificationManagerUtil.BuilNotification(this.getApplicationContext(), "钉钉小助手", "闹钟服务正在运行"));
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 停止前台服务--参数：表示是否移除之前的通知
        stopForeground(true);
        Log.d(TAG, "onDestroy");
    }
}
