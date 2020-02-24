package com.example.prototype.mymoviecataloguemade5.db

import android.database.Cursor
import com.example.prototype.mymoviecataloguemade5.ui.movies.Movies

object MappingMoviesHelper {
    fun mapCursorToArrayList(moviesCursor: Cursor?): ArrayList<Movies> {
        val moviesList = ArrayList<Movies>()
        if (moviesCursor != null) {
            while (moviesCursor.moveToNext()) {
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
                moviesList.add(Movies(id, photo, title, description, release, rating))
            }
        }
        return moviesList
    }

    fun mapCursorToList(moviesCursor: Cursor?): Movies {
        if (moviesCursor != null) {
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

                return Movies(id, photo, title, description, release, rating)
            } else
                return Movies(null, null, null, null, null, 0.0)
        } else
            return Movies(null, null, null, null, null, 0.0)
    }
}