package com.example.notes;

import androidx.annotation.NonNull;

public class not {
    String title, descr;

    public not() {
    }

    public String getTitle() {
        return title;
    }

    public not(String title, String descr) {
        this.title = title;
        this.descr = descr;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
