package com.example.serj.galeria;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Adaptador extends CursorAdapter{

    private static LayoutInflater i;

    public static class ViewHolder {
        public ImageView iv;
    }

    public Adaptador(Context context, Cursor c){
        super(context, c, true);
        this.i = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = i.inflate(R.layout.imagenlista, parent, false);
        ViewHolder vh = new ViewHolder();
        vh.iv = (ImageView)view.findViewById(R.id.imageView);
        view.setTag(vh);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vh = (ViewHolder)view.getTag();
        vh.iv.setTag(cursor.getPosition());
        int imageID = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Integer.toString(imageID));
        Picasso.with(context).load(uri).fit().into(vh.iv);
    }
}
