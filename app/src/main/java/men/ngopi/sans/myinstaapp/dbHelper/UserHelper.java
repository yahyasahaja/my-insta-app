package men.ngopi.sans.myinstaapp.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import men.ngopi.sans.myinstaapp.MainActivity;
import men.ngopi.sans.myinstaapp.models.PostModel;
import men.ngopi.sans.myinstaapp.models.UserModel;
import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.*;
import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.UserColumns.*;

public class UserHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private static UserHelper instance;

    public static UserHelper getInstance() {
        return instance;
    }

    public UserHelper() {
        instance = this;
        context = MainActivity.getInstance();
    }

    public UserHelper(Context context) {
        this.context = context;
    }

    public UserHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public UserHelper close() {
        databaseHelper.close();
        return this;
    }

    public UserModel fetchUserById(int id) {
        Cursor cursor = database.query(
                USER_TABLE,
                null,
                _ID + " = ?",
                new String[] { "" + id },
                null,
                null,
                _ID + " DESC",
                null
        );

        return fetchUser(cursor).get(0);
    }

    public UserModel fetchUserByUsername(String username) {
        Cursor cursor = database.query(
                USER_TABLE,
                null,
                USERNAME + " = ?",
                new String[] { username },
                null,
                null,
                _ID + " DESC",
                null
        );

        return fetchUser(cursor).get(0);
    }

    public ArrayList<UserModel> fetchAllUsers() {
        Cursor cursor = database.query(
                USER_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC",
                null
        );

        return fetchUser(cursor);
    }

    public ArrayList<UserModel> fetchUser(Cursor cursor) {
        ArrayList<UserModel> result = new ArrayList<UserModel>();
        UserModel userModel;

        if (cursor.getCount() > 0) {
            do {
                userModel = new UserModel();
                userModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                userModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                userModel.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
                userModel.setProfilePicture(cursor.getString(cursor.getColumnIndexOrThrow(PROFILE_PICTURE)));
                result.add(userModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return result;
    }

    public long insert(UserModel userModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAME, userModel.getName());
        initialValues.put(USERNAME, userModel.getUsername());
        initialValues.put(PROFILE_PICTURE, userModel.getProfilePicture());
        return database.insert(USER_TABLE, null, initialValues);
    }

    public int update(UserModel userModel) {
        ContentValues args = new ContentValues();
        args.put(NAME, userModel.getName());
        args.put(USERNAME, userModel.getUsername());
        args.put(PROFILE_PICTURE, userModel.getProfilePicture());
        return database.update(
            USER_TABLE,
            args,
            _ID + " = '" + userModel.getId() + "'",
            null
        );
    }

    public int delete(int id) {
        return database.delete(USER_TABLE, _ID + " = '" + id + "'", null);
    }

}
