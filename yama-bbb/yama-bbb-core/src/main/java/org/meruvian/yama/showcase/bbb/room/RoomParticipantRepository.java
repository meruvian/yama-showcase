/**
 * 
 */
package org.meruvian.yama.showcase.bbb.room;

import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author dianw
 *
 */
@Repository
public interface RoomParticipantRepository extends DefaultRepository<RoomParticipant> {
	@Query("SELECT mp FROM RoomParticipant mp WHERE mp.room.id = ?1 AND (mp.participant.username LIKE %?2% AND mp.participant.logInformation.activeFlag = 1)")
	Page<RoomParticipant> findByRoomIdAndParticipantUsernameContains(String roomId, String username, Pageable pageable);
	
	@Query("SELECT mp FROM RoomParticipant mp WHERE mp.participant.username = ?1 AND (mp.room.name LIKE %?2% AND mp.room.logInformation.activeFlag = 1)")
	Page<RoomParticipant> findByParticipantUsernameAndRoomNameContains(String username, String roomName, Pageable pageable);
}