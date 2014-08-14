package org.meruvian.yama.example.service;

import org.meruvian.yama.example.repository.jpa.category.JpaCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryManager {
	JpaCategory getCategoryById(String id);

	Page<? extends JpaCategory> findCategoryByName(String name, Pageable pageable);
	
	boolean removeCategory(JpaCategory Category);

	JpaCategory saveCategory(JpaCategory Category);
	
	JpaCategory updateStatus(JpaCategory Category, int status);

}
