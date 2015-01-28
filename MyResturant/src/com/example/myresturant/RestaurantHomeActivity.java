package com.example.myresturant;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myresturant.adapter.RestaurantListAdapter;
import com.example.myresturant.database.RestaurantInfoDataSource;
import com.example.myresturant.model.RestaurantInformation;
import com.example.myresturant.util.RestaurantConstants;

public class RestaurantHomeActivity extends ActionBarActivity {
	RestaurantInfoDataSource res_data;
	ListView allResturantList;
	ArrayList<RestaurantInformation> res_List;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		res_data = new RestaurantInfoDataSource(this);
		res_List = res_data.getRestaurantList();

		RestaurantListAdapter adapter = new RestaurantListAdapter(
				RestaurantHomeActivity.this, res_List);
		allResturantList = (ListView) findViewById(R.id.restaurantList);
		allResturantList.setAdapter(adapter);
		allResturantList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					private int selectedId;
					private int pos;

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						AlertDialog.Builder alertDialog = new AlertDialog.Builder(
								RestaurantHomeActivity.this);
						// Setting Dialog Title

						final String[] menuList = { "view", "edit", "delete"

						};
						alertDialog.setTitle("Options");
						alertDialog.setIcon(R.drawable.ic_launcher);
						selectedId = res_List.get(position).getId();
						alertDialog.setItems(menuList,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int item) {
										switch (item) {
										case 0:

											Intent intent = new Intent(
													RestaurantHomeActivity.this,
													ViewRestaurantActiviry.class);
											// intent.putExtra(RestaurantConstants.SELECTED_ID,(Parcelable)
											// res_List.get(pos));
											intent.putExtra(
													RestaurantConstants.SELECTED_ID,
													selectedId);

											startActivity(intent);

											// startActivity(intent);
											/*
											 * Toast.makeText(
											 * getApplicationContext(),
											 * String.valueOf(selectedId),
											 * Toast.LENGTH_SHORT).show();
											 */
											break;
										case 1:
											// function 2 code here

											Intent secondIntent = new Intent(
													RestaurantHomeActivity.this,
													UpdateRestaurantInfoActivity.class);
											// intent.putExtra(RestaurantConstants.SELECTED_ID,(Parcelable)
											// res_List.get(pos));
											secondIntent
													.putExtra(
															RestaurantConstants.SELECTED_ID,
															selectedId);

											startActivity(secondIntent);
											break;
										case 2:
											Toast.makeText(
													getApplicationContext(),
													String.valueOf(selectedId),
													Toast.LENGTH_SHORT).show();

											AlertDialog.Builder alertDialog = new AlertDialog.Builder(
													RestaurantHomeActivity.this);

											// Setting Dialog Title
											alertDialog
													.setTitle("Do You Want to delete");

											// Setting Dialog Message
											alertDialog
													.setMessage("Are you sure you want delete this?");

											// Setting Icon to Dialog
											alertDialog
													.setIcon(R.drawable.ic_launcher);

											// Setting Positive "Yes" Button
											alertDialog
													.setPositiveButton(
															"Yes",
															new DialogInterface.OnClickListener() {
																public void onClick(
																		DialogInterface dialog,
																		int which) {

																	// Write
																	// your code
																	// here to
																	// invoke
																	// YES event
																	res_data.deleteRestaurant(selectedId);
																	finish();
																	startActivity(getIntent());
																}
															});
											// Showing Alert Message
											// Setting Negative "NO" Button
											alertDialog
													.setNegativeButton(
															"No",
															new DialogInterface.OnClickListener() {
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	Toast.makeText(
																			getApplicationContext(),
																			"You clicked on No",
																			Toast.LENGTH_SHORT)
																			.show();
																	dialog.cancel();
																}

															});
											alertDialog.show();
											break;
										}
									}
								});
						AlertDialog menuDrop = alertDialog.create();

						menuDrop.show();
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.insert_menu) {
			Intent intent = new Intent(RestaurantHomeActivity.this,
					InsertRestaurantInformationActivity.class);
			startActivity(intent);
			return true;
		}
		if (id == R.id.home_menu) {
			Intent intent = new Intent(this,
					RestaurantHomeActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
