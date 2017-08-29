/**
 * @format:off
 * This file (Subcriber.java) is part of newsletter.
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
package com.springernature.newsletter.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Strings;
import com.springernature.newsletter.data.NewsletterDataStore;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class Subscriber {

	@JsonProperty("recipient”")
	private final String email;

	@JsonIgnore
	private final List<Category> categoryCodes;

	public Subscriber(final String emailAdress, final List<Category> categoryCodes) {
		checkArgument(!Strings.isNullOrEmpty(emailAdress), "No empty email allowed");
		checkArgument(!Strings.isNullOrEmpty(emailAdress.trim()), "No whitespace email allowed");

		email = emailAdress;
		this.categoryCodes = categoryCodes;
	}

	public String getEmailAdress() {
		return email;
	}

	public List<Category> getCategoryCode() {
		return categoryCodes;
	}

	@JsonProperty("notifications")
	@JsonValue
	public List<Book> getNotifications(){
		return NewsletterDataStore.getBooksByCategories(categoryCodes);
	}
}