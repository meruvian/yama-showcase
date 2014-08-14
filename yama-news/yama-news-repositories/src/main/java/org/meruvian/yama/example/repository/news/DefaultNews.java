package org.meruvian.yama.example.repository.news;

import org.meruvian.yama.repository.LogInformation;

public class DefaultNews implements News {
	private String id;
	private LogInformation logInformation = new LogInformation();
	private String title;
	private String content;
	
	public DefaultNews () {}
	
	public DefaultNews(News news) {
		this.id = news.getId();
		this.logInformation = news.getLogInformation();
		this.title = news.getTitle();
		this.content = news.getContent();
	}
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
