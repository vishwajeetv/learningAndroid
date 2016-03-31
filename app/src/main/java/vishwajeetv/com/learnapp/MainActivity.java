package vishwajeetv.com.learnapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends Activity {

    DatabaseHelper mDbHelper = new DatabaseHelper(this);

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseSeeder();


    }

    private void databaseSeeder() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.PRIMARY_KEY, 1);
        values.put(DatabaseContract.User.COLUMN_EMAIL, "vishwajeetvatharkar@gmail.com");
        values.put(DatabaseContract.User.COLUMN_NAME, "Vishwajeet");
        values.put(DatabaseContract.User.COLUMN_PASSWORD, "qwerty");
        Log.e("values", values.toString());

        db.insert(
                DatabaseContract.User.TABLE_NAME,
                DatabaseContract.User.COLUMN_NAME,
                values);

        values = new ContentValues();
        values.put(DatabaseContract.User.PRIMARY_KEY, 2);
        values.put(DatabaseContract.User.COLUMN_EMAIL, "vish@gmail.com");
        values.put(DatabaseContract.User.COLUMN_NAME, "The Lord");
        values.put(DatabaseContract.User.COLUMN_PASSWORD, "qwerty");
        Log.e("values", values.toString());

        db.insert(
                DatabaseContract.User.TABLE_NAME,
                DatabaseContract.User.COLUMN_NAME,
                values);
    }


    public void login(View view)
    {
        EditText emailText   = (EditText)findViewById(R.id.email);
        EditText passwordText   = (EditText)findViewById(R.id.password);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        Log.e("email",email);
        int duration = Toast.LENGTH_SHORT;


        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
        String[] projection = {
                DatabaseContract.User.PRIMARY_KEY,
                DatabaseContract.User.COLUMN_NAME
        };
//
        String selection =
           DatabaseContract.User.COLUMN_EMAIL + " LIKE ? AND "  + DatabaseContract.User.COLUMN_PASSWORD + " LIKE ? ";

//
        String selectionArgs[] = {
                email , password
        };

        Cursor cursor = db.query(
                DatabaseContract.User.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                        // The values for the WHERE claus
                null,null,null
        );
//
       if(cursor.moveToFirst())
       {
           String nameUser = cursor.getString(1);
           Log.e("user name",nameUser);

           Context context = getApplicationContext();
           CharSequence text = "Login Successful!";
           Toast toast = Toast.makeText(context, text, duration);
           toast.show();

           Intent intent = new Intent(this, DashboardActivity.class);
           intent.putExtra("USER_NAME",nameUser);
           startActivity(intent);
       }
        else
       {
           Context context = getApplicationContext();
           CharSequence text = "Invalid Email / Password";

           Toast toast = Toast.makeText(context, text, duration);
           toast.show();

       }

    }
}
