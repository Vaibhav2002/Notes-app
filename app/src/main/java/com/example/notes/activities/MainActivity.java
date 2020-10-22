package com.example.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.adapter.DataManipulation;
import com.example.notes.adapter.adapter;
import com.example.notes.helperClasses.DatabaseHelper;
import com.example.notes.not;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    static RecyclerView recyclerView;
    FloatingActionButton fab;
    static ArrayList<not> foradapter;
    static com.example.notes.adapter.adapter adapter;
    ArrayList<not> tempolist;
    SearchView searchView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DataManipulation dataManipulation = new DataManipulation(foradapter);

        switch (item.getItemId()) {
            case R.id.sortAscen:
                foradapter = dataManipulation.sortAscending();
                break;
            case R.id.sortDescen:
                foradapter = dataManipulation.sortDescending();
                break;
        }
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        foradapter = new ArrayList<>();
        searchView=findViewById(R.id.search);
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
        tempolist= new ArrayList<>(foradapter);
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