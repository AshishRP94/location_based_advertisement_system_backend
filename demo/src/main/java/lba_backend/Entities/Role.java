package lba_backend.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Role implements GrantedAuthority
{
	@Id
	private String role; 

	@Override
	public String getAuthority()
	{
		return this.role;
	}
	
}
