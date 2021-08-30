package lba_backend.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lba_backend.Entities.Role;



@Repository
public interface RoleDao extends JpaRepository<Role,String>
{

}
