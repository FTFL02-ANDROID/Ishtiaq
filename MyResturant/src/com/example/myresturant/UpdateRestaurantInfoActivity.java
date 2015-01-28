package com.example.myresturant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myresturant.database.RestaurantInfoDataSource;
import com.example.myresturant.model.RestaurantInformation;
import com.example.myresturant.util.RestaurantConstants;

public class UpdateRestaurantInfoActivity extends Activity {
	private EditText nameEditText;
	private EditText addressEditText;
	private EditText latitudeEditText;
	private EditText longitudeEditText;
	private EditText menusEditText;
	private EditText specialMenuEditText;
	private EditText closeDayEditText;
	private EditText dailyOpenTimeEditText;
	private EditText descrptionEditText;

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
	private Button buttonEditText;
	private RestaurantInformation res_info;
	private RestaurantInfoDataSource data_source_obj;

	private int mSelectedId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_update_restaurant_info);
		initialize();

		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt(
				RestaurantConstants.SELECTED_ID);
		/* create instance of Data Source class */
		data_source_obj = new RestaurantInfoDataSource(this);
		/*
		 * receive restrant information object by calling a method from Data
		 * Source class and sentd selected id aas parameter
		 */
		res_info = data_source_obj.getRestaurantById(mSelectedId);
		/* SHow existing data */
		setData();

		Toast.makeText(getApplicationContext(), String.valueOf(mSelectedId),
				Toast.LENGTH_SHORT).show();
	}

	public void updateButtonClicked(View view) {
		getData();
		RestaurantInformation res_Info_obj = new RestaurantInformation(mName,
				mAddress, mLatitude, mLongitude, mMenus, mSpecialMenu,
				mCloseDay, mDailyOpenTime, mDescription);
		data_source_obj.editRestaurant(mSelectedId, res_Info_obj);
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
		// buttonEditText=(Button) findViewById(R.id.buttonUpdate);
	}

	public void setData() {
		nameEditText.setText(res_info.getName());
		addressEditText.setText(res_info.getAddrsss());
		latitudeEditText.setText(res_info.getLatitude());
		longitudeEditText.setText(res_info.getLongitude());
		menusEditText.setText(res_info.getMenus());
		specialMenuEditText.setText(res_info.getSpecialMenu());
		closeDayEditText.setText(res_info.getCloseDay());
		dailyOpenTimeEditText.setText(res_info.getDailyOpenTime());
		descrptionEditText.setText(res_info.getDescription());
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
