package men.ngopi.sans.myinstaapp.dbHelper;

import android.provider.BaseColumns;

public class DatabaseContract {
    static final String USER_TABLE = "user";
    static final String POST_TABLE = "post";

    static final class UserColumns implements BaseColumns {
        //UserModel name
        static final String NAME = "name";
        //UserModel username
        static final String USERNAME = "username";
        //UserModel profilePicture
        static final String PROFILE_PICTURE = "profile_picture";
    }

    static final class PostColumns implements BaseColumns {
        //UserModel name
        static final String USER_ID = "user_id";
        //UserModel username
        static final String IMAGE = "image";
        //UserModel profilePicture
        static final String DESCRIPTION = "description";
    }
}
