/**
 * 
 */
package org.meruvian.yama.showcase.bbb.room.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.bbb.response.BBBService;
import org.meruvian.yama.showcase.bbb.room.Room;
import org.meruvian.yama.showcase.bbb.room.RoomService;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author dianw
 *
 */
@Action(name = "/conferences")
public class ConferenceAction {
	@Inject
	private RoomService roomService;
	
	@Inject
	private BBBService bbbService;
	
	@Action
	public ActionResult index(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		page = page < 1 ? 1 : page;
		Page<? extends Room> rooms = roomService.findRoomByName(StringUtils.defaultString(q), new PageRequest(page - 1, max));
		
		return new ActionResult("freemarker", "/view/conference/room/room-list.ftl")
				.addToModel("rooms", rooms);
	}
	
	@Action(name = "/{id}")
	public ActionResult joinConf(@ActionParam("id") String id) {
		return new ActionResult("redirect", bbbService.joinMeeting(id, SessionCredentials.getCurrentUser().getId(), false));
	}
}
