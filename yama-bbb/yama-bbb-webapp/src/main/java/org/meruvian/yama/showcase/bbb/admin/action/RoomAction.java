/**
 * 
 */
package org.meruvian.yama.showcase.bbb.admin.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.bbb.room.Room;
import org.meruvian.yama.showcase.bbb.room.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dianw
 *
 */
@Action(name = "/admin/conference/rooms")
public class RoomAction extends ActionSupport {
	@Inject
	private RoomService roomService;
	
	@Action(method = HttpMethod.GET)
	public ActionResult roomList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		page = page < 1 ? 1 : page;
		Page<? extends Room> rooms = roomService.findRoomByName(StringUtils.defaultString(q), new PageRequest(page - 1, max));
		
		return new ActionResult("freemarker", "/view/admin/conference/room/room-list.ftl")
				.addToModel("rooms", rooms);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult roomForm(@ActionParam("id") String name) {
		ActionResult actionResult = new ActionResult("freemarker", "/view/admin/conference/room/room-form.ftl");
		
		if (!StringUtils.equalsIgnoreCase(name, "-")) {
			Room room = roomService.getRoomById(name);
			actionResult.addToModel("room", room);
		} else {
			actionResult.addToModel("room", new Room());
		}
		
		return actionResult;
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateRoom(@ActionParam("id") String name,  @ActionParam("room") Room room) {
		validateRoom(room);
		if (hasFieldErrors()) {
			return new ActionResult("freemarker", "/view/admin/conference/room/room-form.ftl");
		}
		
		Room r = roomService.saveRoom(room);
		String redirectUri = StringUtils.join("/admin/conference/rooms/", r.getId(), "/edit?success");
		
		if (StringUtils.equalsIgnoreCase(name, "-")) {
			redirectUri = "/admin/conference/rooms?success";
		}
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.POST)
	public ActionResult deleteRoom(@ActionParam("id") String name) {
		roomService.removeRoom(name);
		
		return new ActionResult("redirect", "/admin/conference/rooms");
	}
	
	private void validateRoom(Room room) {
		if (StringUtils.isBlank(room.getName())) {
			addActionError("message.admin.conference.room.name.notempty");
		}
	}
}
