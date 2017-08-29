/**
 * @format:off
 * This file (NewsletterControllerTest.java) is part of newsletter.
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.springernature.newsletter.TestHelper;
import com.springernature.newsletter.data.NewsletterDataStore;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsletterApplication.class)
@WebAppConfiguration
public class NewsletterControllerTest {

	private static final String RESPONSE_JSON_EMPTY = "[]";

	// @format:off
	private static final String RESPONSE_JSON_COMPLETE = "[{\r\n" +
			"	\"notifications\": [{\r\n" +
			"		\"book\": \"book title2\",\r\n" +
			"		\"categoryPaths\": [{\r\n" +
			"			\"categoryPaths\": [\r\n" +
			"			\"cat title1\",\r\n" +
			"			\"cat title2\",\r\n" +
			"			\"cat title3.1\"]\r\n" +
			"		},\r\n" +
			"		{\r\n" +
			"			\"categoryPaths\": [\r\n" +
			"			\"cat title1\",\r\n" +
			"			\"cat title2\",\r\n" +
			"			\"cat title3.2\",\r\n" +
			"			\"cat title4.2\"]\r\n" +
			"		}]\r\n" +
			"	}],\r\n" +
			"	\"recipient\": \"muster@email.de\"\r\n" +
			"},\r\n" +
			"{\r\n" +
			"	\"notifications\": [{\r\n" +
			"		\"book\": \"book title2\",\r\n" +
			"		\"categoryPaths\": [{\r\n" +
			"			\"categoryPaths\": [\"cat title1\",\r\n" +
			"			\"cat title2\",\r\n" +
			"			\"cat title3.1\"]\r\n" +
			"		},\r\n" +
			"		{\r\n" +
			"			\"categoryPaths\": [\r\n" +
			"			\"cat title1\",\r\n" +
			"			\"cat title2\",\r\n" +
			"			\"cat title3.2\",\r\n" +
			"			\"cat title4.2\"]\r\n" +
			"		}]\r\n" +
			"	},\r\n" +
			"	{\r\n" +
			"		\"book\": \"book title1\",\r\n" +
			"		\"categoryPaths\": [{\r\n" +
			"			\"categoryPaths\": [\r\n" +
			"			\"cat title1\",\r\n" +
			"			\"cat title2\",\r\n" +
			"			\"cat title3.2\"]\r\n" +
			"		}]\r\n" +
			"	}],\r\n" +
			"	\"recipient\": \"max@email.de\"\r\n" +
			"}]";
	// @format:on

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private void setUp() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		NewsletterDataStore.resetData();
	}

	/**
	 * Test method for {@link com.springernature.newsletter.controller.NewsletterController#getNewsletters()}.
	 */
	@Test
	public void testGetNewslettersEmptyData() throws Exception {
		setUp();

		mockMvc.perform(get(NewsletterController.REQUEST_PATH_NEWSLETTERS)).andExpect(status().is2xxSuccessful())
		.andExpect(content().json(RESPONSE_JSON_EMPTY));
		mockMvc.perform(post(NewsletterController.REQUEST_PATH_NEWSLETTERS)).andExpect(status().is4xxClientError());
	}

	/**
	 * Test method for {@link com.springernature.newsletter.controller.NewsletterController#getNewsletters()}.
	 */
	@Test
	public void testGetNewslettersCompleteData() throws Exception {
		setUp();

		TestHelper.setUpDataStoreComplete();

		mockMvc.perform(get(NewsletterController.REQUEST_PATH_NEWSLETTERS)).andExpect(status().is2xxSuccessful())
		.andExpect(content().json(RESPONSE_JSON_COMPLETE));
	}
}
