package ua.lysenko.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cellphones")
public class Cellphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "number_id")
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id")
    public Tariff tariff;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cellphone")
    private User user_id;

    @OneToMany(mappedBy = "cellphone")
    List<Call> calls;

    @OneToMany(mappedBy = "cellphone")
    List<Message> messages;

    @OneToMany(mappedBy = "cellphone")
    List<WebSession> webSessions;
}
