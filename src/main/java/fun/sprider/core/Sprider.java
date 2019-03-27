package fun.sprider.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fun.sprider.entity.Product;
import fun.sprider.util.ProductUtil;
@Component
@Scope("prototype")
public class Sprider {
	private static final String URL = "https://list.jd.com/list.html?cat=";
	private List<Product> products = new ArrayList<>();
	@Async
	public Product doSprider(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = client.execute(request);
		String html = EntityUtils.toString(response.getEntity());
		Product p  = new Product();
		p.setProductId(ProductUtil.getProductIDByUrl(url));
		Document document = Jsoup.parse(html);
		Elements summaryHtml = document.select("div.Ptable").select("div.Ptable-item");
		Map<String,Map<String,String>> ext = new LinkedHashMap<>();
		for (int i = 0; i < summaryHtml.size(); i++) {
			Elements nxt = summaryHtml.get(i).select("dl.clearfix");
			Map<String,String> content = new LinkedHashMap<>();
			for (int j = 0; j < nxt.size(); j++) {
				content.put(nxt.get(j).select("dt").text(), nxt.get(j).select("dd").text());
			}
			ext.put(summaryHtml.get(i).select("h3").text(), content);
		}
		p.setProductSummary(new Gson().toJson(ext));
		p.setImg(document.select("img#spec-img").attr("data-origin"));
		Elements elements = document.select(".itemInfo-wrap");
		p.setProductName(elements.select("div.sku-name").text());
		String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_"+elements.select("span.p-price").select("span")
		.last().attr("class").substring(elements.select("span.p-price").select("span")
				.last().attr("class").lastIndexOf('-')+1);
		request = new HttpGet(priceUrl);	
		response = client.execute(request);
		html = EntityUtils.toString(response.getEntity());
		html = html.substring(1);
		html = html.substring(0, html.length()-2);
		Map<String,Object> map = new Gson().fromJson(html, new TypeToken<HashMap<String,Object>>(){}.getType());
		p.setPrice(Double.parseDouble((String) map.get("p")));
		return p;
	}
}
