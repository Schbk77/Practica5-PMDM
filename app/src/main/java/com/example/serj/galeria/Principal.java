package com.example.serj.galeria;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Principal extends ActionBarActivity {

    private Adaptador adaptador;
    private GridView lista;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lista = (GridView) findViewById(R.id.gridView);
        // Inicializar cursor
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID};
        c = getContentResolver().query(uri, projection, null, null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adaptador = new Adaptador(this, c);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c.moveToPosition(position);
                int imageID = c.getInt(c.getColumnIndex(MediaStore.Images.Media._ID));
                Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Integer.toString(imageID));
                String path = uri.toString();
                Intent i = new Intent(Principal.this, PantallaCompleta.class);
                i.putExtra("path", path);
                startActivity(i);
            }
        });
        registerForContextMenu(lista);
    }
}