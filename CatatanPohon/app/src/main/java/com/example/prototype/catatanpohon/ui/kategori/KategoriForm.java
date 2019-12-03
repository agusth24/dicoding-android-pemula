package com.example.prototype.catatanpohon.ui.kategori;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prototype.catatanpohon.R;

public class KategoriForm extends AppCompatActivity
{
    private String title = "Kategori Form";

    private void setActionBarTitle(String title)
    {
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_form);

        setActionBarTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
