package com.zhou.bookshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.entity.OrderDetails;

public interface OrderDetailsDAO {
	/**
	 * 增加订单明细表数据
	 * 
	 * @param orderDetails
	 */
	@Insert("insert into shop_order_details(orderid,bookid,price,num) values(#{order.id},#{book.id},#{price},#{num})")
	public void add(OrderDetails orderDetails);
	
	/**
	 * 一对多联合查询!!!(不熟悉)
	 */
	@Select("select * from shop_order  where id=#{orderid} ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="orderno",property="orderNo"),
		@Result(column="userid",property="user.id"),
		@Result(column="ordertime",property="orderTime"),
		@Result(column="status",property="status"),
		@Result(column="totalmoney",property="totalMoney"),
		@Result(column="paymode",property="payMode"),
		@Result(column="sendway",property="sendway"),
		@Result(column="paystate",property="paystate"),
		@Result(column="account",property="account"),
		@Result(column="telphone",property="telphone"),
		@Result(column="address",property="address"),
		@Result(column="id",property="orderDetails",many=@Many(select="com.zhou.bookshop.dao.OrderDAO.getOrderDetialsByOrderId",fetchType=FetchType.LAZY))
	})
	public Order getOrdersWithBook(int orderid);
	
	
	/**
	 * 
	 * @param orderid
	 * @return  order and user message(后台显示订单详情信息！)
	 */
	@Select("select o.*,u.account uaccount,u.email uemail from shop_order o,shop_user u where o.userid=u.id and o.id=#{orderid}")
	/*在result中 如果column和property的值一样：如@Result(column="orderno",property="orderNo")  则可以不用写此@result！*/
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="orderno",property="orderNo"),
		@Result(column="uaccount",property="user.account"),
		@Result(column="uemail",property="user.email"),
	@Result(column="id",property="orderDetails",many=@Many(select="com.zhou.bookshop.dao.OrderDAO.getOrderDetialsByOrderId",fetchType=FetchType.LAZY))
	})
	public Order getOrdersWithBookAndUser(int orderid);
	
}
