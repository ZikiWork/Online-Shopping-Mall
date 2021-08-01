package com.zhou.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.bookshop.dao.CategoryDAO;
import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Category;
import com.zhou.bookshop.service.CategoryService;

@Service // 记得在此加@Service注解！
public class CategoryServiceImpl implements CategoryService {

	@Autowired // 注入
	private CategoryDAO categoryDAO;

	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDAO.addCategory(category);
	}

	@Override
	public List<Category> getCategorys() {

		return categoryDAO.getCategorys();
	}

	@Override
	public List<Book> getBooktByCategoryId(int categoryid, int parm1) {
		// TODO Auto-generated method stub
		return categoryDAO.getBooktByCategoryId(categoryid, parm1);
	}

	@Override
	public List<Book> getMoreBooktByCategoryId(int categoryid) {
		// TODO Auto-generated method stub
		return categoryDAO.getMoreBooktByCategoryId(categoryid);
	}

	@Override
	public List<Category> getCategorysToindex() {
		// TODO Auto-generated method stub
		return categoryDAO.getCategorysToindex();
	}

	
	//更具种类查询需要显示在界面上的图书信息！！！！（在Category中增加一个List<Book> 来接收Book ）
	@Override
	public List<Category> getCategorysToindex2() {
		List<Category> categories=categoryDAO.getCategorysToindex();
		for (Category category : categories) {
			List<Book> books=categoryDAO.getBooktByCategoryId(category.getId(), 4);
			category.setBooks(books);
		}
		return categories;
	}

}
