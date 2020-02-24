package com.example.prototype.mymoviecataloguemade5.widget

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Binder
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import com.bumptech.glide.Glide
import com.example.prototype.mymoviecataloguemade5.R
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.CONTENT_URI
import java.util.concurrent.ExecutionException


class StackRemoteViewsFactory(private val mContext: Context, intent: Intent?) : RemoteViewsFactory {
    private var list: Cursor? = null
    override fun onCreate() {
        list = mContext.contentResolver.query(
            CONTENT_URI,
            null,
            null,
            null,
            null
        )
    }

    override fun getViewAt(i: Int): RemoteViews {
        val item: ResultsItem = getItem(i)
        val rv = RemoteViews(mContext.packageName, R.layout.movies_widget_item)
        var bitmap: Bitmap? = null
        try {
            bitmap = Glide.with(mContext)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w500" + item.photo)
                .submit()
                .get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        rv.setImageViewBitmap(R.id.imageView, bitmap)
        val extras = Bundle()
        extras.putString(MoviesFavoriteWidget.EXTRA_ITEM, item.title)
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    private fun getItem(position: Int): ResultsItem {
        list?.moveToPosition(position)?.let { check(it) { "Position invalid!" } }
        return ResultsItem(list)
    }

    override fun getCount(): Int {
        return list?.count ?: 0
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun onDataSetChanged() {
        if (list != null) {
            list?.close()
        }

        val identityToken = Binder.clearCallingIdentity()

        // querying ke database
        list = mContext.contentResolver.query(CONTENT_URI, null, null, null, null)

        Binder.restoreCallingIdentity(identityToken)
    }

    override fun onDestroy() {}

}