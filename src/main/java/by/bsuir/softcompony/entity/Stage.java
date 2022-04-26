package by.bsuir.softcompony.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    @Column(name = "stage")
    private String stage;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private List<Task> tasks = new ArrayList<>();

    public Stage() {
    }

    public Stage(Long id, String stage, List<Task> tasks) {
        this.id = id;
        this.stage = stage;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}