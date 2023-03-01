package com.c823.consorcio.entity;

import com.c823.consorcio.enums.Amenities;
import com.c823.consorcio.enums.Turn;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "AMENITIES_RESERVATIONS")
public class ReservationEntity {//Reserva

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AMENITY_ID")
    private Long reservationId;

    @Column(name = "NAME")
    private Amenities name;

    @Column(name = "RESERVATION_DATE")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate reservationDate;

    @Column(name = "TURN_ID")
    private Turn turn;

    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private boolean deleted = Boolean.FALSE;


}
