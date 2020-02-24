package com.example.prototype.mymoviecataloguemade4.db

import android.database.Cursor
import com.example.prototype.mymoviecataloguemade4.ui.tvshow.Tvshows

object MappingTvshowsHelper {
    fun mapCursorToArrayList(tvshowsCursor: Cursor): ArrayList<Tvshows> {
        val tvshowList = ArrayList<Tvshows>()
        while (tvshowsCursor.moveToNext()) {
            val id =
                tvshowsCursor.getString(tvshowsCursor.getColumnIndexOrThrow(DatabaseContract.TvshowsColumn._ID))
            val photo =
                tvshowsCursor.getString(tvshowsCursor.getColumnIndexOrThrow(DatabaseContract.TvshowsColumn.PHOTO))
            val title =
                tvshowsCursor.getString(tvshowsCursor.getColumnIndexOrThrow(DatabaseContract.TvshowsColumn.TITLE))
            val description =
                tvshowsCursor.getString(tvshowsCursor.getColumnIndexOrThrow(DatabaseContract.TvshowsColumn.DESCRIPTION))
            val release =
                tvshowsCursor.getString(tvshowsCursor.getColumnIndexOrThrow(DatabaseContract.TvshowsColumn.RELEASE))
            val rating =
                tvshowsCursor.getDouble(tvshowsCursor.getColumnIndexOrThrow(DatabaseContract.TvshowsColumn.RATING))
            tvshowList.add(Tvshows(id, photo, title, description, release, rating))
        }
        return tvshowList
    }

    fun mapCursorToList(moviesCursor: Cursor): Tvshows {
        if (moviesCursor.moveToFirst()) {
            val id =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MoviesColumn._ID))
            val photo =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MoviesColumn.PHOTO))
            val title =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MoviesColumn.TITLE))
            val description =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MoviesColumn.DESCRIPTION))
            val release =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MoviesColumn.RELEASE))
            val rating =
                moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.MoviesColumn.RATING))

            return Tvshows(id, photo, title, description, release, rating)
        } else
            return Tvshows(null, null, null, null, null, 0.0)
    }
}