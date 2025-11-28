package com.example.controller;

public class NorthwindController {

public void crearBBDD(){
    /*Crea una base de datos llamada almacén con las siguientes tablas y campos
        o Productos: id (pk), nombre, descripción, cantidad, precio.
        o Productos_Fav: id (pk), id_producto (fk)*/
        System.out.println("Creando base de datos.");
        //Ejecuta el método de gestionBD
        System.out.println("Operación completa.");
}
public void cargarDatos(){
    /* • Agregar todos los productos que están ubicados en el siguiente JSON dentro de
    la tabla productos: https://dummyjson.com/products */
    System.out.println("Cargando datos de la URL: https://dummyjson.com/products ");
    //Ejecuta el método de gestionBD
    System.out.println("Operación completa.");
}

public void mostrarProductos(){
//Muestra por consola mediante la ejecución de querys – statement: Todos los productos

}

public void mostrarFavoritos(){

}

public void mostrar600(){

}

public void cargarFavoritos(){
    System.out.println("Actualizando la base de datos.");
    //Ejecuta el método de gestionBD
    System.out.println("Operación completa.");
}

}
