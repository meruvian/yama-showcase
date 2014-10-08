package org.meruvian.yama.showcase.news;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DefaultRepository<Category> {
	public Page<Category> findByNameContainingAndLogInformationActiveFlag(String name, int activeFlag, Pageable pageable);
}
