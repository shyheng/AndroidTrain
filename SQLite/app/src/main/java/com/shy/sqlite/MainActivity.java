package com.shy.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dao dao = new Dao(this);
//        dao.add("shy","1234");
        List<User> users = dao.QueryAll();
        for (User user :users){
            Log.e("shy",user.getId()+"");
            Log.e("shy",user.getUsername());
            Log.e("shy",user.getPassword());
            Log.e("s","___________________");
        }

        dao.update(0,"shy","132");
        List<User> users1 = dao.QueryAll();
        for (User user :users1){
            Log.e("shy",user.getId()+"");
            Log.e("shy",user.getUsername());
            Log.e("shy",user.getPassword());
            Log.e("s","___________________");
        }

//        dao.deleteAll();
//        List<User> users2 = dao.QueryAll();
//        for (User user :users2){
//            Log.e("shy",user.getId()+"");
//            Log.e("shy",user.getUsername());
//            Log.e("shy",user.getPassword());
//            Log.e("s","___________________");
//        }
    }
}