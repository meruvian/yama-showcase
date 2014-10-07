/**
 * 
 */
package org.meruvian.yama.showcase.news;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 *
 */
@Service
@Transactional(readOnly = true)
public class RestNewsService implements NewsService {
	private NewsRepository newsRepository;

	@Inject
	public RestNewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	@Override
	public Page<News> findNewsByKeyword(String q, int max, int page) {
		q = StringUtils.defaultIfBlank(q, "");
		return newsRepository.findByTitleContainingAndLogInformationActiveFlag(q, LogInformation.ACTIVE, new PageRequest(page, max));
	}

	@Override
	public News getNewsById(String id) {
		return newsRepository.findById(id);
	}
	
	@Override
	@Transactional
	public News saveNews(News news) {
		if (StringUtils.isBlank(news.getId())) {
			newsRepository.save(news);
		} else {
			News n = newsRepository.findOne(news.getId());
			n.setTitle(news.getTitle());
			n.setUser(news.getUser());
			n.setContent(news.getContent());
			n.setAbstracts(news.getAbstracts());
			n.setComment(news.isComment());
			n.setDescription(news.getDescription());
			n.setForce(news.isForce());
			n.setCategory(news.getCategory());
			n.setForceDate(news.getForceDate());
			
			news = n;
		}
		
		return news;
	}

	@Override
	@Transactional
	public News updateNews(String id, News news) {
		return saveNews(news);
	}

	@Override
	@Transactional
	public News updateNewsStatus(String id, int status) {
		News news = newsRepository.findOne(id);
		news.getLogInformation().setActiveFlag(status);
		
		return news;
	}

}
