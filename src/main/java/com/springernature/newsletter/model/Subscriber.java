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

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class Subscriber {

	@JsonProperty("recipient”")
	private String email;
	
	@JsonIgnore
	private List<Category> categoryCodes;

	public Subscriber(final String emailAdress, final List<Category> categoryCodes) {
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
		return Collections.emptyList();
	}
}
