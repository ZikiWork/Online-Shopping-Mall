package com.zhou.bookshop.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.bookshop.entity.Address;
import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Cart;
import com.zhou.bookshop.entity.User;
import com.zhou.bookshop.service.AddressService;
import com.zhou.bookshop.service.BookService;
import com.zhou.bookshop.service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private BookService bookService;
	@Autowired
	private AddressService addressService;

	@PostMapping("add")
	@ResponseBody
	public int addCart(@RequestBody Cart cart, Model model,HttpSession session) {
		cartService.updateNum(cart);
		User user = (User) session.getAttribute("user");
		int num = cartService.selectNum(user.getId());
		return num;
	}

	@GetMapping("getcart")
	public String getCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Cart> carts = cartService.getCart(user.getId());
		for (Cart cart : carts) {
			System.out.println(cart.getId());
		}
		model.addAttribute("carts", carts);
		return "cart";
	}

	@PostMapping("cartorder")
	public String addCartOrder(HttpSession session, Model model, int[] cartid) {
		/* 地址： */
		User user = (User) session.getAttribute("user");
		List<Address> addresses = addressService.getAddresses(user.getId());
		model.addAttribute("addresses", addresses);

		/**
		 * 图书信息： 将cart中的Book和num分别存入List中
		 */
		List<Book> books = new ArrayList<>();
		List<Integer> value = new ArrayList<>();
		float totalprice = 0;
		for (Integer cartId : cartid) {
			Cart cart = cartService.getCartByCartId(cartId);
			books.add(cart.getBook());
			value.add(cart.getNum());
			totalprice += cart.getNum() * cart.getBook().getSaleprice();
		}
		model.addAttribute("totalprice", totalprice);// 所选商品的总价！
		model.addAttribute("books", books);
		model.addAttribute("value", value);
		/*
		 * 所选商品总金额
		 */
		return "order_add";

	}

}
