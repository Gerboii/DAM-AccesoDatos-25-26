package com.example.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="nota")
public class Nota {
//Meto un Id para no tener que crear una clase independiente con las dos FKs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //FK
    @ManyToOne
    @JoinColumn(name="dni_alumno")
    private Alumno alumno;
    //FK
    @ManyToOne
    @JoinColumn(name="nombre_asignatura")
    private Asignatura asignatura;

    @Column(name="nota")
    private double valorNota;

    public double getValorNota() {
        return valorNota;
    }

}
