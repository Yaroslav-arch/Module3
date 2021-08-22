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
@Table(name = "gadgets")
public class Gadget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_id")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gadget")
    public List<WebSession> sessionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "gadget")
    private User user;


}
