
package org.meruvian.yama.webapi.service;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.webapi.entity.Category;
import org.meruvian.yama.webapi.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RestCategoryService implements CategoryService {
	@Inject
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getCategoryById(String id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Page<Category> findCategoryByKeyword(String keyword, Pageable pageable) {
		return categoryRepository.findByNameOrDescription(keyword, keyword, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public void removeCategory(String id) {
		getCategoryById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}

	@Override
	@Transactional
	public Category saveCategory(Category category) {
		if (StringUtils.isBlank(category.getId())) {
			category.setId(null);
			category.setName(category.getName());
			return categoryRepository.save(category);
		}
		
		throw new BadRequestException("Id must be empty, use PUT method to update record");
	}

	@Override
	@Transactional
	public Category updateCategory(Category category) {
		Category r = categoryRepository.findById(category.getId());
		r.setName(category.getName());
		r.setDescription(category.getDescription());
		
		return r;
	}
}
