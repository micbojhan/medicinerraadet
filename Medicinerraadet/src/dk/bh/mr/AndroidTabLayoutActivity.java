package dk.bh.mr;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import dk.bh.mr.dlobj.DownloadedData;

@SuppressWarnings("deprecation")
public class AndroidTabLayoutActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tab_layout);
		DownloadedData tis = new DownloadedData();
		tis = (DownloadedData) getIntent().getSerializableExtra("dataforTabs");
		/*
		 * Log.e("BH_Log","TAB: "+tis.getDownloadedDateTime().toString());
		 * Log.e("BH_Log","TAB: "+tis.getMedicinNews());
		 * Log.e("BH_Log","TAB: "+tis.getGoogleCalender());
		 * Log.e("BH_Log","TAB: "+tis.getDownloadedSubjectMails());
		 * Log.e("BH_Log","TAB: "+tis.getDownloadedSemesterMails());
		 */

		Resources res = getResources(); // Resource object to get Drawables
		final TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, NewsTabActivity.class);
		intent.putExtra("DLdata", tis);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("news")
				.setIndicator("Nyheder", res.getDrawable(R.drawable.ic_tab_news))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, ContactTabActivity.class);
		spec = tabHost
				.newTabSpec("contact")
				.setIndicator("Kontakt", res.getDrawable(R.drawable.ic_tab_contact))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, OtherTabActivity.class);
		spec = tabHost
				.newTabSpec("other")
				.setIndicator("Om MR", res.getDrawable(R.drawable.ic_tab_other))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, CalendarTabActivity.class);
		spec = tabHost
				.newTabSpec("calendar")
				.setIndicator("Kalender",
						res.getDrawable(R.drawable.ic_tab_calendar))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(1);

	}

}
