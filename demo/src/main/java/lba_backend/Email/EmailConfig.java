package lba_backend.Email;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class EmailConfig
{
	@Bean
	JavaMailSender javamailsender()
	{
		JavaMailSenderImpl mail=new JavaMailSenderImpl();
		mail.setHost("smtp.gmail.com");
		mail.setPort(587);
//		mail.setUsername("ewallet2222@gmail.com");
//		mail.setPassword("knpoymmngeddpzsy");
		mail.setUsername("lbasystemrait@gmail.com");
		mail.setPassword("jnaektitelnkqdhz");
		
		Properties props=new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		mail.setJavaMailProperties(props);
		return mail;	
	}
	
	
	
	@Bean
	SimpleMailMessage simplemailmessage()
	{
		return new SimpleMailMessage();
	}

}
