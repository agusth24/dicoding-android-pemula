package com.example.prototype.mymoviecataloguemade3.ui.tvshow

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.prototype.mymoviecataloguemade3.R
import kotlinx.android.synthetic.main.activity_tvshows_detail.*
import kotlinx.android.synthetic.main.layout_detail_information.*

class TvshowsDetailActivity : AppCompatActivity() {

    companion object {
        const val OBJECT_TVSHOW = "object_tvsho"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshows_detail)

        supportActionBar?.title = resources.getString(R.string.title_detail_tvshows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dTvshow = intent.getParcelableExtra(OBJECT_TVSHOW) as Tvshows

        val overview = if(dTvshow.description.toString().isEmpty()) {
            resources.getString(R.string.no_desc)
        } else {
            dTvshow.description
        }

        Glide.with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/w500/" + dTvshow.photo))
            .into(img_photo_detail)
        txt_title_detil.text = dTvshow.title
        txt_desc_detil.text = overview
        txt_rating_value.text = dTvshow.rating.toString()
        txt_release_value.text = dTvshow.release
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
