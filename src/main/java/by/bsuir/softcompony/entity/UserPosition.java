package by.bsuir.softcompony.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "position")
public class UserPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    @Column(name = "position")
    private String position;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private List<User> users = new ArrayList<>();

    public UserPosition() {
    }

    public UserPosition(Long id, String position, List<User> users) {
        this.id = id;
        this.position = position;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
