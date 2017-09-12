package ciupa.repository;

import ciupa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kamil on 2017-09-05.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
    Role findByRole(String role);

}
