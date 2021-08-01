package com.zhou.bookshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.zhou.bookshop.SqlProvider.BookListByCondition;
import com.zhou.bookshop.entity.Book;

public interface BookDAO {
	@Insert("insert into shop_book(name,description,details,author,publisher,pubdate,ISBN,marketprice,saleprice,commentnum,commentscore,image,categoryid,booknum) "
			+ "values(#{name},#{description},#{details},#{author},#{publisher},#{pubdate},#{ISBN},#{marketprice},#{saleprice},#{commentnum},#{commentscore},#{image},#{category.id},#{booknum})")
	public void addBook(Book book);

	@Select("select * from shop_book limit 8")
	public List<Book> getBooks();

	@Select("select * from shop_book where id=#{id}")
	public Book getBookById(int id);

	@Select("select saleprice from shop_book where id=#{id}")
	public float getPriceById(int id);

	/**
	 * 分页查找
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@Select("select b.id bid,b.name bname,b.saleprice bsaleprice,b.booknum booknum,c.name cname from shop_book b,shop_category c where b.categoryid=c.id")
	@Results({ 
			@Result(column = "bid", property = "id"), 
			@Result(column = "bname", property = "name"),
			@Result(column = "cname", property = "category.name"),
			@Result(column = "bsaleprice", property = "saleprice"), 
			@Result(column = "booknum", property = "booknum") })
	public List<Book> getBooksBypagehelper();

	/**
	 * 按条件查询：一：方法（包含参数！）；二：结果集映射；三：加@SelectProvider
	 * @param book
	 * @return
	 */
	@SelectProvider(type=BookListByCondition.class,method="getBookListByCategory")
	@Results({ 
		@Result(column = "bid", property = "id"), 
		@Result(column = "bname", property = "name"),
		@Result(column = "cname", property = "category.name"),
		@Result(column = "bsaleprice", property = "saleprice"), 
		@Result(column = "booknum", property = "booknum") })
	public List<Book> getBookByCondition(Book book);
	
	


}
