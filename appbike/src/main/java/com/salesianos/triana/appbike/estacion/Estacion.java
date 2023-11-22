package com.salesianos.triana.appbike.estacion;

import com.salesianos.triana.appbike.bicicleta.Bicicleta;
import com.salesianos.triana.appbike.uso.Uso;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
public class Estacion {


    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NaturalId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;
    private String nombre;
    private String coordenadas;
    private int capacidad;

    @OneToMany(mappedBy = "estacion")
    private Set<Bicicleta> bicicletas;

    @OneToMany(mappedBy = "estacion")
    private List<Uso> usos;
}
