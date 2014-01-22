package com.hp.portlet.book.controller;

import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.hp.portlet.book.domain.Book;
import com.hp.portlet.book.service.BookService;
import com.hp.portlet.book.utils.LongNumberEditor;


/**
 * EditBookController shows the Edit Book form and does request processing of the
 * edit book action.
 *
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes("book")
public class EditBookController {
	@Autowired
	@Qualifier("myBookService")
	private BookService bookService;
	@Autowired
	@Qualifier("myEditBookValidator")
	private Validator myEditBookValidator;
	
	@RenderMapping(params="myaction=editBookForm")
	public String showEditBookForm() {
		return "editBookForm";
	}

	@ActionMapping(params="myaction=editBook")
	public void editBook(@ModelAttribute("book") Book book, BindingResult bindingResult, ActionResponse response, SessionStatus sessionStatus)  {
		myEditBookValidator.validate(book, bindingResult);
		if (!bindingResult.hasErrors()) {
			bookService.editBook(book);
			response.setRenderParameter("myaction", "books");
			sessionStatus.setComplete();
		} else {
			//--this is required. the getBook method is not invoked but the @RequestParam
			//--is still evaluated
			response.setRenderParameter("isbnNumber", book.getIsbnNumber().toString());
			response.setRenderParameter("myaction", "editBookForm");
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Long.class, new LongNumberEditor());
		binder.setDisallowedFields(new String[] {"isbnNumber"});
	}
	
	@ModelAttribute("book")
	public Book getBook(@RequestParam Long isbnNumber) {
		return bookService.getBook(isbnNumber);
	}
}