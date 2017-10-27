package peej.com.banktracker.arrayAdapters;

import android.content.Context;
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
public class dashBoardAdapter extends ArrayAdapter<dashBoardSummary> {
    public dashBoardAdapter(Context context, ArrayList<dashBoardSummary> combinations) {
        super(context, 0, combinations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        dashBoardSummary dash = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dashboard_list, parent, false);
        }
        // Lookup view for data population
         Button dashTotal = (Button) convertView.findViewById(R.id.dashboardTotal);
        TextView dashLabel = (TextView) convertView.findViewById(R.id.dashboardLabel);
        // Populate the data into the template view using the data object
        dashTotal.setText(dash.balance);
        dashLabel.setText(dash.summaryname);
        // Return the completed view to render on screen
        return convertView;
    }
}