package by.bsuir.softcompony.entity;

import javax.persistence.*;

@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "level")
    private String level;
    @Column(name = "category")
    private String category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stage_id")
    private Stage stage;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="response_id")
    private VacancyResponse vacancyResponse;

    public Vacancy() {
    }

    public Vacancy(String name, String level, String category, Stage stage, VacancyResponse vacancyResponse) {
        this.name = name;
        this.level = level;
        this.category = category;
        this.stage = stage;
        this.vacancyResponse = vacancyResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public VacancyResponse getVacancyResponse() {
        return vacancyResponse;
    }

    public void setVacancyResponse(VacancyResponse vacancyResponse) {
        this.vacancyResponse = vacancyResponse;
    }
}
