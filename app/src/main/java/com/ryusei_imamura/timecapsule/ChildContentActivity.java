package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChildContentActivity extends AppCompatActivity {
//ホストの投稿用の画面と分ける用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_content);

        Button uButton = (Button) findViewById(R.id.button11);
        Button sButton = (Button) findViewById(R.id.button2);
        Button tButton = (Button) findViewById(R.id.button10);

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildContentActivity.this, PostActivity.class);
                startActivity(intent);
            }   //テキスト投稿
        });

        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ChildContentActivity.this, TitleActivity.class);
                startActivity(intent2);
            }
        });  //ホストじゃないのでlistにはとばさない

//        uButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(ChildContentActivity.this,UploadActivity.class);
//                startActivity(intent3);
//            }
//        }); 画像投稿オミット
    }

}
