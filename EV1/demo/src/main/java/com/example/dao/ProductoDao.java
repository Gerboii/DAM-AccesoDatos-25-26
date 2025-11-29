package com.example.dao;

import java.util.List;

import com.example.modelos.Producto;

public interface ProductoDao {
int crearBBDD();
void listarProducto(String tipo);
void listarFiltrado();
void actualizarFav();
void actualizarBBDD(List<Producto> listaproducto);

}
