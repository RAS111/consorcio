package com.c823.consorcio.entity;

import com.c823.consorcio.enums.Amenities;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity

public class AmenitieEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "AMENITIE_NAME",nullable = false,unique = true)
    private Amenities amenitieName;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();



}
