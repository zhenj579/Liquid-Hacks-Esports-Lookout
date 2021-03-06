package com.example.liquidlookout;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity implements DataObserver{

    private long start;
    public static Bitmap nullLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);
        DataLoader.loadData(this);
        start = System.currentTimeMillis();
        nullLogo = BitmapFactory.decodeResource(getResources(),R.drawable.nulllogo);
    }

    @Override
    public void update() {
        Log.e("Elapsed Time", ((System.currentTimeMillis() - start) / 1000.0) + " seconds");
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
