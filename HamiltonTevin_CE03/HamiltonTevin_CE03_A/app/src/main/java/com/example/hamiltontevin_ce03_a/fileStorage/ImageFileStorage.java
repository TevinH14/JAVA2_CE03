//Tevin Hamilton
//CE_03_A
//2004
//ImageFileStorage

package com.example.hamiltontevin_ce03_a.fileStorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ImageFileStorage implements Serializable {
    private static final String IMAGE_FILE_START_NAME = "image_gallery_image";
    private static final String IMAGE_FOLDER = "images";

    private File[] mFileArray;

    public File[] getmFileArray() {
        return mFileArray;
    }

    public void setmFileArray(File[] mFileArray) {
        this.mFileArray = mFileArray;
    }

    public ImageFileStorage() {
    }
    public File createNewImageFile(Context _context){
        File folderPath = _context.getExternalFilesDir(IMAGE_FOLDER);
        if(folderPath != null){
            int fileSizeCount = Objects.requireNonNull(folderPath.listFiles()).length;
            String imageFileEndName = Integer.toString(fileSizeCount);
            File newFile = new File(folderPath,IMAGE_FILE_START_NAME+imageFileEndName);
            try {
                boolean isCreated = newFile.createNewFile();
                Log.i("createdFile","file is created" + isCreated);
            }catch (IOException e){
                e.printStackTrace();
            }
            return newFile;
        }
        return null;
    }

    public ArrayList<Bitmap> filesToBitmap(File[] fileArray){
        ArrayList<Bitmap> bmpArray = new ArrayList<>();
        if(fileArray != null){
            for (File f : fileArray) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inSampleSize = 2;
                Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath(), opt);
                bmpArray.add(bmp);
            }
            return bmpArray;
        }
        return null;
    }

    public File[] getImageFiles(Context _context){
        File folderPath = _context.getExternalFilesDir(IMAGE_FOLDER);
        if(folderPath != null) {
            return folderPath.listFiles();
        }
        return null;
    }
}
