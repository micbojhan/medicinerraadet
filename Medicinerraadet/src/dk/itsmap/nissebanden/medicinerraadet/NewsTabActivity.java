package dk.itsmap.nissebanden.medicinerraadet;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.temaprojekt1.MedicinNews.Entries;
import com.example.temaprojekt1.MedicinNews.MedicinNews;
import com.medicin.splashdownload.DownloadedData;

public class NewsTabActivity extends Activity {
	WebView myWebView;
	MedicinNews newsMedicin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_tab);
		myWebView = (WebView) findViewById(R.id.webView_News);

		DownloadedData tis;
		tis = (DownloadedData) getParent().getIntent().getSerializableExtra(
				"dataforTabs");
		newsMedicin = tis.getMedicinNews();

		if (newsMedicin != null)
			setUpHtmlView();

	}

	private void setUpHtmlView() {
		String head = "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>Newsfeed for MedHauze</title>";

		String css = "" +
		
		"<style type=\"text/css\">" +

		"body {" + "background-color: #DAE0F0;"
				+ "font-family: Arial, sans-serif;" + "font-size: 100%;" + "}" +

				".header {" + "background-color: #9898B8;"
				+ "margin-bottom: 0.5em;"
				+ "border-bottom: 0.15em solid #2E3875;" + "}" +

				".logo {" + "height: 2em;" + "float: left;" + "}" +

				".date {" + "font-weight: bold;" + "color: #FFFFFF;" + "}" +

				"table {" + "width: 100%;" + "border-collapse: collapse;"
				+ "border-spacing: 0;" + "}" +

				"table td {" + "vertical-align: central;" + "padding: 0;" + "}"
				+

				".content {" + "text-overflow: ellipsis;"
				+ "line-height: 1.2em;" + "max-height: 9.6em;"
				+ "overflow: hidden;" + "}" +

				".content a:link {" + "color: #2E3875;" + "}" +

				".content a:visited {" + "color: #2E3875;" + "}" +

				".img {" + "float: right;" + "margin: 0.2em;" + "margin-left: 0.6em;"
				+ "max-width: 8em;" + "border-style: none;" + "}" +

				".footer {" + "width: 100%;" + "margin-bottom: 3em;"
				+ "margin-top: 0.5em;" + "}" +

				".link {" + "text-decoration: none;" + "color: #FFFFFF;"
				+ "padding: 0.2em 0.2em 0.2em 0.6em;"
				+ "background-color: #9898B8;" + "float: right;"
				+ "clear: left;" + "}" +

				".link:visited {" + "color: #FFFFFF;" + "}" +

				"</style>";
		final String TD_FORMAT_FULL_PATTERN_PLUS = "yyyy-MM-dd'T'HH:mm:ssZZ";
		
		String stringBuilder = "</head><body>";
		ArrayList<Entries> eList = newsMedicin.getEntries();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				TD_FORMAT_FULL_PATTERN_PLUS);

		for (Entries var : eList) {
			Date date = null;
			// Date timeSpan = null;
			try {
				date = dateFormat.parse(var.getPublished());
				// timeSpan = new Date(date.getTime()-currentDate.getTime());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// String newstring2 = "";
			String newstring = "Nyt fra Medicinerrådet";
			String newdatestring = new SimpleDateFormat("EEEE, d. MMMM yyyy (HH:mm)").format(date);
			char[] stringArray = newdatestring.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			newdatestring = new String(stringArray);

			// Finding image-content with jdom and moving it to the top to improve news appearance
			String content = "<root>" + var.getContent() +"</root>";

			try {
				SAXBuilder b = new SAXBuilder();
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				Document d = b.build(new StringReader(content));
				
				Element imageNode = null;
				List<Element> alinks = d.getRootElement().getChildren();
							
				for(Element e : alinks)
				{
					if(e.getName() == "a"){
						Log.d("MHN_Log", "Fandt et link");
						if(e.getChild("img")!=null)
						{
							Log.d("MHN_Log", "Fandt et billede i et link");
							imageNode = e;
							d.getRootElement().removeContent(e);
							d.getRootElement().addContent(0, imageNode);
							break;
						}
					}
					if(e.getName() == "img"){
						Log.d("MHN_Log", "Fandt et billede uden link");
						imageNode = e;
						d.getRootElement().removeContent(e);
						d.getRootElement().addContent(0, imageNode);
						break;
					}
				}
							
				content = outputter.outputString(d);
			} catch (Exception e) {
				System.err.println(e);
			}
						
			content = content.replace("<root>", "");
			content = content.replace("</root>", "");
						
						
			stringBuilder = stringBuilder
					+ "<div class=\"header\">" 
					+ "<table><tr><td><img class=\"logo\" src=\"logomedicinerraadet.jpg\" /></td>" 
					+ "<td><div class=\"date\">"
					+ newstring
					+ "</div></td></tr></table>" 
					+ "</div><div class=\"content\">"
					+ "<b>" + newdatestring + "</b><br/>" 
					+ content
					+ "</div><div class=\"footer\"><a class=\"link\" href=\""
					+ var.getAlternate() + "\">Læs mere > </a></div>";
		}
		stringBuilder = stringBuilder + "</body></html>";

		myWebView.loadDataWithBaseURL("file:///android_res/drawable/", head + css + stringBuilder, "text/html", "utf-8", null);

	}

}
