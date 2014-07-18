package org.meruvian.yama.example.repository.jpa.news;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.example.repository.jpa.category.JpaCategory;
import org.meruvian.yama.example.repository.news.News;
import org.meruvian.yama.repository.jpa.DefaultJpaPersistence;
import org.meruvian.yama.repository.jpa.JpaLogInformation;
import org.meruvian.yama.repository.jpa.user.JpaUser;

@Entity
@Table(name = "yama_news")
public class JpaNews extends DefaultJpaPersistence implements News {
	
	private JpaUser user;
	private String title;
	private String content;
	private String description;
	private String abstrac;
	private JpaCategory category;
	private boolean isForce = false;
	private Date forceDate;
	private boolean isComment = false;
	
	public JpaNews() {}
	
	public JpaNews(News news) {
		this.id = news.getId();
		this.logInformation = new JpaLogInformation(news.getLogInformation());
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public JpaUser getUser() {
		return user;
	}
	
	public void setUser(JpaUser user) {
		this.user = user;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "abstract")
	public String getAbstrac() {
		return abstrac;
	}

	public void setAbstrac(String abstrac) {
		this.abstrac = abstrac;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	public JpaCategory getCategory() {
		return category;
	}

	public void setCategory(JpaCategory category) {
		this.category = category;
	}

	@Column(name = "force_date")
	public Date getForceDate() {
		return forceDate;
	}

	public void setForceDate(Date forceDate) {
		this.forceDate = forceDate;
	}

	@Column(name = "is_force")
	public boolean isForce() {
		return isForce;
	}

	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}

	@Column(name = "is_comment")
	public boolean isComment() {
		return isComment;
	}

	public void setComment(boolean isComment) {
		this.isComment = isComment;
	}
}
