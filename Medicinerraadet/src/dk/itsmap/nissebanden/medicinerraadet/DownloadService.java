package dk.itsmap.nissebanden.medicinerraadet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.temaprojekt1.MedicinNews.MedicinNews;
import com.example.temaprojekt4.GoogleCalendar.GC_GoogleCalendar;
import com.google.gson.Gson;
import com.medicin.splashdownload.DownloadedData;

import dk.itsmap.nissebanden.medicinerraadet.json.SemesterMails;
import dk.itsmap.nissebanden.medicinerraadet.json.SubjectMails;

public class DownloadService extends Service {
	private DownloadedData downloadedData = new DownloadedData();
	private final IBinder mBinder = new LocalBinder();

	public class LocalBinder extends Binder {
		public DownloadService getService() {
			return DownloadService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public InputStream retrieveStream(String url) {
		HttpClient _defaultHttpClient = new DefaultHttpClient();
		HttpGet _httpGet = new HttpGet(url);
		try {
			HttpResponse _httpResponse = _defaultHttpClient.execute(_httpGet);
			final int _statusCode = _httpResponse.getStatusLine().getStatusCode();
			if (_statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + _statusCode + " for URL " + url);
				sendMessageToSpalshScreen_downloadError();
				return null;
			}
			HttpEntity _httpEntity = _httpResponse.getEntity();
			
				//Log.w("LkOL  ", EntityUtils.toString( _httpEntity, HTTP.UTF_8 ) + "\n\n\n");
			return _httpEntity.getContent();
		} catch (IOException e) {
			_httpGet.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		sendMessageToSpalshScreen_downloadError();
		return null;
	}

	private void sendMessageToSpalshScreen_downloadError() {
		Intent i = new Intent("downloadError");
		LocalBroadcastManager.getInstance(this).sendBroadcast(i);
	}

	private void sendMessageToSpalshScreen_AllDone() {
		Intent i = new Intent("AllDone");
		/*
		 * Log.e("BH_Log1", "switchScreen Check data:" + downloadedData);
		 * Log.e("BH_Log1",
		 * "switchScreen DateTime"+downloadedData.getDownloadedDateTime());
		 * Log.e("BH_Log1",
		 * "switchScreen Kalender"+downloadedData.getGoogleCalender());
		 * Log.e("BH_Log1",
		 * "switchScreen News"+downloadedData.getMedicinNews());
		 * Log.e("BH_Log1",
		 * "switchScreen SemsterMails"+downloadedData.getDownloadedSemesterMails
		 * ()); Log.e("BH_Log1",
		 * "switchScreen SubjectMails"+downloadedData.getDownloadedSubjectMails
		 * ());
		 */
		i.putExtra("dataAllDone", downloadedData);
		LocalBroadcastManager.getInstance(this).sendBroadcast(i);
	}

	private void sendMessageToSpalshScreen_proces(int procent, String tekst) {
		Intent i = new Intent("procestask");
		i.putExtra("proces_procent", procent);
		i.putExtra("proces_tekst", tekst);
		LocalBroadcastManager.getInstance(this).sendBroadcast(i);
	}

	public void startDownloadGoogleCalendarData() {
		new DownloadTaskGoogleCalendar()
				.execute("http://www.google.com/calendar/feeds/studmedsam@gmail.com/public/full?alt=json&orderby=starttime&sortorder=ascending&futureevents=true&singleevents=true");
	}

	public void startDownloadMedicinNews() {
		new DownloadTaskMedicinNews()
				.execute("https://www.facebook.com/feeds/page.php?id=278417128835721&format=json");
	}

	public void startDownload_mails_json_semesters() {
		new DownloadTaskSemesterMails()
				.execute("http://www.bojsen-hansen.dk/medicinerraadet/mails_json_semesters.txt");
	}

	public void startDownload_mailsjson() {
		new DownloadTaskSubjectMails()
				.execute("http://www.bojsen-hansen.dk/medicinerraadet/mailsjson.txt");
	}

	private class DownloadTaskGoogleCalendar extends
			AsyncTask<String, Integer, GC_GoogleCalendar> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			sendMessageToSpalshScreen_proces(0, "Henter kalender");
			Log.e("AsyncTask: ", "onPreExecute: GC_GoogleCalendar");
		}

