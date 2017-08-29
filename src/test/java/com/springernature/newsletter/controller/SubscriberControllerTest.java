/**
 * @format:off
 * This file (SubscriberControllerTest.java) is part of newsletter.
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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.springernature.newsletter.NewsletterApplication;
import com.springernature.newsletter.TestHelper;
import com.springernature.newsletter.data.NewsletterDataStore;
import com.springernature.newsletter.model.Category;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsletterApplication.class)
@WebAppConfiguration
public class SubscriberControllerTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private static final String REQUEST_JSON_PATTERN = "{ \"email\": %s, \"categoryCodes\": %s }";
	private static final String JSON_COMPLETE = String.format(REQUEST_JSON_PATTERN, "\"muster@email.de\"", "[\"code1\"]");
	private static final String JSON_NULL_CATEGORIES = String.format(REQUEST_JSON_PATTERN, "\"muster@email.de\"", "null");
	private static final String JSON_EMPTY_CATEGORIES = String.format(REQUEST_JSON_PATTERN, "\"muster@email.de\"", "[]");
	private static final String JSON_NULL_EMAIL = String.format(REQUEST_JSON_PATTERN, "null", "[\"code1\"]");
	private static final String JSON_WHITESPACES_EMAIL = String.format(REQUEST_JSON_PATTERN, "   ", "[\"code1\"]");

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private void setUp() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		NewsletterDataStore.resetData();
		NewsletterDataStore.addCategory(new Category("code1", "title1"));
	}

	/**
	 * Test method for
	 * {@link com.springernature.newsletter.controller.SubscriberController#addSubscriber(com.springernature.newsletter.controller.SubscriberController.SubcriberInput)}.
	 */
	@Test
	public void testAddSubscriber() throws Exception {
		setUp();

		mockMvc.perform(post(SubscriberController.REQUEST_PATH_SUBSCRIBER).content(JSON_COMPLETE).contentType(TestHelper.CONTENT_TYPE_JSON)).andExpect(status().isOk());

		mockMvc.perform(get(SubscriberController.REQUEST_PATH_SUBSCRIBER)).andExpect(status().is4xxClientError());
		mockMvc.perform(post(SubscriberController.REQUEST_PATH_SUBSCRIBER)).andExpect(status().is4xxClientError());

		mockMvc.perform(post(SubscriberController.REQUEST_PATH_SUBSCRIBER).content(JSON_NULL_CATEGORIES).contentType(TestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(post(SubscriberController.REQUEST_PATH_SUBSCRIBER).content(JSON_EMPTY_CATEGORIES).contentType(TestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(post(SubscriberController.REQUEST_PATH_SUBSCRIBER).content(JSON_NULL_EMAIL).contentType(TestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(post(SubscriberController.REQUEST_PATH_SUBSCRIBER).content(JSON_WHITESPACES_EMAIL).contentType(TestHelper.CONTENT_TYPE_JSON))
		.andExpect(status().is4xxClientError());
	}

}
