package dk.bh.mr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import dk.bh.mr.DownloadService.LocalBinder;
import dk.bh.mr.dlobj.DownloadedData;

public class SplashDownloadActivity extends Activity {
	DownloadService downloadService;
	boolean mBound = false;
	DownloadedData data_downloaded;
	ProgressBar pb_h;
	TextView text_download;
	

	// ProgressBar pb_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Hides the titlebar
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash_download);

		pb_h = (ProgressBar) findViewById(R.id.progressBar_downloading_bar);
		pb_h.setMax(4);
		text_download = (TextView) findViewById(R.id.textView_downloading);
		// pb_spinner = (ProgressBar)
		// findViewById(R.id.progressBar_downloading_spinner);
	}

	public void startDownloadService() {
		Intent intent = new Intent(this, DownloadService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStart() {
		super.onStart();

		data_downloaded = load("object123");
		if (data_downloaded == null) {
			Log.e("BH_Log", "Der er ikke noget gammelt indhold");
			startDownloadService();
		} else {
			Log.e("BH_Log",
					"Der er gammelt indhold tjekker datoen på indholdet");
			Date currentDateTime = new Date();
			long diff = (currentDateTime.getTime() - data_downloaded
					.getDownloadedDateTime().getTime());
			Log.e("BH_Log", "indholdet er " + (double) diff / 60 / 60 / 1000
					+ " timer gammel");
			int timeGammel = 0; // 3 timer
			if (diff > timeGammel * 60 * 60 * 1000) {
				Log.e("BH_Log",
						"Indholdet er for gammelt, Downloader noget nyt");
				startDownloadService();
			} else {
				Log.e("BH_Log", "Indholdet er i en passende alder");
				switchScreen();
			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Unbind from the service
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}

	/** Defines callbacks for service binding, passed to bindService() **/
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// We've bound to LocalService, cast the IBinder and get
			// LocalService instance
			LocalBinder binder = (LocalBinder) service;
			downloadService = binder.getService();
			mBound = true;
			Log.e("BH_Log", "Service: KLAR");
			startService();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};

	public void switchScreen() {
		Intent intent = new Intent(SplashDownloadActivity.this,
				AndroidTabLayoutActivity.class);
		/*
		 * Log.e("BH_Log", "switchScreen Check data:" + data_downloaded);
		 * Log.e("BH_Log",
		 * "switchScreen DateTime"+data_downloaded.getDownloadedDateTime());
		 * Log.e("BH_Log",
		 * "switchScreen Kalender"+data_downloaded.getGoogleCalender());
		 * Log.e("BH_Log",
		 * "switchScreen News"+data_downloaded.getMedicinNews());
		 * Log.e("BH_Log",
		 * "switchScreen SemsterMails"+data_downloaded.getDownloadedSemesterMails
		 * ()); Log.e("BH_Log",
		 * "switchScreen SubjectMails"+data_downloaded.getDownloadedSubjectMails
		 * ());
		 */
		intent.putExtra("dataforTabs", data_downloaded);
		startActivity(intent);
		finish();
	}

	public void startService() {
		if (mBound) {
			downloadService.startDownloadGoogleCalendarData();
			Log.e("BH_Log", "StartDownload: YES");
		} else
			Log.e("BH_Log", "StartDownload: NO");
	}

	/*
	 * 
	 * public void checkDataObj() {
	 * 
	 * Log.d("E", "getExternalStorageState() = " +
	 * Environment.getExternalStorageState()); // Log.d("",
	 * "getExternalCacheDir() = " + c.getExternalCacheDir()); // Log.d("",
	 * "getExternalFilesDir(null) = " + c.getExternalFilesDir(null)); //
	 * Log.d("", "getExternalFilesDir(Environment.DIRECTORY_MOVIES) = " +
	 * c.getExternalFilesDir(Environment.DIRECTORY_MOVIES));
	 * 
	 * File cacheDir;
	 * if(android.os.Environment.getExternalStorageState().equals(
	 * android.os.Environment.MEDIA_MOUNTED)) cacheDir=new
	 * File(android.os.Environment
	 * .getExternalStorageDirectory(),"medicinAppData"); else cacheDir=
	 * getCacheDir(); if(!cacheDir.exists()) cacheDir.mkdirs();
	 */
	/*
	 * DownloadedData m = new DownloadedData(); m.setDownloadedDateTime(new
	 * Date()); boolean result = save("object123",m);
	 * 
	 * if(result) Toast.makeText(this, "Saved object",
	 * Toast.LENGTH_LONG).show(); else Toast.makeText(this,
	 * "Error saving object", Toast.LENGTH_LONG).show();
	 * 
	 * DownloadedData c = load("object123");
	 * 
	 * if(c!= null) Toast.makeText(this,
	 * "Retrieved object"+c.getDownloadedDateTime().toString(),
	 * Toast.LENGTH_LONG).show(); else Toast.makeText(this,
	 * "Error retrieving object", Toast.LENGTH_LONG).show();
	 * 
	 * }
	 */

	public synchronized boolean save(String fileName, DownloadedData objToSave) {
		try {
			Log.e("BH_Log", "SaveLoadObject: Saving object");
			// save to file
			File file = new File(getApplicationContext().getDir("filesdir",
					Context.MODE_PRIVATE)
					+ "/file.file");
			if (file.exists()) {
				Log.e("BH_Log",
						"SaveLoadObject: Object allready excist, delete old object");
				file.delete();
			}

			file.getParentFile().mkdirs();
			file.createNewFile();

			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file));
			oos.writeObject(objToSave);
			oos.close();

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public synchronized DownloadedData load(String fileName) {
		try {
			Log.e("BH_Log", "SaveLoadObject: Loading object");
			File file = new File(getApplicationContext().getDir("filesdir",
					Context.MODE_PRIVATE)
					+ "/file.file");
			if (!file.exists()) {
				Log.e("BH_Log", "SaveLoadObject: Object do not excist... Fail");
				return null;
			}

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			DownloadedData savedObj = (DownloadedData) ois.readObject();
			ois.close();

			return savedObj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// handler for received Intents for the "my-event" event
	private BroadcastReceiver br_proces = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String tekst = intent.getStringExtra("proces_tekst");
			int procent = intent.getIntExtra("proces_procent", 0);

			pb_h.setProgress(procent);
			text_download.setText(tekst);
		}
	};
	// handler for received Intents for the "my-event" event
	private BroadcastReceiver br_allDone = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			DownloadedData tempData = (DownloadedData) intent
					.getSerializableExtra("dataAllDone");
			if (tempData != null) {
				data_downloaded = tempData;
				data_downloaded.setDownloadedDateTime(new Date());
				boolean result = save("object123", data_downloaded);

				if (result)
					Log.e("BH_Log", "Saved object");
				else
					Log.e("BH_Log", "Error saving object");
				switchScreen();
			}
		}
	};
	// handler for received Intents for the "my-event" event
	private BroadcastReceiver br_Error = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.e("BH_Log", "Der kunne ikke downloades nyt indhold");
			if (data_downloaded != null) {
				Log.e("BH_Log", "Bruger det gamle indhold til appen");
				toast1();
				switchScreen();
			} else {
				Log.e("BH_Log", "Der er intet indhold til appen");
				// pb_spinner.setVisibility(View.INVISIBLE);
				text_download
						.setText("Kunne ikke hente indhold \n tjek internetadgang");
				toast2();
				// android.os.Process.killProcess(android.os.Process.myPid());
			}
		}
	};

	public void toast1() {
		Toast.makeText(this, "Kunne ikke hentet nyt data, bruger gammel data",
				Toast.LENGTH_LONG).show();
	}

	public void toast2() {
		Toast.makeText(this, "Kunne ikke hente data, tjek internetadgang",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onResume() {
		super.onResume();
		// Register mMessageReceiver to receive messages.
		LocalBroadcastManager.getInstance(this).registerReceiver(br_allDone,
				new IntentFilter("AllDone"));
		LocalBroadcastManager.getInstance(this).registerReceiver(br_Error,
				new IntentFilter("downloadError"));
		LocalBroadcastManager.getInstance(this).registerReceiver(br_proces,
				new IntentFilter("procestask"));
	}

	@Override
	protected void onPause() {
		// Unregister since the activity is not visible
		LocalBroadcastManager.getInstance(this).unregisterReceiver(br_allDone);
		LocalBroadcastManager.getInstance(this).unregisterReceiver(br_Error);
		LocalBroadcastManager.getInstance(this).unregisterReceiver(br_proces);
		super.onPause();
	}
}
