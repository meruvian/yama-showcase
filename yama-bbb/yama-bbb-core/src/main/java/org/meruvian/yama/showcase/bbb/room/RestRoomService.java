/**
 * 
 */
package org.meruvian.yama.showcase.bbb.room;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 *
 */
@Service
@Transactional(readOnly = true)
public class RestRoomService implements RoomService {
	@Inject
	private RoomRepository roomRepository;
	
	@Inject
	private RoomParticipantRepository participantRepository;

	@Override
	public Room getRoomById(String roomId) {
		return roomRepository.findById(roomId);
	}
	
	@Override
	public Page<Room> findRoomByName(String name, Pageable pageable) {
		return roomRepository.findByNameContains(name, pageable);
	}

	@Override
	@Transactional
	public boolean removeRoom(String roomId) {
		Room room = roomRepository.findById(roomId);
		room.getLogInformation().setActiveFlag(LogInformation.INACTIVE);
		
		return true;
	}

	@Override
	@Transactional
	public Room saveRoom(Room room) {
		if (StringUtils.isBlank(room.getId())) {
			room.setId(null);
			return roomRepository.save(room);
		} else {
			Room m = roomRepository.findById(room.getId());
			m.setName(room.getName());
			m.setDescription(room.getDescription());
			m.setWelcome(room.getWelcome());
			m.setDuration(room.getDuration());
			m.setRecord(room.isRecord());
			m.setRunning(room.isRunning());
			
			return m;
		}
	}

	@Override
	public Page<RoomParticipant> getRoomAndParticipants(String roomId, String username, Pageable pageable) {
		return participantRepository.findByRoomIdAndParticipantUsernameContains(roomId, username, pageable);
	}

	@Override
	public Page<RoomParticipant> findParticipantsRooms(String username, String roomname, Pageable pageable) {
		return participantRepository.findByParticipantUsernameAndRoomNameContains(username, roomname, pageable);
	}
}
