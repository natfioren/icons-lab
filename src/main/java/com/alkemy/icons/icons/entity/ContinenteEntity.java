package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name="continente")
@Getter
@Setter
class ContinenteEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String denominacion;

}

