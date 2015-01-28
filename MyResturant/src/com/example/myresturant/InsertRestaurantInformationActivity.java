package com.example.myresturant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myresturant.database.RestaurantInfoDataSource;
import com.example.myresturant.model.RestaurantInformation;

public class InsertRestaurantInformationActivity extends Activity {

	private EditText nameEditText;
	private EditText addressEditText;
	private EditText latitudeEditText;
	private EditText longitudeEditText;
	private EditText menusEditText;
	private EditText specialMenuEditText;
	private EditText closeDayEditText;
	private EditText dailyOpenTimeEditText;
	private EditText descrptionEditText;
	private Button buttonEditText;

	private String mName = "";
	private int mId;
	private String mAddress = "";
	private String mLatitude = "";
	private String mLongitude = "";
	private String mMenus = "";
	private String mSpecialMenu = "";
	private String mCloseDay = "";
	private String mDailyOpenTime = "";
	private String mDescription = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_restaurant_information);
		initialize();
	}

	public void insertButtonClicked(View view) {
		getData();

		RestaurantInformation res_Info_obj = new RestaurantInformation(mName,
				mAddress, mLatitude, mLongitude, mMenus, mSpecialMenu,
				mCloseDay, mDailyOpenTime, mDescription);
		RestaurantInfoDataSource res_data_source_obj = new RestaurantInfoDataSource(
				this);
		res_data_source_obj.insertRestaurantInfo(res_Info_obj);
		Intent intent = new Intent(InsertRestaurantInformationActivity.this,
				RestaurantHomeActivity.class);
		startActivity(intent);

	}

	public void initialize() {
		nameEditText = (EditText) findViewById(R.id.name_edittext);
		addressEditText = (EditText) findViewById(R.id.address_edittext);
		latitudeEditText = (EditText) findViewById(R.id.latitude_edittext);
		longitudeEditText = (EditText) findViewById(R.id.longitude_edittext);
		menusEditText = (EditText) findViewById(R.id.menus_edittext);
		specialMenuEditText = (EditText) findViewById(R.id.special_menu_edittext);
		closeDayEditText = (EditText) findViewById(R.id.close_day_edittext);
		dailyOpenTimeEditText = (EditText) findViewById(R.id.daily_opentime_edittext);
		descrptionEditText = (EditText) findViewById(R.id.description_edittext);
		buttonEditText = (Button) findViewById(R.id.buttonSubmit);
	}

	public void getData() {

		mName = nameEditText.getText().toString();
		mAddress = addressEditText.getText().toString();
		mLatitude = latitudeEditText.getText().toString();
		mLongitude = longitudeEditText.getText().toString();
		mMenus = menusEditText.getText().toString();
		mSpecialMenu = specialMenuEditText.getText().toString();
		mCloseDay = closeDayEditText.getText().toString();
		mDailyOpenTime = dailyOpenTimeEditText.getText().toString();
		mDescription = descrptionEditText.getText().toString();
	}

}
