/**
 * @format:off
 * This file (CategoryController.java) is part of newsletter.
 *
 *    newsletter is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    any later version.
 *
 *    newsletter is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with newsletter. If not, see <http://www.gnu.org/licenses/>.
 * @format:on
 */
package com.springernature.newsletter.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springernature.newsletter.data.NewsletterDataStore;
import com.springernature.newsletter.model.Category;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
@RestController
public class CategoryController {

	public static class CategoryInput {
		private String title;
		private String code;
		private String superCategoryCode;

		public CategoryInput() {
		}

		public CategoryInput(final String title, final String code, final String superCategoryCode) {
			super();
			this.title = title;
			this.code = code;
			this.superCategoryCode = superCategoryCode;
		}

		public String getTitle() {
			return title;
		}

		public String getCode() {
			return code;
		}

		public String getSuperCategoryCode() {
			return superCategoryCode;
		}
	}

	/**
	 * create a new category and only add an already existing parent category
	 *
	 * @param input
	 *            the category json data
	 * @return the new category
	 */
	@RequestMapping(path = "/categories", method = RequestMethod.POST)
	public void addCategory(@RequestBody(required = true) final CategoryInput input) {

		final Category newCategory = new Category(input.getCode(), input.getTitle(), NewsletterDataStore.getCategories().get(input.getSuperCategoryCode()));
		NewsletterDataStore.addCategory(newCategory);
	}
}
