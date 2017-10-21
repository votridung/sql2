package com.gamemobile.sql2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gamemobile.sql2.model.Them;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hands on 10/21/2017.
 */

public class Database extends SQLiteOpenHelper{
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME="themmon_manager";
    private static final String TABLE_NAME ="themmon";
    private static final String ID ="id";
    private static final String TEN = "tenmon";
    private static final String NGUYENLIEU = "nguyenlieu";
    private static final String CACHLAM ="cachlam";
    private static int VERSION = 1;
    private Context context;

    private String SQLQuery="CREATE TABLE"+ TABLE_NAME +" ("+
            ID +" integer primary key, "+
            TEN +" TEXT, "+
            NGUYENLIEU + " TEXT, "+
            CACHLAM + " TEXT)";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
    }

    public void addMon(Them them){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN, them.getmTen());
        values.put(NGUYENLIEU, them.getmNguyenlieu());
        values.put(CACHLAM, them.getmCachlam());

        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d(TAG, "addThem Successfuly");
    }

    public List<Them> getAllthem() {
        List<Them> listThem = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Them them = new Them();
                them.setmID(cursor.getInt(0));
                them.setmTen(cursor.getString(1)+"");
                them.setmNguyenlieu(cursor.getString(2));
                them.setmCachlam(cursor.getString(3));
                listThem.add(them);

            } while (cursor.moveToNext());
        }
        db.close();
        return listThem;
    }
    public int updateThem(Them them){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN,them.getmTen());
        contentValues.put(NGUYENLIEU,them.getmNguyenlieu());
        contentValues.put(CACHLAM,them.getmCachlam());
        return db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(them.getmID())});
    }
    public int deleteThem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?",new String[] {String.valueOf(id)});
    }
}
