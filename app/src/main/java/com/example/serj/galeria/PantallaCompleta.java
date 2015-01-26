package com.example.serj.galeria;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PantallaCompleta extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallacompleta);

        String imagePath = getIntent().getStringExtra("path");
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageView iv = (ImageView) findViewById(R.id.ivFullScreen);
            Picasso.with(this).load(imagePath).fit().into(iv);
        }
    }
}
