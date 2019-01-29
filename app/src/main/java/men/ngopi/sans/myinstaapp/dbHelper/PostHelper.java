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

import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.POST_TABLE;
import static men.ngopi.sans.myinstaapp.dbHelper.DatabaseContract.PostColumns.*;

public class PostHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private static PostHelper instance;

    public static PostHelper getInstance() {
        return instance;
    }

    public PostHelper() {
        instance = this;
        context = MainActivity.getInstance();
    }

    public PostHelper(Context context) {
        this.context = context;
    }

    public PostHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public PostHelper close() {
        databaseHelper.close();
        return this;
    }

    public ArrayList<PostModel> fetchPostsByUserId(int userId) {
        Cursor cursor = database.query(
                POST_TABLE,
                null,
                USER_ID + " = ?",
                new String[]{ "" + userId },
                null,
                null,
                _ID + " DESC",
                null
        );
        return fetchPost(cursor);
    }

    public PostModel fetchPostsById(int id) {
        Cursor cursor = database.query(
                POST_TABLE,
                null,
                _ID + " = ?",
                new String[]{ "" + id },
                null,
                null,
                _ID + " DESC",
                null
        );
        return fetchPost(cursor).get(0);
    }

    public ArrayList<PostModel> fetchAllPosts() {
        Cursor cursor = database.query(
                POST_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC",
                null
        );
        return fetchPost(cursor);
    }

    public ArrayList<PostModel> fetchPost(Cursor cursor) {
        ArrayList<PostModel> result = new ArrayList<PostModel>();
        PostModel postModel;

        if (cursor.getCount() > 0) {
            do {
                postModel = new PostModel();
                postModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                postModel.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
                postModel.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                postModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                postModel.fetchUser();
                result.add(postModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return result;
    }

    public long insert(PostModel postModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(USER_ID, postModel.getUserId());
        initialValues.put(IMAGE, postModel.getImage());
        initialValues.put(DESCRIPTION, postModel.getDescription());
        return database.insert(POST_TABLE, null, initialValues);
    }

    public int update(PostModel postModel) {
        ContentValues args = new ContentValues();
        args.put(USER_ID, postModel.getUserId());
        args.put(IMAGE, postModel.getImage());
        args.put(DESCRIPTION, postModel.getDescription());
        return database.update(
            POST_TABLE,
            args,
            _ID + " = '" + postModel.getId() + "'",
            null
        );
    }


    public int delete(int id) {
        return database.delete(POST_TABLE, _ID + " = '" + id + "'", null);
    }

}
