package com.ryusei_imamura.timecapsule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.text.DateFormat.getDateTimeInstance;

public class TimeActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Calendar date = Calendar.getInstance();
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        Button button = (Button) findViewById(R.id.button);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        final EditText aLimit = (EditText) findViewById(R.id.aLimit);
        pref = getSharedPreferences("pref_capsule", MODE_PRIVATE);

        String key = pref.getString("current_capsule_key","default");
//        Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();

        aLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TimeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // aLimit.setText(String.format("%d/%02d/%02d", year, month + 1, dayOfMonth)); もともとはこれでsetTextだけしてた
                                date.set(year, month, dayOfMonth, 0, 0);
                                SimpleDateFormat dateFormatEntry = new SimpleDateFormat("yyyy/M/dd");
                                String formattedDate = dateFormatEntry.format(date.getTime());
                                aLimit.setText(formattedDate);
                            }
                        },
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DATE)
                );

                datePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("openDate", date.getTimeInMillis());

                String key = pref.getString("current_capsule_key","default");
//                Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();

                ref.child("capsule").child(key).child("openDate").setValue(date.getTimeInMillis());
//                //shared preferenceでカプセルのキーを保存
                Intent intent = new Intent(TimeActivity.this,TitleActivity.class);
                startActivity(intent);

            }
        });
    }

    //LongからDateに変換したいらしい　←場所がダメでエラーだったっぽい
    public static String getTimeDate(long timestamp) {
        try {
            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(timestamp));
            return dateFormat.format(netDate);
        } catch (Exception e) {
            return "date";
        }
    }


}