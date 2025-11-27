package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

/*Northwind
En nuestra empresa northwind, tenemos que gestionar una base de datos, cuyo objetivo
principal es la gestión de productos, etc.
Se pide la realización de una aplicación que:

    • Crea una base de datos llamada almacén con las siguientes tablas y campos
        o Productos: id (pk), nombre, descripción, cantidad, precio.
        o Productos_Fav: id (pk), id_producto (fk)

    • Agregar todos los productos que están ubicados en el siguiente JSON dentro de
    la tabla productos: https://dummyjson.com/products

    • Muestra por consola mediante la ejecución de querys – statement:
        o Todos los productos
        o Todos los productos favoritos con sus datos

    • Muestra por consola todos los productos de la base de datos que tengan un
    precio inferior a 600€

    • Inserta en la tabla productos_fav aquellos productos que tengan un valor superior
    a 1000€
 */