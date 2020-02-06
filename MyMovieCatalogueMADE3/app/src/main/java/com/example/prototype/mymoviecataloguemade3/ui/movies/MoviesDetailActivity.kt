package com.example.prototype.mymoviecataloguemade3.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.prototype.mymoviecataloguemade3.R
import kotlinx.android.synthetic.main.activity_movies_detail.*
import kotlinx.android.synthetic.main.layout_detail_information.*

class MoviesDetailActivity : AppCompatActivity() {

    companion object {
        const val OBJECT_MOVIE = "object_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        supportActionBar?.title = resources.getString(R.string.title_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dMovie = intent.getParcelableExtra(OBJECT_MOVIE) as Movies

        val overview = if(dMovie.description.toString().isEmpty()) {
            resources.getString(R.string.no_desc)
        } else {
            dMovie.description
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + dMovie.photo)
            .into(img_photo_detail)
        txt_title_detil.text = dMovie.title
        txt_desc_detil.text = overview
        txt_rating_value.text = dMovie.rating.toString()
        txt_release_value.text = dMovie.release
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
