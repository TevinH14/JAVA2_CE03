//Tevin Hamilton
//CE_03_A
//2004
//ImageGalleryFragment

package com.example.hamiltontevin_ce03_a.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.hamiltontevin_ce03_a.MainActivity;
import com.example.hamiltontevin_ce03_a.R;
import com.example.hamiltontevin_ce03_a.adapter.ImageViewAdapter;
import com.example.hamiltontevin_ce03_a.fileStorage.ImageFileStorage;

public class ImageGalleryFragment extends Fragment {


    private static ImageFileStorage mImageFileStorage;
    private static String mAuthority;

    public ImageGalleryFragment() {
    }


    public static ImageGalleryFragment newInstance(ImageFileStorage _imageFileStorage,String auth) {

        Bundle args = new Bundle();
        mImageFileStorage = _imageFileStorage;
        mAuthority = auth;
        ImageGalleryFragment fragment = new ImageGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_imageview,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() != null){

            GridView galleryGridView = getView().findViewById(R.id.gridView_ImageGallery);

            ImageViewAdapter ivca = new ImageViewAdapter(getActivity(),mImageFileStorage.filesToBitmap(mImageFileStorage.getmFileArray()));
            galleryGridView.setAdapter(ivca);

            galleryGridView.setOnItemClickListener(imageSelection);
        }
    }

    private final AdapterView.OnItemClickListener imageSelection = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (getActivity() != null) {
                if (!MainActivity.appBCheck) {
                    Uri passingUri = Uri.parse(String.valueOf(FileProvider.getUriForFile(getActivity(), mAuthority, mImageFileStorage.getmFileArray()[position])));
                    Intent imageView = new Intent(android.content.Intent.ACTION_VIEW);
                    imageView.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    imageView.setDataAndType(passingUri, "image/*");
                    startActivity(imageView);
                } else {
                    Uri passingUri = Uri.parse(String.valueOf(FileProvider.getUriForFile(getActivity(), mAuthority, mImageFileStorage.getmFileArray()[position])));

                    Intent toAppBIntent = new Intent(getActivity(), MainActivity.class);
                    toAppBIntent.setData(passingUri);
                    toAppBIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    getActivity().setResult(Activity.RESULT_OK, toAppBIntent);
                    getActivity().finish();
                }
            }
        }
    };
}
