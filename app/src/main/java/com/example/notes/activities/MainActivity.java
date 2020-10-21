package com.example.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.helperClasses.DatabaseHelper;
import com.example.notes.R;
import com.example.notes.adapter.adapter;
import com.example.notes.not;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    FloatingActionButton fab;
    static ArrayList<not> foradapter;
    static com.example.notes.adapter.adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        foradapter = new ArrayList<>();
        adapter = new adapter(getApplicationContext(), foradapter);
        recyclerView.setAdapter(adapter);
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createTable();
        recyclerView.setHasFixedSize(true);
        ArrayList<not> ar = databaseHelper.getData();
        for (not i : ar) {
            foradapter.add(i);
            adapter.notifyDataSetChanged();
        }
        Log.i("adapter", foradapter.toString());
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            }
        });
    }
}