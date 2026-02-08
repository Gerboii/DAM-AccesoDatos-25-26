package com.example.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="asignatura")
public class Asignatura {
    @Id
    @Column(name="nombre")
    private String nombre;

    //Varias asignaturas se pueden impartir por el mismo profesor.
    @ManyToOne//TODO (fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_profesor") // Esta es la FK en la base de datos
    private Profesor profesor;
}
