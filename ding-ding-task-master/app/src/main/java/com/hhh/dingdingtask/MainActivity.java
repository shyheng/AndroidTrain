package com.hhh.dingdingtask;

import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hhh.dingdingtask.Adapter.GridDivider;
import com.hhh.dingdingtask.Adapter.MyAdapter;
import com.hhh.dingdingtask.dao.AlarmDao;
import com.hhh.dingdingtask.entity.Alarm;
import com.hhh.dingdingtask.entity.Item;
import com.hhh.dingdingtask.tools.AlarmManagerUtil;
import com.hhh.dingdingtask.tools.NotificationManagerUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String Tag = MainActivity.class.getSimpleName();
    private TextView textView1;
    private TextView textView2;
    AlertDialog.Builder alert;
    private BroadcastReceiver updateUIReciver;
    ListView ll;
    AlarmDao alarmDao;
    List<Alarm> data;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置不自动息屏 用了闹钟定时器用不着这个
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //锁屏唤醒
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化界面
        this.initWidget();
        //检查是否开启无障碍服务
        boolean accessibilitySettingsOn = isAccessibilitySettingsOn(MainActivity.this, AlarmAccessibilityService.class);
        if (!accessibilitySettingsOn) {
            Toast.makeText(MainActivity.this, "onCreate:未开启无障碍服务", Toast.LENGTH_SHORT).show();
            alert.show();
            return;
        } else {
            Toast.makeText(MainActivity.this, "onCreate:无障碍服务已开启", Toast.LENGTH_SHORT).show();
        }
        if (!isIgnoringBatteryOptimizations()) {//电池加入白名单
            requestIgnoreBatteryOptimizations();
            return;
        }
        Log.i("TAG", "应用启动：");
        this.textView2.setText("0、onCreate应用启动\n");

        //从service获取数据
        IntentFilter filter = new IntentFilter();
        filter.addAction("dingding.get.data");
        updateUIReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //UI update here
                Bundle bundle = intent.getExtras();
                String msg = bundle.getString("msg");
                textView2.append(msg);

                List<String> time = bundle.getStringArrayList("time");
                List<String> type = bundle.getStringArrayList("type");
