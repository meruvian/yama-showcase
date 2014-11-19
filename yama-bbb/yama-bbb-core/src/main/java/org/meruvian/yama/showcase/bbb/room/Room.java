/**
 * 
 */
package org.meruvian.yama.showcase.bbb.room;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.showcase.bbb.response.MeetingStatusResponse;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "conference_room")
public class Room extends DefaultJpaPersistence {
	private String moderatorPassword = UUID.randomUUID().toString();
	private String attendeePassword = UUID.randomUUID().toString();
	private String name;
	private String description;
	private String welcome;
	private long duration = 0;
	private boolean record = false;
	private boolean running = false;
	private MeetingStatusResponse status = new MeetingStatusResponse();

	@Column(name = "moderator_password", nullable = false)
	public String getModeratorPassword() {
		return moderatorPassword;
	}

	public void setModeratorPassword(String moderatorPassword) {
		this.moderatorPassword = moderatorPassword;
	}

	@Column(name = "attendee_password", nullable = false)
	public String getAttendeePassword() {
		return attendeePassword;
	}

	public void setAttendeePassword(String attendeePassword) {
		this.attendeePassword = attendeePassword;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(length = 500)
	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	@Column(nullable = false)
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public boolean isRecord() {
		return record;
	}

	public void setRecord(boolean record) {
		this.record = record;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Transient
	public MeetingStatusResponse getStatus() {
		return status;
	}

	public void setStatus(MeetingStatusResponse status) {
		this.status = status;
	}

}
