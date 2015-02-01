package com.ftfl.myrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ftfl.myrestaurant.database.RestaurantInfoDataSource;
import com.ftfl.myrestaurant.model.RestaurantInformation;
import com.ftfl.myrestaurant.util.RestaurantConstants;


public class ViewRestaurantActiviry extends Activity {
	private TextView mNameTextView;
	private TextView mAddressTextView;
	private TextView mLatitudeTextView;
	private TextView mLongitudeTextView;
	private TextView mMenusTextView;
	private TextView mSpecialMenuTextView;
	private TextView mCloseDayTextView;
	private TextView mDailyOpenTimeTextView;
	private TextView mDescrptionTextView;
 
	private RestaurantInformation mRes_info;
	private RestaurantInfoDataSource mData_source_obj;

	private int mSelectedId;
	private String mImagePath="";
	
	private ImageView mImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_restaurant);
		initialize();

		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt(
				RestaurantConstants.SELECTED_ID);
		/* create instance of Data Source class */
		mData_source_obj = new RestaurantInfoDataSource(this);
		/*
		 * receive restrant information object by calling a method from Data
		 * Source class and sentd selected id aas parameter
		 */
		mRes_info = mData_source_obj.getRestaurantById(mSelectedId);
		mImagePath=mRes_info.getImage().toString();
		/* SHow existing data */
		setData();

		/*
		 * Toast.makeText( getApplicationContext(), String.valueOf(mSelectedId),
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void viewMap(View view) {
		Intent intent = new Intent(ViewRestaurantActiviry.this,
				ShowMapActivity.class);
		Bundle bundle = new Bundle();

		bundle.putString(RestaurantConstants.LATITUDE, mRes_info.getLatitude());
		bundle.putString(RestaurantConstants.LONGITUDE, mRes_info.getLongitude());
		bundle.putString(RestaurantConstants.RESTAURANTNAME, mRes_info.getName());
		intent.putExtras(bundle);
		startActivity(intent);

		/*
		 * getData(); RestaurantInformation res_Info_obj=new
		 * RestaurantInformation(mName, mAddress, mLatitude, mLongitude, mMenus,
		 * mSpecialMenu, mCloseDay, mDailyOpenTime, mDescription);
		 * data_source_obj.editRestaurant(mSelectedId, res_Info_obj);
		 */
	}

	public void initialize() {
		mNameTextView = (TextView) findViewById(R.id.view_textName);
		mAddressTextView = (TextView) findViewById(R.id.view_addrss_textview);
		mLatitudeTextView = (TextView) findViewById(R.id.view_latitude_textview);
		mLongitudeTextView = (TextView) findViewById(R.id.view_longitude_textview);
		mMenusTextView = (TextView) findViewById(R.id.view_menus_textview);
		mSpecialMenuTextView = (TextView) findViewById(R.id.viewspecial_menu_textview);
		mCloseDayTextView = (TextView) findViewById(R.id.view_close_day_textview);
		mDailyOpenTimeTextView = (TextView) findViewById(R.id.view_daily_open_time_textview);
		mDescrptionTextView = (TextView) findViewById(R.id.view_description_textview);
		 
		mImage = (ImageView)findViewById(R.id.ivPhotoView);
		 //scaleImage(mImage, 480);
		// buttonTextView=(Button) findViewById(R.id.buttonUpdate);
	}

	public void setData() {
		mNameTextView.setText(mRes_info.getName());
		mAddressTextView.setText(mRes_info.getAddrsss());
		mLatitudeTextView.setText(mRes_info.getLatitude());
		mLongitudeTextView.setText(mRes_info.getLongitude());
		mMenusTextView.setText(mRes_info.getMenus());
		mSpecialMenuTextView.setText(mRes_info.getSpecialMenu());
		mCloseDayTextView.setText(mRes_info.getCloseDay());
		mDailyOpenTimeTextView.setText(mRes_info.getDailyOpenTime());
		mDescrptionTextView.setText(mRes_info.getDescription());
		ViewImage();
	}
  public  void ViewImage(){
	  Bitmap bitmap = BitmapFactory.decodeFile(mImagePath);

	    if (bitmap != null) {
	       
	        mImage.setImageBitmap(bitmap);    
	       
	        } 
  }
	public void getData() {

		mNameTextView.getText().toString();
		mAddressTextView.getText().toString();
		mLatitudeTextView.getText().toString();
		mLongitudeTextView.getText().toString();
		mMenusTextView.getText().toString();
		mSpecialMenuTextView.getText().toString();
		mCloseDayTextView.getText().toString();
		mDailyOpenTimeTextView.getText().toString();
		mDescrptionTextView.getText().toString();
	}
	
	
	
	
	private void scaleImage(ImageView view, int boundBoxInDp)
	{
	    // Get the ImageView and its bitmap
	    Drawable drawing = view.getDrawable();
	    Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

	    // Get current dimensions
	    int width = bitmap.getWidth();
	    int height = bitmap.getHeight();

	    // Determine how much to scale: the dimension requiring less scaling is
	    // closer to the its side. This way the image always stays inside your
	    // bounding box AND either x/y axis touches it.
	    float xScale = ((float) boundBoxInDp) / width;
	    float yScale = ((float) boundBoxInDp) / height;
	    float scale = (xScale <= yScale) ? xScale : yScale;

	    // Create a matrix for the scaling and add the scaling data
	    Matrix matrix = new Matrix();
	    matrix.postScale(scale, scale);

	    // Create a new bitmap and convert it to a format understood by the ImageView
	    Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	    BitmapDrawable result = new BitmapDrawable(scaledBitmap);
	    width = scaledBitmap.getWidth();
	    height = scaledBitmap.getHeight();

	    // Apply the scaled bitmap
	    view.setImageDrawable(result);

	    // Now change ImageView's dimensions to match the scaled image
	    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
	    params.width = width;
	    params.height = height;
	    view.setLayoutParams(params);
	}

	private int dpToPx(int dp)
	{
	    float density = getApplicationContext().getResources().getDisplayMetrics().density;
	    return Math.round((float)dp * density);
	}

}
