package dk.itsmap.nissebanden.medicinerraadet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.temaprojekt4.GoogleCalendar.GC_Entry;
import com.example.temaprojekt4.GoogleCalendar.GC_GoogleCalendar;
import com.example.temaprojekt4.GoogleCalendar.GD_When;
import com.example.temaprojekt4.GoogleCalendar.GD_Where;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

public class CalendarTabActivity extends Activity {
	WebView myWebView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_tab);
		myWebView = (WebView) findViewById(R.id.webView_Calendar);
		Log.e("hej", myWebView+"");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_tab, menu);
		return true;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		startDownloadMetode();
	}

	public void startDownloadMetode() {
		// Starting the task. Pass an url as the parameter.
		// new
		// DownloadTaskMedicinInfo().execute("https://graph.facebook.com/278417128835721");
		new DownloadTaskGoogleCalendar()
				.execute("http://www.google.com/calendar/feeds/studmedsam@gmail.com/public/full?alt=json&orderby=starttime&sortorder=ascending&futureevents=true&singleevents=true");
	}

	private class DownloadTaskGoogleCalendar extends
			AsyncTask<String, Integer, GC_GoogleCalendar> {

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
		protected GC_GoogleCalendar doInBackground(String... f_url) {
			String url = f_url[0];
			InputStream source = retrieveStream(url);
			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);
			GC_GoogleCalendar RawSTOG = gson.fromJson(reader, GC_GoogleCalendar.class);
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
		protected void onPostExecute(GC_GoogleCalendar result) {
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
			String css = ""+
			
			"<style type=\"text/css\">"+


			"body {"+
			    "background-color: #DAE0F0;"+
			    "font-family: Arial, sans-serif;"+
			    "font-size: 100%;"+
			"}"+

			".header {"+
			   "font-weight: bold;"+
			   "color: #FFFFFF;"+
			   "background-color: #9898B8;"+
			   "margin-bottom: 0.5em;"+
			   "border-bottom: 0.15em solid #2E3875;"+
			"}"+

			".logo {"+
			    "height: 2em;"+
			    "float: left;"+
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
			    "max-width: 7.5em;"+
			    "border-style: solid;"+
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
					
					
			String html1 = "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">"+
					"<head>"+
						"<title>Newsfeed for MedHauze</title>"+
						//"<link href=\"file:///android_asset/newscss.css\" rel=\"stylesheet\" />"+
					"</head>"+
					"<body>";
		

			String html_part = "";

			List<GC_Entry> entrylist = result.getFeed().getEntry();
			
			for(GC_Entry entry : entrylist)
			{
				
				
				String temp ="<div class=\"header\">"+
							"<table>"+
	            				"<tr>"+
	                				"<td>"+
	                					 
	                    				"<img class=\"logo\" src=\"logomedicinerraadet.jpg\" />"+
	                    			"</td>"+
	                    			"<td>"+
	                    				"<div class=\"date\">"+entry.getTitle().get$t()+"</div>"+
	                    			"</td>"+
	                    		"</tr>"+
	                    	"</table>"+
	                    "</div>"+
	                    "<div class=\"content\">";
						for(GD_Where gd : entry.getGd_where())
						{
							temp+=gd.getValueString() + " <br>";
						}
						for(GD_When gd : entry.getGd_when())
						{
							temp+=gd.getStartTime() + " <br>";
						}
	                    	
	                    
	                    String temp2 = "</div>"+
	                    "<div class=\"footer\">"+
	                    	"<a class=\"link\" href=\"set link\">Læs mere...</a>"+
	                    "</div>";
				html_part+=temp+temp2;
			}

			
			
		     String html2 = "</body>"+
			            "</html>";
				//Log.e("HEHEH", css+html1+html_part+html2);
				//myWebView.loadData(css+html1+html_part+html2, "text/html", "utf-8");
				myWebView.loadDataWithBaseURL("file:///android_res/drawable/",css+html1+html_part+html2 , "text/html", "utf-8",null);

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
