package vishwajeetv.com.learnapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Gets the data repository in write mode
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.PRIMARY_KEY, 1);
        values.put(DatabaseContract.User.COLUMN_EMAIL, "vishwajeetvatharkar@gmail.com");
        values.put(DatabaseContract.User.COLUMN_NAME, "Vishwajeet");
        values.put(DatabaseContract.User.COLUMN_PASSWORD, "qwerty");

        long newRowId;
        newRowId = db.insert(
                DatabaseContract.User.TABLE_NAME,
                DatabaseContract.User.COLUMN_NAME,
                values);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void login(View view)
    {
        Intent intent = new Intent(this, DashboardActivity.class);
        Context context = getApplicationContext();
        CharSequence text = "Login Successful!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        startActivity(intent);
    }
}
