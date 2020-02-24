package com.example.prototype.mymoviecataloguemade5.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.prototype.mymoviecataloguemade5.db.DatabaseContract.MoviesColumn.Companion._ID

class MoviesHelper(context: Context) {
    private val dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = DatabaseContract.MoviesColumn.TABLE_NAME_MOVIE
        private var INSTANCE: MoviesHelper? = null

        fun getInstance(context: Context): MoviesHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MoviesHelper(context)
                    }
                }
            }
            return INSTANCE as MoviesHelper
        }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC",
            null
        )
    }

    fun queryById(id: String?): Cursor {
        return database.query(DATABASE_TABLE, null, "$_ID = ?", arrayOf(id), null, null, null, null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }
}