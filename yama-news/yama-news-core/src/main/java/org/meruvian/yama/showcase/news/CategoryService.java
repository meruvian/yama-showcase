/**
 * 
 */
package org.meruvian.yama.showcase.news;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author dianw
 *
 */
@Path("/categories")
@Api("/categories")
@Produces(MediaType.APPLICATION_JSON)
public interface CategoryService {

	@GET
	@Path("/{id}")
	@ApiOperation(httpMethod = "GET", value = "Get category by ID", response = Category.class)
	Category getCategoryById(@ApiParam("id") @PathParam("id") String id);

	@GET
	@ApiOperation(httpMethod = "GET", value = "Find category by name", response = Category.class, responseContainer = "Page")
	Page<Category> findCategoryByName(@ApiParam("name") @QueryParam("name") String name, Pageable pageable);
	
	boolean removeCategory(Category Category);

	Category saveCategory(Category Category);
	
	Category updateStatus(Category Category, int status);
}
