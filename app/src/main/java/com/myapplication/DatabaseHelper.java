package com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name="bca6";
    static int version=1;
    String createTableUser="CREATE TABLE if not Exists \"user\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"firstname\"\tTEXT,\n" +
            "\t\"Lastname\"\tTEXT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"address\"\tTEXT,\n" +
            "\t\"phone\"\tTEXT,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"image\"\tBLOB \n" +

            ")";


    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }

    //method to insert into database
    public void insertUser(ContentValues values){
        getWritableDatabase().insert("user","", values);

    }

    //method to retrieve from database
    public ArrayList<Userinfo> getUserList(){
        String sql = "Select * from user";
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        ArrayList<Userinfo>list = new ArrayList<>();
        while(cursor.moveToNext()){
            Userinfo info = new Userinfo();
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));
            info.image = cursor.getBlob(cursor.getColumnIndex("image"));
            list.add(info);
        }
        cursor.close();
        return list;
    }
    public Userinfo getUserInfo(String id){
        String sql = "Select * from user where id=?";
        //String sql = "Select * from user where id="+id;
        Cursor cursor = getWritableDatabase().rawQuery(sql, new String[]{id});
        //Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        Userinfo info = new Userinfo();
        while(cursor.moveToNext()){
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));
            info.image = cursor.getBlob(cursor.getColumnIndex("image"));
        }
        cursor.close();
        return info;
    }
    //method to delete and update userinfo from DetailActivity
    public void deleteUser(String id){

        getWritableDatabase().delete("user","id="+id,null);
    }

    public void updateUser(String id, ContentValues values){
        getWritableDatabase().update("user",values,"id=?",new String[]{id});

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
