
package org.meruvian.yama.webapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.web.SessionCredentials;
import org.meruvian.yama.webapi.entity.Article;
import org.meruvian.yama.webapi.entity.Article.Status;
import org.meruvian.yama.webapi.entity.ArticleTags;
import org.meruvian.yama.webapi.repository.ArticleRepository;
import org.meruvian.yama.webapi.repository.ArticleTagsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RestArticleService implements ArticleService {
	@Inject
	private ArticleRepository articleRepository;
	
	@Inject ArticleTagsRepository articleTagsRepository;

	@Override
	public Article getArticleById(String articleId) {
		return articleRepository.findById(articleId);
	}

	@Override
	public Page<Article> findArticleByKeyword(String keyword,
			List<Status> status, Pageable pageable) {
		keyword = StringUtils.join("%",keyword,"%");
		status = status == null ? new ArrayList<Status>() : (status.size() > 0) ? status : Arrays.asList(Status.DRAFT, Status.POSTED);
		return articleRepository.findByTitleOrContent(keyword, keyword, status, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public void removeArticle(String articleId) {
		getArticleById(articleId).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}

	@Override
	@Transactional
	public Article saveArticle(Article article) {
		
		if (StringUtils.isBlank(article.getId())) {
			article.setId(null);
			article.setAuthor(SessionCredentials.getCurrentUser());
			article.setCategory(article.getCategory());
			article.setContent(article.getContent());
			article.setPostDate(new Date());
			article.setStatus(Status.DRAFT);
			article.setSummary(article.getSummary());
			article.setTitle(article.getTitle());
			articleRepository.save(article);
			for(ArticleTags tags : article.getTags()){
				ArticleTags tag = new ArticleTags();
				tag.setArticle(article);
				tag.setTags(tags.getTags());
				articleTagsRepository.save(tag);
			}
			
			return article;
		}
		
		throw new BadRequestException("Id must be empty, use PUT method to update record");
		
	}

	@Override
	@Transactional
	public Article updateArticle(Article article) {
		
		Page<ArticleTags> t = findTagsByArticle(article.getId(), null);
		articleTagsRepository.delete(t);
		
		Article a = articleRepository.findById(article.getId());
		a.setAuthor(SessionCredentials.getCurrentUser());
		a.setCategory(article.getCategory());
		a.setContent(article.getContent());
		a.setPostDate(article.getPostDate());
		a.setStatus(article.getStatus());
		a.setSummary(article.getSummary());
		a.setTitle(article.getTitle());
		for(ArticleTags tags : article.getTags()){
			ArticleTags tag = new ArticleTags();
			tag.setArticle(article);
			tag.setTags(tags.getTags());
			articleTagsRepository.save(tag);
		}
		
		return article;
	}

	@Override
	public Page<ArticleTags> findTagsByArticle(String articleId,
			Pageable pageable) {
		return articleTagsRepository.findTagsByArticle(articleId, LogInformation.ACTIVE, pageable);
	}
	
}
