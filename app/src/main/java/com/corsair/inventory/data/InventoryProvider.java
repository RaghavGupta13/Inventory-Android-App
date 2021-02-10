package com.corsair.inventory.data;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.corsair.inventory.data.InventoryContract;
import com.corsair.inventory.data.InventoryContract.InnerClass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InventoryProvider extends ContentProvider {

    private InventoryDbHelper inventoryDbHelper;

    //LOG_TAG
    private static final String LOG_TAG = InventoryProvider.class.getSimpleName();

    //Uri Matcher Code
    private static final int ITEMS = 100;
    private static final int ITEMS_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_ITEM, ITEMS);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_ITEM + "/#", ITEMS_ID);
    }

    @Override
    public boolean onCreate() {
        inventoryDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();
        Cursor cursor;
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case ITEMS:
                cursor = db.query(InnerClass.TABLE_NAME, projection, null, null, null, null, null);
                break;
            case ITEMS_ID:
                selection = InnerClass._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(InnerClass.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Cannot query the unknown Uri" + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case ITEMS:
                return insertItem(uri, contentValues);
            default:
                throw new IllegalArgumentException("The values cannot be inserted for Uri: " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();
        long row_id = db.insert(InnerClass.TABLE_NAME, null, contentValues);

        if (row_id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
        }
        return ContentUris.withAppendedId(uri, row_id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();

        switch (match) {
            case ITEMS:
                return db.delete(InnerClass.TABLE_NAME, selection, selectionArgs);
            case ITEMS_ID:
                selection = InnerClass._ID + "+?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return db.delete(InnerClass.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case ITEMS:
                return updateItem(uri, contentValues, selection, selectionArgs);

            case ITEMS_ID:
                selection = InnerClass._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, contentValues, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Updation cannot be done for this Uri:" + uri);
        }
    }

    private int updateItem(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();

        return db.update(InnerClass.TABLE_NAME, contentValues, selection, selectionArgs);

    }
}
