package lba_backend.Services;

import lba_backend.Entities.User;

public interface UserInterface
{
	public String save(User user);
	public User   get(String username);
	public String delete(String username);
	public String updatePassowrd(String username,String password);
	public String updateEmail(String username,String email);
	public String deleleAccount(String username);
}
