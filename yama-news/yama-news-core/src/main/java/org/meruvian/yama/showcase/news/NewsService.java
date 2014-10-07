/**
 * 
 */
package org.meruvian.yama.showcase.news;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author dianw
 *
 */
@Path("/news")
@Api("/news")
@Produces(MediaType.APPLICATION_JSON)
public interface NewsService {

	@GET
	@ApiOperation(httpMethod = "GET", value = "Find news by keyword", response = News.class, responseContainer = "List")
	Page<News> findNewsByKeyword(
			@ApiParam("Keyword") @QueryParam("q") String q,
			@ApiParam(value = "Max", defaultValue = "10") @QueryParam("max") @DefaultValue("10") int max,
			@QueryParam("page") int page);

	News saveNews(News news);
	
	@GET
	@ApiOperation(httpMethod = "GET", value = "Find news by ID", response = News.class)
	@Path("/{id}")
	News getNewsById(@ApiParam("ID") @PathParam("id") String id);

	@POST
	@ApiOperation(httpMethod = "POST", value = "Update News", response = News.class)
	@Path("/{id}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	News updateNews(@ApiParam("ID") @PathParam("id") String id, News news);

	@POST
	@ApiOperation(httpMethod = "POST", value = "Edit News", response = News.class)
	@Path("/{id}/statuses")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	News updateNewsStatus(@ApiParam("ID") @QueryParam("id") final String id, @ApiParam("Status") @QueryParam("status") int status);
}
