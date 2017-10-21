package com.gamemobile.sql2.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.gamemobile.sql2.model.Them;

import java.util.List;

/**
 * Created by hands on 10/21/2017.
 */

public class CustomAdapter extends ArrayAdapter<Them>{

    private Context context;
    private int resoure;
    private List<Them> listThem;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Them> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resoure=resource;
        this.listThem=objects;
    }
}
