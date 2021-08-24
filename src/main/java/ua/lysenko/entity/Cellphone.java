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
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "number_id")
    private String number;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    public Tariff tariff;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;

}
