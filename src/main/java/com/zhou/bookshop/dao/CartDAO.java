package com.zhou.bookshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhou.bookshop.entity.Cart;

public interface CartDAO {

	@Insert("insert into shop_cart_details (userid,bookid,num) values(#{userid},#{book.id},#{num})")
	public void addCart(Cart cart);

	/**
	 * 根据Userid查询商品的总数量！（方法需要更改 ）
	 * 
	 * @return
	 */
	@Select("select sum(num) from shop_cart_details where userid=#{userid}")
	public int selectNum(int userid);

	@Select("select b.id bookid,b.name bname,b.saleprice bsaleprice,b.image bimage,c.id cid,c.num cnum from shop_cart_details c,shop_book b where c.userid=#{userid} and c.bookid=b.id")
	@Results({ @Result(id = true, column = "cid", property = "id"), @Result(column = "bookid", property = "book.id"),
			@Result(column = "bname", property = "book.name"),
			@Result(column = "bsaleprice", property = "book.saleprice"),
			@Result(column = "bimage", property = "book.image"), @Result(column = "cnum", property = "num") })
	public List<Cart> getCart(int userid);

	@Update("update shop_cart_details set num=num+#{num} where userid=#{userid} and bookid=#{book.id}")
	public void updateNum(Cart cart);

	@Select("select count(*) from shop_cart_details where userid=#{userid} and bookid=#{bookid}")
	public int selectCartByUserIdAndBookId(int userid, int bookid);
	
	/*多表查询*/
	@Select("select b.id bookid,b.name bname,b.saleprice bsaleprice,b.image bimage,c.id cid,c.num cnum from shop_cart_details c,shop_book b where c.id=#{cartid} and c.bookid=b.id")
	@Results({ @Result(id = true, column = "cid", property = "id"), @Result(column = "bookid", property = "book.id"),
			@Result(column = "bname", property = "book.name"),
			@Result(column = "bsaleprice", property = "book.saleprice"),
			@Result(column = "bimage", property = "book.image"), @Result(column = "cnum", property = "num") })
	public Cart getCartByCartId(int cartid);
}
