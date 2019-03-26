package fun.sprider.util;

public class ProductUtil {
	public static String getProductIDByUrl(String url) {
		return url.substring(url.lastIndexOf("/")+1,url.indexOf(".html"));
	}
}
