package dk.bh.mr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
//import android.widget.Toast;


import dk.bh.mr.dlobj.DownloadedData;
import dk.bh.mr.json.googlecalendar.GC_Entry;
import dk.bh.mr.json.googlecalendar.GoogleCalendar;
import dk.bh.mr.json.googlecalendar.GD_When;
import dk.bh.mr.json.googlecalendar.GD_Where;

public class CalendarTabActivity extends Activity {
	WebView myWebView;
	GoogleCalendar googleCalender;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_tab);
		myWebView = (WebView) findViewById(R.id.webView_Calendar);
		Log.e("hej", myWebView + "");
		DownloadedData tis;
		tis = (DownloadedData) getParent().getIntent().getSerializableExtra(
				"dataforTabs");

		googleCalender = tis.getGoogleCalender();
		Log.e("BH_log", "DownloadedData: " + tis + " - googleCalender: "
				+ googleCalender);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (googleCalender != null)
			setUpHtmlView();
	}

	public void setUpHtmlView() {

		String css = "" +

		"<style type=\"text/css\">" +

		"body {" + "background-color: #DAE0F0;"
				+ "font-family: Arial, sans-serif;" + "font-size: 100%;" + "}" +

				".header {" + "background-color: #9898B8;"
				+ "margin-bottom: 0.5em;"
				+ "border-bottom: 0.15em solid #2E3875;" + "}" +

				".logo {" + "height: 2em;" + "float: left;" + "}" +

				".date {" + "font-weight: bold;" + "color: #FFFFFF;"
				+ "text-align: left;" + "}" +

				"table {" + "width: 100%;" + "border-collapse: collapse;"
				+ "border-spacing: 0;" + "}" +

				"table td {" + "vertical-align: central;" + "padding: 0;" + "}"
				+

				"table td:nth-of-type(odd) {" + "width: 10%;" + "}" +

				".content {" + "text-overflow: ellipsis;"
				+ "line-height: 1.2em;" + "max-height: 9.6em;"
				+ "overflow: hidden;" + "}" +

				".content a:link {" + "color: #2E3875;" + "}" +

				".content a:visited {" + "color: #2E3875;" + "}" +

				".img {" + "float: right;" + "margin: 0.2em;"
				+ "max-width: 7.5em;" + "border-style: solid;" + "}"
				+ "</style>";

		String html1 = "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">"
				+ "<head><title>Newsfeed for MedHauze</title>"; 
				

		String html_part = "</head><body>";

		List<GC_Entry> entrylist = googleCalender.getFeed().getEntry();
		String startTime, headerTime, preheaderTime = "", endTime, tempText;
		SimpleDateFormat compareFormater = new SimpleDateFormat("dd");
		SimpleDateFormat headerFormatter = new SimpleDateFormat("dd. MMMM yyyy");
		SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
		SimpleDateFormat googleFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		for (GC_Entry entry : entrylist) 
		{
			headerTime = startTime = endTime = tempText = "";
			for (GD_When gd : entry.getGd_when()) 
			{
				startTime = gd.getStartTime();
				endTime = gd.getEndTime();
				if (startTime.length() == 10) 
				{
					startTime = startTime + "T00:00";
					endTime = endTime + "T00:00";
				}
				else
				{
					startTime = startTime.substring(0, 16);
					endTime = endTime.substring(0, 16);
				}
				try 
				{
					int _end = Integer.parseInt(compareFormater.format(googleFormatter.parse(endTime)));
					int _sta = Integer.parseInt(compareFormater.format(googleFormatter.parse(startTime)));
					if(_end-_sta == 0) // Same day
					{
						headerTime = headerFormatter.format(googleFormatter.parse(startTime));
						startTime = timeFormater.format(googleFormatter.parse(startTime));
						endTime = timeFormater.format(googleFormatter.parse(endTime));
						tempText = "<b>Tidspunkt:</b> " + startTime + "-" + endTime;
					}
					else if(_end-_sta > 0) // More than one day..
					{
						headerTime = compareFormater.format(googleFormatter.parse(startTime)) + "-" + headerFormatter.format(googleFormatter.parse(endTime));
						startTime = timeFormater.format(googleFormatter.parse(startTime));
						endTime = timeFormater.format(googleFormatter.parse(endTime));
						if(startTime.equals(endTime))
						{
							tempText = "<b>Tidspunkt:</b> Ikke sat (Hele dagen: " + startTime + "-" + endTime + ")";
						}
						else
							tempText = "<b>Tidspunkt:</b> " + startTime + "-" + endTime;
					}
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}
			}
			String temp = "";
			if(headerTime.equals(preheaderTime))
			{
				temp += "<hr noshade><div class=\"content\">" + "<b>"
						+ entry.getTitle().get$t() + "</b><br>" + tempText;
			}
			else
			{
			temp += "<div class=\"header\">" + "<table>" + "<tr>" + "<td>"
					+ "<img class=\"logo\" src=\"logomedicinerraadet.jpg\" />"
					+ "</td>" + "<td>" + "<div class=\"date\">  " + headerTime
					+ "</div>" + "</td>" + "</tr>" + "</table>" + "</div>"
					+ "<div class=\"content\">" + "<b>"
					+ entry.getTitle().get$t() + "</b><br>" + tempText;
			}
			for (GD_Where gd : entry.getGd_where()) {
				if (!(gd.getValueString().length() == 0))
					temp += "<br><b>Hvor:</b> " + gd.getValueString();
			}
			if (!(entry.getContent().get$t().length() == 0))
			{
				if(entry.getContent().get$t().contains("http"))
					temp += "<br><b>Beskrivelse:</b> " + "<a class=\"link\" href=\"" + entry.getContent().get$t() + "\">Læs mere</a>";
				else
				temp += "<br><b>Beskrivelse:</b> " + entry.getContent().get$t();
			}

			temp += "<br><br></div>";

			html_part += temp;
			preheaderTime = headerTime;
		}

		String html2 = "</body>" + "</html>";
		myWebView.loadDataWithBaseURL("file:///android_res/drawable/", css
				+ html1 + html_part + html2, "text/html", "utf-8", null);

	}

}
