package com.zhou.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.bookshop.dao.BookDAO;
import com.zhou.bookshop.dao.OrderDAO;
import com.zhou.bookshop.dao.OrderDetailsDAO;
import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.entity.OrderDetails;
import com.zhou.bookshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderDetailsDAO orderDetailsDAO;
	@Autowired
	private BookDAO bookDAO;

	/**
	 * 增加订单的同时，增加订单明细表（中间表）！
	 */
	@Override
	public void add(Order order, List<OrderDetails> orderDetailses) {
		/* 获取多件商品信息时 */
		/**
		 * 计算总价格！商品总价格 从数据库里查询出来计算！避免用户恶意修改！！！将此总价存入数据库！
		 */
		float safeprice = 0, price = 0;
		int num = 0;
		for (OrderDetails orderDetails : orderDetailses) {

			price = bookDAO.getPriceById(orderDetails.getBook().getId());
			num = orderDetails.getNum();
			safeprice += price * num;
		}
		System.out.println(safeprice);
		order.setTolalMoney(safeprice);

		order.setStatus("V");// 给定
		order.setOrderNo("BookNo");
		order.setPaystate("N");
		orderDAO.add(order);

		/* 增加订单明细 */
		for (OrderDetails orderDetails : orderDetailses) {
			orderDetails.setOrder(order);
			orderDetailsDAO.add(orderDetails);
		}
	}

	/**
	 * 根据用户ID查询订单
	 */
	@Override
	public List<Order> getOrderByuserId(int userId) {
		// TODO Auto-generated method stub
		return orderDAO.getOrderByuserId(userId);
	}

	/**
	 * 查询订单详细详情！
	 */
	@Override
	public Order getOrdersWithBook(int orderid) {
		// TODO Auto-generated method stub
		return orderDetailsDAO.getOrdersWithBook(orderid);
	}

	@Override
	/**
	 * 按条件！分页！查询
	 */
	public PageInfo<Order> getBooksBypagehelper(int page, Order order) {
		PageHelper.startPage(page, 5);
		List<Order> orders = orderDAO.getOrderByCondition(order);
		PageInfo<Order> pageInfo = new PageInfo<>(orders);
		return pageInfo;
	}

	@Override
	/**
	 * 查询 order详细信息 和 user
	 */
	public Order getOrdersWithBookAndUser(int orderid) {
		return orderDetailsDAO.getOrdersWithBookAndUser(orderid);
	}

	@Override
	public List<Order> getOrdersIndex() {
		// TODO Auto-generated method stub
		return orderDAO.getOrdersIndex();
	}

	@Override
	public List<Order> getOrdersUser(int userid) {
		// TODO Auto-generated method stub
		return orderDAO.getOrdersUser(userid);
	}

}
