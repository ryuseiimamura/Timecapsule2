package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        TextView textView = (TextView) findViewById(R.id.textView);
        Button button7 = (Button) findViewById(R.id.button7);

        final EditText vCapsuleName = (EditText) findViewById(R.id.aCapsuleName);
        final EditText vGraduate = (EditText) findViewById(R.id.aGraduate);
        final EditText vSchool = (EditText) findViewById(R.id.aSchool);
        final EditText vTeacher = (EditText) findViewById(R.id.aTeacher);
        final EditText vAikotoba = (EditText) findViewById(R.id.aAikotoba);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(AccessActivity.this, ContentActivity.class);
                startActivity(intent7);
            }
        });
    }
}