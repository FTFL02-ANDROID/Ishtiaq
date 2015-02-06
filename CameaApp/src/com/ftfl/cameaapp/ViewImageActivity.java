package com.ftfl.cameaapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ftfl.cameaapp.model.LocationAndImage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewImageActivity  extends Activity implements LocationListener{
 private ImageView mIvPhotoView;
private String mCurrentPhotoPath=null;
private LocationManager locationManager;
private Location location;
private double mLatitude=21.64646464;
private double mLongitude=90.546446;
private TextView mLatitudeTextView;
private TextView mLongitudeTextView;
private EditText mRemarksEditText;
private String provider;
private Criteria criteria;
private LocationAndImage locationObj;
private String mCurrentDate;
private String mCurrentTime;


@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_view_image);
    initialize();
    mCurrentPhotoPath=getIntent().getExtras().getString("path");
    
    setPic();
	 locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	// Creating a criteria object to retrieve provider
	 criteria = new Criteria();

	// Getting the name of the best provider
	 provider = locationManager.getBestProvider(criteria, true);

	// Getting Current Location
	 location = locationManager.getLastKnownLocation(provider);

	if (location != null) {
		onLocationChanged(location);
	}
	locationManager.requestLocationUpdates(provider, 20000, 0, this);
	 mLatitudeTextView.setText(String.valueOf(mLatitude));
	 mLongitudeTextView.setText(String.valueOf(mLongitude));
}


private void setPic() {
	
    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
	mIvPhotoView.setImageBitmap(bitmap);
}
@Override
public void onLocationChanged(Location location) {
	// TODO Auto-generated method stub
	
	 mLatitude = location.getLatitude();

	// Getting longitude of the current location
	 mLongitude = location.getLongitude();
	 mLatitudeTextView.setText(String.valueOf(mLatitude));
	 mLongitudeTextView.setText(String.valueOf(mLongitude));
}
@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	
}
@Override
public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	
}
@Override
public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
	
}

public void insertdData(View v){
	Toast.makeText(this, "fdd", 100).show();
	Date date = Calendar.getInstance().getTime();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
	  mCurrentDate= sdf.format(date);
	  mCurrentTime="";
	locationObj= new LocationAndImage(String.valueOf(mLatitude), String.valueOf(mLongitude), mRemarksEditText.getText().toString(),mCurrentPhotoPath, mCurrentDate,mCurrentTime);
}
public void initialize(){
	
	 mIvPhotoView=(ImageView) findViewById(R.id.ivPhotoView);
	    mLatitudeTextView=(TextView) findViewById(R.id.latitudeTextView);
	    mLongitudeTextView=(TextView) findViewById(R.id.longitudeTextView);
	    mIvPhotoView=(ImageView) findViewById(R.id.ivPhotoView);
	    mRemarksEditText=(EditText) findViewById(R.id.remarkEditText);
}
}
