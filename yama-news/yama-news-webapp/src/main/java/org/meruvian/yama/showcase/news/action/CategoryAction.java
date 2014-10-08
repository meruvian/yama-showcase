package org.meruvian.yama.showcase.news.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.news.Category;
import org.meruvian.yama.showcase.news.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/category")
public class CategoryAction extends ActionSupport {
	@Inject
	private CategoryService categoryService;
	
	@Action(method = HttpMethod.GET)
	public ActionResult categoryList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		Page<? extends Category> categories = categoryService.findCategoryByName(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/backend/category/category-list.ftl")
				.addToModel("categories", categories);
	}
	
	@Action(name = "{id}/edit", method = HttpMethod.GET)
	public ActionResult categoryForm(@ActionParam("id") String id) {
		ActionResult actionResult = new ActionResult("freemarker", "/view/backend/category/category-form.ftl");
		
		if (!StringUtils.equalsIgnoreCase(id, "-")) {
			Category category = categoryService.getCategoryById(id);
			actionResult.addToModel("category", category);
		} else {
			actionResult.addToModel("category", new Category());
		}
		
		return actionResult;
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateCategory(@ActionParam("id") String id,  @ActionParam("category") Category category) {
		if (hasFieldErrors())
			return new ActionResult("freemarker", "/view/backend/category/category-form.ftl");
		
		Category n = categoryService.saveCategory(category);
		String redirectUri = "/backend/category/" + n.getId() + "/edit?success";
		
		if (StringUtils.equalsIgnoreCase(id, "-"))
			redirectUri = "/backend/category?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/edit/status", method = HttpMethod.POST)
	public ActionResult updateCategoryStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		Category c = new Category() {{ setId(id); }};
		c = categoryService.updateStatus(c, status);
		
		String redirectUri = "/backend/news/" + c.getId() + "/edit?success";
		
		return new ActionResult("redirect", redirectUri);
	}
}
