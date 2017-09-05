package com.example.rkrmills.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RKRMILLS on 28-03-2017.
 */

public class Databasehelper extends SQLiteOpenHelper {

    public static final String database_name="student.db";
    public static final String table_name="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="USERNAME";
    public static final String COL_3="PASSWORD";

    public Databasehelper(Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_name+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }

    public boolean insertData(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalue=new ContentValues();
        contentvalue.put(COL_2,username);
        contentvalue.put(COL_3,password);
       long result= db.insert(table_name,null,contentvalue);//insert method returns -1 if data is not inserted
        if(result==-1)
        return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);//creating instance of cursor class
        return res;

    }

    public boolean updateData(String id,String username,String password){
        //step 1 create instaance of sqlitedatabase
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        db.update(table_name,contentValues,"ID =?", new String[] {id});
          return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(table_name,"ID = ?",new String[] {id});

    }
}

