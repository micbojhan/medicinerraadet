package dk.itsmap.nissebanden.medicinerraadet;

import java.util.ArrayList;
import java.util.List;

import com.medicin.splashdownload.DownloadedData;

import dk.itsmap.nissebanden.medicinerraadet.json.SemesterMail;
import dk.itsmap.nissebanden.medicinerraadet.json.SemesterMails;
import dk.itsmap.nissebanden.medicinerraadet.json.SubjectMail;
import dk.itsmap.nissebanden.medicinerraadet.json.SubjectMails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ContactTabActivity extends Activity {
	Spinner spinner_subject;
	Spinner spinner_semester;
	Button btnSubmit;
	TextView contextView;
	SubjectMails subjectMails;
	SemesterMails semesterMails;

	// String email = "";
	// String subject = "";
	// String context = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Use the current view
		setContentView(R.layout.activity_contact_tab);

		DownloadedData tis;
		tis = (DownloadedData) getParent().getIntent().getSerializableExtra(
				"dataforTabs");
		subjectMails = tis.getDownloadedSubjectMails();
		semesterMails = tis.getDownloadedSemesterMails();

		contextView = (TextView) findViewById(R.id.text_body);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		spinner_subject = (Spinner) findViewById(R.id.subject_spinner);
		spinner_subject.setOnItemSelectedListener(new SubjectOnItemSelectedListener());
		spinner_semester = (Spinner) findViewById(R.id.semester_spinner);
		spinner_semester.setVisibility(View.INVISIBLE);
		addItemsOnSpinnerSemester(semesterMails);
		addItemsOnSpinnerSubject(subjectMails);
		addListenerOnButton();
		addTextResetter();
		/*
		 * // Create an ArrayAdapter using the string array and a default
		 * spinner // layout ArrayAdapter<CharSequence> subjectAdapter =
		 * ArrayAdapter .createFromResource(this, R.array.subject_array,
		 * android.R.layout.simple_spinner_item); // Specify the layout to use
		 * when the list of choices appears subjectAdapter
		 * .setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item); // Apply the adapter
		 * to the spinner spinner_subject.setAdapter(subjectAdapter);
		 * 
		 * 
		 * // Create an ArrayAdapter using the string array and a default
		 * spinner // layout ArrayAdapter<CharSequence> semesterAdapter =
		 * ArrayAdapter .createFromResource(this, R.array.semester_array,
		 * android.R.layout.simple_spinner_item); // Specify the layout to use
		 * when the list of choices appears semesterAdapter
		 * .setDropDownViewResource
		 * (android.R.layout.simple_spinner_dropdown_item); // Apply the adapter
		 * to the spinner spinner_semester.setAdapter(semesterAdapter);
		 */
	}
	
	public void addTextResetter(){
		final EditText et = (EditText) findViewById(R.id.text_body);
		et.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(et.getText().toString().equals("Skriv din besked her !"))
				et.setText("");
				
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_tab, menu);
		return true;
	}

	public void addItemsOnSpinnerSemester(SemesterMails result) {

		List<String> list = new ArrayList<String>();
		for (SemesterMail mail : result.getMails()) {
			list.add(mail.getNickName());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_semester.setAdapter(dataAdapter);
	}

	public void addItemsOnSpinnerSubject(SubjectMails result) {

		List<String> list = new ArrayList<String>();
		for (SubjectMail mail : result.getMails()) {
			list.add(mail.getNickName());
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_subject.setAdapter(dataAdapter);
	}

	public class SubjectOnItemSelectedListener implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			// Toast.makeText(parent.getContext(), "OnItemSelectedListener : " +
			// parent.getItemAtPosition(pos).toString(),
			// Toast.LENGTH_SHORT).show();
			for (SubjectMail sb : subjectMails.getMails()) {
				if (sb.getNickName() == parent.getItemAtPosition(pos)) {
					if (sb.getShowSemester() == true) {
						spinner_semester.setVisibility(View.VISIBLE);
						break;
					} else {
						spinner_semester.setVisibility(View.INVISIBLE);
						break;
					}
				}
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}

	}

	public void loopSubjects() {
		for (SubjectMail sb : subjectMails.getMails()) {
			if (sb.getNickName() == String.valueOf(spinner_subject.getSelectedItem())) {
				if (sb.getShowSemester() == true)
				{
					loopSemesters();
					break;
				}
					
				else {
					sendMail("MR-app: " + sb.getNickName(), contextView.getText().toString(), new String[] { sb.getMailAdr() });
					break;
				}
			}
		}
	}

	public void loopSemesters() {
		for (SemesterMail sem : semesterMails.getMails()) {
			if (sem.getNickName() == String.valueOf(spinner_semester.getSelectedItem())) {
				sendMail("MR-app: " + sem.getNickName(), contextView.getText().toString(), new String[] { sem.getMailAdr() });
				break;

			}
		}
	}

	// get the selected dropdown list value
	public void addListenerOnButton() {
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				loopSubjects();
				/*
				Toast.makeText(
						ContactTabActivity.this,
						"OnClickListener : "
								+ "\nSpinner 1 : "
								+ String.valueOf(spinner_subject
										.getSelectedItem())
								+ "\nSpinner 2 : "
								+ String.valueOf(spinner_semester
										.getSelectedItem()), Toast.LENGTH_SHORT)
						.show();
						*/
			}

		});
	}

	private boolean sendMail(String subject, String body, String[] recipients) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, recipients);
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT, body);
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(ContactTabActivity.this,
					"There are no email clients installed.", Toast.LENGTH_SHORT)
					.show();
			return true;
		}
		return true;
	}
}
