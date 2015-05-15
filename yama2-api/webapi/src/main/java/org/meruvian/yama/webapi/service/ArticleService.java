
package org.meruvian.yama.webapi.service;

import java.util.List;

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

import org.meruvian.yama.webapi.entity.Article;
import org.meruvian.yama.webapi.entity.Article.Status;
import org.meruvian.yama.webapi.entity.ArticleTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@Path("/api/articles")
@Api("/articles")
@Produces(MediaType.APPLICATION_JSON)
public interface ArticleService {
	@GET
	@Path("/{articleId}")
	@ApiOperation(httpMethod = "GET", value = "Get article by ID", response = Article.class)
	Article getArticleById(@PathParam("articleId") String articleId);

	@GET
	@ApiOperation(httpMethod = "GET", value = "Find article by keyword", response = Article.class, responseContainer = "Page")
	Page<Article> findArticleByKeyword(@QueryParam("q") @DefaultValue("") String keyword, @QueryParam("s") List<Status> status, 
			Pageable pageable);
	
	@DELETE
	@Path("/{articleId}")
	@ApiOperation(httpMethod = "DELETE", value = "Delete article by ID")
	void removeArticle(@PathParam("articleId") String articleId);

	@POST
	@ApiOperation(httpMethod = "POST", value = "Post article", response = Article.class)
	Article saveArticle(Article article);
	
	@PUT
	@Path("/{articleId}")
	@ApiOperation(httpMethod = "PUT", value = "PUT article by ID", response = Article.class)
	Article updateArticle(Article article);
	
	@GET
	@Path("/{articleId}/tags")
	@ApiOperation(httpMethod = "GET", value = "Get Article Tags", response = ArticleTags.class, responseContainer = "Page")
	Page<ArticleTags> findTagsByArticle(@PathParam("articleId") String articleId, Pageable pageable);
	
}
