package com.ishtiaq.nipa.tithi.ftflicareapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ishtiaq.nipa.tithi.ftflicareapp.Adapter.OneDayChartListAdapter;
import com.ishtiaq.nipa.tithi.ftflicareapp.database.DietTableDataSource;
import com.ishtiaq.nipa.tithi.ftflicareapp.model.DietModel;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.FTFLICareConstants;

public class FTFLICareDietlistBySelectedDate extends Activity {
	private String date;
	private DietTableDataSource dietTableObject;
	private ArrayList<DietModel> dietListByDate;
	private ListView selectedByDateListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet_lixt_selected_date);
		date = getIntent().getExtras().getString(FTFLICareConstants.SELECTED_Date);
		Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
		dietTableObject = new DietTableDataSource(this);
		dietListByDate = dietTableObject.getCurrentDateDietList(1, date);

		OneDayChartListAdapter adapter = new OneDayChartListAdapter(
				FTFLICareDietlistBySelectedDate.this, dietListByDate);
		selectedByDateListView = (ListView) findViewById(R.id.dailyDietList);
		selectedByDateListView.setAdapter(adapter);
		selectedByDateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						FTFLICareDietlistBySelectedDate.this);
				// Setting Dialog Title
				alertDialog.setTitle(getString(R.string.delete_dialog_title));
				// Setting Dialog Message
				alertDialog.setMessage(R.string.delete_dialog_message);
				// Setting Icon to Dialog
				alertDialog.setIcon(R.drawable.ic_launcher);

				// Setting Positive "Yes" Button
				alertDialog.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

								// Write your code here to invoke YES event
								Toast.makeText(getApplicationContext(),
										"You clicked on YES",
										Toast.LENGTH_SHORT).show();
							}
						});

				// Setting Negative "NO" Button
				alertDialog.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to invoke NO event
								Toast.makeText(getApplicationContext(),
										"You clicked on NO", Toast.LENGTH_SHORT)
										.show();
								dialog.cancel();
							}
						});

				// Showing Alert Message
				alertDialog.show();
			}

		});
	}

}
