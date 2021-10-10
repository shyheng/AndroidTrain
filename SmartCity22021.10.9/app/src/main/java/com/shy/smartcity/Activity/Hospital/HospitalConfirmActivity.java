package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shy.smartcity.Activity.IndexActivity;
import com.shy.smartcity.R;

public class HospitalConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_confirm);
        TextView ks = findViewById(R.id.hos_con_ks);
        ks.setText(HospitalDepartmentActivity.department);
        TextView time = findViewById(R.id.hos_con_sj);
        time.setText(HospitalDepartmentInfoActivity.nian+" "+HospitalDepartmentInfoActivity.time);

    }

    public void you(View view) {
        Intent intent = new Intent(HospitalConfirmActivity.this, IndexActivity.class);
        startActivity(intent);
        Toast.makeText(HospitalConfirmActivity.this,"预约成功！",Toast.LENGTH_LONG).show();
    }
}