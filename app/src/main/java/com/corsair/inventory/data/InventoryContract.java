package com.corsair.inventory.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {

    //constructor
    private InventoryContract() {

    }

    public static final String CONTENT_AUTHORITY = "com.corsair.inventory"; // content auth. for the uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY); //Usable Uri
    public static final String PATH_ITEM = "item";

    public static abstract class InnerClass implements BaseColumns {

        public static final String TABLE_NAME = "inventory";
        public static final String _ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String SUPPLIER = "supplier";
        public static final String PRICE = "price";
        public static final String DESCRIPTION = "description";

        //Complete Content Uri
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ITEM);
    }
}
