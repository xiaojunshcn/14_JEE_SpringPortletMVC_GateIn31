package com.hp.portlet.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hp.portlet.book.domain.Book;
import com.hp.portlet.book.service.BookService;


@Component("myAddBookValidator")
public class AddBookValidator implements Validator {
	@Autowired
	@Qualifier("myBookService")
	private BookService bookService;
	
	public boolean supports(Class<?> klass) {
		return Book.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Book book = (Book)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.book.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotEmpty.book.author");
		String name = book.getName();
		if(name.length() >100 || name.length() < 10) {
			errors.rejectValue("name", "fieldLength.book.name");
		}
		if(!bookService.isUniqueISBN(book.getIsbnNumber())) {
			errors.rejectValue("isbnNumber", "unique.constraint.failure");
		}
	}
}
