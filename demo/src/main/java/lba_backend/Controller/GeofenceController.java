package lba_backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lba_backend.Entities.Geofence;
import lba_backend.Services.GeofenceImp;

@RestController
@CrossOrigin("*")
public class GeofenceController
{
	
//	public String     save(String shopname);
//	public List<Geofence> getAllByUsername(String username);
//	public String     delete(String shopname);
	
	@Autowired
	GeofenceImp igeofence;
	
	@PostMapping("/lba/geofence/save")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity save(@RequestBody Geofence geofence,Authentication auth)
	{
		
		UsernamePasswordAuthenticationToken usernamepasswordauthtoken=new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getAuthorities());
		if(auth.getAuthorities().size()==1)
		{
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(auth.getAuthorities().iterator().next().getAuthority());
			if((authority.getAuthority().equals("user") && (!usernamepasswordauthtoken.getName().equals(geofence.getUsername()))))
			{
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		}
	
		try
		{
			
			System.out.println("in save");
			return ResponseEntity.status(HttpStatus.OK)
					.body(igeofence.save(geofence));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}
	}
	
	
	
	@GetMapping("/lba/geofence/get/{username}")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity getAllByUsername(@PathVariable("username") String username,Authentication auth)
	{
		
		UsernamePasswordAuthenticationToken usernamepasswordauthtoken=new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getAuthorities());
		if(auth.getAuthorities().size()==1)
		{
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(auth.getAuthorities().iterator().next().getAuthority());
			if((authority.getAuthority().equals("user") && (!usernamepasswordauthtoken.getName().equals(username))))
			{
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			
		}
		try
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(igeofence.getAllByUsername(username));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	
	
	
	@PostMapping("/lba/geofence/delete/{username}")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity deleteGeofence(@PathVariable("username") String username,Authentication auth)
	{
		
		UsernamePasswordAuthenticationToken usernamepasswordauthtoken=new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getAuthorities());
		if(auth.getAuthorities().size()==1)
		{
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(auth.getAuthorities().iterator().next().getAuthority());
			if((authority.getAuthority().equals("user") && (!usernamepasswordauthtoken.getName().equals(username))))
			{
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		}
		
		try
		{
			return ResponseEntity.status(HttpStatus.OK)
					             .body(igeofence.delete(username));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	
}
