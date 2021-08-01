package com.zhou.bookshop.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Category;
import com.zhou.bookshop.service.BookService;
import com.zhou.bookshop.service.CategoryService;

@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	/* 用户首页查询图书列表！ */
	@RequestMapping("listbook")
	public String bookList(Model model) {
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return "book_list";
	}

	/* 点击更多查看图书信息 */
	@GetMapping("booklist/{categoryid}")
	public String bookList(@PathVariable int categoryid, Model model) {
		List<Book> books = categoryService.getMoreBooktByCategoryId(categoryid);
		model.addAttribute("books", books);
		return "book_list";
	}

	/**
	 * 后台增加图书！
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("add")
	public String addBook(Model model) {
		List<Category> categorys = categoryService.getCategorys();// 得到类别下拉框显示！
		model.addAttribute("categorys", categorys);
		return "admin/book_add";
	}

	@PostMapping("add")
	public String addBook(Book book, MultipartFile file) {// 和上传文件的name一样！
		System.out.println(book.toString());
		String uuid = UUID.randomUUID().toString(); // 获取UUID并转化为String对象
		uuid = uuid.replace("-", ""); // 因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
		System.out.println(uuid);
		try {
			file.transferTo(new File("e://springworkspace//Bookimage//" + uuid + ".jpg"));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setImage("/Bookimage/" + uuid + ".jpg");
		bookService.addBook(book);
		System.out.println(book);
		return "suc";
		// return "redirect:/book/listbook";// 重定向！
	}

	/* 按条件查询时。刚进入首页，book不为空。springMVC主动创建了Book实例 */
	/* 后台查看图书 */
	@GetMapping("list/{pagenum}")
	public String lookBook(Model model, @PathVariable int pagenum, Book book) {
		System.out.println(pagenum);
		/* 分类下拉框 */
		List<Category> categorys = categoryService.getCategorys();// 得到类别下拉框显示！
		model.addAttribute("categorys", categorys);
		/* 分页 */
		PageInfo<Book> pageInfo = bookService.getBooksBypagehelper(pagenum, book);
		model.addAttribute("pageInfo", pageInfo);
		/* 将查询条件继续显示在页面上！ */
		model.addAttribute("book", book);// 直接保存Book

		return "admin/book_list";
	}
}
