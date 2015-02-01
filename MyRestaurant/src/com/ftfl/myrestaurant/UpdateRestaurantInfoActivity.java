package com.ftfl.myrestaurant;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.ftfl.myrestaurant.database.RestaurantInfoDataSource;
import com.ftfl.myrestaurant.model.RestaurantInformation;
import com.ftfl.myrestaurant.util.RestaurantConstants;


public class UpdateRestaurantInfoActivity extends Activity {
	private EditText mNameEditText;
	private EditText mAddressEditText;
	private EditText mLatitudeEditText;
	private EditText mLongitudeEditText;
	private EditText mMenusEditText;
	private EditText mSpecialMenuEditText;
	private EditText mCloseDayEditText;
	private EditText mDailyOpenTimeEditText;
	private EditText mDescrptionEditText;

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
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView mIvPhotoView = null;
	String mCurrentPhotoPath;
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
		Intent intent = new Intent(UpdateRestaurantInfoActivity.this,
				RestaurantHomeActivity.class);
		startActivity(intent);
	}

	public void initialize() {
		mNameEditText = (EditText) findViewById(R.id.name_edittext);
		mAddressEditText = (EditText) findViewById(R.id.address_edittext);
		mLatitudeEditText = (EditText) findViewById(R.id.latitude_edittext);
		mLongitudeEditText = (EditText) findViewById(R.id.longitude_edittext);
		mMenusEditText = (EditText) findViewById(R.id.menus_edittext);
		mSpecialMenuEditText = (EditText) findViewById(R.id.special_menu_edittext);
		mCloseDayEditText = (EditText) findViewById(R.id.close_day_edittext);
		mDailyOpenTimeEditText = (EditText) findViewById(R.id.daily_opentime_edittext);
		mDescrptionEditText = (EditText) findViewById(R.id.description_edittext);
		
		mIvPhotoView = (ImageView) findViewById(R.id.ivPhotoView);

		// buttonEditText=(Button) findViewById(R.id.buttonUpdate);
	}

	public void setData() {
		mNameEditText.setText(res_info.getName());
		mAddressEditText.setText(res_info.getAddrsss());
		mLatitudeEditText.setText(res_info.getLatitude());
		mLongitudeEditText.setText(res_info.getLongitude());
		mMenusEditText.setText(res_info.getMenus());
		mSpecialMenuEditText.setText(res_info.getSpecialMenu());
		mCloseDayEditText.setText(res_info.getCloseDay());
		mDailyOpenTimeEditText.setText(res_info.getDailyOpenTime());
		mDescrptionEditText.setText(res_info.getDescription());
	}

	public void getData() {

		mName = mNameEditText.getText().toString();
		mAddress = mAddressEditText.getText().toString();
		mLatitude = mLatitudeEditText.getText().toString();
		mLongitude = mLongitudeEditText.getText().toString();
		mMenus = mMenusEditText.getText().toString();
		mSpecialMenu = mSpecialMenuEditText.getText().toString();
		mCloseDay = mCloseDayEditText.getText().toString();
		mDailyOpenTime = mDailyOpenTimeEditText.getText().toString();
		mDescription = mDescrptionEditText.getText().toString();
	}
	public void dispatchTakePictureIntent(View v) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try 
			{
				photoFile = createImageFile();
			} 
			catch (IOException ex) 
			{
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
						.show();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) 
			{
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) 
		{
			setPic();
			galleryAddPic();
		}
	}
	
	/**
	 * If user wants to load photo into gallery
	 */
	private void galleryAddPic() 
	{
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    File f = new File(mCurrentPhotoPath);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    this.sendBroadcast(mediaScanIntent);
	}


	private void setPic() {
		// Get the dimensions of the View
		int targetW = mIvPhotoView.getWidth();
		int targetH = mIvPhotoView.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);// bmOptions
		mIvPhotoView.setImageBitmap(bitmap);
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		
		/**************************
		|---- You will get the image location from this variable which you will save into database
		*/
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}

}
