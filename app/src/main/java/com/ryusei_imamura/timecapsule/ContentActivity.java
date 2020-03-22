package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContentActivity extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Button uButton = (Button) findViewById(R.id.button3);
        Button sButton = (Button) findViewById(R.id.button4);
        Button tButton = (Button) findViewById(R.id.button5);
        pref = getSharedPreferences("pref_capsule", MODE_PRIVATE);
        Toast.makeText(getApplicationContext(), pref.getString("current_capsule_key","test"), Toast.LENGTH_LONG).show();

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ContentActivity.this, ListActivity.class);
                startActivity(intent2);
            }
        });

        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ContentActivity.this,UploadActivity.class);
                startActivity(intent3);
            }
        });
    }

}