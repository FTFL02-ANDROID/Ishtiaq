package com.ishtiaq.nipa.tithi.ftflicareapp.model;

public class ProfileModel {
	private int id;
	private String mName, mFatherName, mMotherName, gender, mDOB, mBirthPlace,
			weight, height, mEyeColor, mSpecialComment;

	public ProfileModel(String mName, String mFatherName, String mMotherName,
			String gender, String mDOB, String mBirthPlace, String weight,
			String height, String mEyeColor, String mSpecialComment) {
		super();

		this.mName = mName;
		this.mFatherName = mFatherName;
		this.mMotherName = mMotherName;
		this.gender = gender;
		this.mDOB = mDOB;
		this.mBirthPlace = mBirthPlace;
		this.weight = weight;
		this.height = height;
		this.mEyeColor = mEyeColor;
		this.mSpecialComment = mSpecialComment;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getmName() {
		return mName;
	}

	public String getmFatherName() {
		return mFatherName;
	}

	public String getmMotherName() {
		return mMotherName;
	}

	public String getGender() {
		return gender;
	}

	public String getmDOB() {
		return mDOB;
	}

	public String getmBirthPlace() {
		return mBirthPlace;
	}

	public String getHeight() {
		return height;
	}

	public String getmEyeColor() {
		return mEyeColor;
	}

	public String getmSpecialComment() {
		return mSpecialComment;
	}

}
