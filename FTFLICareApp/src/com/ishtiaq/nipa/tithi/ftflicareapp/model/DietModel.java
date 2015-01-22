package com.ishtiaq.nipa.tithi.ftflicareapp.model;

public class DietModel {
	int mDietId,mProfileId;
	String mDietName;
	String mDietDate;
	String mDay;
	String mDietTime;
	String mDietMenu;
	int mIsAlarmChecked;

	public DietModel(String eDietName,String eDietDate ,String eDietTime,String mDay, String eDietMenu,int eIsAlarmChecked)
			 {
		super();
		
		this.mDietName = eDietName;
		this.mDietDate = eDietDate;
		this.mDietTime = eDietTime;
		this.mDay=mDay;
		this.mDietMenu = eDietMenu;
		this.mIsAlarmChecked=eIsAlarmChecked;
	
	
	}

	public int getmDietId() {
		return mDietId;
	}

	public void setmDietId(int mDietId) {
		this.mDietId = mDietId;
	}

	public int getmProfileId() {
		return mProfileId;
	}

	public void setmProfileId(int mProfileId) {
		this.mProfileId = mProfileId;
	}

	public String getmDietName() {
		return mDietName;
	}

	public String getmDietDate() {
		return mDietDate;
	}
    
	public int getmIsAlarmChecked() {
		return mIsAlarmChecked;
	}

	public String getmDay() {
		return mDay;
	}

	public String getmDietTime() {
		return mDietTime;
	}

	public String getmDietMenu() {
		return mDietMenu;
	}
	
	
	
	

}
