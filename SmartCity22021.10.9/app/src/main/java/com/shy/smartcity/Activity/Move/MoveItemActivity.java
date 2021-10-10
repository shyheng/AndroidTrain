package com.shy.smartcity.Activity.Move;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shy.smartcity.R;
import com.shy.smartcity.web.RequestImage;

import java.util.ArrayList;

public class MoveItemActivity extends AppCompatActivity {

    ListView listView;

    ImageView i1;
    ImageView i2;
    ImageView i3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_item);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView textView = findViewById(R.id.move_move);
        textView.setText(name);
        Button button = findViewById(R.id.f_e);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MoveItemActivity.this,MoveActivity.class);
                startActivity(intent1);
            }
        });

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add("热心网友\n这个好，这也太棒了");
        }

        listView = findViewById(R.id.lv_new);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);


        i1 = findViewById(R.id.im1);
        i2 = findViewById(R.id.im2);
        i3 = findViewById(R.id.im3);

        RequestImage.request(i1,
                "https://search-operate.cdn.bcebos.com/2278a38e654d7160ef5a7b084217a660.jpg",this);

        RequestImage.request(i2,
                "https://t11.baidu.com/it/u=2925288351,2304405600&fm=55&app=54&fmt=auto?w=1140&h=640",this);
        RequestImage.request(i3,
                "https://t11.baidu.com/it/u=2487170001,3075912551&fm=55&app=54&fmt=auto?w=1140&h=640",this);
    }
}