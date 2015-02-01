package com.ftfl.myrestaurant.model;

public class RestaurantInformation {

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
	private String mImagePath = "";

	public RestaurantInformation(String eName, String eAddress,
			String eLatitude, String eLongitude, String eMenus,
			String eSpecialMenu, String eCloseDay, String eDailyOpenTime,
			String eDescription) {
		super();
		this.mName = eName;
		this.mAddress = eAddress;
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mMenus = eMenus;
		this.mSpecialMenu = eSpecialMenu;
		this.mCloseDay = eCloseDay;
		this.mDailyOpenTime = eDailyOpenTime;
		this.mDescription = eDescription;
	}

	public int getId() {
		return mId;
	}

	public void setId(int eId) {
		this.mId = eId;
	}

	public String getName() {
		return mName;
	}

	public String getAddrsss() {
		return mAddress;
	}

	public String getLatitude() {
		return mLatitude;
	}

	public String getLongitude() {
		return mLongitude;
	}

	public String getMenus() {
		return mMenus;
	}

	public String getSpecialMenu() {
		return mSpecialMenu;
	}

	public String getCloseDay() {
		return mCloseDay;
	}

	public String getDailyOpenTime() {
		return mDailyOpenTime;
	}

	public String getDescription() {
		return mDescription;
	}
	
	public void setImage(String eImagePath) {
		this.mImagePath=eImagePath;
	}

	public String getImage() {
		return mImagePath;
	}

}
