package fun.sprider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import fun.sprider.entity.JDLink;
@Mapper
public interface JDLinkMapper {
	@Insert("insert into jdlink(linkid) value(#{linkId})")
	public int insertJDLink(JDLink link);
	
	@Select("select * from jdlink")
	public List<String> getAllLinks();
}
