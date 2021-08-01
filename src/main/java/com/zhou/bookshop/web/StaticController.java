package com.zhou.bookshop.web;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.zhou.bookshop.entity.Category;
import com.zhou.bookshop.service.CategoryService;

/**
 * 静态页面产生！！！（可以解决高并发的问题）
 * @author Administrator
 *
 */

@Controller
@RequestMapping("static")
public class StaticController {
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 导航条的静态页面显示！
	 * @param request
	 * @param response
	 */
	@RequestMapping("category")
	public void categoryList(HttpServletRequest request,HttpServletResponse response) {
		TemplateEngine engine = new TemplateEngine();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		engine.setTemplateResolver(templateResolver);
		ServletContext servletContext=request.getServletContext();
		
		List<Category> categoryes = categoryService.getCategorys();
		WebContext context = new WebContext(request,response,servletContext);
		context.setVariable("categoryes",categoryes);
		
		Set<String> set=new HashSet();
		set.add("category");//设置需要导入的片段！
		
		try {
			engine.process("StaticCategory",set,context, new FileWriter("src/main/resources/templates/html/category.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 首页显示各类别图书信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("categorybooks")
	public String categoryBooks(HttpServletRequest request,HttpServletResponse response) {
		TemplateEngine engine = new TemplateEngine();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		engine.setTemplateResolver(templateResolver);
		ServletContext servletContext=request.getServletContext();
		WebContext context = new WebContext(request,response,servletContext);
		List<Category> categorys =categoryService.getCategorysToindex2();
		context.setVariable("categorys",categorys);
		
		Set<String> set=new HashSet();
		set.add("category_books");//设置需要导入的片段！
		
		try {
			engine.process("StaticCategory",set,context, new FileWriter("src/main/resources/templates/html/category_books.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "suc";
	}
	
	
}
