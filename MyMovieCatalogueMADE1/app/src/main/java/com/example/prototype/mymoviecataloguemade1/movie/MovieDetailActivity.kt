package com.example.prototype.mymoviecataloguemade1.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.prototype.mymoviecataloguemade1.R

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val OBJECT_MOVIE = "object_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imgPhoto: ImageView = findViewById(R.id.img_photo_detail)
        val txtTitle: TextView = findViewById(R.id.txt_title_detil)
        val txtRating: TextView = findViewById(R.id.txt_rating)
        val txtRelease: TextView = findViewById(R.id.txt_release)
        val txtDesc: TextView = findViewById(R.id.txt_desc_detil)

        val dMovie = intent.getParcelableExtra(OBJECT_MOVIE) as Movie

        imgPhoto.setImageResource(dMovie.photo)
        txtTitle.text = dMovie.title
        txtDesc.text = dMovie.description
        txtRating.text = getString(R.string.rating_detail, dMovie.rating)
        txtRelease.text = getString(R.string.release_detail, dMovie.release)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
