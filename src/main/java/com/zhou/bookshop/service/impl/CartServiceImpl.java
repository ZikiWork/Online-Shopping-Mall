package com.zhou.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.bookshop.dao.CartDAO;
import com.zhou.bookshop.entity.Cart;
import com.zhou.bookshop.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;

	@Override
	public void addCart(Cart cart) {
		// TODO Auto-generated method stub
		cartDAO.addCart(cart);
	}

	@Override
	public int selectNum(int userid) {
		// TODO Auto-generated method stub
		return cartDAO.selectNum(userid);
	}

	@Override
	public List<Cart> getCart(int userid) {
		// TODO Auto-generated method stub
		return cartDAO.getCart(userid);
	}

	/**
	 * updateNum:购买相同商品时， 更具Userid和Bookid 更新购物车的商品的数量！
	 */
	@Override
	public void updateNum(Cart cart) {
		// TODO Auto-generated method stub
		int cartNum = cartDAO.selectCartByUserIdAndBookId(cart.getUserid(), cart.getBook().getId());
		if (cartNum == 0) {
			cartDAO.addCart(cart);
		} else {
			cartDAO.updateNum(cart);
		}
	}

	/**
	 * 查询商品数量
	 */
	@Override
	public int selectCartByUserIdAndBookId(int userid, int bookid) {
		// TODO Auto-generated method stub
		return cartDAO.selectCartByUserIdAndBookId(userid, bookid);
	}

	/**
	 * 根据Cart的id查询Book信息以及Cart中的数量！
	 */
	@Override
	public Cart getCartByCartId(int cartid) {
		// TODO Auto-generated method stub
		return cartDAO.getCartByCartId(cartid);
	}

}
