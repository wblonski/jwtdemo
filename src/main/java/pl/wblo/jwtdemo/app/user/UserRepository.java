package pl.wblo.jwtdemo.app.user;


import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    default Optional<User> findByEmail(String email) {
        return findAll().stream().filter(s -> s.getEmail().equals(email)).findFirst();
    }

    default User save(@Nonnull User user) {
        return saveAndFlush(user);
    }

    ;
}
