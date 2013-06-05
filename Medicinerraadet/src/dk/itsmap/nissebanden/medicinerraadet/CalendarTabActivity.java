package dk.itsmap.nissebanden.medicinerraadet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.temaprojekt4.GoogleCalendar.GC_Entry;
import com.example.temaprojekt4.GoogleCalendar.GC_GoogleCalendar;
import com.example.temaprojekt4.GoogleCalendar.GD_When;
import com.example.temaprojekt4.GoogleCalendar.GD_Where;
import com.medicin.splashdownload.DownloadedData;

public class CalendarTabActivity extends Activity {
	WebView myWebView;
	GC_GoogleCalendar googleCalender;

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

		for (GC_Entry entry : entrylist) {
			String startTime, headerTime, endTime, tempText, shortStartTime;
			headerTime = "";
			tempText = "";
			SimpleDateFormat formatter, FORMATTER, HeaderFormatter;

			for (GD_When gd : entry.getGd_when()) {
				startTime = gd.getStartTime();
				endTime = gd.getEndTime();
				if (startTime.length() > 10) {
					shortStartTime = startTime.substring(0, 15);
					endTime = endTime.substring(11, 16);
					formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
					FORMATTER = new SimpleDateFormat(
							"'D.' dd. MMMM yyyy 'kl.' HH:mm");
					HeaderFormatter = new SimpleDateFormat("dd. MMMM yyyy");
					try {
						startTime = FORMATTER.format(formatter
								.parse(shortStartTime));
						headerTime = HeaderFormatter.format(formatter
								.parse(shortStartTime));
						tempText = startTime + "-" + endTime + "<br>";
						// temp += FORMATTER.format(formatter.parse(startTime))+
						// "-"+ endTime + " <br>";
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					FORMATTER = new SimpleDateFormat("dd. MMMM yyyy");
					try {
						startTime = FORMATTER
								.format(formatter.parse(startTime));
						headerTime = startTime;
						endTime = FORMATTER.format(formatter.parse(endTime));
						tempText = "Hel-/flerdagsarrangement <br>" + "Fra "
								+ startTime + " til " + endTime + "<br>";
						// temp += "Fra "
						// + FORMATTER.format(formatter.parse(startTime))
						// + " til "
						// + FORMATTER.format(formatter.parse(endTime))
						// + "<br>";
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			String temp = "<div class=\"header\">" + "<table>" + "<tr>"
					+ "<td>"
					+ "<img class=\"logo\" src=\"logomedicinerraadet.jpg\" />"
					+ "</td>" + "<td>" + "<div class=\"date\">" + headerTime
					+ "</div>" + "</td>" + "</tr>" + "</table>" + "</div>"
					+ "<div class=\"content\">" + "<b>"
					+ entry.getTitle().get$t() + "</b><br>" + tempText;

			for (GD_Where gd : entry.getGd_where()) {
				if (!gd.getValueString().isEmpty())
					temp += "Hvor: " + gd.getValueString();
			}
			if (!entry.getContent().get$t().isEmpty())
				temp += "<br>Beskrivelse: " + entry.getContent().get$t();

			temp += "<br><br></div>";

			html_part += temp;
		}

		String html2 = "</body>" + "</html>";
		myWebView.loadDataWithBaseURL("file:///android_res/drawable/", css
				+ html1 + html_part + html2, "text/html", "utf-8", null);

	}

}
