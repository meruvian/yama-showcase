/**
 * 
 */
package org.meruvian.yama.showcase.bbb.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author dianw
 * 
 */
@JacksonXmlRootElement(localName = "meeting")
public class Meeting {
	@JacksonXmlProperty(localName = "meetingID")
	private String meetingId;
	@JacksonXmlProperty(localName = "createTime")
	private long createTime;
	@JacksonXmlProperty(localName = "attendeePW")
	private String attendeePassword;
	@JacksonXmlProperty(localName = "moderatorPW")
	private String moderatorPassword;
	@JacksonXmlProperty(localName = "hasBeenForciblyEnded")
	private String hasBeenForciblyEnded;
	@JacksonXmlProperty(localName = "messageKey")
	private String messageKey;
	@JacksonXmlProperty(localName = "message")
	private String message;
	
	private String meetingName;
	private String running;

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getAttendeePassword() {
		return attendeePassword;
	}

	public void setAttendeePassword(String attendeePassword) {
		this.attendeePassword = attendeePassword;
	}

	public String getModeratorPassword() {
		return moderatorPassword;
	}

	public void setModeratorPassword(String moderatorPassword) {
		this.moderatorPassword = moderatorPassword;
	}

	public String getHasBeenForciblyEnded() {
		return hasBeenForciblyEnded;
	}

	public void setHasBeenForciblyEnded(String hasBeenForciblyEnded) {
		this.hasBeenForciblyEnded = hasBeenForciblyEnded;
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

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getRunning() {
		return running;
	}

	public void setRunning(String running) {
		this.running = running;
	}
}
