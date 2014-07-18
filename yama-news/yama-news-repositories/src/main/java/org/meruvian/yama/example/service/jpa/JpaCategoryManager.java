package org.meruvian.yama.example.service.jpa;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.example.repository.jpa.category.JpaCategory;
import org.meruvian.yama.example.repository.jpa.category.JpaCategoryRepository;
import org.meruvian.yama.example.service.CategoryManager;
import org.meruvian.yama.repository.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JpaCategoryManager implements CategoryManager {
	private JpaCategoryRepository categoryRepository;

	@Inject
	public JpaCategoryManager(JpaCategoryRepository categoryRepository){
		this.categoryRepository = categoryRepository;
	}
	
	public JpaCategory getCategoryById(String id) {
		return categoryRepository.findOne(id);
	}

	public Page<? extends JpaCategory> findCategoryByName(String name,
			Pageable pageable) {
		name = StringUtils.defaultIfBlank(name, "");
		return categoryRepository.findByNameContainingAndLogInformationActiveFlag(name, LogInformation.ACTIVE, pageable);
	}

	@Transactional
	public boolean removeCategory(JpaCategory category) {
		if (category == null)
			return false;
		
		categoryRepository.delete(category.getId());
		
		return true;
	}

	@Transactional
	public JpaCategory saveCategory(JpaCategory category) {
		if (StringUtils.isBlank(category.getId()))
			categoryRepository.save(category);
		else {
			JpaCategory c = categoryRepository.findOne(category.getId());
			c.setName(category.getName());
			c.setDescription(category.getDescription());
			c.setParentCategory(category.getParentCategory());
			
			category = c;
		}
		
		return category;
	}

	@Transactional
	public JpaCategory updateStatus(JpaCategory category, int status) {
		category = categoryRepository.findOne(category.getId());
		category.getLogInformation().setActiveFlag(status);
		
		return category;
	}

}
