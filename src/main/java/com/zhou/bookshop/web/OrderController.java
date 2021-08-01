package com.zhou.bookshop.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.zhou.bookshop.entity.Address;
import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Order;
import com.zhou.bookshop.entity.OrderDetailsVO;
import com.zhou.bookshop.entity.User;
import com.zhou.bookshop.service.AddressService;
import com.zhou.bookshop.service.BookService;
import com.zhou.bookshop.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private BookService bookService;
	@Autowired
	private AddressService addressService;

	@GetMapping("add/{bookId}/{value}")
	public String addOrder(@PathVariable int bookId, @PathVariable int value, Model model, HttpSession session) {
		/* 从上一界面跳转过来查询数据显示到此界面 */
		Book book = bookService.getBookById(bookId);
		/**
		 * 直接购买时，获取购买数量！存到集合里，与购物车结算时，参数信息保持一致（books，value）
		 */
		model.addAttribute("value", Arrays.asList(value));
		model.addAttribute("books", Arrays.asList(book));
		/* 下单界面展示地址 */
		User user = (User) session.getAttribute("user");
		List<Address> addresses = addressService.getAddresses(user.getId());
		model.addAttribute("addresses", addresses);

		/* 展示给用户的价格 */
		float totalprice = book.getSaleprice() * value;
		model.addAttribute("totalprice", totalprice);
		return "order_add";

	}

	@PostMapping("add")
	public String addOrder(Order order, OrderDetailsVO orderDetailsVO, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		order.setUser(user);
		System.out.println(user.getId());

//		/* 商品总价格 从数据库里查询出来计算！避免用户恶意修改！！！将此总价存入数据库！ */
//		float Price = bookService.getPriceById(orderDetailsVO.getOrderDetalis().get(0).getBook().getId());
//		int Num = orderDetailsVO.getOrderDetalis().get(0).getNum();
//		float safePrice = Price * Num;
//		System.out.println(safePrice);
//		order.setTolalMoney(safePrice);

		orderService.add(order, orderDetailsVO.getOrderDetalis());

		model.addAttribute("order", order);

		return "order_cfm";
	}

	@PostMapping("pay")
	public String orderPay() {
		return "suc";
	}
	
	/**
	 * 后台!查看所有订单
	 */
	/*对于接受表单提交的参数 可以直接使用实体类Order接收！ 其他参数可以再定义一个属性参数接收*/
	@GetMapping("list")
	public String orderList(Integer page,Order order,Model model) {
		/*从其他页面跳转过来时：page为空，用Integer  接收page，再做判断*/
		if(page==null) {page=1;}
		PageInfo<Order> pageInfo=orderService.getBooksBypagehelper(page, order);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("order", order);
		return "admin/order_list";
	}
	
	@GetMapping("order_view/{id}")
	public String getOrderDetials(@PathVariable int id,Model model) {
		System.out.println(id);
		Order order=orderService.getOrdersWithBookAndUser(id);
		model.addAttribute("order", order);
		return "admin/order_view";
	}
}
