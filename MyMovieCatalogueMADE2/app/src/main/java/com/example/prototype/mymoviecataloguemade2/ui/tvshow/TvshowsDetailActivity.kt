package com.example.prototype.mymoviecataloguemade2.ui.tvshow

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.prototype.mymoviecataloguemade2.R
import java.math.RoundingMode

class TvshowsDetailActivity : AppCompatActivity() {

    companion object {
        const val OBJECT_TVSHOW = "object_tvsho"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshows_detail)

        supportActionBar?.title = resources.getString(R.string.title_detail_tvshows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imgPhoto: ImageView = findViewById(R.id.img_photo_detail)
        val txtTitle: TextView = findViewById(R.id.txt_title_detil)
        val txtRating: TextView = findViewById(R.id.txt_rating_value)
        val txtRelease: TextView = findViewById(R.id.txt_release_value)
        val txtDesc: TextView = findViewById(R.id.txt_desc_detil)

        val dTvshow = intent.getParcelableExtra(OBJECT_TVSHOW) as Tvshows

        val rating = dTvshow.rating.toDouble() / 100 * 5
        val rounded = rating.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        Glide.with(this)
            .load(Uri.parse("file:///android_asset/" + dTvshow.photo))
            .into(imgPhoto)
        txtTitle.text = dTvshow.title
        txtDesc.text = dTvshow.description
        txtRating.text = rounded.toString()
        txtRelease.text = dTvshow.release
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
