package com.zhou.bookshop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.bookshop.entity.Category;
import com.zhou.bookshop.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("add")
	public String add() {
		return "admin/category_add";
	}
	@PostMapping("add")
	public String add(Category category) {
		categoryService.addCategory(category);
		return "redirect:/category/list";//重定向！
	}

	@RequestMapping("list")
	public String categoryList(Model model) {
		List<Category> categorys = categoryService.getCategorys();
		model.addAttribute("categorys", categorys);
		return "admin/category_list";
	}
}
