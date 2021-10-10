package com.shy.smartcity.Activity.Hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shy.smartcity.R;

public class HospitalInfoActivity extends AppCompatActivity {

    public static String name;
    public static String sex;
    public static String sfz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);



        Button button = findViewById(R.id.hos_info_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalInfoActivity.this,HospitalRegisterActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.hos_info_qr);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText n = findViewById(R.id.name);
                name = n.getText().toString();
                EditText se = findViewById(R.id.sex);
                sex = se.getText().toString();
                EditText fs = findViewById(R.id.sfz);
                sfz = fs.getText().toString();
                if (name.equals("")||sex.equals("")||sfz.equals("")){
                    Toast.makeText(HospitalInfoActivity.this,"未填写完",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(HospitalInfoActivity.this,HospitalDepartmentActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}