package lba_backend.Services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lba_backend.Dao.RoleDao;
import lba_backend.Dao.UserDao;
import lba_backend.Email.EmailService;
import lba_backend.Entities.Role;
import lba_backend.Entities.User;


@Service
public class UserImp implements UserInterface
{
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	RoleDao roledao;
	
    @Autowired
    PasswordEncoder pencode;
    
    @Autowired
    EmailService email;
    
	@Override
	public String save(User user)
	{
		String status="";
		try
		{
			
			Role role=roledao.findById("user")
					.orElse(Role.builder().role("user").build());
			
		    Optional<User> temp=userdao.findById(user.getUsername());

			if(temp.isEmpty()==false)
			{
				status="username_exits";
			}
			else
			{
				
				  
			  User suser=User.builder().email(user.getEmail())
						               .password(pencode.encode(user.getPassword()))
						               .username(user.getUsername())
						               .roles(Arrays.asList(role))
						               .build();
				  
				userdao.save(suser);
				status="sucessfully_saved";
				email.sendOnSignup(user.getEmail(),user.getUsername());
			}
		}
		
		catch(Exception e)
		{
			System.out.println(""+e);
		}
		
		return status;
		
	}

	@Override
	public User get(String username)
	{
		System.out.println("in get");
		if(userdao.existsById(username)==true)
		{
			System.out.println("in if");
			User user=userdao.findById(username).get();
			return user;
		}
		return null;
	}
	

	@Override
	public String delete(String username)
	{
		String status="";
		Optional<User>user=userdao.findById(username);
		if(user.isPresent()==true)
		{
			 userdao.deleteById(username);
		     status="sucessfully_deleted";				
		}
		else
		{
			status="user_not_found";							
		}
		
		return status;
	}
	

	@Override
	public String updatePassowrd(String username, String password)
	{
		String status="";
		Optional<User>user=userdao.findById(username);
		if(user.isPresent()==true)
		{
			 User suser=User.builder().email(user.get().getEmail())
						               .password(password)
						               .username(user.get().getUsername())
						               .roles(user.get().getRoles())
						               .build();
			 
			 status="sucessfully_updated";				
			 userdao.save(suser);
		}
		else
		{
			status="user_not_found";					
		}
		return status;
	}

	
	@Override
	public String updateEmail(String username, String email)
	{
		String status="";
		
		Optional<User>user=userdao.findById(username);
		if(user.isPresent()==true)
		{
			 User suser=User.builder().email(email)
						               .password(user.get().getPassword())
						               .username(user.get().getUsername())
						               .roles(user.get().getRoles())
						               .build();
			 
			 status="sucessfully_updated";				
			 userdao.save(suser);
		}
		else
		{
			status="user_not_found";					
		}
		return status;
	}

	@Override
	public String deleleAccount(String username)
	{
		String status="";
		
		Optional<User>user=userdao.findById(username);
		if(user.isPresent()==true)
		{
			userdao.deleteById(username);
			status="sucessfully_account_deleted";
		}
		else
		{
			status="user_not_found";			
		}
		
		return status;
	}
	

}
