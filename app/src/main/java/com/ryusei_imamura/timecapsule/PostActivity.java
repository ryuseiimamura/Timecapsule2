package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("message");
    SharedPreferences pref;


    EditText mUsernametext;
    EditText mPostText;
    Button mPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mUsernametext = (EditText) findViewById(R.id.username);
        mPostText = (EditText) findViewById(R.id.message);
        mPostButton = (Button) findViewById(R.id.post);

    }

    public void post(View v) {
        String message = mPostText.getText().toString();
        String userName = mUsernametext.getText().toString();
        Post post = new Post(userName, message);

        refMsg.push().setValue(post);

        String key = pref.getString("current_capsule_key","default");
        pref = getSharedPreferences("pref_capsule", MODE_PRIVATE);
        ref.child("capsule").child(key).child("message").setValue(message);
        ref.child("capsule").child(key).child("message").setValue(userName);

        finish();
    }

}


