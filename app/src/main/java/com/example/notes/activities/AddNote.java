package com.example.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.notes.helperClasses.DatabaseHelper;
import com.example.notes.R;
import com.example.notes.not;
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
            t.setText(MainActivity.foradapter.get(val).getTitle());
            d.setText(MainActivity.foradapter.get(val).getDescr());
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
            DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
            if (val == -1) {
                Log.i("Insert","Inserting");
                databaseHelper.insertData(titleText,descText);
                MainActivity.foradapter.add(new not(titleText, descText));
                MainActivity.adapter.notifyItemInserted(MainActivity.foradapter.size());

            } else {
                Log.i("Update","Updating");
                databaseHelper.updateData(titleText,descText,val);
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