package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences pref;
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
        pref = getSharedPreferences("pref_capsule", MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String graduate = mGradutate.getText().toString();
                String school = mSchool.getText().toString();
                String teacher = mTeacher.getText().toString();
                String aikotoba = mAikotoba.getText().toString();

                Capsule capsule = new Capsule(graduate,school,teacher,aikotoba,0L);

              refMsg.push().setValue(capsule);
//                key取得するやつ    keyがとれてるかどこでみる？
//                String key = refMsg.child("capsule").push().getKey();

                String key = refMsg.push().getKey();

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("current_capsule_key",key);
                editor.commit();

// create a child with index value
//                refMsg.child(key).setValue(new );

                Intent intent=new Intent(SettingActivity.this,ContentActivity.class);
                startActivity(intent);
            }
        });
    }

}