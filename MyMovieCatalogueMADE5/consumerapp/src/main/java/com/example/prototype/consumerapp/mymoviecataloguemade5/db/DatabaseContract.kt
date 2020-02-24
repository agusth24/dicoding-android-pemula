package com.example.prototype.consumerapp.mymoviecataloguemade5.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.example.prototype.mymoviecataloguemade5.provider"
    const val SCHEME = "content"

    internal class MoviesColumn : BaseColumns {
        companion object {
            const val TABLE_NAME_MOVIE = "movies"
            const val ID = "_id"
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
}