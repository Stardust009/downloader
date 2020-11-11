package com.fs.test1.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class MyContentProvider: ContentProvider() {
    override fun onCreate(): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
//        TODO("Not yet implemented")
        return null
    }

    override fun getType(uri: Uri): String? {
//        TODO("Not yet implemented")
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        TODO("Not yet implemented")
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
//        TODO("Not yet implemented")
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
//        TODO("Not yet implemented")
        return 0
    }
}