		@Override
		protected GC_GoogleCalendar doInBackground(String... f_url) {
			String url = f_url[0];
			InputStream source = retrieveStream(url);
			Gson gson = new Gson();
			if (source == null)
				return null;
			Reader reader = null;
			try {
				reader = new InputStreamReader(source, HTTP.UTF_8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (reader != null)
			return gson.fromJson(reader,GC_GoogleCalendar.class);
			return null;
		}

		@Override
		protected void onPostExecute(GC_GoogleCalendar result) {
			super.onPostExecute(result);
			if (result != null) {
				Log.e("AsyncTask: ", "onPostExecute: GC_GoogleCalendar");
				downloadedData.setGoogleCalende(result);
				// dismissProgressBar();
				startDownloadMedicinNews();

				sendMessageToSpalshScreen_proces(1, "Kalender hentet");

			}
		}
	}

	private class DownloadTaskMedicinNews extends
			AsyncTask<String, Integer, MedicinNews> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.e("HER_AsyncTask: ", "onPreExecute");
			sendMessageToSpalshScreen_proces(1, "Henter nyheder");
		}

		@Override
		protected MedicinNews doInBackground(String... f_url) {
			String url = f_url[0];
			InputStream source = retrieveStream(url);
			Gson gson = new Gson();
			Reader reader = null;
			try {
				reader = new InputStreamReader(source, HTTP.ISO_8859_1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (reader != null)
				return gson.fromJson(reader, MedicinNews.class);
			return null;
		}

		@Override
		protected void onPostExecute(MedicinNews result) {
			super.onPostExecute(result);
			downloadedData.setMedicinNews(result);
			sendMessageToSpalshScreen_proces(2, "Nyheder hentet");
			Log.e("HER_AsyncTask: ", "onPostExecute");
			startDownload_mailsjson();
		}
	}

	private class DownloadTaskSubjectMails extends
			AsyncTask<String, Integer, SubjectMails> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.e("HER_AsyncTask: ", "onPreExecute");
			sendMessageToSpalshScreen_proces(2, "Henter emner");
		}

		@Override
		protected SubjectMails doInBackground(String... f_url) {
			String url = f_url[0];
			InputStream source = retrieveStream(url);
			Gson gson = new Gson();
			Reader reader = null;
			try {
				reader = new InputStreamReader(source, HTTP.ISO_8859_1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (reader != null)
			return gson.fromJson(reader, SubjectMails.class);
			return null;
		}

		@Override
		protected void onPostExecute(SubjectMails result) {
			super.onPostExecute(result);
			downloadedData.setDownloadedSubjectMails(result);
			sendMessageToSpalshScreen_proces(3, "Emner hentet");
			Log.e("HER_AsyncTask: ", "onPostExecute");
			startDownload_mails_json_semesters();
		}
	}

	private class DownloadTaskSemesterMails extends
			AsyncTask<String, Integer, SemesterMails> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.e("HER_AsyncTask: ", "onPreExecute");
			sendMessageToSpalshScreen_proces(3, "Henter mails");
		}

		@Override
		protected SemesterMails doInBackground(String... f_url) {
			String url = f_url[0];
			InputStream source = retrieveStream(url);
			Gson gson = new Gson();
			Reader reader = null;
			try {
				reader = new InputStreamReader(source, HTTP.ISO_8859_1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (reader != null)
			return gson.fromJson(reader, SemesterMails.class);
			return null;
		}

		@Override
		protected void onPostExecute(SemesterMails result) {
			super.onPostExecute(result);
			downloadedData.setDownloadedSemesterMails(result);
			sendMessageToSpalshScreen_proces(4, "Mails hentet");
			Log.e("HER_AsyncTask: ", "onPostExecute");

			sendMessageToSpalshScreen_AllDone();
		}
	}

}
