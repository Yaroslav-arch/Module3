package ua.lysenko.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "time_of_send")
    private LocalDateTime timeOfSend;

    @Column(name = "content")
    private String content;

    @Column(name = "sender_id")
    private long sender;

    @Column(name = "receiver_id")
    private long receiver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cellphone_id")
    private Cellphone cellphone;

}
