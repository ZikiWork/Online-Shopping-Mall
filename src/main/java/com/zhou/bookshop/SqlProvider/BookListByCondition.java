package com.zhou.bookshop.SqlProvider;

import com.zhou.bookshop.entity.Book;

public class BookListByCondition {

	public String getBookListByCategory(Book book) {
		String sql = "select b.id bid,b.name bname,b.saleprice bsaleprice,b.booknum booknum,c.name cname from shop_book b,shop_category c where b.categoryid=c.id";
		/**
		 * 进入界面时：没有条件，book为空！getcategory（）也为空！
		 */
		if (book.getCategory() != null) {
			if (book.getCategory().getId() != 0) {
				sql += " and c.id='" + book.getCategory().getId() + "'";
			}
			if (book.getName() != null && (book.getName().trim().length()) != 0) {
				sql += " and b.name like'%" + book.getName() + "%'";
			}
			if (book.getBooknum() != 0) {
				sql += " and b.name like'%" + book.getName() + "%'";
			}
		}
		return sql;
	}
}
