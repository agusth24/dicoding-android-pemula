package com.example.prototype.mymoviecataloguemade5.widget

import android.database.Cursor
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.DESCRIPTION
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.PHOTO
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.RATING
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.RELEASE
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.TITLE
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion._ID
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.getColumnDouble
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.getColumnString

class ResultsItem(cursor: Cursor?) {
    var id: String? = null
    var photo: String? = null
    var title: String? = null
    var description: String? = null
    var release: String? = null
    var rating: Double = 0.0

    init {
        id = getColumnString(cursor, _ID)
        photo = getColumnString(cursor, PHOTO)
        title = getColumnString(cursor, TITLE)
        description = getColumnString(cursor, DESCRIPTION)
        release = getColumnString(cursor, RELEASE)
        rating = getColumnDouble(cursor, RATING)
    }

    override fun toString(): String {
        return """ResultsItem{description = '$description',title = '$title',photo = '$photo',release = '$release',id = '$id'}"""
    }
}