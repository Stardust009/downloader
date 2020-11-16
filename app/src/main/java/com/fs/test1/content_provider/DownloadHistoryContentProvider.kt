package com.fs.test1.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.fs.test1.AUTHORITY
import com.fs.test1.db.DownloadHistoryDBHelper

class DownloadHistoryContentProvider: ContentProvider() {
    private lateinit var dbHelper: DownloadHistoryDBHelper
    private lateinit var sqliteDb: SQLiteDatabase
    private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    companion object {
        lateinit var db: DownloadHistoryDBHelper
    }

    init {
        uriMatcher.addURI(AUTHORITY, "download_history", 1)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val name = getTableName(uri)
        return sqliteDb.query(name, projection, selection, selectionArgs, null, null, sortOrder, null)
    }

    override fun onCreate(): Boolean {
        dbHelper = DownloadHistoryDBHelper(context, DownloadHistoryDBHelper.DatabaseName)
        db = dbHelper
        sqliteDb = dbHelper.writableDatabase
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    private fun getTableName(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            1 -> "download_history"
            else -> "download_history"
        }
    }
}