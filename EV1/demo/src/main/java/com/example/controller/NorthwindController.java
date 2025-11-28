package com.example.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.dao.ProductoDaoImp;
import com.example.modelos.Producto;
import com.example.modelos.ProductoResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NorthwindController {
private ProductoDaoImp pd = new ProductoDaoImp();

public void crearBBDD(){
    /*Crea una base de datos llamada almacén con las siguientes tablas y campos
        o Productos: id (pk), nombre, descripción, cantidad, precio.
        o Productos_Fav: id (pk), id_producto (fk)*/
        System.out.println("Creando base de datos.");
        //Ejecuta el método de gestionBD
        pd.crearBBDD();
        System.out.println("Operación completa.");
}
public void cargarDatos(){
    /* • Agregar todos los productos que están ubicados en el siguiente JSON dentro de
    la tabla productos: https://dummyjson.com/products */
    System.out.println("Cargando datos de la URL: https://dummyjson.com/products ");
    
        ObjectMapper mapper = new  ObjectMapper();
        try {
            URL url = new URL("https://dummyjson.com/products");
            try {
                ProductoResponse response = mapper.readValue(url,ProductoResponse.class);
                for (Producto p : response.getProducts()) {
                    System.out.println(p);
                }
            } catch (StreamReadException e) {
                System.out.println("Error en lectura JSON.");
            } catch (DatabindException e) {
                System.out.println("Error en el mapeo JSON.");
            } catch (IOException e) {
                System.out.println("Error en la conexión JSON.");
            }

        } catch (MalformedURLException e) {
            System.out.println("Error en la URL.");
        }
    //Ejecuta el método de gestionBD
        pd.actualizarBBDD();
    System.out.println("Operación completa.");
}

public void mostrarProductos(){
    System.out.println("Mostrando tabla de productos:");
    pd.listarProducto("productos");

}

public void mostrarFavoritos(){
    System.out.println("Mostrando tabla de productos favoritos:");
    pd.listarProducto("fav");
}

public void mostrar600(){
    System.out.println("Mostrando tabla de productos por menos de 600€:");
    pd.listarFiltrado();
}

public void cargarFavoritos(){
    System.out.println("Actualizando la base de datos.");
    //Ejecuta el método de gestionBD
    pd.actualizarFav();
    System.out.println("Operación completa.");
}

}
