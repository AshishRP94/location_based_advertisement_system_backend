package lba_backend.Services;

import java.util.List;

import lba_backend.Entities.Geofence;
import lba_backend.Entities.User;

public interface GeofenceInterface
{
	public String     save(Geofence shopname);
	public List<Geofence> getAllByUsername(String username);
	public String     delete(String shopname);
	
}
