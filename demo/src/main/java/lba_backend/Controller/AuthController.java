package lba_backend.Controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lba_backend.Entities.LoginRequest;
import lba_backend.config.JwtUtil;
import lba_backend.config.UserDetailServiceImp;



@RestController
@CrossOrigin("*")
public class AuthController
{
	@Autowired
	private AuthenticationManager auth;
	
	@Autowired
	private UserDetailServiceImp user;
	
	@Autowired
	private JwtUtil jwt;
    

	
	
	@PostMapping("/generatetoken")
	public  ResponseEntity generatetoken(@RequestBody LoginRequest req) throws Exception
	{
		
		System.out.println("in generate token");
		try
		{
			authenticate(req.getUsername(),req.getPassword());
		}
		
		catch(UsernameNotFoundException  e)
		{
			e.printStackTrace();
			System.out.println("username not found");	
		}
		

		UserDetails ud=this.user.loadUserByUsername(req.getUsername());
		String token=this.jwt.generateToken(ud);
		String prefix="token:";
		return ResponseEntity.ok((prefix+token));	
	}
	
	
	private  void authenticate(String username,String password) throws Exception
	{
		
		try
		{
			auth.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		
		catch(DisabledException e)
		{
			throw new Exception("User Disabled"+e.getMessage());
		}
		
		catch(BadCredentialsException e)
		{
			throw new Exception("Invalid Credentials"+e.getMessage());
		}
	}
	
	
	

}
