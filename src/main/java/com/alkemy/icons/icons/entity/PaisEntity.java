package com.alkemy.icons.icons.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "pais")
@Getter
@Setter
@SQLDelete (sql = "UPDATE pais SET deleted = true WHERE id=?")
@Where (clause = "deleted=false")
class PaisEntity<ContinenteEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String denominacion;

    @Column(name = "cant_habitantes")
    private Long cantidadHabitantes;

    private Long superficie; // m2

    // SOLO Para SOFT DELETE:
    private boolean deleted = Boolean.FALSE;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,})
    @JoinColumn(name = "continente_id", insertable = false, updatable = false)
    private ContinenteEntity continente;


    @Column(name = "continente_id", nullable = false)
    private Long continenteId;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(
            name = "icon_pais",
            joinColumns= @JoinColumn(name = "pais_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<IconEntity> icons;

    {
        icons = new HashSet<>();
    }


    // Metodos



    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final PaisEntity other = (PaisEntity) obj;
        return other.id == this.id;
    }

}
