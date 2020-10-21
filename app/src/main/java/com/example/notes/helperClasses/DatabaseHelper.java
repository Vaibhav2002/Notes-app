package com.example.notes.helperClasses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.notes.activities.MainActivity;
import com.example.notes.not;

import java.util.ArrayList;

public class DatabaseHelper {
    private final String DATABASE_NAME = "com.example.notes";
    SQLiteDatabase sql;

    public DatabaseHelper(Context context) {
        sql = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void createTable() {
        sql.execSQL("CREATE TABLE IF NOT EXISTS notes (id INTEGER PRIMARY KEY, title VARCHAR , description VARCHAR)");
    }

    public void insertData(String titleText, String descText) {
        String sqL = "INSERT INTO notes (title,description) VALUES (?,?)";
        SQLiteStatement sqLiteStatement = sql.compileStatement(sqL);
        sqLiteStatement.bindString(1, titleText);
        sqLiteStatement.bindString(2, descText);
        sqLiteStatement.execute();
    }

    public void updateData(String titleText, String descText, int val) {
        String sqL = "UPDATE notes SET title=?,description=? WHERE id=?";
        SQLiteStatement sqLiteStatement = sql.compileStatement(sqL);
        sqLiteStatement.bindString(1, titleText);
        sqLiteStatement.bindString(2, descText);
        sqLiteStatement.bindString(3, Integer.toString(val));
        sqLiteStatement.execute();
    }

    public ArrayList<not> getData() {
        ArrayList<not> res = new ArrayList<>();
        Cursor c = sql.rawQuery("SELECT * FROM notes", null);
        if (c.getCount() == 0) {
            res.add(new not("Sample Note", "Sample"));
        } else {
            int titIndex = c.getColumnIndex("title");
            int descIndex = c.getColumnIndex("description");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                res.add(new not(c.getString(titIndex), c.getString(descIndex)));
                c.moveToNext();
            }
        }
        return res;
    }

    public void deleteData(String title) {
        String sqL = "DELETE FROM notes WHERE title=?";
        SQLiteStatement sqLiteStatement = sql.compileStatement(sqL);
        sqLiteStatement.bindString(1,title);
        sqLiteStatement.execute();
    }
}
