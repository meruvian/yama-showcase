/**
 * Copyright 2014 Meruvian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meruvian.yama.webapi.repository;

import org.meruvian.yama.core.DefaultRepository;
import org.meruvian.yama.webapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 *
 */
@Repository
public interface CategoryRepository extends DefaultRepository<Category> {
	@Query("SELECT c FROM Category c WHERE (c.name LIKE %?1% OR c.description LIKE %?2%)"
			+ " AND c.logInformation.activeFlag = ?3")
	Page<Category> findByNameOrDescription(String name, String description, int activeFlag, Pageable pageable);
	
	@Query("SELECT c FROM Category c WHERE c.name = ?1 AND c.logInformation.activeFlag = ?2")
	Category getByName(String name, int activeFlag);
}
