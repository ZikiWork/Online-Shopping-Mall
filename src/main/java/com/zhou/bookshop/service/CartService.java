package com.zhou.bookshop.service;

import java.util.List;

import com.zhou.bookshop.entity.Cart;

public interface CartService {
	public void addCart(Cart cart);

	public int selectNum(int userid);

	public List<Cart> getCart(int userid);

	public void updateNum(Cart cart);

	public int selectCartByUserIdAndBookId(int userid, int bookid);

	public Cart getCartByCartId(int cartid);

}
