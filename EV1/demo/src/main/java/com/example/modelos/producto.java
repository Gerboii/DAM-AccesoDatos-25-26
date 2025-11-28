package com.example.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown=true)

public class producto {
// o Productos: id (pk), nombre, descripción, cantidad, precio.
//o Productos_Fav: id (pk), id_producto (fk)
int id;
String title;
String description;
double price;
int stock;
}
