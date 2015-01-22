package com.ishtiaq.nipa.tithi.ftflicareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ishtiaq.nipa.tithi.ftflicareapp.database.DietTableDataSource;
import com.ishtiaq.nipa.tithi.ftflicareapp.model.DietModel;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.FTFLICareConstants;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.SetDateOnClickDialog;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.setTimeOnclickDialog;

public class FTFLICareEditDietActivity extends Activity {
	private EditText dietNameEditText;
	private EditText dateEditText;
	private EditText timeEditText;
	private EditText menuDescriptionEditText;
	private CheckBox alarmSetCheckBox;
	private String mDietName, mDietDate, mDietTime, mDay, mDietMenuDescription;
	private int profileId = 1;
	private int mIsAlarmCecked = 0;
	private DietTableDataSource dietTableObject;
	private DietModel selectedDiet;
	private int selectedId;
	private SetDateOnClickDialog datePickerObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icare_edit_diet);
		// get selected id by click
		selectedId = getIntent().getExtras().getInt(
				FTFLICareConstants.SELECTED_ID);
		initialize();
		setDateToview();
	}

	public void editDiet(View view) {
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
		// call function from data source and pass selected id and diet object
		// to Edit

		long notificationOnEdit = dietTableObject.editDiet(selectedId,
				dietModelObject);

		// Toast.makeText(getBaseContext(), String.valueOf(notificationOnEdit),
		// Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(FTFLICareEditDietActivity.this,
				FTFLICareHomeActivity.class);
		startActivity(intent);
	}

	public void initialize() {
		dietTableObject = new DietTableDataSource(this);
		// fetch value the selected diet
		selectedDiet = dietTableObject.getDietById(selectedId);

		dietNameEditText = (EditText) findViewById(R.id.dietNameEditText);

		dateEditText = (EditText) findViewById(R.id.dateEditText);
		/*
		 * passing editText to SetDateOnClickDialog class
		 */
		/*
		 * to show date on focus and put value into EditTExt
		 */
		datePickerObj = new SetDateOnClickDialog(dateEditText, this);

		int dayOfWeek = datePickerObj.dayOfWeek;

		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		timeEditText = (EditText) findViewById(R.id.timeEditText);

		setTimeOnclickDialog timePickerObj = new setTimeOnclickDialog(
				timeEditText, this);

		menuDescriptionEditText = (EditText) findViewById(R.id.menuEditText);

		alarmSetCheckBox = (CheckBox) findViewById(R.id.alarmSetCheckBox);

	}

	public void setDateToview() {

		dietNameEditText.setText(selectedDiet.getmDietName().toString());

		menuDescriptionEditText.setText(selectedDiet.getmDietMenu().toString());

		dateEditText.setText(selectedDiet.getmDietDate().toString());

		timeEditText.setText(selectedDiet.getmDietTime().toString());

		if (selectedDiet.getmIsAlarmChecked() == 1) {

			alarmSetCheckBox.setChecked(true);

		} else {

			alarmSetCheckBox.setChecked(false);
		}

	}
}
