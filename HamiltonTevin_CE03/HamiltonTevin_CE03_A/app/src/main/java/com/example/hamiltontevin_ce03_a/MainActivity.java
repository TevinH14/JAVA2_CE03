//Tevin Hamilton
//CE_03_A
//2004
//MainActivity

package com.example.hamiltontevin_ce03_a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hamiltontevin_ce03_a.fileStorage.ImageFileStorage;
import com.example.hamiltontevin_ce03_a.fragment.ImageGalleryFragment;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_PICTURE = 0x01001;
    private  static final String AUTHORITY = "com.example.hamiltontevin_ce03_a.fileprovider";
    private static final String IS_APP_B_EXTRA = "IS_APP_B_EXTRA";

    public static boolean appBCheck = false;
    private ImageFileStorage mImageFileStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent() != null){
            appBCheck = getIntent().hasExtra(IS_APP_B_EXTRA);
        }
        mImageFileStorage = new ImageFileStorage();
        mImageFileStorage.setmFileArray(mImageFileStorage.getImageFiles(this));
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_imageContainer, ImageGalleryFragment.newInstance(mImageFileStorage,AUTHORITY)).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_imageContainer, ImageGalleryFragment.newInstance(mImageFileStorage,AUTHORITY)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_take_picture){
            File newImageFile = mImageFileStorage.createNewImageFile(this);
            if(newImageFile != null) {
                Uri uri = FileProvider.getUriForFile(this, AUTHORITY, newImageFile);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                startActivityForResult(intent, REQUEST_TAKE_PICTURE);
            }
        }
        return true;
    }
}
