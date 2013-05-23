package dk.itsmap.nissebanden.medicinerraadet;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class CalendarTabActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
		textview.setText("This is the Calendar tab");
		setContentView(textview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_tab, menu);
		return true;
	}
}
