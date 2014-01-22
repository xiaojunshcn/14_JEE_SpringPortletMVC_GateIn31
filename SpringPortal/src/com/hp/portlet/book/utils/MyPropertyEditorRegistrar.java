package com.hp.portlet.book.utils;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * PropertyEditorRegistrar which registers LongNumberPropertyEditor.
 */
public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {

	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Long.class, new LongNumberEditor());
	}

}
