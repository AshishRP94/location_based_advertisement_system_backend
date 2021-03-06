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

import lba_backend.Entities.Prebook;
import lba_backend.Services.PrebookImp;

@RestController
@CrossOrigin("*")
public class PrebookController
{
	@Autowired
	PrebookImp iprebook; 
	
	
	@PostMapping("/lba/prebook/save")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity savePrebook(@RequestBody Prebook prebook,Authentication auth)
	{
		
		UsernamePasswordAuthenticationToken usernamepasswordauthtoken=new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getAuthorities());
		if(auth.getAuthorities().size()==1)
		{
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(auth.getAuthorities().iterator().next().getAuthority());
			if((authority.getAuthority().equals("user") && (!usernamepasswordauthtoken.getName().equals(prebook.getUsername()))))
			{
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			
		}
		
		try
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(iprebook.save(prebook));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}
	}
	
	
	@GetMapping("/lba/prebook/get/{username}")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity getPrebook(@PathVariable("username")String username,Authentication auth)
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
					.body(iprebook.get(username));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	}
	
	
	@GetMapping("/lba/prebook/delete/{username}/id/{id}")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity deletePrebook(@PathVariable("username")String username,@PathVariable("id")Long id,Authentication auth)
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
					.body(iprebook.delete(username,id));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	
	
	
}
