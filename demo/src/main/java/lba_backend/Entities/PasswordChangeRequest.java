package lba_backend.Entities;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PasswordChangeRequest
{
	String password;
	String username;
	
}
