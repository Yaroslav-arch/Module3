package ua.lysenko.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_id")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cellphone_id")
    private Cellphone cellphone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gadget_id")
    public Gadget gadget;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caller")
    private List<Call> calls = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
