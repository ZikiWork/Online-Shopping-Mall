package com.zhou.bookshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.bookshop.dao.BookDAO;
import com.zhou.bookshop.entity.Book;
import com.zhou.bookshop.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.addBook(book);
	}

	@Override
	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		List<Book> books=bookDAO.getBooks();
		for (int i = 0; i < books.size(); i++) {
			if(i%2==0) {
				books.remove(i);
			}
		}
		return books;
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return bookDAO.getBookById(id);
	}

	@Override
	public float getPriceById(int id) {
		// TODO Auto-generated method stub
		return bookDAO.getPriceById(id);
	}

	/**
	 * 实现book分页功能！ return pageInfo!
	 *
	 *在spring boot中实现分页功能：
	 *第一：配置pom文件：	
	 *<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper-spring-boot-starter</artifactId>
			<version>1.2.9</version>
		</dependency>
		第二：PageInfo接受返回值！
	 */
	@Override
	public PageInfo<Book> getBooksBypagehelper(int page,Book book) {
		PageHelper.startPage(page, 2);
		//List<Book> books = bookDAO.getBooksBypagehelper();//查询所有数据
		List<Book> books=bookDAO.getBookByCondition(book);//按条件查询数据!
		PageInfo<Book> pageInfo = new PageInfo<>(books);
		return pageInfo;
	}

}
