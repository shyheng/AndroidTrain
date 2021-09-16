package com.hhh.dingdingtask.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * https://www.jianshu.com/p/a030c4c6f650
 */
public class MobileButlerUtil {

    private final Context mContext;

    public MobileButlerUtil(Context context) {
        this.mContext = context;
    }

    public boolean isHuawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.equalsIgnoreCase("huawei") || Build.BRAND.equalsIgnoreCase("honor");
        }
    }

    public boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("xiaomi");
    }

    public boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("oppo");
    }

    public boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("vivo");
    }

    public boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("meizu");
    }

    public boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("samsung");
    }

    public boolean isLeTV() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("letv");
    }

    public boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.equalsIgnoreCase("smartisan");
    }


    public void goHuaweiSetting() {
        try {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }

    public void goXiaomiSetting() {
        showActivity("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }

    public void goOPPOSetting() {
        try {
            showActivity("com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity("com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity("com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity("com.coloros.safecenter");
                }
            }
        }
    }

    public void goVIVOSetting() {
        showActivity("com.iqoo.secure");
    }

    public void goMeizuSetting() {
        showActivity("com.meizu.safe");
    }

    public void goSamsungSetting() {
        try {
            showActivity("com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity("com.samsung.android.sm");
        }
    }

    public void goLetvSetting() {
        showActivity("com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }

    public void goSmartisanSetting() {
        showActivity("com.smartisanos.security");
    }

    /**
     * 跳转到指定应用的首页
     */
    public void showActivity(@NonNull String packageName) {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);
        mContext.startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    public void showActivity(@NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


}