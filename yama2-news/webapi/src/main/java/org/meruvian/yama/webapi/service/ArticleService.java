
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
public interface ArticleService {
	@GET
	@Path("/{articleId}")
	Article getArticleById(@PathParam("articleId") String articleId);

	@GET
	Page<Article> findArticleByKeyword(@QueryParam("q") @DefaultValue("") String keyword, @QueryParam("s") List<Status> status, 
			Pageable pageable);
	
	@DELETE
	@Path("/{articleId}")
	void removeArticle(@PathParam("articleId") String articleId);

	@POST
	Article saveArticle(Article article);
	
	@PUT
	@Path("/{articleId}")
	Article updateArticle(@PathParam("articleId") String articleId, Article article);
}
