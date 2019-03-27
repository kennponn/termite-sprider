package fun.sprider.entity;

import java.util.Map;

import lombok.Data;

@Data
public class Product {
	private String productId;
	private String productName;
	private String productSummary;
	private String img;
	private Double price;
	
}
