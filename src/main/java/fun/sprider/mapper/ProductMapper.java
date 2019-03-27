package fun.sprider.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import fun.sprider.entity.Product;
@Mapper
public interface ProductMapper {
	@Insert("insert into product(productId,productName,"
			+ "productSummary,img,price) value(#{productId},#{productName}," + 
			"#{productSummary},#{img},#{price})")
	public int insertProduct(Product p);
}
