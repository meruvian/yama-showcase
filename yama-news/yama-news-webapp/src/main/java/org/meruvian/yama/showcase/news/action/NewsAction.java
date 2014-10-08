package org.meruvian.yama.showcase.news.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.news.Category;
import org.meruvian.yama.showcase.news.CategoryService;
import org.meruvian.yama.showcase.news.News;
import org.meruvian.yama.showcase.news.NewsService;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/news")
public class NewsAction extends ActionSupport {
	
	@Inject
	private NewsService newsService;
	
	@Inject
	private CategoryService categoryService;
	
	@Action(method = HttpMethod.GET)
	public ActionResult  newsList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		Page<News> news = newsService.findNewsByKeyword(q, max, page);
		
		return new ActionResult("freemarker", "/view/backend/news/news-list.ftl").addToModel("newss", news);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult newsForm(@ActionParam("id") String id) {		
		ActionResult actionResult = new ActionResult("freemarker", "/view/backend/news/news-form.ftl");
		
		Page<Category> categories = categoryService.findCategoryByName("", null);
		actionResult.addToModel("categories", categories);
		
		News news = new News();
		if (!StringUtils.equalsIgnoreCase(id, "-")) {
			news = newsService.getNewsById(id);
		} 
		actionResult.addToModel("news", news);

		return actionResult;
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateNews(@ActionParam("id") String id, @ActionParam("news") News news) {
		if (hasFieldErrors())
			return new ActionResult("freemarker", "/view/backendca/news/news-form.ftl");
		
		System.out.println(news.getCategory());
		
		if(news.getCategory().getId() != null)
			news.setCategory(categoryService.getCategoryById(news.getCategory().getId()));
		
		News n = newsService.saveNews(news);
		String redirectUri = StringUtils.join("/backend/news/", n.getId(), "/edit?success");
		
		if (StringUtils.equalsIgnoreCase(id, "-"))
			redirectUri = "/backend/news?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/edit/status", method = HttpMethod.POST)
	public ActionResult updateNewsStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		News news = newsService.updateNewsStatus(id, status);
		
		String redirectUri = StringUtils.join("/backend/news/", news.getId(), "/edit?success");
		
		return new ActionResult("redirect", redirectUri);
	}

}
