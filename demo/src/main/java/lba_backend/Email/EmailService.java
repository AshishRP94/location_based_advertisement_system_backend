package lba_backend.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailService implements Notification
{
	
	@Autowired
	JavaMailSender javamailsend;
	
	@Autowired
	SimpleMailMessage mail; 


	@Override
	public void sendOnSignup(String email,String name)
	{
		try
		{
		mail.setTo(email);
		mail.setSubject("Testing || Welcome "+name+"||"+"Your one stop destination for shopping");
		mail.setText("Welcome To Location Based Advertisement System");
		javamailsend.send(mail);
		}
		
		catch(Exception e)
		{
			System.out.println(""+e);
		}
	}

	@Override
	public void sendoOnPrebook(String email, String product, Long amount,String name)
	{
		
		try
		{
			mail.setTo(email);
		    mail.setSubject("Testing||Hi "+name);
		    mail.setText("This "+product+" has been sucessfully pre booked at "+amount+" rs");
		    javamailsend.send(mail);
		}
		
		catch(Exception e)
		{
			System.out.println(""+e);
		}

	}

	@Override
	public void deletePrebook(String email, String product, Long amount,String name)
	{
		try
		{
			mail.setTo(email);
		    mail.setSubject("Testing||Hi "+name);
		    mail.setText("This "+product+" has been sucessfully deleted");
		    javamailsend.send(mail);
		}
		
		catch(Exception e)
		{
			System.out.println(""+e);
		}

		
		
	}
}
