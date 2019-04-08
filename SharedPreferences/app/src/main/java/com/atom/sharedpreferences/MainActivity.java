package com.atom.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String TAG = "========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data",
                        0).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 18);
                editor.putBoolean("married", false);
                // Log.d(TAG, "***************");
                editor.apply();
            }
        });

        Button restoreData = findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);
                Log.d(TAG, "name is " + name);
                Log.d(TAG, "age is " + age);
                Log.d(TAG, "married is " + married);
                Log.d(TAG, pref.getAll().toString());
            }
        });
    }
}
