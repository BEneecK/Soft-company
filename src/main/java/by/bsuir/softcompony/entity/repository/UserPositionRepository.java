package by.bsuir.softcompony.entity.repository;

import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.UserPosition;
import org.springframework.data.repository.CrudRepository;

public interface UserPositionRepository extends CrudRepository<UserPosition, Long> {
    UserPosition findByPosition(String position);
}
