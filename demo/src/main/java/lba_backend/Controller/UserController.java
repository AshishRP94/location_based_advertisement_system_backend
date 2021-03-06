package lba_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import lba_backend.Entities.PasswordChangeRequest;
import lba_backend.Entities.User;
import lba_backend.Services.UserImp;

@RestController
@CrossOrigin("*")
public class UserController
{
	
	@Autowired
	UserImp iuser;
	
	@PostMapping("/lba/register")
    @PreAuthorize(value = "permitAll()")
	public ResponseEntity register(@RequestBody User user)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(iuser.save(user));
	}
	
	
	
	@GetMapping("/lba/get/{username}")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity getDetails(@PathVariable("username") String username,Authentication auth)
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
			System.out.println("in getdetails");
			return ResponseEntity.status(HttpStatus.OK)
					.body(iuser.get(username));
			
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

		}
		
	}
	
	
	@GetMapping("/lba/updateemail/{username}/{email}")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity updateEmail(@PathVariable("username") String username,@PathVariable("email") String email,Authentication auth)
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
					.body(iuser.updateEmail(username, email));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	}
	
	
	@PostMapping("lba/updatepassword")
	@PreAuthorize(value = "hasAnyAuthority('admin','user')")
	public ResponseEntity updatePassword(@RequestBody PasswordChangeRequest req,Authentication auth)
	{
		UsernamePasswordAuthenticationToken usernamepasswordauthtoken=new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getAuthorities());
		if(auth.getAuthorities().size()==1)
		{
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(auth.getAuthorities().iterator().next().getAuthority());
			if((authority.getAuthority().equals("user") && (!usernamepasswordauthtoken.getName().equals(req.getUsername()))))
			{
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		}
		
		try
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(iuser.updatePassowrd(req.getUsername(), req.getPassword()));
		}
		catch(Exception e)
		{
			System.out.println("error-->"+e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}	
	}
	
	
}
