package lba_backend.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lba_backend.Dao.PrebookDao;
import lba_backend.Dao.UserDao;
import lba_backend.Email.EmailService;
import lba_backend.Entities.Prebook;
import lba_backend.Entities.User;


@Service
public class PrebookImp implements PrebookInterface
{
	
	@Autowired
	PrebookDao dao;
	
	
	@Autowired
	UserDao userdao;
	
	
    @Autowired
    EmailService email;
	
	@Override
	public String save(Prebook prebook)
	{
		
	String status="";
//	Optional<Prebook>temp=	dao.findById(prebook.getId());
//	if(temp.isEmpty/()==true)
//	{
		dao.save(prebook);
		Optional<User>user=userdao.findById(prebook.getUsername());
		if(user.isEmpty()==false)
		{
			status="sucessfully_saved";
			email.sendoOnPrebook(user.get().getEmail(),prebook.getProduct(),prebook.getAmount(),user.get().getUsername());			
		}
		else
		{
			status="username_not_exists";			
		}
		
//	}
//	else
//	{
//		status="id_exists";
//	}
		return status;
	}

	@Override
	public List<Prebook> get(String username)
	{
		List<Prebook>result=dao.findAllByUsername(username);
		if(result.size()==0)
		{
			return null;
		}
		else
		{
			return result;			
		}
	}
	
	@Override
	public String delete(String username,Long id)
	{
		
		if(userdao.findById(username).isPresent()==true)
		{
			Optional<Prebook>prebook=dao.findById(id);
			email.deletePrebook(userdao.findById(username).get().getEmail(), prebook.get().getProduct(), prebook.get().getAmount(),username);
			dao.deleteById(id);
			return "sucessfully_deleted";
		}
		else
		{
			return "username_not_exists";
		}
		
	}
	
}
