/**
 * @format:off
 * This file (Category.java) is part of newsletter.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Strings;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class Category {

	@JsonIgnore
	private final String code;
	@JsonIgnore
	private final String title;
	@JsonIgnore
	private final Category parent;

	public Category(final String code, final String title) {
		this(code, title, null);
	}

	public Category(final String code, final String title, final Category parent) {
		checkArgument(!Strings.isNullOrEmpty(code), "No empty code allowed");
		checkArgument(!Strings.isNullOrEmpty(code.trim()), "No whitespace code allowed");
		checkArgument(!Strings.isNullOrEmpty(title), "No empty title allowed");
		checkArgument(!Strings.isNullOrEmpty(title.trim()), "No whitespace title allowed");

		this.code = code;
		this.title = title;
		this.parent = parent;
	}

	public String getTitle() {
		return title;
	}

	public Category getParent() {
		return parent;
	}

	public String getCode() {
		return code;
	}

	public List<Category> getCategoryPath(){
		final List<Category> result = new ArrayList<>();
		result.add(this);
		if (parent != null) {
			result.addAll(parent.getCategoryPath());
		}
		return result;
	}

	@JsonProperty("categoryPaths")
	@JsonValue
	public List<String> getCategoryPathStringList(){
		final List<Category> pathList = getCategoryPath();
		Collections.reverse(pathList);
		return pathList.parallelStream().map(category -> category.getTitle()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return title;
	}
}
