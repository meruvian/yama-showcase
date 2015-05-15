package org.meruvian.yama.webapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.meruvian.yama.core.DefaultPersistence;

@Entity
@Table(name = "yama2showcase_category")
public class Category extends DefaultPersistence {
	private String name;
	private String description;
	private int level = 0;

	@NotBlank
	@Column(unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
