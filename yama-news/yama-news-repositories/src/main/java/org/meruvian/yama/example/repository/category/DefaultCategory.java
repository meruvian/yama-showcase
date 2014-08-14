package org.meruvian.yama.example.repository.category;

import org.meruvian.yama.repository.LogInformation;

public class DefaultCategory implements Category {
	private String id;
	private LogInformation logInformation = new LogInformation();
	private String name;
	private String description;
	private Category parentCategory;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public LogInformation getLogInformation() {
		return logInformation;
	}
	
	public void setLogInformation(LogInformation logInformation) {
		this.logInformation = logInformation;
	}
	
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
	
	public Category getParentCategory() {
		return parentCategory;
	}
	
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
}
