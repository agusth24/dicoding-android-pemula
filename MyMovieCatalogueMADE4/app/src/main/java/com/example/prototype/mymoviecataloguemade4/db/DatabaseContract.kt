package com.example.prototype.mymoviecataloguemade4.db

import android.provider.BaseColumns

class DatabaseContract {
    internal class MoviesColumn : BaseColumns {
        companion object {
            const val TABLE_NAME_MOVIE = "movies"
            const val _ID = "_id"
            const val PHOTO = "photo"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val RELEASE = "release"
            const val RATING = "rating"
        }
    }

    internal class TvshowsColumn : BaseColumns {
        companion object {
            const val TABLE_NAME_TVSHOW = "tvshows"
            const val _ID = "_id"
            const val PHOTO = "photo"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val RELEASE = "release"
            const val RATING = "rating"
        }
    }
}