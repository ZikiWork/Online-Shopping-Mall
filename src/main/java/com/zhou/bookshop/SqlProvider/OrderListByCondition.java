package com.zhou.bookshop.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import com.zhou.bookshop.entity.Order;

public class OrderListByCondition {

	/**
	 *动态SQl产生 
	 * @param order
	 * @return
	 */
	public String getOrderListByCondition(Order order) {
		return new SQL() {
			{
				SELECT("id,orderno,ordertime,status,paymode,sendway,paystate,account");
				FROM("shop_order");
				if (order.getStatus() != null && !order.getStatus().equals("0")) {
					WHERE("status=#{status}");
				}
				if (order.getPaystate() != null && !order.getPaystate().equals("0")) {
					WHERE("paystate=#{paystate}");
				}
			}
		}.toString();
	}
}