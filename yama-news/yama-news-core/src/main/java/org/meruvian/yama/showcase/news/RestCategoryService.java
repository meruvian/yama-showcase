/**
 * 
 */
package org.meruvian.yama.showcase.news;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 *
 */
@Service
@Transactional(readOnly = true)
public class RestCategoryService implements CategoryService {
	private CategoryRepository categoryRepository;

	@Inject
	public RestCategoryService(CategoryRepository categoryRepository){
		this.categoryRepository = categoryRepository;
	}
	
	public Category getCategoryById(String id) {
		return categoryRepository.findOne(id);
	}

	public Page<Category> findCategoryByName(String name, Pageable pageable) {
		name = StringUtils.defaultIfBlank(name, "");
		return categoryRepository.findByNameContainingAndLogInformationActiveFlag(name, LogInformation.ACTIVE, pageable);
	}

	@Transactional
	public boolean removeCategory(Category category) {
		if (category == null)
			return false;
		
		categoryRepository.delete(category.getId());
		
		return true;
	}

	@Transactional
	public Category saveCategory(Category category) {
		if (StringUtils.isBlank(category.getId()))
			categoryRepository.save(category);
		else {
			Category c = categoryRepository.findOne(category.getId());
			c.setName(category.getName());
			c.setDescription(category.getDescription());
			c.setParentCategory(category.getParentCategory());
			
			category = c;
		}
		
		return category;
	}

	@Transactional
	public Category updateStatus(Category category, int status) {
		category = categoryRepository.findOne(category.getId());
		category.getLogInformation().setActiveFlag(status);
		
		return category;
	}
}
