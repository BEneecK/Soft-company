package by.bsuir.softcompony.entity.repository;

import by.bsuir.softcompony.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsByEmail(String email);
    User findByEmail(String password);
}
