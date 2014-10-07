package org.meruvian.yama.showcase.news;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends DefaultRepository<News> {
	public Page<News> findByTitleContainingAndLogInformationActiveFlag(String title,int activeFlag, Pageable pageable);
}
