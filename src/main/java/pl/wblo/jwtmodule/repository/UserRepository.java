package pl.wblo.jwtmodule.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wblo.jwtmodule.repository.entity.JwtUser;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<JwtUser, Integer> {

     Optional<JwtUser> findByEmail(String email);

}
