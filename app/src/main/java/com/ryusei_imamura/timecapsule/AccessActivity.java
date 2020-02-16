package com.ryusei_imamura.timecapsule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccessActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("message");
    ArrayList<post> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        TextView textView = (TextView) findViewById(R.id.textView);
        Button button7 = (Button) findViewById(R.id.button7);

//        final EditText vCapsuleName = (EditText) findViewById(R.id.aCapsuleName);
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
        refMsg.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post value = dataSnapshot.getValue(Post.class);

                items.add(value);

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //あいことばなどのテキストの情報を取得するところ
        String graduate = vGraduate.getText().toString();
        String school = vSchool.getText().toString();
        String teacher = vTeacher.getText().toString();
        String aikotoba = vAikotoba.getText().toString();

        Capsule post = new Capsule(graduate,school,teacher,aikotoba);

        refMsg.push().setValue(post);
    }
}