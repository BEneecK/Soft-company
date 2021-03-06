package by.bsuir.softcompony.entity.repository;

import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.Vacancy;
import by.bsuir.softcompony.entity.VacancyResponse;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    Vacancy findByVacancyResponse(VacancyResponse vacancyResponse);
}
