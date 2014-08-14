package org.meruvian.yama.example.repository.jpa.news;

import org.meruvian.yama.example.repository.news.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaNewsRepository extends NewsRepository<JpaNews> {
	
	public Page<JpaNews> findByTitleContainingAndLogInformationActiveFlag(String title,int activeFlag, Pageable pageable);
	
}
