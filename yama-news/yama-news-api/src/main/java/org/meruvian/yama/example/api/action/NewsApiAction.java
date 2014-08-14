package org.meruvian.yama.example.api.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.annotations.Form;
import org.meruvian.yama.example.repository.jpa.news.JpaNews;
import org.meruvian.yama.example.service.NewsManager;
import org.meruvian.yama.example.service.jaxrs.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NewsApiAction implements NewsService {

	@Inject
	private NewsManager newsManager;
	
	public Page<? extends JpaNews> getNewses(String q, int max, int page){
		max = max == 0 ? 10 : max;
		return newsManager.findNewsByTitle(q, new PageRequest(page, max));
	}
	
	public JpaNews newsForm(String id) {
		if (!StringUtils.equalsIgnoreCase(id, "-"))
			return newsManager.getNewsById(id);
		else 
			return new JpaNews();
	}
	
	public JpaNews updateNews(String id, @Form JpaNews news) {
		System.out.println("news : "+news.getTitle());
		return newsManager.saveNews(news);
	}
	
	public JpaNews updateNewsStatus(final String id, int status) {
		JpaNews n = new JpaNews();
		n.setId(id);
		return newsManager.updateStatus(n, status);
	}
	
}
