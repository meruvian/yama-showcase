/**
 * Copyright 2014 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.webapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javassist.expr.NewArray;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.web.SessionCredentials;
import org.meruvian.yama.webapi.entity.Article;
import org.meruvian.yama.webapi.entity.Article.Status;
import org.meruvian.yama.webapi.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 *
 */
@Service
@Transactional(readOnly = true)
public class RestArticleService implements ArticleService {
	@Inject
	private ArticleRepository articleRepository;
	
	@Override
	public Article getArticleById(String articleId) {
		return articleRepository.findById(articleId);
	}

	@Override
	public Page<Article> findArticleByKeyword(String keyword, List<Status> statuses, Pageable pageable) {
		keyword = StringUtils.join("%", keyword, "%");
		statuses = statuses == null ? new ArrayList<Status>() : (statuses.size() > 0) ? statuses : Arrays.asList(Status.DRAFT, Status.POSTED);
		return articleRepository.findByTitleOrContent(keyword, keyword, statuses, LogInformation.ACTIVE, pageable);
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
			article.setStatus(Status.DRAFT);
			return articleRepository.save(article);
		}
		
		throw new BadRequestException("Id must be empty, use PUT method to update record");
	}

	@Override
	@Transactional
	public Article updateArticle(String articleId, Article article) {
		Article a = getArticleById(article.getId());
		a.setTitle(article.getTitle());
		a.setContent(article.getContent());
		a.setAuthor(article.getAuthor());
		a.setStatus(article.getStatus());
		a.setCategory(article.getCategory());
		
		return a;
	}
}