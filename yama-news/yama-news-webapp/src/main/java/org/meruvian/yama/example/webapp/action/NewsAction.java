package org.meruvian.yama.example.webapp.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.example.repository.jpa.category.JpaCategory;
import org.meruvian.yama.example.repository.jpa.news.JpaNews;
import org.meruvian.yama.example.service.CategoryManager;
import org.meruvian.yama.example.service.NewsManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/news")
public class NewsAction extends ActionSupport {
	
	@Inject
	private NewsManager newsManager;
	
	@Inject
	private CategoryManager categoryManager;
	
	@Action(method = HttpMethod.GET)
	public ActionResult  newsList(@ActionParam("q") String q, @ActionParam("max") int max,
			@ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		Page<? extends JpaNews> news = newsManager.findNewsByTitle(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/backend/news/news-list.ftl")
				.addToModel("newss", news);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult newsForm(@ActionParam("id") String id) {
		Page<? extends JpaCategory> categories = categoryManager.findCategoryByName("", null);
		
		ActionResult actionResult = new ActionResult("freemarker", "/view/backend/news/news-form.ftl")
		.addToModel("categories", categories);
		
		if (!StringUtils.equalsIgnoreCase(id, "-")) {
			JpaNews news = newsManager.getNewsById(id);
			actionResult.addToModel("news", news);
		} else {
			actionResult.addToModel("news", new JpaNews());
		}
		
		return actionResult;
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateNews(@ActionParam("id") String id,  @ActionParam("news") JpaNews news) {
		if (hasFieldErrors())
			return new ActionResult("freemarker", "/view/backendca/news/news-form.ftl");
		
		if(news.getCategory().getId() != null)
			news.setCategory(categoryManager.getCategoryById(news.getCategory().getId()));
		
		JpaNews n = newsManager.saveNews(news);
		String redirectUri = "/backend/news/" + n.getId() + "/edit?success";
		
		if (StringUtils.equalsIgnoreCase(id, "-"))
			redirectUri = "/backend/news?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/edit/status", method = HttpMethod.POST)
	public ActionResult updateNewsStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		JpaNews n = new JpaNews();
		n.setId(id);
		System.out.println(n.getId());
		n = newsManager.updateStatus(n, status);
		
		String redirectUri = "/backend/news/" + n.getId() + "/edit?success";
		
		return new ActionResult("redirect", redirectUri);
	}

}
