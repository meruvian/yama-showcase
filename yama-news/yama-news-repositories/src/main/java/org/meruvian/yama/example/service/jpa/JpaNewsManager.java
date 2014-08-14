package org.meruvian.yama.example.service.jpa;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.example.repository.jpa.news.JpaNews;
import org.meruvian.yama.example.repository.jpa.news.JpaNewsRepository;
import org.meruvian.yama.example.service.NewsManager;
import org.meruvian.yama.repository.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JpaNewsManager implements NewsManager {
	private JpaNewsRepository newsRepository;
	
	@Inject
	public JpaNewsManager(JpaNewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	public JpaNews getNewsById(String id) {
		return newsRepository.findOne(id);
	}

	public Page<? extends JpaNews> findNewsByTitle(String title, Pageable pageable) {
		title = StringUtils.defaultIfBlank(title, "");
		return newsRepository.findByTitleContainingAndLogInformationActiveFlag(title, LogInformation.ACTIVE, pageable);
	}

	@Transactional
	public boolean removeNews(JpaNews news) {
		if (news == null)
			return false;
		
		newsRepository.delete(news.getId());
		
		return true;
	}

	@Transactional
	public JpaNews saveNews(JpaNews news) {
		System.out.println("saving... "+news.getId());
		if (StringUtils.isBlank(news.getId())) {
			newsRepository.save(news);
		} else {
			JpaNews n = newsRepository.findOne(news.getId());
			n.setTitle(news.getTitle());
			n.setUser(news.getUser());
			n.setContent(news.getContent());
			n.setAbstrac(news.getAbstrac());
			n.setComment(news.isComment());
			n.setDescription(news.getDescription());
			n.setForce(news.isForce());
			n.setCategory(news.getCategory());
			n.setForceDate(news.getForceDate());
			
			news = n;
		}
		
		return news;
	}

	@Transactional
	public JpaNews updateStatus(JpaNews news, int status) {
		System.out.println("sds"+news.getId());
		news = newsRepository.findOne(news.getId());
		news.getLogInformation().setActiveFlag(status);
		
		return news;
	}

}
