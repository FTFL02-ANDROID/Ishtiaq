package com.ishtiaq.nipa.tithi.ftflicareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ishtiaq.nipa.tithi.ftflicareapp.database.DatabaseHelperClass;
import com.ishtiaq.nipa.tithi.ftflicareapp.database.DietTableDataSource;
import com.ishtiaq.nipa.tithi.ftflicareapp.model.DietModel;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.SetDateOnClickDialog;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.setTimeOnclickDialog;

public class FTFLICareCreateDietActivity extends Activity {

	private EditText dietNameEditText;
	private EditText dateEditText;
	private EditText timeEditText;
	private EditText menuDescriptionEditText;
	private CheckBox alarmSetCheckBox;
	private String mDietName, mDietDate, mDietTime, mDay, mDietMenuDescription;
	public int mIsAlarmCecked = 0;
	private DatabaseHelperClass dbHelper;
	private int profileId = 1;
	private DietTableDataSource dietTableObject;
	private SetDateOnClickDialog datePickerObj;
	private setTimeOnclickDialog timePickerObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icare_create_diet);
		initialize();
		dbHelper = new DatabaseHelperClass(this);

	}

	public void createDiet(View view) {
		mDietName = dietNameEditText.getText().toString();

		mDietDate = dateEditText.getText().toString();

		mDietTime = timeEditText.getText().toString();

		mDietMenuDescription = menuDescriptionEditText.getText().toString();

		if (alarmSetCheckBox.isChecked()) {

			mIsAlarmCecked = 1;
		}

		DietModel dietModelObject = new DietModel(mDietName, mDietDate,
				mDietTime, mDay, mDietMenuDescription, mIsAlarmCecked);
		dietModelObject.setmProfileId(profileId);

		dietTableObject = new DietTableDataSource(getBaseContext());

		long numberOfRowAdded = dietTableObject.createDiet(dietModelObject);

		Toast.makeText(getBaseContext(), String.valueOf(numberOfRowAdded),
				Toast.LENGTH_SHORT).show();
		/*
		 *  start new Activity
		 */
		Intent intent = new Intent(FTFLICareCreateDietActivity.this,
				FTFLICareHomeActivity.class);
		startActivity(intent);
	}

	public void initialize() {

		dietNameEditText = (EditText) findViewById(R.id.dietNameEditText);

		dateEditText = (EditText) findViewById(R.id.dateEditText);
		/*
		 *  passing editText to SetDateOnClickDialog class
		 */
		/*
		 *  to show date on focus and put value into EditTExt
		 */
		datePickerObj = new SetDateOnClickDialog(dateEditText, this);

		int dayOfWeek = datePickerObj.dayOfWeek;

		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		timeEditText = (EditText) findViewById(R.id.timeEditText);
		/*
		 *  passing editText to setTimeOnclickDialog class
		 */
		/*
		 *  to show date on focus and put value into EditTExt
		 */
		timePickerObj = new setTimeOnclickDialog(timeEditText, this);

		menuDescriptionEditText = (EditText) findViewById(R.id.menuEditText);

		alarmSetCheckBox = (CheckBox) findViewById(R.id.alarmSetCheckBox);

	}

}
