package com.zhou.bookshop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.service.OrderService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private OrderService orderService; 
	
	@GetMapping("login")
	public String Login() {

		return "admin/login";
	}

	@PostMapping("login")
	public String joinLogin() {
		
		return "redirect:/admin/index";
	}

	@GetMapping("index")
	public String joinIndex(Model model) {
		List<Order> orders=orderService.getOrdersIndex();
		model.addAttribute("orders", orders);
		return "admin/index";
	}

}
