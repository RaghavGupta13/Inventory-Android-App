package com.corsair.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.corsair.inventory.data.InventoryContract;
import com.corsair.inventory.data.InventoryContract.InnerClass;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayInfo();
    }

    private void displayInfo() {

        String[] projection = {
                InnerClass._ID,
                InnerClass.NAME,
                InnerClass.SUPPLIER,
                InnerClass.PRICE
        };

        Cursor cursor = getContentResolver().query(InnerClass.CONTENT_URI,
                projection,
                null,
                null,
                null);

        ListView list = (ListView) findViewById(R.id.list_view);
        InventoryAdapter adapter = new InventoryAdapter(this, cursor);
        list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //when user clicks any of the options in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.add_main:
                Intent intent = new Intent(this, EditorActivity.class);
                startActivity(intent);
            case R.id.about_main:

        }
        return super.onOptionsItemSelected(item);
    }
}