package com.zhou.bookshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhou.bookshop.entity.Book;

public interface BookService {
	/**
	 * 
	 * @param book
	 */
	public void addBook(Book book);

	/**
	 * 
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Book getBookById(int id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public float getPriceById(int id);
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public PageInfo<Book> getBooksBypagehelper(int page,Book book);


}
