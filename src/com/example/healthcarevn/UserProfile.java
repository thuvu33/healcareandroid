package com.example.healthcarevn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends ActionBarActivity {
	private EditText editTextSearchNameDoctor;
	private Button bntSearchHospital;
	private Button bntSearchFaculty;
	private Button bntSearchDoctor;
	public static final int REQUEST_CHOOSE_HOSPITAL = 111;
	public static final int REQUEST_CHOOSE_FACULTY = 112;
	public static final int RESPONSE_OK = 113;
	public static final int RESPONSE_FAIL = 114;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);

		Intent intent = getIntent();
		String username = intent.getStringExtra(MainActivity.USER_NAME);
		editTextSearchNameDoctor = (EditText) findViewById(R.id.editTextSearchNameDoctor);

		bntSearchHospital = (Button) findViewById(R.id.buttonSearchHospital);
		bntSearchHospital.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(UserProfile.this,
						ChooseHospitalActivity.class);
				startActivityForResult(intent, REQUEST_CHOOSE_HOSPITAL);
			}
		});

		bntSearchFaculty = (Button) findViewById(R.id.buttonSearchFaculty);
		bntSearchFaculty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			}
		});

		bntSearchDoctor = (Button) findViewById(R.id.buttonSearchDoctor);
		bntSearchDoctor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CHOOSE_HOSPITAL) {
			switch (resultCode) {
			case RESPONSE_OK:
				String response = data.getStringExtra("data");
				break;
			case RESPONSE_FAIL:
				response = data.getDataString();
				break;
			}
		}
	}
}