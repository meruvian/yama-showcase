package org.meruvian.yama.webapi.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.meruvian.yama.core.DefaultPersistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "yama2showcase_article_tags")
public class ArticleTags extends DefaultPersistence {
	private Article article;
	private String tags;

	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "article_id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
