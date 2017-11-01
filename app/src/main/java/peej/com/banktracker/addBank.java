package peej.com.banktracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import peej.com.banktracker.db.TaskContract;
import peej.com.banktracker.db.TaskDbHelper;

public class addBank extends AppCompatActivity{

    private TaskDbHelper mHelper;
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button submit = (Button) findViewById(R.id.newBankSubmitButton);
        mHelper = new TaskDbHelper(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(TaskContract.TaskEntry.COL_BANK_NAME_TITLE, ((EditText)findViewById(R.id.bankEditText)).getText().toString());
                values.put(TaskContract.TaskEntry.COL_ACCOUNT_NAME_TITLE, ((EditText)findViewById(R.id.accountEditText)).getText().toString());
                values.put(TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE, Float.parseFloat(((EditText)findViewById(R.id.balanceEditText)).getText().toString()));


//                Spinner accountTypeSpinner = (Spinner) findViewById(R.id.accountTypeSpinner);
//                String accountTypeString = accountTypeSpinner.getSelectedItem().toString();
//                Spinner currencySpinner = (Spinner) findViewById(R.id.accountTypeSpinner);
//                String currencyString = accountTypeSpinner.getSelectedItem().toString();
                values.put(TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE, ((Spinner) findViewById(R.id.accountTypeSpinner)).getSelectedItem().toString());
                values.put(TaskContract.TaskEntry.COL_CURRENCY, ((Spinner) findViewById(R.id.currencyTypeSpinner)).getSelectedItem().toString());

                db.insertWithOnConflict(TaskContract.TaskEntry.TABLE,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE);
                db.close();
                //readDB();

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked

                                Intent intent = new Intent(addBank.this, MainActivity.class);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                Intent intent1 = new Intent(addBank.this, MainActivity.class);
                                startActivity(intent1);
                                break;
                        }
                    }
                };
                if(((Spinner) findViewById(R.id.accountTypeSpinner)).getSelectedItem().toString().equals(getString(R.string.savings_account))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(addBank.this);
                    builder.setMessage("Does your savings account have an expiry date?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
                else{
                    Intent intent = new Intent(addBank.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });


    }


    public void readDB(){
        SQLiteDatabase db = mHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                TaskContract.TaskEntry.COL_BANK_NAME_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_NAME_TITLE,
                TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE,
                TaskContract.TaskEntry.COL_CURRENCY

        };

// Filter results WHERE "title" = 'My Title'
        String selection = TaskContract.TaskEntry.COL_BANK_NAME_TITLE + " = ?";
        String[] selectionArgs = { "*" };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        Toast.makeText(getApplicationContext(), "" + cursor.getCount(), Toast.LENGTH_SHORT).show();
        cursor.close();
        db.close();

    }





}


