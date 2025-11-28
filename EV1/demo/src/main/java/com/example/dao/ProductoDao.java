package com.example.dao;

public interface ProductoDao {
int crearBBDD();
int actualizarBBDD();
void listarProducto(String tipo);
void listarFiltrado();
void actualizarFav();

}