//                String[] nzs = new String[nz.size()];
//                for (int i = 0; i < nz.size(); i++) {
//                    nzs[i] = nz.get(i);
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        getApplicationContext(),
//                        android.R.layout.simple_list_item_1,nzs);
//                ll.setAdapter(adapter);
//                ll.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });

                List<Item> items = new ArrayList<>();
                for (int i = 0; i < time.size(); i++) {
                    Item item = new Item();
                    item.setTime(time.get(i));
                    item.setWorktype(type.get(i));
                    items.add(item);
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.addItemDecoration(new GridDivider(getApplicationContext()));
                recyclerView.setAdapter(new MyAdapter(items));

            }
        };
        //动态注册广播接受器
        registerReceiver(updateUIReciver, filter);
        //数据访问类
        alarmDao = new AlarmDao(this);


    }

    private void init(String tip) {
        this.textView2.setText("");
        //开启前台服务 https://www.jianshu.com/p/a030c4c6f650
        Intent intent = new Intent(MainActivity.this, AlarmAccessibilityService.class);
        Intent intent2 = new Intent(MainActivity.this, AlarmService.class);//定时服务开启
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {//8.0以上的开启方式不同
            startForegroundService(intent);
            startForegroundService(intent2);
        } else {
            startService(intent);
            startService(intent2);
        }
        //查询闹钟
        //https://blog.csdn.net/qiuqiu_qiuqiu123/article/details/55190222
        //第一次启动（重新启动）才添加闹钟到数据库
        //AlarmDao alarmDao = new AlarmDao(this);
        //Toast.makeText(MainActivity.this, tip+"数据库闹钟个数"+alarmDao.total(), Toast.LENGTH_LONG).show();
        //this.textView2.append("1、查询数据库闹钟配置数"+alarmDao.total()+"\n");
        //if (!MyApplication.getInstance().isIsenable()) {
        //Toast.makeText(MainActivity.this, tip+"应用第一次启动，初始化闹钟", Toast.LENGTH_LONG).show();
        //Log.e(Tag, tip+"第一次启动初始化数据库");
        //添加闹钟  初始化数据
//            Alarm o1 = alarmDao.QueryOne(7, 40);
//            if (o1 == null) {
//                alarmDao.add(7, 40, MyApplication.GO_WORK);
//            }
//            Alarm o2 = alarmDao.QueryOne(11, 55);
//            if (o2 == null) {
//                alarmDao.add(11, 55, MyApplication.OFF_WORK);
//            }
//            Alarm o3 = alarmDao.QueryOne(13, 10);
//            if (o3 == null) {
//                alarmDao.add(13, 10, MyApplication.GO_WORK);
//            }
//            Alarm o4 = alarmDao.QueryOne(17, 50);
//            if (o4 == null) {
//                alarmDao.add(17, 50, MyApplication.OFF_WORK);
//            }
        //}
        //Toast.makeText(MainActivity.this, tip+"设置闹钟", Toast.LENGTH_SHORT).show();
        //this.textView2.append("2、设置闹钟"+"\n");
        //List<Alarm> objs = alarmDao.QueryAll();//从数据库查询所有闹钟设置
        //StringBuilder sb=new StringBuilder();
//        for (Alarm o : objs) {
//            //if (!AlarmManagerUtil.pendingIntents.containsKey(String.valueOf(o.getId()))) {//不包含表示没有此闹钟
//            Log.e(Tag, o.getHour() + "h:" + o.getMinute() + "m");
//            AlarmManagerUtil.addAlarm(MainActivity.this, o.getHour(), o.getMinute(), o.getId());
//            //}
//            sb.append(o.getId()+"."+o.getHour()+":"+o.getMinute()+"  类型："+o.getWorktype()+"\n");
//            //textView1.setText(o.getHour()+"h:"+o.getMinute()+"m"+o.getWorktype());
//        }
        //测试
        //AlarmManagerUtil.addAlarm(MainActivity.this, 15, 26, 1);
        //textView2.append(sb.toString());
        //textView2.append("3、"+tip+"打卡闹钟设置成功");

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean accessibilitySettingsOn = isAccessibilitySettingsOn(MainActivity.this, AlarmAccessibilityService.class);
        if (!accessibilitySettingsOn) {
            Toast.makeText(MainActivity.this, "onResume:未开启无障碍服务", Toast.LENGTH_SHORT).show();
            alert.show();
            return;
        } else {
            Toast.makeText(MainActivity.this, "无障碍服务已开启", Toast.LENGTH_SHORT).show();
        }
        if (!isIgnoringBatteryOptimizations()) {//电池加入白名单
            requestIgnoreBatteryOptimizations();
            return;
        }
        init("onResume:");
//       Boolean ret= AlarmManagerUtil.hasAlarmWithRequestCodeIsId(MainActivity.this, 15, 24,1);
//       if(ret){
//           textView2.append("14-56 已经存在");
//       }else{
//           textView2.append("14-56 步经存在");
//       }
    }

    //加载控件
    private void initWidget() {
        textView1 = findViewById(R.id.editTextText2); //上班时间打卡
        textView2 = findViewById(R.id.textView2);
        textView1.setText(MyApplication.getInstance().getMySetting().getCompanyName());
        Button btn = this.findViewById(R.id.button);//设置
        Button btn2 = this.findViewById(R.id.button2);//设置
        Button btn3 = this.findViewById(R.id.button3);//设置
        Button btn4 = this.findViewById(R.id.button4);//设置
//        ll = findViewById(R.id.lv);
        recyclerView = findViewById(R.id.rv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().getMySetting().setCompanyName(textView1.getText().toString()).save();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buildAlarmDialog(MyApplication.GO_WORK);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildAlarmDialog(MyApplication.OFF_WORK);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Alarm> objs = alarmDao.QueryAll();
                for (Alarm o : objs) {
                    AlarmManagerUtil.cancelAlarmWithRequestCodeIsId(getApplicationContext(), o.getHour(), o.getMinute(), o.getId());
                }
                alarmDao.deleteAll();
                init("");
            }
        });

        alert = alert();//创建未开启障碍服务 对话框

    }

    //检测无障碍服务时候开启
    public static boolean isAccessibilitySettingsOn(Context mContext, Class<? extends AccessibilityService> clazz) {
        int accessibilityEnabled = 0;
        final String service = mContext.getPackageName() + "/" + clazz.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //    无障碍系统弹窗
    public AlertDialog.Builder alert() {
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //    设置Title的内容
        builder.setTitle("提示");
        //    设置Content来显示一个信息
        builder.setMessage("检测到未开启系统无障碍服务，是否开启？");
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转系统无障碍服务
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            }
        });
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
//                    Toast.makeText(MainActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
            }
        });
        return builder;
    }

    /**
     * Called when a view has been clicked.
     */
    //@Override
    //public void onClick(View v) {
    // AlarmDao alarmDao=  new AlarmDao(this);
    // alarmDao.add(6,2,"go");
    // }
    //构建时间对话框
    public void buildAlarmDialog(String clockType) {
        //获取当前系统的时间
        Calendar calendar = Calendar.getInstance();//实例化日历
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//得到小时
        int munute = calendar.get(Calendar.MINUTE);//得到分

        //弹出时间对话框
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //设置闹铃(单次闹钟)  //唤醒屏幕  将来时间的毫秒数     跳转
                //AlarmManagerUtil.addAlarm(MainActivity.this, hourOfDay, minute, 1);
                alarmDao.add(hourOfDay, minute, clockType);
                init("");
            }
        }, hour, munute, true);
        //显示对话框
        timePickerDialog.show();

    }

    /**
     * 开启白名单
     */
    public void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否已经开启了白名单：
     *
     * @return
     */
    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        return isIgnoring;
    }

}