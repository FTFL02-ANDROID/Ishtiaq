package com.ishtiaq.nipa.tithi.ftflicareapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.google.gson.Gson;
import com.ishtiaq.nipa.tithi.ftflicareapp.model.ProfileModel;
import com.ishtiaq.nipa.tithi.ftflicareapp.util.FTFLICareConstants;

public class FTFLICareCreateSingleProfileActivity extends Activity {

	private EditText mName, mFatherName, mMotherName, mDOB, mWeight, mHeight,
			mEyeColor, mBirthPlace, mSpecialComment;

	private RadioGroup radioSexGroup;
	private RadioButton radioSexButton;

	SharedPreferences sharedPref;
	Gson json;
	Editor editor;
	Button submitButton, deleteButton;
	TextView formTitle;
	private RadioButton female;
	private RadioButton male;
	String profileString;
	ProfileModel profileObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_profile);

		initialize();
		sharedPref = getSharedPreferences(FTFLICareConstants.MyPREFERENCES,
				Context.MODE_PRIVATE);
		json = new Gson();

		if (sharedPref.contains(FTFLICareConstants.PREF)) {
			submitButton.setText("Edit");
			formTitle.setText("Edit Profile");
			String getProfile = sharedPref.getString(FTFLICareConstants.PREF,
					"");
			profileObject = json.fromJson(getProfile, ProfileModel.class);
			SetSharedPreferenceData();

		}

	}

	public void createProfile(View view) {

		radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		
		int selectedId = radioSexGroup.getCheckedRadioButtonId();
		
		radioSexButton = (RadioButton) findViewById(selectedId);
		
		String gender = radioSexButton.getText().toString();
		
		ProfileModel profile_model_obj = new ProfileModel(mName.getText()
				.toString(), mFatherName.getText().toString(), mMotherName
				.getText().toString(), gender, mDOB.getText().toString(),
				mBirthPlace.getText().toString(), mWeight.getText().toString(),
				mHeight.getText().toString(), mEyeColor.getText().toString(),
				mSpecialComment.getText().toString());
		
		profile_model_obj.setId(1);

		String profileString = json.toJson(profile_model_obj);
		
		editor = sharedPref.edit();
		
		editor.putString(FTFLICareConstants.PREF, profileString);
		
		editor.commit();
		
		finish();
		
		startActivity(getIntent());

	}

	// View Profile Using SharedPreference
	public void SetSharedPreferenceData() {
		mName.setText(profileObject.getmName().toString());
		
		mFatherName.setText(profileObject.getmFatherName().toString());
		
		mMotherName.setText(profileObject.getmMotherName().toString());
		
		mDOB.setText(profileObject.getmDOB().toString());
		
		mBirthPlace.setText(profileObject.getmBirthPlace().toString());
		
		mWeight.setText(profileObject.getWeight().toString());
		
		mHeight.setText(profileObject.getHeight().toString());
		
		mEyeColor.setText(profileObject.getmEyeColor().toString());
		
		mSpecialComment.setText(profileObject.getmSpecialComment().toString());

		if (profileObject.getGender().equals("Male")) {
			male.setChecked(true);
		} else {

			female.setChecked(true);
		}
	}

	
	public void deleteProfile(View view) {
		sharedPref.edit().remove(FTFLICareConstants.PREF).commit();
		finish();
		startActivity(getIntent());

	}
    
	public void initialize() {

		mName = (EditText) findViewById(R.id.editName);
		
		mFatherName = (EditText) findViewById(R.id.editFatherName);
		
		mMotherName = (EditText) findViewById(R.id.editMotherName);
		
		mDOB = (EditText) findViewById(R.id.editDOB);
		
		mBirthPlace = (EditText) findViewById(R.id.editBirthPlace);
		
		mWeight = (EditText) findViewById(R.id.editWeight);
		
		mHeight = (EditText) findViewById(R.id.editHeight);
		
		mEyeColor = (EditText) findViewById(R.id.editEyeColor);
		
		mSpecialComment = (EditText) findViewById(R.id.editSpecialComment);
		
		submitButton = (Button) findViewById(R.id.buttonSubmit);
		
		formTitle = (TextView) findViewById(R.id.textCreateProfileHeader);
		
		male = (RadioButton) findViewById(R.id.radioButtonMale);
		
		female = (RadioButton) findViewById(R.id.radioButtonFemale);

	}

}
