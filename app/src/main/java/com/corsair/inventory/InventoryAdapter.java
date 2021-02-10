package com.corsair.inventory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.corsair.inventory.data.InventoryContract.InnerClass;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class InventoryAdapter extends CursorAdapter {

    public InventoryAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView supplierTextView = (TextView) view.findViewById(R.id.supplier);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(InnerClass.NAME));
        String supplier = cursor.getString(cursor.getColumnIndexOrThrow(InnerClass.SUPPLIER));
        String price = "$ " + cursor.getString(cursor.getColumnIndexOrThrow(InnerClass.PRICE));


        nameTextView.setText(name);
        supplierTextView.setText(supplier);
        priceTextView.setText(price);
    }
}
