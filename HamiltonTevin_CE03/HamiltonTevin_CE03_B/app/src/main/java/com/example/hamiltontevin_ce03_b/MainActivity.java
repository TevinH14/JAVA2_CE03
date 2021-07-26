//Tevin Hamilton
//CE_03_B
//2004
//MainActivity

package com.example.hamiltontevin_ce03_b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hamiltontevin_ce03_b.fragment.ImageDisplayFragment;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String IS_APP_B_EXTRA = "IS_APP_B_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_imageContainer, ImageDisplayFragment.newInstance(null)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent imageIntent = new Intent(Intent.ACTION_PICK);
        imageIntent.setType("image/jpeg");
        imageIntent.putExtra(IS_APP_B_EXTRA,true);
        startActivityForResult(imageIntent, 0x0010);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Bitmap bitmapImage = null;
            Uri imageUri = data.getData();
            try {
                if(imageUri != null){
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    bitmapImage = BitmapFactory.decodeStream(inputStream);
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            if(bitmapImage != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_imageContainer, ImageDisplayFragment.newInstance(bitmapImage)).commit();

            }
        }

    }
}




