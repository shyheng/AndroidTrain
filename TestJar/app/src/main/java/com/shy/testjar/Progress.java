package com.shy.testjar;

import android.app.ProgressDialog;
import android.content.Context;

public class Progress {
    private static ProgressDialog dialog;
    public static void show(Context context){
        dialog = new ProgressDialog(context);
        dialog.setTitle("请稍等");
        dialog.setMessage("正在为全力加速");
        dialog.show();
    }
    public static void diss(){
        dialog.dismiss();
    }
}
