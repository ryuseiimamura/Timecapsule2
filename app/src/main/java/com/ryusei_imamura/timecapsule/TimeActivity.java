package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Button button=(Button)findViewById(R.id.button);
        TextView textView6=(TextView)findViewById(R.id.textView6);

        final EditText  vLimit= (EditText) findViewById(R.id.aLimit);

    }

}