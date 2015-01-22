package com.ishtiaq.nipa.tithi.ftflicareapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ishtiaq.nipa.tithi.ftflicareapp.database.DietTableDataSource;
import com.ishtiaq.nipa.tithi.ftflicareapp.model.DietModel;

public class FTFLICareDietHistoryActivity extends Activity {
	/*
	 * private RadioGroup dietHistoryRadioGroup; private RadioButton
	 * radioSelectedButton; private RadioButton all; private RadioButton
	 * monthly; private RadioButton daily; private int isRadioChecked=0;
	 */
	private ArrayList<DietModel> allDiet;
	private ArrayList<String> allDateAndDay;
	private DietTableDataSource dietTableObject;
	private ListView dietHistoryList;
	private ArrayAdapter historyListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet_history);
		initialize();
		allDiet = dietTableObject.getAllDietHistoy();
		for (int i = 0; i < allDiet.size(); i++) {

			allDateAndDay.add(allDiet.get(i).getmDietDate() + " "
					+ allDiet.get(i).getmDay());
		}
		historyListAdapter = new ArrayAdapter<String>(this, R.layout.row,
				R.id.label, allDateAndDay);
		dietHistoryList = (ListView) findViewById(R.id.selectedDietList);
		dietHistoryList.setAdapter(historyListAdapter);
		dietHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				String str = allDateAndDay.get(position).toString();
				String[] splited = str.split("\\s+");
				String dates = splited[0];
				Toast.makeText(getApplicationContext(), dates,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(FTFLICareDietHistoryActivity.this,
						FTFLICareDietlistBySelectedDate.class);
				intent.putExtra("selectedDate", dates);
				startActivity(intent);

			}
		});

		/*
		 * radioSelectedButton.setOnCheckedChangeListener(new
		 * OnCheckedChangeListener() {
		 * 
		 * @Override public void onCheckedChanged(CompoundButton buttonView,
		 * boolean isChecked) { // TODO Auto-generated method stub
		 * 
		 * } });
		 */

	}

	public void initialize() {
		/*
		 * dietHistoryRadioGroup=(RadioGroup) findViewById(R.id.radioGroup1);
		 * int selectedId = dietHistoryRadioGroup.getCheckedRadioButtonId();
		 * radioSelectedButton=(RadioButton) findViewById(selectedId);
		 */
		allDateAndDay = new ArrayList<String>();
		dietTableObject = new DietTableDataSource(this);
	}
}
