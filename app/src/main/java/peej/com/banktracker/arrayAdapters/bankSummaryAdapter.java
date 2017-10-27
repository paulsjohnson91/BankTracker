package peej.com.banktracker.arrayAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import peej.com.banktracker.R;

/**
 * Created by johnsopa on 27-10-17.
 */
public class bankSummaryAdapter extends ArrayAdapter<bankSummary> {
    public bankSummaryAdapter(Context context, ArrayList<bankSummary> banks) {
        super(context, 0, banks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        bankSummary bank = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bank_list, parent, false);
        }
        // Lookup view for data population
        TextView tvBankName = (TextView) convertView.findViewById(R.id.bank_name_list);
        TextView tvAccountName = (TextView) convertView.findViewById(R.id.account_name_list);
        TextView tvrecordID = (TextView) convertView.findViewById(R.id.bank_id_field);
        Button tvBalanceButton = (Button) convertView.findViewById(R.id.BalanceButton);
        // Populate the data into the template view using the data object
        tvBalanceButton.setText(bank.balance);
        tvBankName.setText(bank.bankname);
        tvAccountName.setText(bank.accountname);
        tvrecordID.setText(bank.record_id);
        // Return the completed view to render on screen
        return convertView;
    }
}