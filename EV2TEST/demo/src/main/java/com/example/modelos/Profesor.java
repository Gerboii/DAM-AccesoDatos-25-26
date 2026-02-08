package com.example.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="profesor")
public class Profesor {
    @Id
    @Column(name="dni_profesor")
    private String dniProfesor;
    @Column(name="nombre",nullable = false)
    private String nombre;
    @Column(name="contrasena",nullable = false)
    private String contrasena;

    public Profesor(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
}
