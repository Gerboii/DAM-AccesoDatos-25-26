package com.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.modelos.producto;
import com.example.modelos.productoResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new  ObjectMapper();
        try {
            URL url = new URL("https://dummyjson.com/products");
            try {
                productoResponse response = mapper.readValue(url,productoResponse.class);
                for (producto p : response.getProducts()) {
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