//Tevin Hamilton
//CE_03_B
//2004
//ImageDisplayFragment

package com.example.hamiltontevin_ce03_b.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hamiltontevin_ce03_b.R;


public class ImageDisplayFragment extends Fragment {

    public static final String ARGS_APP_A_IMAGE_FILE = "ARGS_APP_A_IMAGE_FILE";

    private static Bitmap mSelectedImage;
    public ImageDisplayFragment() {
    }

    public static ImageDisplayFragment newInstance(Bitmap image) {

        Bundle args = new Bundle();
        mSelectedImage = image;
        ImageDisplayFragment fragment = new ImageDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() != null){
            ImageView imageView = getView().findViewById(R.id.main_ImageDisplay);
            if(mSelectedImage != null) {
                imageView.setImageBitmap(mSelectedImage);
            }
        }
    }


}
