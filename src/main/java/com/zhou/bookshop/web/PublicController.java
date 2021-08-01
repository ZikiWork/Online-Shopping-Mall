package com.zhou.bookshop.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Category;
import com.zhou.bookshop.entity.User;
import com.zhou.bookshop.service.BookService;
import com.zhou.bookshop.service.CategoryService;
import com.zhou.bookshop.service.UserService;

@Controller
@RequestMapping("/")
public class PublicController {

	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	// 首页
	@RequestMapping
	public String bookList(Model model) {
		/* 得到所有类别 */
		List<Category> categoryes = categoryService.getCategorys();
		model.addAttribute("categoryes", categoryes);
		
		/*得到在首页显示的类别*/
		List<Category> categorys = categoryService.getCategorysToindex();
		model.addAttribute("categorys", categorys);
		
		/* 得到最新的图书 */
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return "index";

	}
	
	
	@GetMapping("/{categoryId}")
	@ResponseBody
	public List<Book> getCategoryBooks(@PathVariable int categoryId) {
		/* 展示各个类别的图书 （4本！）*/
		List<Book> categoryBooks = categoryService.getBooktByCategoryId(categoryId,4);
		System.out.println(categoryId);
		return categoryBooks;
	}

	
	
	// 查看商品信息
	@RequestMapping("bookview/{id}")
	public String bookView(@PathVariable int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "book_view";
	}

	// 用户注册
	@GetMapping("reg")
	public String reg() {
		return "reg";
	}
	
	@GetMapping("isexist")
	@ResponseBody // 返回视图
	public boolean AccountIsExist(User user) {
		return userService.isExist(user.getAccount());
	}

	@PostMapping("reg")
	public String reg(User user) {
		userService.addUser(user);
		System.out.println(user);
		return "login";
	}

	// 用户登录
	@GetMapping("login")
	public String login(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}

	@PostMapping("login")
	public String login(User user, HttpSession session, Model model, HttpServletRequest request) {
		user = userService.getUserByCondition(user.getAccount(), user.getPassword());
		if (user == null) {
			model.addAttribute("msg", "账号或密码不正确!");
			return "login";
		} else {
			session.setAttribute("user", user);
			return "redirect:/";
		}
	}

	
//	@PostMapping("login/{acount}/{password}")
//	public String login(@PathVariable String account, @PathVariable String password, HttpSession session) {
//		User user = userService.getUserByCondition(account, password);
//		if (user == null) {
//			session.setAttribute("msg", "账号或密码不正确！");
//			return "login";
//		} else {
//			session.setAttribute("user", user);
//			return "index";
//		}
//	}
	
	
	/*静态化页面：*/
	
	
	
	

}
