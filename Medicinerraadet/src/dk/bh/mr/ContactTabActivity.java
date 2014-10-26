package dk.bh.mr;

//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
import java.util.List;

//import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.text.Html;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import dk.bh.mr.dlobj.DownloadedData;
import dk.bh.mr.json.contactmr.ContactMedicinerRaadet;
import dk.bh.mr.json.contactmr.Emails;


public class ContactTabActivity extends Activity {
	Spinner spinner_subject;
	Spinner spinner_semester;
	Button btnSubmit;
	TextView contextView;
	//SubjectMails subjectMails;
	//SemesterMails semesterMails;
	ContactMedicinerRaadet contactMails;
	TextView contentTextFelt;

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
		//subjectMails = tis.getDownloadedSubjectMails();
		//semesterMails = tis.getDownloadedSemesterMails();
		contactMails = tis.getContactMedicinerRaadet();

		contextView = (TextView) findViewById(R.id.text_body);
		contentTextFelt = (TextView) findViewById(R.id.contextTekstFelt);
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		spinner_subject = (Spinner) findViewById(R.id.subject_spinner);
		spinner_subject.setOnItemSelectedListener(new SubjectOnItemSelectedListener());
		spinner_semester = (Spinner) findViewById(R.id.semester_spinner);
		spinner_semester.setVisibility(View.INVISIBLE);
		
		String stringBuildContext = "";
		for (String _str : contactMails.getContact().getContext()) 
		{
			stringBuildContext += _str;
		}
		contentTextFelt.setText(stringBuildContext);
		
		ArrayAdapter<Emails> dataAdapter = new ArrayAdapter<Emails>(this,android.R.layout.simple_spinner_item, contactMails.getContact().getEmails());
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_subject.setAdapter(dataAdapter);
		
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

	public void addTextResetter() {
		final EditText et = (EditText) findViewById(R.id.text_body);
		et.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (et.getText().toString().equals("Skriv din besked her !"))
					et.setText("");

			}
		});
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.news_tab, menu); return true; }
	 */
	public void addItemsOnSpinnerSemester(List<Emails> result) {

		ArrayAdapter<Emails> dataAdapter = new ArrayAdapter<Emails>(this, android.R.layout.simple_spinner_item, result);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_semester.setAdapter(dataAdapter);
	}

	public class SubjectOnItemSelectedListener implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			// Toast.makeText(parent.getContext(), "OnItemSelectedListener : " +
			// parent.getItemAtPosition(pos).toString(),
			// Toast.LENGTH_SHORT).show();
			
			Emails mSelected = (Emails) parent.getItemAtPosition(pos);
						
			if (!mSelected.getSubemails().isEmpty())
			{
				spinner_semester.setVisibility(View.VISIBLE);
				addItemsOnSpinnerSemester(mSelected.getSubemails());
			}
			else 
				spinner_semester.setVisibility(View.INVISIBLE);
		}
		

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}

	}



	// get the selected dropdown list value
	public void addListenerOnButton() {
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Emails mSelected = (Emails) spinner_subject.getSelectedItem();
				if (!mSelected.getSubemails().isEmpty())
					mSelected = (Emails) spinner_semester.getSelectedItem();
				String[] array = mSelected.getMailAdrs().toArray(new String[mSelected.getMailAdrs().size()]);
				sendMail("MR-app: " + mSelected.getNickName(), contextView.getText().toString(),array );
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
