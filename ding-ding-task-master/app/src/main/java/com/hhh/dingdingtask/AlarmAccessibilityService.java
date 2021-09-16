package com.hhh.dingdingtask;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import com.hhh.dingdingtask.tools.NotificationManagerUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 无障碍
 * https://www.jianshu.com/p/fc499ddea138
 * https://www.bilibili.com/read/cv8747010/
 * https://www.sohu.com/a/335995743_100004247
 * https://www.jianshu.com/p/4cd8c109cdfb  抢红包案例
 * <p>
 * 查找view id：https://blog.csdn.net/qq_42023080/article/details/105842271
 */
public class AlarmAccessibilityService extends AccessibilityService {
    private static final String TAG = AlarmAccessibilityService.class.getSimpleName();
    private static final int NOTIFICATION_FLAG = 0X11;
    //包名
    String packageName;
    String className;
    String p_c;
    //public static int step = 0;//保存第几步
    boolean[] steps;//保存第几步

    //https://www.cnblogs.com/renhui/p/8575299.html
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 启动前台服务
        // 参数一：唯一的通知标识；参数二：通知消息。
        startForeground(NOTIFICATION_FLAG, NotificationManagerUtil.BuilNotification(this.getApplicationContext(), "钉钉小助手", "无障碍服务正在运行"));
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

    //发生用户界面事件回调此事件
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e(TAG, "无障碍服务事件回调！包名：" + event.getPackageName());
        if (MyApplication.getInstance().getSteps() != null) {
            steps = MyApplication.getInstance().getSteps();
        } else {
            return;
        }
        //new Random().nextInt(28)
        //钉钉打卡  https://blog.csdn.net/weixin_33913332/article/details/88009560
        //第1步判断启动是否是钉钉应用
        if (event.getPackageName() != null && "com.alibaba.android.rimet".equals(event.getPackageName())) {
            packageName = event.getPackageName().toString();
            //获取类名
            className = event.getClassName().toString();
            p_c = packageName + "." + className;
            //Log.e(TAG, "包名：" +packageName + "，" + "类名：" + className);
            Log.e(TAG, "已进入钉钉app");

            ArrayList<AccessibilityNodeInfo> nodes = new ArrayList<AccessibilityNodeInfo>();
            AccessibilityNodeInfo node = getRootInActiveWindow();
            if (node != null) {
                nodes.add(node);
                getNodesFromWindows(nodes, node);

                for (AccessibilityNodeInfo cNode : nodes) {
                    executeOperation(cNode, event);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean executeOperation(AccessibilityNodeInfo info, AccessibilityEvent event) {
        //Log.e(TAG, "执行操作");
        if (info == null) return false;
        if (info.getChildCount() == 0) {
            if (info.getText() != null) {
                //Log.e(TAG, "无障碍4 ");
                //第一步 打开天阳宏业应用中心
                if (!steps[0]) {
                    if (MyApplication.getInstance().getMySetting().getCompanyName().equals(info.getText().toString())) {
                        if ("android.widget.TextView".equals(info.getClassName())) {
                            String viewIdResourceName = info.getViewIdResourceName();
                            Log.i(TAG, "第一步获取资源点：" + viewIdResourceName);
                            AccessibilityNodeInfo parent = info;
                            while (parent != null) {
                                if (parent.isClickable()) {
                                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    steps[0] = true;
                                    Log.e(TAG, "进入工作页");
                                    break;
                                }
                                parent = parent.getParent();
                            }
                        }
                    }
                }
                //第二步点击考勤打卡 打开考勤打卡
                if (!steps[1]) {
                    if ("考勤打卡".equals(info.getText().toString())) {
                        if ("android.view.View".equals(info.getClassName())) {
                            String viewIdResourceName = info.getViewIdResourceName();
                            Log.i(TAG, "第二步获取资源点：" + viewIdResourceName);
                            AccessibilityNodeInfo parent = info;
                            while (parent != null) {
                                if (parent.isClickable()) {
                                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                    steps[1] = true;
                                    Log.e(TAG, "进入考勤打卡页面");
                                    break;
                                }
                                parent = parent.getParent();
                            }
                        }
                    }
                }

                if (!steps[2]) {
                    if (MyApplication.getInstance().getClockType().equals(MyApplication.GO_WORK)) {
                        if ("上班打卡".equals(info.getText().toString())) {
                            if ("android.view.View".equals(info.getClassName())) {
                                String viewIdResourceName = info.getViewIdResourceName();
                                Log.i(TAG, "第三步步获取资源点：" + viewIdResourceName);
                                AccessibilityNodeInfo parent = info;
                                while (parent != null) {
                                    if (parent.isClickable()) {
                                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                        steps[2] = true;
                                        steps = new boolean[3];
                                        Log.e(TAG, "上班打卡成功");
                                        afterTask();
                                        break;
                                    }
                                    parent = parent.getParent();
                                }
                            }
                        }

                    } else {
                        if ("下班打卡".equals(info.getText().toString())) {
                            if ("android.view.View".equals(info.getClassName())) {
                                String viewIdResourceName = info.getViewIdResourceName();
                                Log.i(TAG, "第三步步获取资源点：" + viewIdResourceName);
                                AccessibilityNodeInfo parent = info;
                                while (parent != null) {
                                    if (parent.isClickable()) {
                                        parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                        steps[2] = true;
                                        steps = new boolean[3];
                                        Log.e(TAG, "下班打卡成功");
                                        afterTask();
                                        break;
                                    }
                                    parent = parent.getParent();
                                }
                            }
                        }
                    }

                }


            }
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {

                if (info.getChild(i) != null) {
                    Log.i(TAG, "info.getChild(i) " + info.getChild(i).toString());
                    Log.i(TAG, "info.getChildCount() " + info.getChildCount());
                    executeOperation(info.getChild(i), event);
                }
            }
        }
        return false;
    }

    //获取子节点窗口
    private void getNodesFromWindows(ArrayList<AccessibilityNodeInfo> nodes, AccessibilityNodeInfo node) {

        for (int i = 0; i < node.getChildCount() - 1; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            nodes.add(child);
//            if (child.getChildCount() > 0) {
//                getNodesFromWindows(nodes, child);
//            }
//            Log.i(TAG, child + "");
        }

    }

    /**
     * 递归查找当前聊天窗口中的文字信息节点，如红包信息
     * <p>
     * 聊天窗口中的红包都存在"领取红包"一词,因此可根据该词查找红包
     *
     * @param node
     * @param text 要查找的文章中
     * @deprecated
     */
    public AccessibilityNodeInfo recycle(AccessibilityNodeInfo node, String text) {
        if (node.getChildCount() == 0) {
            if (node.getText() != null) {
                if (text == node.getText().toString() && node.isVisibleToUser()) {
                    return node;
                }
            }
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChild(i) != null) {
                    AccessibilityNodeInfo ret = recycle(node.getChild(i), text);
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "无障碍服务断开");
    }

    @Override
    protected void onServiceConnected() {
        Log.d(TAG, "无障碍服务开启............");
    }

    public void afterTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clickBackKey();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.e(TAG, "任务完成后退 ");
                        Context context = getApplicationContext();
                        //发送广播通知
                        NotificationManagerUtil.simpleNoticeWithTime(context, "钉钉小助手", "打卡成功");
                        //Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                        //i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //startActivity(i1);
                        clickHomeKey();
                    }
                }, 3000);
            }
        }, 3000);//延时1s执行
    }

    public Boolean clickBackKey() {
        return performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    public Boolean clickHomeKey() {
        return performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
    }
}

