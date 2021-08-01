package com.zhou.bookshop.web;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.entity.User;
import com.zhou.bookshop.service.OrderService;
import com.zhou.bookshop.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	@GetMapping("add")
	public String addUser() {
		return "reg";
	}

	/**
	 * 用户注册校验!
	 * 
	 * @param user
	 * @param captcha
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("add")
	public String addUser(User user, String captcha, Model model, HttpSession session) {
		String safecode = session.getAttribute("SafeCodeServlet").toString();
		System.out.println(safecode);
		if (captcha == null) {
			model.addAttribute("safecodemsg", "验证码不能为空！");
			// return "reg";
		} else if (!safecode.equals(captcha)) {
			model.addAttribute("safecodemsg", "验证码不正确，请重新输入！");
			// return "reg";
		} else if (safecode.equals(captcha)) {
			userService.addUser(user);
			return "login";
		}
		return "reg";
	}

	@GetMapping("person")
	public String Person(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = orderService.getOrdersUser(user.getId());
		model.addAttribute("orders", orders);

		return "users/index";
	}

	/*
	 * @GetMapping("person/{id}")
	 * 
	 * @ResponseBody public User Person(@PathVariable int id) { User user =
	 * userService.getUserByid(id); return user; }
	 */

	@GetMapping("updatephoto")
	public String updatePhoto() {
		return "users/user_avatar";
	}

	@PostMapping("updatePhoto")
	public String updatePhoto(MultipartFile photoFile, HttpSession session, Model model) {
		String uuid = UUID.randomUUID().toString(); // 获取UUID并转化为String对象
		uuid = uuid.replace("-", ""); // 因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
		System.out.println(uuid);
		try {
			photoFile.transferTo(new File("e://springworkspace//Userimage//" + uuid + ".jpg"));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String photo = "Userimage/" + uuid + ".jpg";
		User user = (User) session.getAttribute("user");
		userService.updateUserPhoto(photo, user.getId());
		user.setAvatat(photo);

//		User user = userService.getUserByid(user.getId());
//		model.addAttribute("user", user);

		return "/users/index";
	}

	@GetMapping("address_list")
	public String Address() {
		return "users/address_list";
	}

//	@GetMapping("orders")
//	public String Orders() {
//		return "users/order_list";
//	}

	@GetMapping("orders")
	public String getOrder(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = orderService.getOrderByuserId(user.getId());
		model.addAttribute("orders", orders);
		return "users/order_list";
	}

	/**
	 * 根据得到的订单id 查出订单和书籍的信息！
	 * 
	 * @param id
	 * @return 返回order
	 */
	@GetMapping("order_view/{id}")
	public String orderView(@PathVariable int id, Model model) {
		Order order = orderService.getOrdersWithBook(id);
		System.out.println(order);
		model.addAttribute("order", order);
		return "users/order_view";
		// return "suc";
	}

	/**
	 * 返回ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView View() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("attributeName", new Object());

		try {
			Class.forName("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}

}
