/**
 * 
 */
package org.meruvian.yama.showcase.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dianw
 *
 */
public interface CategoryService {
	Category getCategoryById(String id);

	Page<? extends Category> findCategoryByName(String name, Pageable pageable);
	
	boolean removeCategory(Category Category);

	Category saveCategory(Category Category);
	
	Category updateStatus(Category Category, int status);
}
