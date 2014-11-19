/**
 * 
 */
package org.meruvian.yama.showcase.bbb.room;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.user.JpaUser;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "conference_room_participant", uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "participant_id"}))
public class RoomParticipant extends DefaultJpaPersistence {
	private Room room;
	private JpaUser participant;
	private boolean moderator = false;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@ManyToOne
	@JoinColumn(name = "participant_id", nullable = false)
	public JpaUser getParticipant() {
		return participant;
	}

	public void setParticipant(JpaUser participant) {
		this.participant = participant;
	}

	public boolean isModerator() {
		return moderator;
	}
	
	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}
}
