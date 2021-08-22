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
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_id")
    private String name;

    @Column(name = "price")
    private float price;

    @OneToMany(mappedBy = "tariff")
    private List<Cellphone> cellphone = new ArrayList<>();

}
