package com.example.eva3_9_imagen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView ImgVwImagen;
    Bitmap imagen;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ImgVwImagen.setImageBitmap(imagen);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImgVwImagen = findViewById(R.id.ImgVwImagen);

        Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                imagen = cargarImagen("https://www.quo.es/wp-content/uploads/2019/10/descubren-que-la-piramide-de-giza-podria-concentrar-energias-electromagneticas.jpg");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        tHilo.start();

    }

    public Bitmap cargarImagen(String url){
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return  bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}