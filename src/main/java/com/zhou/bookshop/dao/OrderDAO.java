package com.zhou.bookshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.zhou.bookshop.SqlProvider.OrderListByCondition;
import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.entity.OrderDetails;

public interface OrderDAO {
	@Insert("insert into shop_order(orderno,userid,ordertime,status,totalmoney,paymode,sendway,paystate,account,telphone,address) "
			+ "values(#{orderNo},#{user.id},now(),#{status},#{totalMoney},#{payMode},#{sendway},#{paystate},#{account},#{telphone},#{address})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	public void add(Order order);

	@Select("select * from shop_order where userid=#{user.id}")
	public List<Order> getOrderByuserId(int userId);
	
	/**
	 * 首页最新10条信息！
	 * @return
	 */
	@Select("select * from shop_order order by id desc limit 10 ")
	public List<Order> getOrdersIndex();
	
	/**
	 * 个人首页展示的订单信息
	 * @return
	 */
	@Select("select * from shop_order where userid=#{user.id} order by id desc limit 3 ")
	public List<Order> getOrdersUser(int userid);
	
	/**
	 * 一对多查询：根据order的id查询所有的订单明细 并把书籍信息带出！（不熟悉）
	 * 
	 * @param id
	 * @return
	 */
	@Select("select b.id bid,b.image bimage,b.name bname,b.saleprice bsaleprice,d.num dnum from shop_order_details d,shop_book b where d.bookid=b.id and d.orderid=#{id}")
	@Results({ @Result(column = "bid", property = "book.id"), @Result(column = "bimage", property = "book.image"),
			@Result(column = "bname", property = "book.name"),
			@Result(column = "bsaleprice", property = "book.saleprice"), @Result(column = "dnum", property = "num") })
	public List<OrderDetails> getOrderDetialsByOrderId(int id);
	

	/**
	 * 按条件查询！
	 * @param order
	 * @return List<Order>
	 */
	@SelectProvider(type = OrderListByCondition.class, method = "getOrderListByCondition")
	public List<Order> getOrderByCondition(Order order);
}