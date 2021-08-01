package com.zhou.bookshop.entity;

public class Book {
	private int id;
	private String name;
	private String description;
	private String details;
	private String author;
	private String publisher;
	private String pubdate;
	private String ISBN;
	private float marketprice;
	private float saleprice;
	private int commentnum;
	private float commentscore;
	private String image;
	private Category category;
	private int booknum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPulisher() {
		return publisher;
	}

	public void setPulisher(String pulisher) {
		this.publisher = pulisher;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public float getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(float marketprice) {
		this.marketprice = marketprice;
	}

	public float getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}

	public int getCommnentnum() {
		return commentnum;
	}

	public void setCommnentnum(int commnentnum) {
		this.commentnum = commnentnum;
	}

	public float getCommentscore() {
		return commentscore;
	}

	public void setCommentscore(float commentscore) {
		this.commentscore = commentscore;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getBooknum() {
		return booknum;
	}

	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}

	public int getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", description=" + description + ", details=" + details
				+ ", author=" + author + ", publisher=" + publisher + ", pubdate=" + pubdate + ", ISBN=" + ISBN
				+ ", marketprice=" + marketprice + ", saleprice=" + saleprice + ", commentnum=" + commentnum
				+ ", commentscore=" + commentscore + ", image=" + image + ", category=" + category + ", booknum="
				+ booknum + "]";
	}

}
