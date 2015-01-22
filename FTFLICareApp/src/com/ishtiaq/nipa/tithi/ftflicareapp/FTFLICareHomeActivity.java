package com.ishtiaq.nipa.tithi.ftflicareapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ishtiaq.nipa.tithi.ftflicareapp.Adapter.OneDayChartListAdapter;
import com.ishtiaq.nipa.tithi.ftflicareapp.database.DietTableDataSource;
import com.ishtiaq.nipa.tithi.ftflicareapp.model.DietModel;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.FTFLICareConstants;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.FTFLICareFunctions;

public class FTFLICareHomeActivity extends Activity {
	private DietTableDataSource dietTableObject;
	private ArrayList<String> upcommingDates;
	private ArrayList<DietModel> CurrentDateDietList;
	private ArrayList<DietModel> upcomingDietObject;
	private ListView todaysDietListView, upcomingDietListView;
	private String mCurrentDate;
	private int selectedId;
	private FTFLICareFunctions functions;
	private ArrayAdapter upcomingDateListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		functions = new FTFLICareFunctions();
		mCurrentDate = functions.getCurrentDate();

		dietTableObject = new DietTableDataSource(this);
		
	//	Current Date DietList
		CurrentDateDietList = dietTableObject.getCurrentDateDietList(1,
				mCurrentDate);

		OneDayChartListAdapter adapter = new OneDayChartListAdapter(
				FTFLICareHomeActivity.this, CurrentDateDietList);
		todaysDietListView = (ListView) findViewById(R.id.dailyDietList);
		todaysDietListView.setAdapter(adapter);
		todaysDietListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								FTFLICareHomeActivity.this);
						// Setting Dialog Title
						final String[] menuList = {
								getString(R.string.edit_diet),
								getString(R.string.delete_diet) };
						alertDialog.setTitle(getString(R.string.options));
						alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = CurrentDateDietList.get(position)
								.getmDietId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:
											// EDIT 1 code here
											Intent editIntent = new Intent(
													FTFLICareHomeActivity.this,
													FTFLICareEditDietActivity.class);
											editIntent
													.putExtra(
															FTFLICareConstants.SELECTED_ID,
															selectedId);
											startActivity(editIntent);
											Toast.makeText(
													getApplicationContext(),
													String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();
											break;
										case 1:
											// function 2 code here
											break;
										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();
						menuDrop.show();

					}

				});
		//upcoming Date Diet List 
		
		upcomingDietObject = dietTableObject
				.getUpcommingDietListByDate(mCurrentDate);

		upcommingDates = new ArrayList<String>();
		for (int i = 0; i < upcomingDietObject.size(); i++) {
			upcommingDates.add(upcomingDietObject.get(i).getmDietDate()
					.toString()
					+ "  " + upcomingDietObject.get(i).getmDay());
		}

		upcomingDateListAdapter = new ArrayAdapter<String>(this, R.layout.row,
				R.id.label, upcommingDates);
		upcomingDietListView = (ListView) findViewById(R.id.upComingDietList);
		upcomingDietListView.setAdapter(upcomingDateListAdapter);
		upcomingDietListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

						String str = upcommingDates.get(position).toString();
						String[] splited = str.split("\\s+");
						String dates = splited[0];
						Toast.makeText(getApplicationContext(), dates,
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(FTFLICareHomeActivity.this,
								FTFLICareDietlistBySelectedDate.class);
						intent.putExtra("selectedDate", dates);
						startActivity(intent);

					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_create_diet:
			Intent intent = new Intent(FTFLICareHomeActivity.this,
					FTFLICareCreateDietActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_edit_diet:
			Intent editDietIntent = new Intent(FTFLICareHomeActivity.this,
					FTFLICareEditDietActivity.class);
			startActivity(editDietIntent);
			return true;
		case R.id.action_edit_profile:
			Intent editprofileIntent = new Intent(FTFLICareHomeActivity.this,
					FTFLICareCreateSingleProfileActivity.class);
			startActivity(editprofileIntent);
			return true;
		case R.id.action_diet_history:
			Intent diethistoryIntent = new Intent(FTFLICareHomeActivity.this,
					FTFLICareDietHistoryActivity.class);
			startActivity(diethistoryIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
