package by.bsuir.softcompony.entity;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "document_name")
    private String taskDocName;
    @Column(name = "solution_doc_name")
    private String solutionDocName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stage_id")
    private Stage stage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    public Task() {}

    public Task(String description, String taskDocName, Client client) {
        this.id = id;
        this.description = description;
        this.taskDocName = taskDocName;
        this.client = client;
    }

    public Task(String description, String taskDocName, String solutionDocName, Client client, Stage stage, User user) {
        this.description = description;
        this.taskDocName = taskDocName;
        this.solutionDocName = solutionDocName;
        this.client = client;
        this.stage = stage;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskDocName() {
        return taskDocName;
    }

    public void setTaskDocName(String taskDocName) {
        this.taskDocName = taskDocName;
    }

    public String getSolutionDocName() {
        return solutionDocName;
    }

    public void setSolutionDocName(String solutionDocName) {
        this.solutionDocName = solutionDocName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
