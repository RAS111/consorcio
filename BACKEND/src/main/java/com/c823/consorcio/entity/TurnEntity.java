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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TURNS")
public class TurnEntity { // Giro o turnos

  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TURN_ID", nullable = false,unique = true)
  private Long turnId;

  @Enumerated(EnumType.STRING)
  @Column(name = "TURN_NAME",nullable = false,unique = true)
  private Turn turnName;

  @ManyToMany
  private List<UserEntity> users = new ArrayList<>();





}
