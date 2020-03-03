package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.security.Timestamp;
import java.text.DateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Map map = new HashMap();
    map.put("timestamp",ServerValue.TIMESTAMP);   //timestampを追加するための準備をしたい？
    ref.child("capsule").updateChildren(map);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Button button = (Button) findViewById(R.id.button);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        final EditText aLimit = (EditText) findViewById(R.id.aLimit);

        aLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar date = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TimeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                aLimit.setText(String.format("%d/%02d/%02d", year, month + 1, dayOfMonth));
                            }
                        },
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DATE)
                );

                datePickerDialog.show();
          }
        });

//CapsuleからLongにして投稿→表示する段階でDateにしていく方針で


//timestampをfirebaseに投げたい？のではないか
        public class YourModelClass {
            private Map<String, String> timestamp;

            public YourModelClass() {
            }

            public void setTimestamp(Map<String, String> timestamp) {
                this.timestamp = timestamp;
            }

            public Map<String, String> getTimestamp() {
                return timestamp;
            }
        }
        //LongからDateに変換したいらしい
        public static String getTimeDate ( long timestamp){
            try {
                DateFormat dateFormat = DateFormat.getDateTimeInstance();
                Date netDate = (new Date(timestamp));
                return dateFormat.format(netDate);
            } catch (Exception e) {
                return "date";
            }
        }

        String dateText = getTimeDate(timestamp);

    }

}