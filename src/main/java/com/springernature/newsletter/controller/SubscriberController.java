/**
 * @format:off
 * This file (SubcriberController.java) is part of newsletter.
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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.springernature.newsletter.data.NewsletterDataStore;
import com.springernature.newsletter.model.Category;
import com.springernature.newsletter.model.Subscriber;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
@RestController
public class SubscriberController {

	public static class SubcriberInput {
		private String email;
		private List<String> categoryCodes;

		public SubcriberInput() {
			super();
		}

		public SubcriberInput(final String email, final List<String> categoryCodes) {
			super();
			this.email = email;
			this.categoryCodes = categoryCodes;
		}

		public String getEmail() {
			return email;
		}

		public List<String> getCategoryCodes() {
			return categoryCodes;
		}
	}

	public static final String REQUEST_PATH_SUBSCRIBER = "/subscribers";

	/**
	 * create a new subscriber model data from input and add all existing category models by the input codes
	 *
	 * @param input
	 *            json data
	 * @return a new subscriber model
	 */
	@RequestMapping(path = REQUEST_PATH_SUBSCRIBER, method = RequestMethod.POST)
	public void addSubscriber(@RequestBody(required = true) final SubcriberInput input) {
		checkNotNull(input.getCategoryCodes(), "No NULL value allowed");
		checkArgument(!input.categoryCodes.isEmpty(), "No empty category code list allowed");
		checkArgument(!Strings.isNullOrEmpty(input.getEmail()), "No empty email allowed");
		checkArgument(!Strings.isNullOrEmpty(input.getEmail().trim()), "No whitespace email allowed");

		final List<Category> categories = NewsletterDataStore.getCategoriesByCodes(input.getCategoryCodes());
		final Subscriber newSubscriber = new Subscriber(input.getEmail(), categories);
		NewsletterDataStore.addSubscriber(newSubscriber);
	}
}
