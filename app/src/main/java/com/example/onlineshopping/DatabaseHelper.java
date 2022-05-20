package com.example.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USER = "user";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_USER_NAME = "user_name";
    private static final String COL_USER_PASSWORD = "user_password";
    private static final String COL_USER_EMAIL = "user_email";
    private static final String COL_USER_MOBILE = "user_mobile";
    private static final String COL_USER_ADD= "user_address";
    private static final String COL_USER_FNAME = "user_full_name";



    private String CREATE_TABLE = "CREATE TABLE "+ TABLE_USER + "(" +
            COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_NAME + " TEXT," +
            COL_USER_EMAIL + " TEXT," + COL_USER_PASSWORD + " TEXT," + COL_USER_FNAME + " TEXT,"+ COL_USER_MOBILE + " TEXT," + COL_USER_ADD + " TEXT"+ ")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+ TABLE_USER;


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(COL_USER_NAME, user.getUsername());
        values.put(COL_USER_EMAIL, user.getEmail());
        values.put(COL_USER_PASSWORD, user.getPassword());
        values.put(COL_USER_FNAME, user.getFullname());
        values.put(COL_USER_MOBILE, user.getNumber());
        values.put(COL_USER_ADD, user.getAddress());

        db.insert(TABLE_USER,null,values);
        db.close();
    }

    protected Boolean checkUser(String email){

        String[] col = { COL_USER_ID };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COL_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, col, selection, selectionArgs, null, null,null);
        int cursorCount = cursor.getCount();
        db.close();

        if(cursorCount>0){
            return true;
        }
        return false;

    }
    protected String validateUser(String email) {
        SQLiteDatabase dataBase = this.getReadableDatabase();
        String[] whereArgs = {email};
        String selection = COL_USER_EMAIL + " = ?";
        Cursor cursor = dataBase.rawQuery("SELECT "+COL_USER_PASSWORD+" FROM " + TABLE_USER + " WHERE " + selection, whereArgs);
        cursor.moveToFirst();
        if (cursor.getCount()> 0) {
            return cursor.getString(0);
        }
        dataBase.close();
        return null;
    }

    protected User getUnameEmail(String email) {
        SQLiteDatabase dataBase = this.getReadableDatabase();
        String[] whereArgs = {email};
        String selection = COL_USER_EMAIL + " = ?";

        try{
            Cursor cursor = dataBase.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + selection, whereArgs);
            cursor.moveToFirst();
            if(cursor.getCount()>0){
               return new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
            }
            else {
                Log.e("Error: ", "User Not Found");
            }
        }catch(Exception e){ }

        dataBase.close();
        return null;

    }
    protected User returnUser(String email) {
        SQLiteDatabase dataBase = this.getReadableDatabase();
        String[] whereArgs = {email};
        String selection = COL_USER_EMAIL + " = ?";

        try{
            Cursor cursor = dataBase.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + selection, whereArgs);
            cursor.moveToFirst();
            if(cursor.getCount()>0){
                return new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
            }
            else {
                Log.e("Error: ", "User Not Found");
            }
        }catch(Exception e){
//            Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        dataBase.close();
        return null;

    }
    protected void updateUserDetails(@NonNull User user){
        SQLiteDatabase db= this.getWritableDatabase();
        String update  = "update " + TABLE_USER + " SET " + COL_USER_FNAME +  " = "+"'"+user.getFullname()+"', " + COL_USER_MOBILE +  " = "+"'"+user.getNumber()+"', " + COL_USER_ADD +  " = "+"'"+user.getAddress()+"'"+ " WHERE " + COL_USER_EMAIL +  " = "+"'"+user.getEmail()+"'";

        db.execSQL(update);
        db.close();

    }

//    protected User returnUserAcc(String email,Context c){
//        SQLiteDatabase dataBase = this.getReadableDatabase();
//        String[] whereArgs = {email};
//        String selection = COL_USER_EMAIL + " = ?";
//
//        try{
//            Cursor cursor = dataBase.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + selection, whereArgs);
//            cursor.moveToFirst();
//            if(cursor.getCount()>0){
//                return new User(
//                        );
//            }
//        }catch(Exception e){
//            Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//        dataBase.close();
//        return null;
//    }

}
