package org.meruvian.yama.example.repository.jpa.category;

import org.meruvian.yama.example.repository.category.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaCategoryRepository extends CategoryRepository<JpaCategory>{
	public Page<JpaCategory> findByNameContainingAndLogInformationActiveFlag(String name,int activeFlag, Pageable pageable);
}
