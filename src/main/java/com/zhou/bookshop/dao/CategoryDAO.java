package com.zhou.bookshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Category;

public interface CategoryDAO {

	@Insert("insert into shop_category(name,booknum) values(#{name},0)")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	public void addCategory(Category category);

	@Select("select * from shop_category")
	public List<Category> getCategorys();

	/**
	 * 判断条件：是否现在首页上显示
	 * 
	 * @return
	 */
	@Select("select * from shop_category where ifview='A'")
	public List<Category> getCategorysToindex();

	
	/**
	 * 查询类别对应的书籍及信息
	 */
	@Select("select b.id bookid,b.name bname,b.marketprice bmarketprice,b.saleprice bsaleprice,b.image bimage,c.id cid,c.name cname from shop_category c,shop_book b where c.id=#{categoryid} and c.id=b.categoryid limit #{parm1}")
	@Results({ @Result(id = true, column = "cid", property = "category.id"),
			@Result(column = "cname", property = "category.name"), @Result(column = "bookid", property = "id"),
			@Result(column = "bname", property = "name"), @Result(column = "bmarketprice", property = "marketprice"),
			@Result(column = "bsaleprice", property = "saleprice"), @Result(column = "bimage", property = "image") })
	public List<Book> getBooktByCategoryId(int categoryid, int parm1);

//    @Select("SELECT c.id cid,c.name cname,b.id bookid,b.name bname,b.marketprice bmarketprice,b.saleprice bsaleprice,b.image bimage FROM shop_category c RIGHT JOIN shop_book b ON c.id=b.categoryid")
//		右外链接查询

	/**
	 * 查询类别对应的所有书籍及信息
	 */
	@Select("select b.id bookid,b.name bname,b.marketprice bmarketprice,b.saleprice bsaleprice,b.image bimage,c.id cid,c.name cname from shop_category c,shop_book b where c.id=#{categoryid} and c.id=b.categoryid ")
	@Results({ @Result(id = true, column = "cid", property = "category.id"),
			@Result(column = "cname", property = "category.name"), @Result(column = "bookid", property = "id"),
			@Result(column = "bname", property = "name"), @Result(column = "bmarketprice", property = "marketprice"),
			@Result(column = "bsaleprice", property = "saleprice"), @Result(column = "bimage", property = "image") })
	public List<Book> getMoreBooktByCategoryId(int categoryid);

}
