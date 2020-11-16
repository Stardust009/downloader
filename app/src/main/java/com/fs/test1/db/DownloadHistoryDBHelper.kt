package com.fs.test1.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DownloadHistoryDBHelper : SQLiteOpenHelper {

    companion object {
        const val DatabaseName = "DownloadHistory"
    }

    constructor(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory? = null,
        version: Int = 1
    ) : super(context, name, factory, version)

    override fun onCreate(db: SQLiteDatabase) {

        val sql =
            "create table download_history (id INTEGER PRIMARY KEY AUTOINCREMENT, file_name text, file_size text, file_path text)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}