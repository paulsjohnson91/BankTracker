package peej.com.banktracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import peej.com.banktracker.arrayAdapters.bankSummary;
import peej.com.banktracker.arrayAdapters.bankSummaryAdapter;
import peej.com.banktracker.db.TaskContract;
import peej.com.banktracker.db.TaskDbHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private TaskDbHelper mHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mTaskListView = (ListView) findViewById(R.id.list_banks);
        mHelper = new TaskDbHelper(this);
        displayBanks();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewBank();



            }
        });

    }

    private void addNewBank(){
        Intent intent = new Intent(this, addBank.class);
        startActivity(intent);
    }

    private void displayBanks(){
        ArrayList<bankSummary> arrayOfBanks = new ArrayList<bankSummary>();
// Create the adapter to convert the array to views
        bankSummaryAdapter adapter = new bankSummaryAdapter(this, arrayOfBanks);



// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_banks);
        listView.setAdapter(adapter);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COL_BANK_NAME_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_NAME_TITLE,
                TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE,
                TaskContract.TaskEntry.COL_CURRENCY

        };
        String selection = TaskContract.TaskEntry.COL_BANK_NAME_TITLE + " = ?";
        String[] selectionArgs = { "*" };
        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
//        while (cursor.moveToNext()) {
//            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
//            taskList.add(cursor.getString(idx));
//        }

//        if (mAdapter == null) {
//            mAdapter = new ArrayAdapter<>(this,
//                    R.layout.bank_list,
//                    R.id.task_title,
//                    taskList);
//            mTaskListView.setAdapter(mAdapter);
//        } else {
//            mAdapter.clear();
//            mAdapter.addAll(taskList);
//            mAdapter.notifyDataSetChanged();
//        }

        // Construct the data source
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_ACCOUNT_NAME_TITLE);
            int idx1 = cursor.getColumnIndex(TaskContract.TaskEntry.COL_BANK_NAME_TITLE);
            int idx2 = cursor.getColumnIndex(TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE);
            int idx3 = cursor.getColumnIndex(TaskContract.TaskEntry._ID);
            adapter.add(new bankSummary(cursor.getString(idx),cursor.getString(idx1),"£" + cursor.getString(idx2),cursor.getString(idx3)));
        }



        cursor.close();
        db.close();
    }

    public void gotoSummary(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.bank_id_field);
        String task = String.valueOf(taskTextView.getText());
        Toast.makeText(this, task, Toast.LENGTH_SHORT).show();



//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        db.delete(TaskContract.TaskEntry.TABLE,
//                TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",
//                new String[]{task});
//        db.close();
//        updateUI();
    }

}
