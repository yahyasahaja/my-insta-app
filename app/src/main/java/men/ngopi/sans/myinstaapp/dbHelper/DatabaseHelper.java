package men.ngopi.sans.myinstaapp.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.*;
import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.UserColumns.*;
import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.PostColumns.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "myinstaapp";
    public final static String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " ( "
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + USERNAME + " TEXT NOT NULL UNIQUE, "
            + PROFILE_PICTURE + " TEXT NOT NULL);";
    public final static String CREATE_POST_TABLE = "CREATE TABLE " + POST_TABLE + " ( "
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + IMAGE + " TEXT NOT NULL, "
            + DESCRIPTION + " TEXT NOT NULL, "
            + "FOREIGN KEY (" + USER_ID + ") REFERENCES " + USER_TABLE + "(" + _ID + "));";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + POST_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
