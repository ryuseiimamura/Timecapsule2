package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        //タイムカプセルの中身見るアクティビティのつもり　現状のテキストオンリー仕様だと掲示板のリストの応用でできそう　ローマ字しか入らないのなんとかせねば　あいことばとかうたんでも””で画面進められちゃうからパスワードとかのやつ応用する？
    }
}
