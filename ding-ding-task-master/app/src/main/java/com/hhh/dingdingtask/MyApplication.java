package com.hhh.dingdingtask;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hhh.dingdingtask.tools.AlarmManagerUtil;
import com.hhh.dingdingtask.tools.JsonUtil;


public class MyApplication extends Application {
    private static final String FILENAME_AND_KEY = MySetting.class.getSimpleName();

    //应用是否启动
    //private  boolean isenable=false;
    private static MyApplication myApplication = null;
    public final static String GO_WORK = "1";//上班打卡
    public final static String OFF_WORK = "0";//下班打卡

    private boolean[] steps;

    private String clockType;//签到类型 上班 下班


    public static MyApplication getInstance() {
        return myApplication;
    }

    public String getClockType() {
        return clockType;
    }

    public void setClockType(String clockType) {
        this.clockType = clockType;
    }

    public void setGoWork() {
        this.clockType = GO_WORK;
    }

    public void setOffWork() {
        this.clockType = OFF_WORK;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }


    public boolean[] getSteps() {
        return steps;
    }

    public void setSteps(boolean[] steps) {
        this.steps = steps;
    }

    /**
     * 获取配置信息
     *
     * @return 没有时，返回空对象，不是null
     */
    public MySetting getMySetting() {
        MySetting s = getData();
        if (s == null) {
            s = new MySetting();
            saveData(s);
        }
        return s;
    }

    //保存当前数据
    private static boolean saveData(MySetting mSetting) {
        SharedPreferences.Editor editor = MyApplication.getInstance().getSharedPreferences(FILENAME_AND_KEY, Activity.MODE_PRIVATE).edit();
        editor.putString(FILENAME_AND_KEY, JsonUtil.toJson(mSetting));
        boolean isEditorSucc = editor.commit();
        return isEditorSucc;
    }

    //    获取本地setting数据
    //    @return 异常时为null
    private static MySetting getData() {
        SharedPreferences mSharedPreferences = MyApplication.getInstance().getSharedPreferences(FILENAME_AND_KEY, Activity.MODE_PRIVATE);
        String jjbsetingStr = mSharedPreferences.getString(FILENAME_AND_KEY, null);
        return JsonUtil.fromJson(jjbsetingStr, MySetting.class);
    }
    /**
     * 是否启动过
     *
     * @return
     */
    //public boolean isIsenable() {
    //    return !AlarmManagerUtil.pendingIntents.isEmpty();
    //}

    /**
     * 应用是否启动
     *
     * @param isenable
     */
//    public void setIsenable(boolean isenable) {
//        this.isenable = isenable;
//    }
    public static class MySetting {
        private String companyName = "美和易思";

        /**
         * 钉主页中间的公司名称
         *
         * @return
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * 钉钉主页中间的公司名称
         *
         * @param companyName
         */
        public MySetting setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        //保存
        public MySetting save() {
            saveData(this);
            return this;
        }
    }
}
