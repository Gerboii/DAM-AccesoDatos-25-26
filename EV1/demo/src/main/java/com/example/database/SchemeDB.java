package com.example.database;

public class SchemeDB {
/*Productos: id (pk), nombre, descripción, cantidad, precio.
o Productos_Fav: id (pk), id_producto (fk) */
String TAB_PROD = "productos";
String TAB_FAV = "productos_fav"; 

String COL_ID = "id";
String COL_NAME = "nombre";
String COL_DESCRIP = "descripcion";
String COL_CANTIDAD = "cantidad";
String COL_PRECIO = "precio";
String COL_ID_PROD = "id_producto";

}
