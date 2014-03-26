package dk.bh.mr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class OtherTabActivity extends Activity {
	TextView button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_other_tab);

		addButton();

	}

	private void addButton() {
		button = (TextView) findViewById(R.id.se_udvalg);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setContentView(R.layout.activity_other_tab_extra);

			}
		});
	}

	@Override
	public void onBackPressed() {
		setContentView(R.layout.activity_other_tab);
		addButton();
	}

	@Override
	protected void onPause() {
		setContentView(R.layout.activity_other_tab);
		addButton();
		super.onPause();
	}
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.news_tab, menu); return true; }
	 */
}
