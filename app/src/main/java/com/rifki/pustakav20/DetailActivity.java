package com.rifki.pustakav20;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private TextView txt_judul,txt_waktunya,txt_penulis,txt_penerbit,txt_ketersediaan,txt_description;
    private ImageView img_header,img_icon;
    int id = 1;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private static final int  MEGABYTE = 1024 * 1024;
    private String folderName="Pustaka";
    private String fileUrl= "http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/file_buku/";
    //    private String fileUrl="http://rivkiey.com/pustaka/file_buku/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(DetailActivity.this);
        final String namaFile= getIntent().getStringExtra("nama_file");
        final String url_preview = getIntent().getStringExtra("url_preview");
        img_header = (ImageView) findViewById(R.id.img_header);
        //img_icon = (ImageView) findViewById(R.id.img_icon);
        txt_judul = (TextView) findViewById(R.id.txt_judul);
        txt_judul.setTypeface(tf);
     //   txt_waktunya = (TextView) findViewById(R.id.txt_waktu);
        txt_penulis = (TextView) findViewById(R.id.txt_penulis);
        txt_penerbit = (TextView) findViewById(R.id.txt_penerbit);
       // txt_ketersediaan = (TextView) findViewById(R.id.txt_ketersediaan);
        txt_description = (TextView) findViewById(R.id.txt_deskripsi);
        txt_judul.setText(getIntent().getStringExtra("judul"));
//        txt_waktunya.setText( getIntent().getStringExtra("waktu"));
        txt_penulis.setText("Penulis : " +getIntent().getStringExtra("penulis"));
        txt_penerbit.setText("Penerbit : " +getIntent().getStringExtra("penerbit"));
      //  txt_ketersediaan.setText("Ketersediaan :" +getIntent().getStringExtra("ketersediaan"));
        txt_description.setText("Deskripsi : " +getIntent().getStringExtra("deskripsi"));
        Glide.with(getApplicationContext())
                .load("http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/ikon/"
                        +getIntent().getStringExtra("buku_ikon"))
                .placeholder(R.drawable.icon_buku)
                .into(img_header);

//        Glide.with(getApplicationContext())
//                .load("http://rivkiey.com/pustaka/header/" + getIntent().getStringExtra("foto sampul"))
//                .placeholder(R.drawable.ntb)
//                .into(img_header);
       // Button downloadButton = (Button) findViewById(R.id.btn_download);
        Button previewButton = (Button) findViewById(R.id.btn_preview);
        previewButton.setTypeface(tf);
//       // downloadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                download(namaFile);
//            }
//        });
        final Intent i = new Intent(this, PreviewActivity.class);
        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("url_preview",url_preview);
                startActivity(i);
            }
        });
    }

}


