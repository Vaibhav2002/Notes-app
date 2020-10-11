package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNote extends AppCompatActivity {
    TextInputEditText t,d;
    FloatingActionButton fab;
    TextInputLayout tit,des;
    int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        t=findViewById(R.id.tit);
        d=findViewById(R.id.des);
        tit=findViewById(R.id.titlayout);
        des=findViewById(R.id.deslayout);
        fab=findViewById(R.id.fab2);
        val=getIntent().getIntExtra("NO",-1);
        if(val!=-1) {
            t.setText(MainActivity.title.get(val));
            d.setText(MainActivity.descr.get(val));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveNote())
                    finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(saveNote())
            super.onBackPressed();

    }

    protected boolean  saveNote()
    {
        String titleText=t.getText().toString().trim();
        String descText=d.getText().toString().trim();
        if(validate(titleText,descText)) {
            SQLiteDatabase sql = this.openOrCreateDatabase("com.example.notes", Context.MODE_PRIVATE, null);
            if (val == -1) {
                String sqL = "INSERT INTO notes (title,description) VALUES (?,?)";
                SQLiteStatement sqLiteStatement = sql.compileStatement(sqL);
                sqLiteStatement.bindString(1, titleText);
                sqLiteStatement.bindString(2, descText);
                sqLiteStatement.execute();
                MainActivity.title.add(titleText);
                MainActivity.descr.add(descText);
                MainActivity.foradapter.add(new not(titleText, descText));
                MainActivity.adapter.notifyItemInserted(MainActivity.title.size());

            } else {
                String sqL = "UPDATE notes SET title=?,description=? WHERE id=?";
                SQLiteStatement sqLiteStatement = sql.compileStatement(sqL);
                sqLiteStatement.bindString(1, titleText);
                sqLiteStatement.bindString(2, descText);
                sqLiteStatement.bindString(3, Integer.toString(val));
                sqLiteStatement.execute();
                MainActivity.title.set(val, titleText);
                MainActivity.descr.set(val, descText);
                MainActivity.foradapter.set(val, new not(titleText, descText));
                MainActivity.adapter.notifyDataSetChanged();
            }
            Toast.makeText(this, "Noted saved", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean validate(String titleText, String descText) {
        boolean flag=true;
        if(titleText.isEmpty())
        {
            t.setError("Field cannot be empty");
            flag=false;
        }else tit.setErrorEnabled(false);
        if(descText.isEmpty())
        {
            d.setError("Field cannot be empty");
            flag=false;
        }else des.setErrorEnabled(false);
        return flag;
    }
}