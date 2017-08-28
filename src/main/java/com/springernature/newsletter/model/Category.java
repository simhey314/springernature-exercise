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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class Category {
	private final String code;
	private final String title;
	private Category parent;

	public Category(final String code, final String title) {
		super();
		this.code = code;
		this.title = title;
	}

	public Category(final String code, final String title, final Category parent) {
		super();
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
		List<Category> result = new ArrayList<>();
		result.add(this);
		if (parent != null) {
			result.addAll(parent.getCategoryPath());
		}
		return result;
	}

	@Override
	public String toString() {
		return title;
	}
}
