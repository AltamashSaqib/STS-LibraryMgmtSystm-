package com.asaqib.LibraryMgmtSystm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int isbn;
	@Column
	private String title;
	private String author;
	private String price;
	// Date date = new Date();
	
	
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIsbn() {
		return isbn;
	}
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Books [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}	
	
}
	