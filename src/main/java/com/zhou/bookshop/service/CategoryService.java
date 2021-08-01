package com.zhou.bookshop.service;

import java.util.List;

import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.entity.Category;

public interface CategoryService {

	public void addCategory(Category category);

	public List<Category> getCategorys();

	public List<Book> getMoreBooktByCategoryId(int categoryid);

	public List<Book> getBooktByCategoryId(int categoryid, int parm1);
	
	public List<Category> getCategorysToindex();

	public List<Category> getCategorysToindex2();
	
}
