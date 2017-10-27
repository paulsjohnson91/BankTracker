package peej.com.banktracker.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import peej.com.banktracker.R;
import peej.com.banktracker.arrayAdapters.bankSummary;
import peej.com.banktracker.arrayAdapters.bankSummaryAdapter;



/**
 * Created by johnsopa on 27-10-17.
 */

public class DBReader {
    private static TaskDbHelper mHelper;

    public static float getCurrentsTotal(Context c){
        mHelper = new TaskDbHelper(c);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COL_BANK_NAME_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_NAME_TITLE,
                TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE,
                TaskContract.TaskEntry.COL_CURRENCY

        };
        String selection = TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE + "=?";
        //String[] selectionArgs = { "" + R.string.current_account };
        String[] selectionArgs = { "*" };
//        Cursor cursor = db.query(
//                TaskContract.TaskEntry.TABLE,                     // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                null                                 // The sort order
//        );
        //Cursor cursor =  db.rawQuery( "select * from " + TaskContract.TaskEntry.TABLE + " where "+TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE + "="+R.string.current_account+"", null );
        String colname = c.getString(R.string.current_account);
        Cursor cursor =  db.rawQuery( "select * from " + TaskContract.TaskEntry.TABLE + " where "+TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE + "='"+colname+"'", null );
        float current_total = 0;
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE);
            Log.d("balance",cursor.getString(idx));
            Log.d("Account type",cursor.getString(cursor.getColumnIndex(TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE)));
            current_total += Float.parseFloat(cursor.getString(idx));
        }




        cursor.close();
        db.close();

        return current_total;
    }

    public static float getSavingsTotal(Context c){
        mHelper = new TaskDbHelper(c);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COL_BANK_NAME_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_NAME_TITLE,
                TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE,
                TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE,
                TaskContract.TaskEntry.COL_CURRENCY

        };
        String selection = TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE + "=?";
        //String[] selectionArgs = { "" + R.string.current_account };
        String[] selectionArgs = { "*" };
//        Cursor cursor = db.query(
//                TaskContract.TaskEntry.TABLE,                     // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                null                                 // The sort order
//        );
        //Cursor cursor =  db.rawQuery( "select * from " + TaskContract.TaskEntry.TABLE + " where "+TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE + "="+R.string.current_account+"", null );
        String colname = c.getString(R.string.savings_account);
        Cursor cursor =  db.rawQuery( "select * from " + TaskContract.TaskEntry.TABLE + " where "+TaskContract.TaskEntry.COL_ACCOUNT_TYPE_TITLE + "='"+colname+"'", null );
        float current_total = 0;
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_INITIAL_BALANCE_TITLE);
            current_total += Float.parseFloat(cursor.getString(idx));
        }




        cursor.close();
        db.close();

        return current_total;
    }

}
