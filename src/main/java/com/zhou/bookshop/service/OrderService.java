package com.zhou.bookshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.entity.OrderDetails;

/**
 * 增加订单的同时增加订单明细表中的数据！
 * 
 * @author Administrator
 *
 */

public interface OrderService {
	public void add(Order order, List<OrderDetails> orderDetailses);

	public List<Order> getOrderByuserId(int userId);

	public Order getOrdersWithBook(int orderid);

	/**
	 * 查询最新10条记录！
	 * @return
	 */
	public List<Order> getOrdersIndex();
	
	/*查询最近三条信息*/
	public List<Order> getOrdersUser(int userid);
	
	/**
	 * 
	 * @param page 页码
	 * @param order
	 * @return 分页结果！
	 */
	public PageInfo<Order> getBooksBypagehelper(int page, Order order);
	
	public Order getOrdersWithBookAndUser(int orderid);
}
