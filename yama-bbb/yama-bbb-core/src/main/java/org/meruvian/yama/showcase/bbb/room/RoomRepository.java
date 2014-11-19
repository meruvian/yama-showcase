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
public interface RoomRepository extends DefaultRepository<Room> {
	@Query("SELECT r FROM Room r WHERE r.name LIKE %?1% AND r.logInformation.activeFlag = 1")
	Page<Room> findByNameContains(String name, Pageable pageable);
}
