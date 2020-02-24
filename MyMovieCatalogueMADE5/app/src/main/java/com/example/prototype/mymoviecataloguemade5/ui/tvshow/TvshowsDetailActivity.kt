package com.example.prototype.mymoviecataloguemade5.ui.tvshow

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.prototype.mymoviecataloguemade5.R
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract
import com.example.prototype.mymoviecataloguemade5.db.MappingTvshowsHelper
import com.example.prototype.mymoviecataloguemade5.db.TvshowsHelper
import kotlinx.android.synthetic.main.activity_tvshows_detail.*
import kotlinx.android.synthetic.main.layout_detail_information.*

class TvshowsDetailActivity : AppCompatActivity() {

    private var menu: Menu? = null
    private var dTvshow: Tvshows? = null
    private var dFavTvshows: Tvshows? = null
    private lateinit var tvshowHelper: TvshowsHelper

    companion object {
        const val OBJECT_TVSHOW = "object_tvshow"
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
        setContentView(R.layout.activity_tvshows_detail)

        supportActionBar?.title = resources.getString(R.string.title_detail_tvshows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dTvshow = intent.getParcelableExtra(OBJECT_TVSHOW) as Tvshows

        val overview = if (dTvshow?.description.toString().isEmpty()) {
            resources.getString(R.string.no_desc)
        } else {
            dTvshow?.description
        }

        Glide.with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/w500/" + dTvshow?.photo))
            .into(img_photo_detail)
        txt_title_detail.text = dTvshow?.title
        txt_desc_detil.text = overview
        txt_rating_value.text = dTvshow?.rating.toString()
        txt_release_value.text = dTvshow?.release

        tvshowHelper = TvshowsHelper.getInstance(applicationContext)
        tvshowHelper.open()
        dFavTvshows = loadDBSync(dTvshow?.id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_options_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (dFavTvshows?.id != null) {
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
                if (dFavTvshows?.id == null) {
                    val values = ContentValues()
                    values.put(DatabaseContract.TvshowsColumn.ID, dTvshow?.id)
                    values.put(DatabaseContract.TvshowsColumn.PHOTO, dTvshow?.photo)
                    values.put(DatabaseContract.TvshowsColumn.TITLE, dTvshow?.title)
                    values.put(DatabaseContract.TvshowsColumn.DESCRIPTION, dTvshow?.description)
                    values.put(DatabaseContract.TvshowsColumn.RELEASE, dTvshow?.release)
                    values.put(DatabaseContract.TvshowsColumn.RATING, dTvshow?.rating)

                    val result = tvshowHelper.insert(values)
                    if (result > 0) {
                        setResult(RESULT_ADD, intent)
                        Toast.makeText(
                            this@TvshowsDetailActivity,
                            "Favorite: " + dTvshow?.title,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@TvshowsDetailActivity,
                            "Failed Add Movies",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    val tvshowId = dFavTvshows?.id
                    val tvshowTitle = dFavTvshows?.title

                    val dialogTitle = "Delete Favorite Movies"
                    val dialogMessage =
                        "Are you sure delete movie $tvshowTitle?"
                    val alertDialogBuilder = AlertDialog.Builder(this, R.style.MyAlertDialog)

                    alertDialogBuilder.setTitle(dialogTitle)
                    alertDialogBuilder
                        .setMessage(dialogMessage)
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, id ->
                            val result = tvshowHelper.deleteById(tvshowId.toString()).toLong()
                            if (result > 0) {
                                val intent = Intent()
                                intent.putExtra(EXTRA_POSITION, dTvshow?.id)
                                setResult(RESULT_DELETE, intent)
                                Toast.makeText(
                                    this@TvshowsDetailActivity,
                                    "Deleted: $tvshowTitle",
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this@TvshowsDetailActivity,
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

    private fun loadDBSync(id: String?): Tvshows {
        val cursor = tvshowHelper.queryById(id)

        return MappingTvshowsHelper.mapCursorToList(cursor)
    }

    override fun onDestroy() {
        super.onDestroy()
        tvshowHelper.close()
    }
}
