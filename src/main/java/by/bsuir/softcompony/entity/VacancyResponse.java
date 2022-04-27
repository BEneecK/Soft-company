package by.bsuir.softcompony.entity;

import javax.persistence.*;

@Entity
@Table(name = "response")
public class VacancyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "cv_doc_name")
    private String CVDocName;
    @OneToOne(mappedBy = "vacancyResponse")
    private Vacancy vacancy;

    public VacancyResponse() {
    }

    public VacancyResponse(String firstName, String lastName, String email, String CVDocName, Vacancy vacancy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.CVDocName = CVDocName;
        this.vacancy = vacancy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCVDocName() {
        return CVDocName;
    }

    public void setCVDocName(String CVDocName) {
        this.CVDocName = CVDocName;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
