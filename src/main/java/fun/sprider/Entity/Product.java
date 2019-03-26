package fun.sprider.Entity;

import java.util.Map;

import lombok.Data;

@Data
public class Product {
	private String productId;
	private String productName;
	private Map<String,Map<String,String>> productSummary;
	private String img;
	private Double price;
	
}
