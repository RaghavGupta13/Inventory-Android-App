package com.corsair.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.corsair.inventory.data.InventoryContract.InnerClass;

public class InventoryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "store.db";

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InnerClass.TABLE_NAME + " ("
                + InnerClass._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InnerClass.NAME + " TEXT,"
                + InnerClass.SUPPLIER + " TEXT,"
                + InnerClass.PRICE + " REAL,"
                + InnerClass.DESCRIPTION + " TEXT);";
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //This is still version 1 of the db. Therefore, this method can be left blank
    }
}
