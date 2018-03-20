package tk.bhavya.nouihabittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import tk.bhavya.nouihabittracker.HabitDBhelper;

import tk.bhavya.nouihabittracker.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {
    private HabitDBhelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitDBhelper(this);
        Button fab = (Button) findViewById(R.id.abc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                displayDatabaseInfo();
            }
        });}


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }


    private Cursor read() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                new String[]{
                        HabitEntry._ID,
                        HabitEntry.COLUMN_NAME,
                        HabitEntry.COLUMN_FEEL,
                        HabitEntry.COLUMN_FREQUENCY,
                },
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }


    private void displayDatabaseInfo() {
        Cursor cursor = read();

        TextView displayView = (TextView) findViewById(R.id.basicTextView);
        try {
            displayView.setText("The table contains " + cursor.getCount() + " items.\n\n");
            displayView.append(HabitEntry._ID + " ->" +
                    HabitEntry.COLUMN_NAME + " ->" +
                    HabitEntry.COLUMN_FEEL + " ->" +
                    HabitEntry.COLUMN_FREQUENCY);
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME);
            int feelColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_FEEL);
            int frequencyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_FREQUENCY);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentFeel = cursor.getString(feelColumnIndex);
                int currentFrequency = cursor.getInt(frequencyColumnIndex);
                displayView.append(("\n" + currentID + " ->" +
                        currentName + " ->" +
                        currentFeel + " ->" +
                        currentFrequency));
            }
        } finally {

            cursor.close();
        }


    }


    public void insertData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME, "Sleep");
        values.put(HabitEntry.COLUMN_FEEL, "GOOD");
        values.put(HabitEntry.COLUMN_FREQUENCY, 7);
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }


}