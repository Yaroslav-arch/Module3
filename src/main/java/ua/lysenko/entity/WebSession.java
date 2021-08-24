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
@Table(name = "web_sessions")
public class WebSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "time_of_connection")
    private LocalDateTime timeOfConnection;

    @Column(name = "session_duration_sec")
    private int sessionDurationSec;

    @Column(name = "traffic_kb")
    private int trafficKb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gadget_id")
    private Gadget gadget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cellphone_id")
    private Cellphone cellphone;

}
