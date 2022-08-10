package my.plogging.Repository;

import my.plogging.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);
}
