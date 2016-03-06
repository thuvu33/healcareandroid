package com.example.healthcarevn;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseHospitalActivity extends ActionBarActivity {
	MyCustomAdapter dataAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_choose_hospital);
		displayListView();
		checkButtonClick();
	}

	private void displayListView() {

		// Array list of countries
		ArrayList<Hospital> countryList = new ArrayList<Hospital>();
		Hospital country = new Hospital("AFG", "Afghanistan", false);
		countryList.add(country);
		country = new Hospital("ALB", "Albania", true);
		countryList.add(country);
		country = new Hospital("DZA", "Algeria", false);
		countryList.add(country);
		country = new Hospital("ASM", "American Samoa", true);
		countryList.add(country);
		country = new Hospital("AND", "Andorra", true);
		countryList.add(country);
		country = new Hospital("AGO", "Angola", false);
		countryList.add(country);
		country = new Hospital("AIA", "Anguilla", false);
		countryList.add(country);

		// create an ArrayAdaptar from the String Array
		dataAdapter = new MyCustomAdapter(this, R.layout.hospital_info,
				countryList);
		ListView listView = (ListView) findViewById(R.id.listViewChooseHospital);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * Hospital country = (Hospital) parent
				 * .getItemAtPosition(position);
				 * Toast.makeText(getApplicationContext(), "Clicked on Row: " +
				 * country.getName(), Toast.LENGTH_LONG).show();
				 */
			}
		});

	}

	private void checkButtonClick() {

		Button myButton = (Button) findViewById(R.id.buttonChooseHospital);
		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				StringBuffer responseText = new StringBuffer();
				ArrayList<Hospital> countryList = dataAdapter.countryList;
				for (int i = 0; i < countryList.size(); i++) {
					Hospital country = countryList.get(i);
					if (country.isSelected()) {
						responseText.append("\n" + country.getName());
					}
				}
				Intent intent = getIntent();
				intent.putExtra("data", responseText.toString());
				setResult(113, intent);
				finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_hospital, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/* Class */
	private class MyCustomAdapter extends ArrayAdapter<Hospital> {

		private ArrayList<Hospital> countryList;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Hospital> countryList) {
			super(context, textViewResourceId, countryList);
			this.countryList = new ArrayList<Hospital>();
			this.countryList.addAll(countryList);
		}

		private class ViewHolder {
			TextView code;
			CheckBox name;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.hospital_info, null);

				holder = new ViewHolder();
				holder.code = (TextView) convertView.findViewById(R.id.code);
				holder.name = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(holder);

				holder.name.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Hospital country = (Hospital) cb.getTag();
						country.setSelected(cb.isChecked());
					}
				});
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Hospital country = countryList.get(position);
			holder.code.setText(" (" + country.getCode() + ")");
			holder.name.setText(country.getName());
			holder.name.setChecked(country.isSelected());
			holder.name.setTag(country);

			return convertView;

		}

	}

}
