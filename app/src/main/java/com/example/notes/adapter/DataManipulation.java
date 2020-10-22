package com.example.notes.adapter;

import com.example.notes.not;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataManipulation {


    ArrayList<not> oglist;

    public DataManipulation(ArrayList<not> oglist) {
        this.oglist = oglist;
    }

    public ArrayList<not> sortAscending() {
        Collections.sort(oglist, new sortAscen());
        return oglist;
    }

    public ArrayList<not> sortDescending() {
        Collections.sort(oglist, new sortDescen());
        return oglist;
    }
}


class sortAscen implements Comparator<not> {

    @Override
    public int compare(not o1, not o2) {
        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }
}

class sortDescen implements Comparator<not> {

    @Override
    public int compare(not o1, not o2) {
        return o2.getTitle().compareToIgnoreCase(o1.getTitle());
    }
}
