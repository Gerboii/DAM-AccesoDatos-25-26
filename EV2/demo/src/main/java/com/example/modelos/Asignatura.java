package com.example.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name ="asignatura")
public class Asignatura {
    @Id
    private String nombre;
    private String dni_profesor;

    public Asignatura(String dni_profesor, String nombre) {
        this.nombre = nombre;
    }
}
