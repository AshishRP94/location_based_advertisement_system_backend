package lba_backend.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import lba_backend.Entities.Geofence;
import lba_backend.Entities.Prebook;


@Repository
public interface PrebookDao extends CrudRepository<Prebook,Long>
{
	public List<Prebook> findAllByUsername(String username);
	public void deleteAllByUsername(String username);
	
	
}
