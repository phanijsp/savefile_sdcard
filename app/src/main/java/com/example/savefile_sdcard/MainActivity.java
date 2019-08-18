package com.example.savefile_sdcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }
        File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/aditya");
        Boolean bool=file2.mkdir();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/aditya","one.txt");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("Hello Aditya ! ".getBytes());
            fos.close();
            Toast.makeText(this,"Saved in"+Environment.getExternalStorageDirectory().getAbsolutePath()+"/aditya",Toast.LENGTH_SHORT).show();
            textView.setText("File Saved in"+Environment.getExternalStorageDirectory().getAbsolutePath()+"/aditya");
        }catch (Exception e){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted !",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Permission Denied !",Toast.LENGTH_SHORT).show();
                }
        }
    }
}
