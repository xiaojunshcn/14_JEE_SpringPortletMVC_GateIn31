package com.hp.portlet.book.domain;

import org.springframework.beans.factory.annotation.Configurable;
/**
 * Book Domain object which defines properties of a book in the catalog.
 * 
 */
@Configurable
public class Book {
	private String name;
	private String author;
	private Long isbnNumber;
	
	public Book(String name, String author, long isbnNumber) {
		this.name = name;
		this.author = author;
		this.isbnNumber = isbnNumber;
	}
	public Book() {
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(Long isbnNumber) {
		this.isbnNumber = isbnNumber;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		Book otherBook = (Book)otherObject;
		if(otherBook.getIsbnNumber() == this.isbnNumber) {
			return true;
		} else {
			return false;
		}
	}
}
