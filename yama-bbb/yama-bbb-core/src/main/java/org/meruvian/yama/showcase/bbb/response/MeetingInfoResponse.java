/**
 * 
 */
package org.meruvian.yama.showcase.bbb.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author dianw
 * 
 */
@JacksonXmlRootElement(localName = "response")
public class MeetingInfoResponse {
	private String returncode;
	private String meetingName;
	private String meetingID;
	private long createTime;
	private String createDate;
	private String voiceBridge;
	private String attendeePW;
	private String moderatorPW;
	private String running;
	private String recording;
	private String hasBeenForciblyEnded;
	private long startTime;
	private long endTime;
	private long participantCount;
	private long maxUsers;
	private long moderatorCount;
	private String metadata;
	private String messageKey;
	private String message;
	
	@JacksonXmlProperty(localName = "attendee")
	@JacksonXmlElementWrapper(localName = "attendees")
	private List<Attendee> attendees = new ArrayList<Attendee>();
	
	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getMeetingID() {
		return meetingID;
	}

	public void setMeetingID(String meetingID) {
		this.meetingID = meetingID;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getVoiceBridge() {
		return voiceBridge;
	}

	public void setVoiceBridge(String voiceBridge) {
		this.voiceBridge = voiceBridge;
	}

	public String getAttendeePW() {
		return attendeePW;
	}

	public void setAttendeePW(String attendeePW) {
		this.attendeePW = attendeePW;
	}

	public String getModeratorPW() {
		return moderatorPW;
	}

	public void setModeratorPW(String moderatorPW) {
		this.moderatorPW = moderatorPW;
	}

	public String getRunning() {
		return running;
	}

	public void setRunning(String running) {
		this.running = running;
	}

	public String getRecording() {
		return recording;
	}

	public void setRecording(String recording) {
		this.recording = recording;
	}

	public String getHasBeenForciblyEnded() {
		return hasBeenForciblyEnded;
	}

	public void setHasBeenForciblyEnded(String hasBeenForciblyEnded) {
		this.hasBeenForciblyEnded = hasBeenForciblyEnded;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getParticipantCount() {
		return participantCount;
	}

	public void setParticipantCount(long participantCount) {
		this.participantCount = participantCount;
	}

	public long getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		this.maxUsers = maxUsers;
	}

	public long getModeratorCount() {
		return moderatorCount;
	}

	public void setModeratorCount(long moderatorCount) {
		this.moderatorCount = moderatorCount;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Attendee> getAttendees() {
		return attendees;
	}
	
	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}
}
