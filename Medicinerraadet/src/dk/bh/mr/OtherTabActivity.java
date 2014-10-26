package dk.bh.mr;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import dk.bh.mr.dlobj.DownloadedData;
import dk.bh.mr.json.facebookfeed.Entries;
import dk.bh.mr.json.ommr.OmMedicinerRaadet;
import dk.bh.mr.json.ommr.Ommr;
import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class OtherTabActivity extends Activity {

	private WebView webView;
	OmMedicinerRaadet omData;
	String temp1 = "", temp2 = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_tab);

		webView = (WebView) findViewById(R.id.webView_about);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		DownloadedData dl_data;
		dl_data = (DownloadedData) getParent().getIntent()
				.getSerializableExtra("dataforTabs");
		omData = dl_data.getMedicinInfo();

		if (omData != null)
			setUpHtmlView();

	}

	public void setUpHtmlView() {
		String head = "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta charset=\"UTF-8\"><title>Newsfeed for MedHauze</title>";
		String css = ""
				+ "<style type=\"text/css\">"
				+ "body {"
				+ "background-color: #DAE0F0;"
				+ "font-family: Arial, sans-serif;"
				+ "font-size: 100%;"
				+ "}"
				+

				".header {"
				+ "background-color: #9898B8;"
				+ "margin-bottom: 0.5em;"
				+ "border-bottom: 0.15em solid #2E3875;"
				+ "}"

				+ ".date {"
				+ "font-weight: bold;"
				+ "text-align:left;"
				+ "align:left;"
				+ "color: #FFFFFF;"
				+ "background-color: #FF00FF;"
				+ "}"

				+ "li{display:inline;}"

				+ "table {"
				+ "width: 100%;"
				+ "align:left;"
				+ "padding: 99;"
				+ "text-align:left;"
				// + "border-collapse: collapse;"
				+ "border-spacing: 0;"
				+ "}"

				+ "tr  {"
				// + "vertical-align: central;"
				+ "}"

				+ "td {"
				+ "vertical-align: central;"
				+ "text-align:left;"
				+ "align:left;"
				+ "padding: 0;"
				+ "}"

				+ ".SubContentHide {display:none}"
				+ ".AbeHide {display:none; left:50px;}"
				+ ".ContentHide {display:none}"

				+ ".SubContent { display:inline}"
				+ ".Abe {display:inline; left:50px; background-color: #FF00FF;}"
				+ ".Content { display:inline;"
				// + "text-overflow: ellipsis;"
				// + "line-height: 1.2em;"
				// + "overflow: hidden;"
				+ "}"
				+

				".content a:link {"
				+ "color: #2E3875;"
				+ "}"
				+ ".content a:visited {"
				+ "color: #2E3875;"
				+ "}"

				+ ".logo {"
				+ "height: 2em;"
				+ "float: left;"
				+ "}"

				// + ".img {"
				// + "float: right;"
				// + "margin: 0.2em;"
				// + "margin-left: 0.6em;"
				// + "max-width: 30%;"
				// + "border-style: none;"
				// + "}"

				+ ".footer {" + "width: 100%;" + "margin-bottom: 3em;"
				+ "margin-top: 0.5em;" + "}" +

				".link {" + "text-decoration: none;" + "color: #FFFFFF;"
				+ "padding: 0.2em 0.2em 0.2em 0.6em;"
				+ "background-color: #9898B8;" + "float: right;"
				+ "clear: left;" + "}" +

				".link:visited {" + "color: #FFFFFF;" + "}" +

				"</style>";
		String javaScript = "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js\" type=\"text/javascript\"></script>"
				+ "<script type=\"text/javascript\">"
				+ "function toggle(obj){"
				+ "if(obj.parent().hasClass('Nyhed')){"
				+ "obj.parent().find('#Content').removeClass('Content').addClass('ContentHide'); "
				+ "obj.parent().find('#Abe').removeClass('Abe').addClass('AbeHide'); "
				+ "obj.parent().removeClass('Nyhed').addClass('NyhedHide'); }"
				+ "else if(obj.parent().hasClass('NyhedHide')){"
				+ "obj.parent().find('#Content').removeClass('ContentHide').addClass('Content'); "
				+ "obj.parent().find('#Abe').removeClass('AbeHide').addClass('Abe'); "
				+ "obj.parent().removeClass('NyhedHide').addClass('Nyhed');  }"

				+ "else if(obj.parent().hasClass('SubNyhed')){"
				+ "obj.parent().find('#SubContent').removeClass('SubContent').addClass('SubContentHide'); "
				+ "obj.parent().removeClass('SubNyhed').addClass('SubNyhedHide'); }"
				+ "else if(obj.parent().hasClass('SubNyhedHide')){"
				+ "obj.parent().find('#SubContent').removeClass('SubContentHide').addClass('SubContent'); "
				+ "obj.parent().removeClass('SubNyhedHide').addClass('SubNyhed');  }	}"

				+ "</script>";

		String bodyHtml = "</head><body>";

		String bodyHtmlEnd = "</body></html>";
		String tekstIndhold = buildContext(omData.getOmmr());
		temp1 = tekstIndhold;
		webView.loadDataWithBaseURL("file:///android_res/drawable/", head + css
				+ javaScript + bodyHtml + tekstIndhold + bodyHtmlEnd,
				"text/html", "utf-8", null);

	}

	String buildContext(List<Ommr> list) {
		String build = "<table>";
		for (Ommr varOmData : list) {

			if (varOmData.getExpand())
				build += "<div class=\"Nyhed\" id=\"Nyhed\">";
			else
				build += "<div class=\"NyhedHide\" id=\"Nyhed\" >";

			build += "<div class=\"header\" id=\"header\" onclick=\"toggle($(this));\">"
					+ "<tr><td>"
					+ "<ul>"
					+ "<li><img class=\"logo\" src=\"logomedicinerraadet.jpg\"/></li>"
					+ "<li><div class=\"date\">" + varOmData.getSubject() + "</div></li>"
					+ "</ul></td></tr>"
					+ "</div>";

			if (varOmData.getExpand())
				build += "<tr><td><div class=\"Content\" id=\"Content\">";
			else
				build += "<tr><td><div class=\"ContentHide\" id=\"Content\">";

			for (String strList : varOmData.getContext()) {
				build += strList + "<br>";
			}
			build += "</div></td></tr>"; // end of content

			if (!varOmData.getSubcontext().isEmpty()) {
				build += "<tr><td><div class=\"Abe\" id=\"Abe\">";
				// build += buildSubContext(varOmData.getSubcontext());
				build += "</div></tr></td>";
			}

			build += "</div>";

		}
		return build += "</table>";
	}

	String buildSubContext(List<Ommr> list) {
		String build = "<table>";

		for (Ommr varOmData : list) {
			if (varOmData.getExpand())
				build += "<div class=\"SubNyhed\" id=\"SubNyhed\">";
			else
				build += "<div class=\"SubNyhedHide\" id=\"SubNyhed\" >";

			build += "<div class=\"header\" id=\"header\" onclick=\"toggle($(this));\">"
					+ "<tr><td>"
					+ "<ul>"
					+ "<li><img class=\"logo\" src=\"logomedicinerraadet.jpg\"/></li>"
					+ "<li><div class=\"date\">" + varOmData.getSubject() + "</div></li>"
					+ "</ul></td></tr>"
					+ "</div>";

			if (varOmData.getExpand())
				build += "<tr><td><div class=\"SubContent\" id=\"SubContent\">";
			else
				build += "<tr><td><div class=\"SubContentHide\" id=\"SubContent\">";

			for (String strList : varOmData.getContext()) {
				build += strList + "<br>";
			}
			build += "</td></tr></div>";
		}
		return build;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Log.d("BH Logger", temp1);
		Log.d("BH Logger", temp2);
	}
}
