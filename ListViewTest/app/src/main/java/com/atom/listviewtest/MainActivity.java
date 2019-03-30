package com.atom.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String[] data = { "Appple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Strawberry",
        "Cherry", "Mango", "Apple", "Banana", "Orange", "wWatermelon", "Pear", "Grape", "Strawberry",
        "Cherry", "Mango", };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
