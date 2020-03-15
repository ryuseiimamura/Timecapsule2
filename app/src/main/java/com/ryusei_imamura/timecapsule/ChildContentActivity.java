package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChildContentActivity extends AppCompatActivity {
//ホストの投稿用の画面と分ける用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_content);
    }
}
