package org.meruvian.yama.example.repository.category;

import org.meruvian.yama.example.repository.jpa.category.JpaCategory;
import org.meruvian.yama.repository.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository<T extends Category> extends DefaultRepository<T> {
	Page<JpaCategory> findByNameContainingAndLogInformationActiveFlag(String name,int activeFlag, Pageable pageable);
}
