package com.hp.portlet.book.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hp.portlet.book.domain.Book;


/**
 * Validator that is used by the EditBookController, for validating Book domain
 * object.
 *
 */
@Component("myEditBookValidator")
public class EditBookValidator implements Validator {

	public boolean supports(Class<?> klass) {
		return Book.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Book book = (Book)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.book.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotEmpty.book.author");
		String name = book.getName();
		if(name.length() >100 || name.length() < 10) {
			errors.rejectValue("name", "fieldLength");
		}
	}
}
