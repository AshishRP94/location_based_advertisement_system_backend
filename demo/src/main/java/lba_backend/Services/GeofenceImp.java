package lba_backend.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lba_backend.Dao.GeofenceDao;
import lba_backend.Email.EmailService;
import lba_backend.Entities.Geofence;
import lba_backend.Entities.User;

@Service
public class GeofenceImp implements GeofenceInterface
{
	@Autowired
	GeofenceDao geofencedao;
	
    @Autowired
    EmailService email;

	@Override
	public String save(Geofence geofence)
	{
		Optional<Geofence>temp=geofencedao.findById(geofence.getShopsname());
		String status="";
		if(temp.isEmpty()==true)
		{
			System.out.println("if in saved imp");
			geofencedao.save(geofence);
			status="sucessfully_saved";
		}
		else
		{			
			System.out.println("else in saved imp");
			status="username_exists";
		}
		
		return status;
	}
	
	
	
	@Override
	public List<Geofence> getAllByUsername(String username)
	{
		
		List<Geofence>tlist=geofencedao.findAllByUsername(username);
		if(tlist.size()==0)
		{
			return null;			
		}
		else
		{
		    return tlist;
		}
	}

	@Override
	public String delete(String shopname)
	{
		
		Optional<Geofence>temp=geofencedao.findById(shopname);
		String status="";
		if(temp.isEmpty()==false)
		{
			geofencedao.deleteById(shopname);
			status="sucessfully_deleted";
		}
		else
		{
			status="username_does_not_exists";
		}
		
		return status;
	}
	

}
