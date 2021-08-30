package lba_backend.Email;

public interface Notification
{
	public void sendOnSignup(String email,String name);
	public void sendoOnPrebook(String email,String product,Long amount,String name);
	public void deletePrebook(String email,String product,Long amount,String name);
}
