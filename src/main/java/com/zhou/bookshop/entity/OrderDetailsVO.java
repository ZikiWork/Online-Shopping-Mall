package com.zhou.bookshop.entity;

import java.util.List;

/**
 * 采用VO 的方式获取复杂参数! 界面上给定的参数必须和实体类中的对应！
 * 如：<input type="text" name="users[0].name" placeholder="姓名"/><!-- 通过数组方式给控件命名
 * --> <input type="text" name="users[0].age" placeholder="年龄"/><br/>
 * 
 * @author Administrator
 *
 */
public class OrderDetailsVO {
	private List<OrderDetails> orderDetalis;

	public List<OrderDetails> getOrderDetalis() {
		return orderDetalis;
	}

	public void setOrderDetalis(List<OrderDetails> orderDetalis) {
		this.orderDetalis = orderDetalis;
	}

}
