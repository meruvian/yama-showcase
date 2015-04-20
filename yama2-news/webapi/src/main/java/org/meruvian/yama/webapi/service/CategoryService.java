
package org.meruvian.yama.webapi.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.meruvian.yama.webapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
public interface CategoryService {
	@GET
	@Path("/{id}")
	Category getCategoryById(@PathParam("id") String id);

	@GET
	Page<Category> findCategoryByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);

	@DELETE
	@Path("/{id}")
	void removeCategory(@PathParam("id") String id);

	@POST
	Category saveCategory(Category category);
	
	@PUT
	@Path("/{id}")
	Category updateCategory(Category category);
}
