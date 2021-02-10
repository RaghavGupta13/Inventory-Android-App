package com.corsair.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.corsair.inventory.data.InventoryContract;
import com.corsair.inventory.data.InventoryDbHelper;

import com.corsair.inventory.data.InventoryContract.InnerClass;

import java.text.DecimalFormat;

public class EditorActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mSupplier;
    private EditText mPrice;
    private EditText mDesc;
    private InventoryDbHelper inventoryDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mName = (EditText) findViewById(R.id.nameValue);
        mSupplier = (EditText) findViewById(R.id.supplierValue);
        mPrice = (EditText) findViewById(R.id.priceValue);
        mDesc = (EditText) findViewById(R.id.descValue);
    }

    private void insertItem() {

        float mPriceFloat = Float.parseFloat(mPrice.getText().toString());
        DecimalFormat df = new DecimalFormat("0.00");

        ContentValues values = new ContentValues();
        values.put(InnerClass.NAME, mName.getText().toString());
        values.put(InnerClass.SUPPLIER, mSupplier.getText().toString());
        values.put(InnerClass.PRICE, df.format(mPriceFloat));
        values.put(InnerClass.DESCRIPTION, mDesc.getText().toString());

        Uri newUri = getContentResolver().insert(InnerClass.CONTENT_URI, values);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_editor:
                insertItem();
                Toast.makeText(this, "A new item has been added", Toast.LENGTH_SHORT).show();
                finish();

        }

        return super.onOptionsItemSelected(item);
    }
}