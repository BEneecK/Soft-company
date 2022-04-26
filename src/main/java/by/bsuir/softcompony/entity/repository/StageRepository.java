package by.bsuir.softcompony.entity.repository;

import by.bsuir.softcompony.entity.Stage;
import org.springframework.data.repository.CrudRepository;

public interface StageRepository extends CrudRepository<Stage, Long> {
    Stage findByStage(String stage);
}
