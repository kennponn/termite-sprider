package fun.sprider.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import fun.sprider.entity.JDLink;
import fun.sprider.mapper.JDLinkMapper;
import fun.sprider.mapper.ProductMapper;
import fun.sprider.util.ProductUtil;
@Component
public class JDLinksPool {
	@Autowired
	private JDLinkMapper jDLinkMapper;
	@Autowired
	private ProductMapper productMapper;
	@Async
	public void init(ConfigurableApplicationContext ctx) throws ClientProtocolException, IOException  {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet("https://list.jd.com/list.html?cat=670,677,678");
		CloseableHttpResponse response = client.execute(request);
		String html = EntityUtils.toString(response.getEntity());
		Document document = Jsoup.parse(html);
		int page = Integer.parseInt(document.select(".p-skip").select("b").text());
		for (int i = 2; i <= page; i++) {
			System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\tPage"+i+" has Get!");
			request = new HttpGet("https://list.jd.com/list.html?cat=670,677,678&page="+i);
			 response = client.execute(request);
			 html = EntityUtils.toString(response.getEntity());
			 document = Jsoup.parse(html);
			 Elements summaryHtml = document.select("a");
				for (int j = 0; j < summaryHtml.size(); j++) {
					if (Pattern.matches("/*item.jd.com/[0-9]*.html", summaryHtml.get(j).attr("href"))) {
						try {
							jDLinkMapper.insertJDLink(
									new JDLink(ProductUtil.getProductIDByUrl(summaryHtml.get(j).
											attr("href"))));
							
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
						finally {
							try {
								productMapper.insertProduct(new Sprider().doSprider("http:"+summaryHtml.get(j).attr("href")));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								continue;
							}
						}
					}
				}
		}
		
		
	}
}
