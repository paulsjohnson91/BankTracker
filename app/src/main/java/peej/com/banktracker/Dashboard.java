package peej.com.banktracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import peej.com.banktracker.arrayAdapters.bankSummary;
import peej.com.banktracker.arrayAdapters.bankSummaryAdapter;
import peej.com.banktracker.arrayAdapters.dashBoardAdapter;
import peej.com.banktracker.arrayAdapters.dashBoardSummary;
import peej.com.banktracker.db.DBReader;

public class Dashboard extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
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
        setContentView(R.layout.activity_dashboard);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.DashNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        ArrayList<dashBoardSummary> arrayOfSummaries = new ArrayList<dashBoardSummary>();
// Create the adapter to convert the array to views
        dashBoardAdapter adapter = new dashBoardAdapter(this, arrayOfSummaries);



// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_dashboard);
        listView.setAdapter(adapter);
        Float currentsTotal = DBReader.getCurrentsTotal(this);
        Float savingsTotal = DBReader.getSavingsTotal(this);
        if(currentsTotal!=0){
            adapter.add(new dashBoardSummary("Current Accounts","£" + DBReader.getCurrentsTotal(this)));
        }
        if(savingsTotal!=0){
            adapter.add(new dashBoardSummary("Savings Accounts","£" + DBReader.getSavingsTotal(this)));
        }

//        TextView tv = (TextView) findViewById(R.id.CurrentAccountTotal);
//        tv.setText("£" + DBReader.getCurrentsTotal(this));
    }

    public void gotoCurrentSummary(View view){

    }

}
