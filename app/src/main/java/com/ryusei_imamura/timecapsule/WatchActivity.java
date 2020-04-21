package com.ryusei_imamura.timecapsule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WatchActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("capsule");

    ListView mListView;

    ArrayList<Post> items;

    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);


        TextView mtextView = (TextView) findViewById(R.id.textView8);
        mListView = (ListView) findViewById(R.id.listView);
        Button button6 = (Button) findViewById(R.id.button12);

        items = new ArrayList<>();
        postAdapter = new PostAdapter(this, 0, items);

        mListView.setAdapter(postAdapter);

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

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WatchActivity.this, TitleActivity.class);
                startActivity(intent);
            }
        });
        //タイムカプセルの中身見るアクティビティのつもり　現状のテキストオンリー仕様だと掲示板のリストの応用でできそう　　あいことばとかうたんでも””で画面進められちゃうからパスワードとかのやつ応用する？

    }
}
