package com.wj.dome.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.wj.dome.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        show(new BlankFragment1());
        onClick();
    }

    @SuppressLint("ResourceAsColor")
    public void onClick() {
        tv1.setOnClickListener((view) -> {
            show(new BlankFragment1());
            tv1.setTextColor(R.color.teal_200);
            tv2.setTextColor(R.color.black);
            tv3.setTextColor(R.color.black);
        });

        tv2.setOnClickListener((view) -> {
            show(new BlankFragment2());
            tv2.setTextColor(R.color.teal_200);
            tv1.setTextColor(R.color.black);
            tv3.setTextColor(R.color.black);
        });

        tv3.setOnClickListener((view) -> {
            show(new BlankFragment3());
            tv3.setTextColor(R.color.teal_200);
            tv1.setTextColor(R.color.black);
            tv2.setTextColor(R.color.black);
        });
    }

    public void show(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl, fragment);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode){
            if (System.currentTimeMillis() - time > 2000){
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}