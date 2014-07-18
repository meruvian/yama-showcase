package org.meruvian.yama.example.service;

import org.meruvian.yama.example.repository.jpa.news.JpaNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsManager {
	JpaNews getNewsById(String id);

	Page<? extends JpaNews> findNewsByTitle(String title, Pageable pageable);
	
	boolean removeNews(JpaNews News);

	JpaNews saveNews(JpaNews News);
	
	JpaNews updateStatus(JpaNews News, int status);

}
