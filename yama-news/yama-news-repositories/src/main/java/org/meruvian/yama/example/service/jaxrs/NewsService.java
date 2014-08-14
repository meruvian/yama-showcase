package org.meruvian.yama.example.service.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.Form;
import org.meruvian.yama.example.repository.jpa.news.JpaNews;
import org.springframework.data.domain.Page;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/news")
@Api("/news")
@Produces(MediaType.APPLICATION_JSON)
public interface NewsService {

	@GET
	@ApiOperation(httpMethod = "GET", value = "Find News by keyword", response = JpaNews.class, responseContainer = "List")
	Page<? extends JpaNews> getNewses(
			@ApiParam("Keyword") @QueryParam("q") String q,
			@ApiParam(value = "Max", defaultValue = "10") @QueryParam("max") @DefaultValue("10") int max,
			@QueryParam("page") int page);

	@GET
	@ApiOperation(httpMethod = "GET", value = "Find News by ID", response = JpaNews.class)
	@Path("/{id}")
	JpaNews newsForm(@ApiParam("ID") @PathParam("id") String id);

	@POST
	@ApiOperation(httpMethod = "POST", value = "Edit News", response = JpaNews.class)
	@Path("/{id}")
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	JpaNews updateNews(@ApiParam("ID") @PathParam("id") String id, JpaNews news);

	@POST
	@ApiOperation(httpMethod = "POST", value = "Edit News", response = JpaNews.class)
	@Path("/{id}/statuses")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	JpaNews updateNewsStatus(@ApiParam("ID") @QueryParam("id") final String id,
			@ApiParam("Status") @QueryParam("status") int status);
}
