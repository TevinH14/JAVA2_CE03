//Tevin Hamilton
//CE_03_A
//2004
//ImageViewAdapter

package com.example.hamiltontevin_ce03_a.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.hamiltontevin_ce03_a.R;

import java.util.ArrayList;

public class ImageViewAdapter extends BaseAdapter {
    private final ArrayList<Bitmap> imagesArray;
    private final Context mContacts;

    public ImageViewAdapter(Context context , ArrayList<Bitmap> imagesArray) {
        this.imagesArray = imagesArray;
        mContacts = context;
    }

    @Override
    public int getCount() {
        if(imagesArray != null){
            return imagesArray.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(imagesArray != null && imagesArray.size() > 0){
            return imagesArray.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         Bitmap bm = (Bitmap) getItem(position);

        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContacts)
                    .inflate(R.layout.girdview_display_format, parent, false);

            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (imagesArray != null) {
            vh.imageHolder.setImageBitmap(bm);

            return convertView;
        }
        return null;
    }

    static class ViewHolder{
        final ImageView imageHolder;

        ViewHolder(View layout) {
            this.imageHolder = layout.findViewById(R.id.imageView_capturedImages);

        }
    }

}
