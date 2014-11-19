/**
 * 
 */
package org.meruvian.yama.showcase.bbb.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dianw
 *
 */
public interface RoomService {
	Room getRoomById(String roomId);
	
	Page<Room> findRoomByName(String name, Pageable pageable);
	
	boolean removeRoom(String roomId);
	
	Room saveRoom(Room room);
	
	Page<RoomParticipant> getRoomAndParticipants(String roomId, String username, Pageable pageable);
	
	Page<RoomParticipant> findParticipantsRooms(String username, String roomname, Pageable pageable);
}
