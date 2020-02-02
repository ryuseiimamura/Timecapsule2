package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("capsule");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        button = (Button) findViewById(R.id.captureButton);
        textView=(TextView)findViewById(R.id.textView);

        final EditText mGradutate=(EditText)findViewById(R.id.graduate);
        final EditText mSchool=(EditText)findViewById(R.id.school);
        final EditText mTeacher=(EditText)findViewById(R.id.teacher);
        final EditText mAikotoba=(EditText)findViewById(R.id.aikotoba);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String graduate = mGradutate.getText().toString();
                String school = mSchool.getText().toString();
                String teacher = mTeacher.getText().toString();
                String aikotoba = mAikotoba.getText().toString();

                Capsule post = new Capsule(graduate,school,teacher,aikotoba);

                refMsg.push().setValue(post);
                Intent intent=new Intent(SettingActivity.this,ContentActivity.class);
                startActivity(intent);
            }
        });
    }

}