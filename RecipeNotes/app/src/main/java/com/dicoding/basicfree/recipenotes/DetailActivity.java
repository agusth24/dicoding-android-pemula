package com.dicoding.basicfree.recipenotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.io.InputStream;


public class DetailActivity extends AppCompatActivity
{
    public static final String TV_JUDUL = "Judul";
    public static final String TV_DETAIL = "Detail";
    public static final String IMAGE_PHOTOS = "Image";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Recipe Detail");
        }

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        TextView resJudul = findViewById(R.id.tv_item_name);
        TextView resDetail = findViewById(R.id.tv_item_detail);
        ImageView resPhoto = findViewById(R.id.img_item_photo);

        String tv_judul = getIntent().getStringExtra(TV_JUDUL);
        String tv_detail = getIntent().getStringExtra(TV_DETAIL);
        String image_photo = getIntent().getStringExtra(IMAGE_PHOTOS);

        resJudul.setText(tv_judul);

        Glide.with(this)
                .load(image_photo)
                .apply(new RequestOptions())
                .into(resPhoto);

        try {
            InputStream is = getAssets().open(tv_detail);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);
            resDetail.setText(text);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
