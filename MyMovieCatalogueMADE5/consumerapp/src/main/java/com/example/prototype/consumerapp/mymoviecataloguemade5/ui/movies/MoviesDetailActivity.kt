package com.example.prototype.consumerapp.mymoviecataloguemade5.ui.movies

import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.prototype.consumerapp.mymoviecataloguemade5.R
import com.example.prototype.consumerapp.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.CONTENT_URI
import com.example.prototype.consumerapp.mymoviecataloguemade5.db.MappingMoviesHelper
import kotlinx.android.synthetic.main.activity_movies_detail.*
import kotlinx.android.synthetic.main.layout_detail_information.*


class MoviesDetailActivity : AppCompatActivity() {
    private var menu: Menu? = null
    private var dMovie: Movies? = null
    private var dFavMovie: Movies? = null
    private lateinit var uriWithId: Uri

    companion object {
        const val OBJECT_MOVIE = "object_movie"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_detail)

        supportActionBar?.title = resources.getString(R.string.title_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dMovie = intent.getParcelableExtra(OBJECT_MOVIE) as Movies

        val overview = if (dMovie?.description.toString().isEmpty()) {
            resources.getString(R.string.no_desc)
        } else {
            dMovie?.description
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + dMovie?.photo)
            .into(img_photo_detail)
        txt_title_detil.text = dMovie?.title
        txt_desc_detil.text = overview
        txt_rating_value.text = dMovie?.rating.toString()
        txt_release_value.text = dMovie?.release

        val handlerThread = HandlerThread("DataObserverConsumer")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                dFavMovie = loadDBSync(dMovie?.id.toString())
            }
        }

        contentResolver?.registerContentObserver(
            CONTENT_URI,
            true,
            myObserver
        )
        dFavMovie = loadDBSync(dMovie?.id.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }


    private fun loadDBSync(id: String?): Movies {
        uriWithId = Uri.parse("$CONTENT_URI/$id")
        val cursor = contentResolver.query(uriWithId, null, null, null, null)

        return MappingMoviesHelper.mapCursorToList(cursor)
    }


}
