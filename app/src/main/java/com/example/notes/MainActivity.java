package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    static ArrayList<String> title;
    static ArrayList<String> descr;
    static ArrayList<not> foradapter;
    static adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle);
        foradapter=new ArrayList<>();
        title=new ArrayList<>();
        descr=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        SQLiteDatabase sql=this.openOrCreateDatabase("com.example.notes", Context.MODE_PRIVATE,null);
        sql.execSQL("CREATE TABLE IF NOT EXISTS notes (id INTEGER PRIMARY KEY, title VARCHAR , description VARCHAR)");
        Cursor c=sql.rawQuery("SELECT * FROM notes",null);
        Log.i("cursor",Integer.toString(c.getCount()));
        if(c.getCount()==0) {
            title.add("Sample Note");
            descr.add("Sample");
        }else {
            int titIndex = c.getColumnIndex("title");
            int descIndex = c.getColumnIndex("description");
            c.moveToFirst();
            while (!c.isAfterLast()){
                title.add(c.getString(titIndex));
                descr.add(c.getString(descIndex));
                c.moveToNext();
            }

        }
        Log.i("title",title.toString());
        Log.i("dec",descr.toString());
        for(int i=0;i<title.size();i++)
            foradapter.add(new not(title.get(i),descr.get(i)));
        Log.i("adapter",foradapter.toString());
        adapter=new adapter(getApplicationContext(),foradapter);
        recyclerView.setAdapter(adapter);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,AddNote.class);
                startActivity(intent);
            }
        });
    }
}