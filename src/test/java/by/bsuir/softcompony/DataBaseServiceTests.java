package by.bsuir.softcompony;

import by.bsuir.softcompony.entity.Stage;
import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.UserPosition;
import by.bsuir.softcompony.entity.Vacancy;
import by.bsuir.softcompony.entity.repository.StageRepository;
import by.bsuir.softcompony.entity.repository.UserPositionRepository;
import by.bsuir.softcompony.entity.repository.UserRepository;
import by.bsuir.softcompony.entity.repository.VacancyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataBaseServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPositionRepository userPositionRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void getStageByIdTest() {
        Stage stage = stageRepository.findById(1L).orElseThrow();
        assert(stage.getId()).equals(1L);
    }
    @Test
    public void getUserByIdTest() {
        User user = userRepository.findById(1L).orElseThrow();
        assert(user.getId()).equals(1L);
    }
    @Test
    public void getUserPositionByIdTest() {
        UserPosition userPosition = userPositionRepository.findById(1L).orElseThrow();
        assert(userPosition.getId()).equals(1L);
    }
    @Test
    public void getVacancyByIdTest() {
        Vacancy vacancy = vacancyRepository.findById(1L).orElseThrow();
        assert(vacancy.getId()).equals(1L);
    }

}
