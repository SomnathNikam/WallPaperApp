package com.example.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    WallpaperManager wallpaperManager;
    Bitmap bitmap1,bitmap2;
    int width,height;
    BitmapDrawable bitmapDrawable;
    DisplayMetrics displayMetrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imagev);
        button = findViewById(R.id.btnset);

        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        bitmapDrawable =(BitmapDrawable) imageView.getDrawable();
        bitmap1 = bitmapDrawable.getBitmap();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetScreenWidthHeight();


                SetBitmapSize();

                wallpaperManager = WallpaperManager.getInstance(MainActivity.this);

                try {
                    wallpaperManager.setBitmap(bitmap2);
                    wallpaperManager.suggestDesiredDimensions(width,height);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            private void SetBitmapSize() {
                bitmap2 = Bitmap.createScaledBitmap(bitmap1,width,height,false);
            }

            private void GetScreenWidthHeight() {
                displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
            }
        });

    }
}