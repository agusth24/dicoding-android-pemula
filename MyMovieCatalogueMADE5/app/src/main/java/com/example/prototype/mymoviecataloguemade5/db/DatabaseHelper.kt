package com.example.prototype.mymoviecataloguemade5.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion.TABLE_NAME_MOVIE
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.TvshowsColumn.Companion.TABLE_NAME_TVSHOW

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {

        private const val DATABASE_NAME = "moviecatalogue"

        private const val DATABASE_VERSION = 2

        private val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE $TABLE_NAME_MOVIE" +
                " (${DatabaseContract.MoviesColumn.ID} TEXT PRIMARY KEY," +
                " ${DatabaseContract.MoviesColumn.PHOTO} TEXT NOT NULL," +
                " ${DatabaseContract.MoviesColumn.TITLE} TEXT NOT NULL," +
                " ${DatabaseContract.MoviesColumn.DESCRIPTION} TEXT NOT NULL," +
                " ${DatabaseContract.MoviesColumn.RELEASE} TEXT NOT NULL," +
                " ${DatabaseContract.MoviesColumn.RATING} REAL NOT NULL)"

        private val SQL_CREATE_TABLE_TVSHOW = "CREATE TABLE $TABLE_NAME_TVSHOW" +
                " (${DatabaseContract.TvshowsColumn.ID} TEXT PRIMARY KEY," +
                " ${DatabaseContract.TvshowsColumn.PHOTO} TEXT NOT NULL," +
                " ${DatabaseContract.TvshowsColumn.TITLE} TEXT NOT NULL," +
                " ${DatabaseContract.TvshowsColumn.DESCRIPTION} TEXT NOT NULL," +
                " ${DatabaseContract.TvshowsColumn.RELEASE} TEXT NOT NULL," +
                " ${DatabaseContract.TvshowsColumn.RATING} REAL NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE)
        db.execSQL(SQL_CREATE_TABLE_TVSHOW)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_MOVIE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_TVSHOW")
        onCreate(db)
    }
}