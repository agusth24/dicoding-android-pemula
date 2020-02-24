package com.example.prototype.mymoviecataloguemade4.ui.movies

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.prototype.mymoviecataloguemade4.R
import com.example.prototype.mymoviecataloguemade4.db.DatabaseContract
import com.example.prototype.mymoviecataloguemade4.db.MappingMoviesHelper
import com.example.prototype.mymoviecataloguemade4.db.MoviesHelper
import kotlinx.android.synthetic.main.activity_movies_detail.*
import kotlinx.android.synthetic.main.layout_detail_information.*


class MoviesDetailActivity : AppCompatActivity() {
    private var menu: Menu? = null
    private var dMovie: Movies? = null
    private var dFavMovie: Movies? = null
    private lateinit var movieHelper: MoviesHelper

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

        movieHelper = MoviesHelper.getInstance(applicationContext)
        movieHelper.open()
        dFavMovie = loadDBSync(dMovie?.id.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (dFavMovie?.id != null) {
            menu.findItem(R.id.favorite_menu)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                if (dFavMovie?.id == null) {
                    val values = ContentValues()
                    values.put(DatabaseContract.TvshowsColumn._ID, dMovie?.id)
                    values.put(DatabaseContract.MoviesColumn.PHOTO, dMovie?.photo)
                    values.put(DatabaseContract.MoviesColumn.TITLE, dMovie?.title)
                    values.put(DatabaseContract.MoviesColumn.DESCRIPTION, dMovie?.description)
                    values.put(DatabaseContract.MoviesColumn.RELEASE, dMovie?.release)
                    values.put(DatabaseContract.MoviesColumn.RATING, dMovie?.rating)

                    val result = movieHelper.insert(values)
                    if (result > 0) {
                        setResult(RESULT_ADD, intent)
                        Toast.makeText(
                            this@MoviesDetailActivity,
                            "Favorite: " + dMovie?.title,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@MoviesDetailActivity,
                            "Failed Add Movies",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {

                    val movieId = dFavMovie?.id
                    val movieTitle = dFavMovie?.title

                    val dialogTitle = "Delete Favorite Movies"
                    val dialogMessage: String =
                        "Are you sure delete movie $movieTitle?"
                    val alertDialogBuilder = AlertDialog.Builder(this, R.style.MyAlertDialog)

                    alertDialogBuilder.setTitle(dialogTitle)
                    alertDialogBuilder
                        .setMessage(dialogMessage)
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, id ->
                            val result = movieHelper.deleteById(movieId.toString()).toLong()
                            if (result > 0) {
                                val intent = Intent()
                                intent.putExtra(EXTRA_POSITION, movieId)
                                setResult(RESULT_DELETE, intent)
                                Toast.makeText(
                                    this@MoviesDetailActivity,
                                    "Deleted: $movieTitle",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this@MoviesDetailActivity,
                                    "Failed Deleted Movies",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        .setNegativeButton("No") { dialog, id -> dialog.cancel() }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadDBSync(id: String?): Movies {
        val cursor = movieHelper.queryById(id)
        val movies = MappingMoviesHelper.mapCursorToList(cursor)

        return movies
    }

    override fun onDestroy() {
        super.onDestroy()
        movieHelper.close()
    }
}
