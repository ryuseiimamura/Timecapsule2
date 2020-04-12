package com.ryusei_imamura.timecapsule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AccessActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("capsule");
    List<Capsule> capsules = new ArrayList<>();
    SharedPreferences pref;

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
        pref = getSharedPreferences("pref_capsule", MODE_PRIVATE);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //あいことばなどのテキストの情報を取得するところ
                String graduate = vGraduate.getText().toString();
                String school = vSchool.getText().toString();
                String teacher = vTeacher.getText().toString();
                String aikotoba = vAikotoba.getText().toString();

                Capsule myCapsule = new Capsule(graduate, school, teacher, aikotoba, 0L); //Long型のときは数字のあとに"L"いれる

                for (Capsule capsule : capsules) {
                    if (capsule.school.equals(myCapsule.school) && capsule.graduate.equals(myCapsule.graduate) && capsule.teacher.equals(myCapsule.teacher) && capsule.aikotoba.equals(myCapsule.aikotoba)) {
                        long now = System.currentTimeMillis(); //現在時刻の取得
                        //Long型のまま現在時刻とカプセルのオープン可時刻との比較 　チェック用→if(true){} と if(false){}
                        if (capsule.openDate >= now) {
                            Toast.makeText(getApplicationContext(), "まだ開けられる日付になってません！", Toast.LENGTH_LONG).show();
                        } else if(capsule.openDate==0L) {
                            //投稿に使う用のほう(うまくいった)
                            Intent intent7 = new Intent(AccessActivity.this, ChildContentActivity.class);
                            startActivity(intent7);

                                //キーをfirebaseからgetしてくるアクセスした人の端末にもアクセスしているカプセルのキーを保存していく
//                            String key = refMsg.child("capsule").push().getKey();
//                            String key = refMsg.push().getKey();
//
//                            SharedPreferences.Editor editor = pref.edit();
//                            editor.putString("current_capsule_key",key);
//                            editor.commit();
                        }else{
                            //作ったカプセルの中を見る画面に遷移
                            Intent intent8 = new Intent(AccessActivity.this, WatchActivity.class);
                            startActivity(intent8);
                        }
                        return; //条件達成してたらここで処理を終了して次に行く　ここでいうとopenDateでなんらかにひっかかったら下のtoastはスキップされて次へ行く
                    }
                }
                    Toast.makeText(getApplicationContext(), "カプセルがみつかりません！", Toast.LENGTH_LONG).show();
            }
        });

        //listからコピーしてきたやつ
        refMsg.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Capsule value = dataSnapshot.getValue(Capsule.class);
                capsules.add(value); //←こいつ全部データベースのやつもってる
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

    }
}