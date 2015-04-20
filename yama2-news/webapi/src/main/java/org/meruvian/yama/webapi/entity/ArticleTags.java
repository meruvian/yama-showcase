package org.meruvian.yama.webapi.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.meruvian.yama.core.DefaultPersistence;

//@Entity
//@Table(name = "yama2showcase_article_tags")
public class ArticleTags extends DefaultPersistence {
	private Article article;
	private Tags tags;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "article_id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "tags_id")
	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}

}
