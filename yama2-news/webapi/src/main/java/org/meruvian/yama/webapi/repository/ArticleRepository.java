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
package org.meruvian.yama.webapi.repository;

import java.util.List;

import org.meruvian.yama.core.DefaultRepository;
import org.meruvian.yama.webapi.entity.Article;
import org.meruvian.yama.webapi.entity.Article.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 *
 */
@Repository
public interface ArticleRepository extends DefaultRepository<Article> {
	@Query("SELECT a FROM Article a WHERE (a.title LIKE ?1 OR a.content LIKE ?2)"
			+ " AND (a.status IN (?3)) AND a.logInformation.activeFlag = ?4")
	Page<Article> findByTitleOrContent(String title, String content, List<Status> status, 
			int activeFlag,	Pageable pageable);
	
	@Query("SELECT a FROM Article a WHERE YEAR(a.postDate) = ?1 AND a.status = 'POSTED'"
			+ " AND a.logInformation.activeFlag = ?2")
	Page<Article> findByPostDate(int year, int activeFlag, Pageable pageable);
	
	@Query("SELECT a FROM Article a WHERE YEAR(a.postDate) = ?1 AND MONTH(a.postDate) = ?2"
			+ " AND a.status = 'POSTED' AND a.logInformation.activeFlag = ?3")
	Page<Article> findByPostDate(int year, int month, int activeFlag, Pageable pageable);
	
	@Query("SELECT a FROM Article a WHERE YEAR(a.postDate) = ?1 AND MONTH(a.postDate) = ?2"
			+ " AND DAY(a.postDate) = ?3 AND a.status = 'POSTED'"
			+ " AND a.logInformation.activeFlag = ?4")
	Page<Article> findByPostDate(int year, int month, int day, int activeFlag, Pageable pageable);
}
