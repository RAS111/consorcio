package com.c823.consorcio.entity;

import com.c823.consorcio.enums.Amenities;
import com.c823.consorcio.enums.Turn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TURNS")
public class TurnEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "TURN_ID", nullable = false,unique = true)
  private Long turnId;

  @Enumerated(EnumType.STRING)
  @Column(name = "TURN_NAME",nullable = false,unique = true)
  private Turn amenitieName;

  @ManyToMany
  private List<UserEntity> users = new ArrayList<>();





}
