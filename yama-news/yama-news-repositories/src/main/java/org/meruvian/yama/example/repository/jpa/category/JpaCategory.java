package org.meruvian.yama.example.repository.jpa.category;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.example.repository.category.Category;
import org.meruvian.yama.repository.jpa.DefaultJpaPersistence;

@Entity
@Table(name = "yama_category")
public class JpaCategory extends DefaultJpaPersistence implements Category{
	private String name;
	private String description;
	private JpaCategory parentCategory;
	
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

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public JpaCategory getParentCategory() {
		return parentCategory;
	}
	
	public void setParentCategory(JpaCategory parentCategory) {
		this.parentCategory = parentCategory;
	}
}
