package com.example.prototype.consumerapp.mymoviecataloguemade5.db

import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.example.prototype.mymoviecataloguemade5.provider"
    const val SCHEME = "content"

    internal class MoviesColumn : BaseColumns {
        companion object {
            const val TABLE_NAME_MOVIE = "movies"
            const val _ID = "_id"
            const val PHOTO = "photo"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val RELEASE = "release"
            const val RATING = "rating"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME_MOVIE)
                .build()
        }

    }

    fun getColumnDouble(cursor: Cursor?, columnName: String?): Double {
        return cursor?.getDouble(cursor.getColumnIndex(columnName)) ?: 0.0
    }

    fun getColumnString(cursor: Cursor?, columnName: String?): String {
        return cursor?.getString(cursor.getColumnIndex(columnName)) ?: ""
    }

    fun getColumnInt(cursor: Cursor?, columnName: String?): Int {
        return cursor?.getInt(cursor.getColumnIndex(columnName)) ?: 0
    }
}