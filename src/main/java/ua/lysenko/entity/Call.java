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
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "time_of_call")
    private LocalDateTime timeOfCall;

    @Column(name = "call_duration_sec")
    private int callDurationSec;

    @Column(name = "caller_id")
    private long caller;

    @Column(name = "receiver_id")
    private long receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cellphone_id")
    private Cellphone cellphone;
}
