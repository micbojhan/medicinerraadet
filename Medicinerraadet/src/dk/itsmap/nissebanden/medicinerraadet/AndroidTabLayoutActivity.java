package dk.itsmap.nissebanden.medicinerraadet;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class AndroidTabLayoutActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_android_tab_layout);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, NewsTabActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("news").setIndicator("Nyheder",
	                      res.getDrawable(R.drawable.ic_tab_news))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, ContactTabActivity.class);
	    spec = tabHost.newTabSpec("contact").setIndicator("Kontakt",
	                      res.getDrawable(R.drawable.ic_tab_contact))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, OtherTabActivity.class);
	    spec = tabHost.newTabSpec("other").setIndicator("Andet",
	                      res.getDrawable(R.drawable.ic_tab_other))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(1);
	}
}
