package fun.sprider.core;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JDLinksPool {
	public void init() throws ParseException, IOException {
		CloseableHttpClient client =  HttpClients.createDefault();
		HttpGet request = new HttpGet("https://list.jd.com/list.html?cat=670,677,678");
		CloseableHttpResponse response = client.execute(request);
		String html = EntityUtils.toString(response.getEntity());
		Document document = Jsoup.parse(html);
		Elements summaryHtml = document.select("a");
		for (int i = 0; i < summaryHtml.size(); i++) {
			if(Pattern.matches("/*item.jd.com/[0-9]*.html",summaryHtml.get(i).attr("href")))
				System.out.println(summaryHtml.get(i).attr("href"));
		}
		
	}
}
