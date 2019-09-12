package com.dicoding.basicfree.recipenotes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AboutActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView resPhoto = findViewById(R.id.img_item_photo);
        Glide.with(this)
                .load("https://www.dicoding.com/images/small/avatar/20190814215337b02510b0a144cfa06442e8e154a32491.png")
                .apply(new RequestOptions())
                .into(resPhoto);

        TextView resName = findViewById(R.id.tv_name);
        TextView resEmail = findViewById(R.id.tv_email);

        resName.setText("Agus Tri Haryono");
        resEmail.setText("agustharyono24@gmail.com");
    }

}
