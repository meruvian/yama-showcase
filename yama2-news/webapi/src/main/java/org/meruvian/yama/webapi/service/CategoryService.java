
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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@Path("/api/categories")
@Api("/categories")
@Produces(MediaType.APPLICATION_JSON)
public interface CategoryService {
	@GET
	@Path("/{id}")
	@ApiOperation(httpMethod = "GET", value = "Get category by ID", response = Category.class)
	Category getCategoryById(@PathParam("id") String id);

	@GET
	@ApiOperation(httpMethod = "GET", value = "Find category by name", response = Category.class, responseContainer = "Page")
	Page<Category> findCategoryByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			Pageable pageable);

	@DELETE
	@Path("/{id}")
	@ApiOperation(httpMethod = "DELETE", value = "Delete category by ID")
	void removeCategory(@PathParam("id") String id);

	@POST
	@ApiOperation(httpMethod = "POST", value = "Post category", response = Category.class)
	Category saveCategory(Category category);
	
	@PUT
	@Path("/{id}")
	@ApiOperation(httpMethod = "PUT", value = "PUT category by ID", response = Category.class)
	Category updateCategory(Category category);
}
