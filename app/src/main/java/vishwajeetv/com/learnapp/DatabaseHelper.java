package vishwajeetv.com.learnapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vishwajeetv on 01/04/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.User.TABLE_NAME + " (" +
                    DatabaseContract.User.PRIMARY_KEY + " INTEGER PRIMARY KEY," +
                    DatabaseContract.User.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    DatabaseContract.User.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    DatabaseContract.User.COLUMN_PASSWORD + TEXT_TYPE+
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.User.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
