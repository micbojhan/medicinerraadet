package dk.itsmap.nissebanden.medicinerraadet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.temaprojekt1.MedicinNews.Entries;
import com.example.temaprojekt1.MedicinNews.MedicinNews;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsTabActivity extends Activity {
	WebView myWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_tab);
		myWebView = (WebView) findViewById(R.id.webView_News);
		startDownloadMetode();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_tab, menu);
		return true;
	}

	public void startDownloadMetode() {
		// Starting the task. Pass an url as the parameter.
		// new
		// DownloadTaskMedicinInfo().execute("https://graph.facebook.com/278417128835721");
		new DownloadTaskMedicinNews()
				.execute("https://www.facebook.com/feeds/page.php?id=278417128835721&format=json");
	}

	private class DownloadTaskMedicinNews extends
			AsyncTask<String, Integer, MedicinNews> {

		// Start....
		/**
		 * Before starting background thread Show Progress Bar Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// displayProgressBar("Downloading...");
			// showDialog(progress_bar_type);
			// brord cast
			Log.e("HER_AsyncTask: ", "onPreExecute");
		}

		// Selve arbejdet...
		/**
		 * Downloading file in background thread
		 * */
		@Override
		protected MedicinNews doInBackground(String... f_url) {
			String url = f_url[0];
			InputStream source = retrieveStream(url);
			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);
			MedicinNews RawSTOG = gson.fromJson(reader, MedicinNews.class);
			return RawSTOG;
		}

		// imens
		/**
		 * Updating progress bar
		 * */
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			// updateProgressBar(values[0]);
			// pDialog.setProgress(Integer.parseInt(progress[0]));
			Log.e("HER_AsyncTask: ", "onProgressUpdate");
		}

		// FÆRDIG
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		@Override
		protected void onPostExecute(MedicinNews result) {
			super.onPostExecute(result);
			// dismissProgressBar();

			// dismiss the dialog after the file was downloaded
			// dismissDialog(progress_bar_type);

			// Displaying downloaded image into image view
			// Reading image path from sdcard
			// String imagePath =
			// Environment.getExternalStorageDirectory().toString() +
			// "/downloadedfile.jpg";
			// setting downloaded into image view
			// my_image.setImageDrawable(Drawable.createFromPath(imagePath));

			Log.e("HER_AsyncTask: ", "onPostExecute");
			sendMessageNews(result);
		}
	}
	
	public InputStream retrieveStream(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();
		} catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}
	
	
	private void sendMessageNews(MedicinNews RawJson) {
		// Intent i = new Intent("NuErJegKlar");
		// i.putExtra("YourArrayList", RawJson);
		// sendBroadcast(i);
		// LocalBroadcastManager.getInstance(this).sendBroadcast(i);
		String css = ""+
				
		"<style type=\"text/css\">"+


		"body {"+
		    "background-color: #DAE0F0;"+
		    "font-family: Arial, sans-serif;"+
		    "font-size: 100%;"+
		"}"+

		".header {"+
		   "background-color: #9898B8;"+
		   "margin-bottom: 0.5em;"+
		   "border-bottom: 0.15em solid #2E3875;"+
		"}"+

		".logo {"+
		    "height: 2em;"+
		    "float: left;"+
		"}"+
		    
		".date {"+
			"font-weight: bold;"+
			"color: #FFFFFF;"+
		"}"+

		"table {"+
		    "width: 100%;"+
		    "border-collapse: collapse;"+
		    "border-spacing: 0;"+
		"}"+

		    "table td {"+
		        "vertical-align: central;"+
		        "padding: 0;"+
		    "}"+

		".content {"+
		    "text-overflow: ellipsis;"+
		    "line-height: 1.2em;"+
		    "max-height: 9.6em;"+
		    "overflow: hidden;"+
		"}"+
		    
		".content a:link {"+
	        "color: #2E3875;"+
	    "}"+
	        
		".content a:visited {"+
	        "color: #2E3875;"+
	    "}"+

		".img {"+
		    "float: right;"+
		    "margin: 0.2em;"+
		    "max-width: 9em;"+
		    "border-style: none;"+
		"}"+

		".footer {"+
		    "width: 100%;"+
		    "margin-bottom: 3em;"+
		    "margin-top: 0.5em;"+
		"}"+

		".link {"+
		    "text-decoration: none;"+
		    "color: #FFFFFF;"+
		    "padding: 0.2em 0.2em 0.2em 0.6em;"+
		    "background-color: #9898B8;"+
		    "float: right;"+
		    "clear: left;"+
		"}"+

		    ".link:visited {"+
		        "color: #FFFFFF;"+
		    "}"+

			"</style>";
		   final String TD_FORMAT_FULL_PATTERN_PLUS = "yyyy-MM-dd'T'HH:mm:ssZZ";
		   final String TD_FORMAT_FULL_PATTERN_MINU = "yyyy-MM-dd'T'hh:mm:ss";//:SSS'-'hh:mm";
		String stringBuilder = "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>Newsfeed for MedHauze</title><link href=\"newscss.css\" rel=\"stylesheet\" /></head><body>";
		ArrayList<Entries> eList = RawJson.getEntries();
		SimpleDateFormat dateFormat = new SimpleDateFormat(TD_FORMAT_FULL_PATTERN_PLUS);
		Date currentDate = new Date();
		
		for (Entries var : eList) {
			Date date = null;
			//Date timeSpan = null;
			try {
				date = dateFormat.parse(var.getPublished());
				//timeSpan = new Date(date.getTime()-currentDate.getTime());
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//String newstring2 = "";
			String newstring = "Nyt fra Medicinerrådet";
			String newdatestring = new SimpleDateFormat("EEEE, d. MMMM yyyy (HH:mm)").format(date);
			
			//newstring += new SimpleDateFormat("y d D - HH:mm:ss (kk)").format(timeSpan);
			/* 
			if(timeSpan.compareTo(new Date(0, 0, 0,1,0))<0)
			{
				newstring2 = new SimpleDateFormat("m").format(timeSpan)+"minutter siden";
			}
			else if(timeSpan.compareTo(new Date(0, 0, 1))<0)
			{
				newstring2 = new SimpleDateFormat("h").format(timeSpan)+" timer siden";
			}
			else if(timeSpan.compareTo(new Date(0, 0, 30))<0)
			{
				newstring2 = new SimpleDateFormat("D").format(timeSpan)+" dag(e) siden";
			}
			else if(timeSpan.compareTo(new Date(0, 0, 365))<0)
			{
				newstring2 = new SimpleDateFormat("d").format(timeSpan)+" månede(r) siden";
			}
			else //if(timeSpan.compareTo(new Date(1900, 0, 365))<0)
			{
				newstring2 = new SimpleDateFormat("y").format(timeSpan)+" år siden";
			}
			*/
			stringBuilder = stringBuilder
					+ "<div class=\"header\"><table><tr><td><img class=\"logo\" src=\"logomedicinerraadet.jpg\" /></td><td><div class=\"date\">"
					+ newstring
					+ "</div></td></tr></table></div><div class=\"content\">"
					+ "<b>"+newdatestring+"</b><br/>"
					+ var.getContent()
					+ "</div><div class=\"footer\"><a class=\"link\" href=\""
					+ var.getAlternate()
					+ "\">Læs mere > </a></div></div>";
			// string.add(var.getContent());
		}
		stringBuilder = stringBuilder + "</body></html>";
		
		
		myWebView.loadDataWithBaseURL("file:///android_res/drawable/",css+stringBuilder , "text/html", "utf-8",null);



	}


}
