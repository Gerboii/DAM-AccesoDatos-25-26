package com.example.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="alumno")
public class Alumno {
    @Id
    @Column(name="dni_alumno")
    private String dniAlumno;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="contrasena", nullable = false)
    private String contrasena;

    public Alumno(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
}
