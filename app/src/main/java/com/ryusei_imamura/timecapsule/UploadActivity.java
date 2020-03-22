package com.ryusei_imamura.timecapsule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadActivity extends AppCompatActivity {
//全面的に保留してます
    private static final int READ_REQUEST_CODE = 42;

    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imageRef;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

// ▼ テスト用のルール
//              service firebase.storage {
//                      match /b/freebase-654b7.appspot.com/o {
//                              match /{allPaths=**} {
//                                      allow read;
//                                      allow write: if true;
//                              }
//                      }
//              }

// アップロード中のダイアログ
    progress = new ProgressDialog(UploadActivity .this);

    storage =FirebaseStorage.getInstance();
    storageRef =storage.getReferenceFromUrl("gs://freebase-654b7.appspot.com/");

    // 現在の画面をアップロード( View )
    Button captureButton = (Button) UploadActivity.this.findViewById(R.id.captureButton);
captureButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

// View から Bitmap 取得
        View view = v.getRootView();    // 画面全体
        view.setDrawingCacheEnabled(true);
        Bitmap cache = view.getDrawingCache();
        Bitmap rootViewCapture = Bitmap.createBitmap(cache);
        view.setDrawingCacheEnabled(false);

// 画像アップロード用パス決定
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String uploadImagePath = String.format("image/%s.png", sf.format(cal.getTime()));
        imageRef = storageRef.child(uploadImagePath);

// byte[] に変換
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        rootViewCapture.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

// アップロード中の表示
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("画像をアップロードしています");
        progress.show();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progress.dismiss();

                Log.i("lightbox", "アップロードに成功しました");
                long size = taskSnapshot.getMetadata().getSizeBytes();
                Log.i("lightbox", String.format("サイズ : %d", size));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progress.dismiss();

                Log.i("lightbox", "アップロードに失敗しました");
            }
        });
    }
    });

    // ギャラリーの画像をアップロード
    Button galleryButton = (Button) UploadActivity.this.findViewById(R.id.galleryButton);
galleryButton.setOnClickListener(new View.OnClickListener()
    { @Override
        public void onClick (View v){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }
    });

}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

// ギャラリーからの戻り
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (data != null) {
// Uri が戻されます
                uri = data.getData();
                Log.i("lightbox", "Uri: " + uri.toString());

// 画像アップロード用パス決定
                ContentResolver contentResolver = UploadActivity.this.getContentResolver();
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                cursor.moveToFirst();
// とりあえず固定( 2 => _display_name )
                String name = cursor.getString(2);
                Log.i("lightbox", "FileName: " + name);

//この カーソルの詳細
                String[] columnName = cursor.getColumnNames();
                for (int i = 0; i < columnName.length; i++) {
                    Log.i("lightbox", String.format("%d : %s : %s", i, columnName[i], cursor.getString(i)));
                }

                String uploadImagePath = String.format("image/%s", name);
                imageRef = storageRef.child(uploadImagePath);

// アップロード中の表示
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setMessage("画像をアップロードしています");
                progress.show();

// 画像アップロード
                UploadTask uploadTask = imageRef.putFile(uri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progress.dismiss();

                        Log.i("lightbox", "アップロードに成功しました");
                        long size = taskSnapshot.getMetadata().getSizeBytes();
                        Log.i("lightbox", String.format("サイズ : %d", size));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progress.dismiss();

                        Log.i("lightbox", "アップロードに失敗しました");
                    }
                });

            }

        }
    }

}