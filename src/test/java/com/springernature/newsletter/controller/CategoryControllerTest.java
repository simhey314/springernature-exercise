/**
 * @format:off
 * This file (CategoryControllerTest.java) is part of newsletter.
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.springernature.newsletter.NewsletterApplication;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsletterApplication.class)
@WebAppConfiguration
public class CategoryControllerTest {

	private static final String REQUEST_JSON_PATTERN = "{ \"code\": \"%s\", \"title\": \"%s\", \"superCategoryCode\": %s }";
	private static final String CATEGORY_JSON_COMPLETE = String.format(REQUEST_JSON_PATTERN, "cat1", "category 01", "\"cat0\"");
	private static final String CATEGORY_JSON_NULL_CATEGORY = String.format(REQUEST_JSON_PATTERN, "cat1", "category 01", "null");
	private static final String CATEGORY_JSON_EMPTY_CATEGORY = String.format(REQUEST_JSON_PATTERN, "cat1", "category 01", "");
	private static final String CATEGORY_JSON_EMPTY_TITLE = String.format(REQUEST_JSON_PATTERN, "cat1", "", "\"cat0\"");
	private static final String CATEGORY_JSON_WHITSPACES_TITLE = String.format(REQUEST_JSON_PATTERN, "cat1", "   ", "\"cat0\"");
	private static final String CATEGORY_JSON_EMPTY_CODE = String.format(REQUEST_JSON_PATTERN, "", "category 01", "\"cat0\"");
	private static final String CATEGORY_JSON_WHITSPACES_CODE = String.format(REQUEST_JSON_PATTERN, "   ", "category 01", "\"cat0\"");

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private void setUp() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test method for
	 * {@link com.springernature.newsletter.controller.CategoryController#addCategory(com.springernature.newsletter.controller.CategoryController.CategoryInput)}.
	 */
	@Test
	public void testAddCategory() throws Exception {
		setUp();

		mockMvc.perform(post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_COMPLETE).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().isOk());

		mockMvc.perform(get(CategoryController.REQUEST_PATH_CATEGORIES)).andExpect(status().is4xxClientError());
		mockMvc.perform(post(CategoryController.REQUEST_PATH_CATEGORIES)).andExpect(status().is4xxClientError());
		mockMvc.perform(
				post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_NULL_CATEGORY).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().isOk());

		mockMvc.perform(
		        post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_EMPTY_CATEGORY).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		        .andExpect(status().is4xxClientError());
		mockMvc.perform(post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_EMPTY_TITLE).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(
				post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_WHITSPACES_TITLE).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_EMPTY_CODE).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(
				post(CategoryController.REQUEST_PATH_CATEGORIES).content(CATEGORY_JSON_WHITSPACES_CODE).contentType(ControllerTestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
	}

}
