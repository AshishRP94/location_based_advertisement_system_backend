package lba_backend.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lba_backend.Entities.Geofence;

@Repository
public interface GeofenceDao extends JpaRepository<Geofence,String>
{
	public List<Geofence> findAllByUsername(String username);
	
}
