package com.c823.consorcio.entity;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
public class MessageEntity {

    @Id
    //@GeneratedValue (strategy = GenerationType.AUTO)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String detail;
    private String message;

    @CreationTimestamp
    @Column (name= "CREATION_DATE")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;



}
