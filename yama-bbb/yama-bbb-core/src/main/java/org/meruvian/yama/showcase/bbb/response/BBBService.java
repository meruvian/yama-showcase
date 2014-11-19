/**
 * 
 */
package org.meruvian.yama.showcase.bbb.response;


/**
 * @author dianw
 *
 */
public interface BBBService {
	MeetingInfoResponse getMeetingInfoById(String roomId);
	
	CreateMeetingResponse createMeeting(String roomId);
	
	EndMeetingResponse endMeeting(String roomId);
	
	String joinMeeting(String roomId, String userId, boolean asModerator);
}
