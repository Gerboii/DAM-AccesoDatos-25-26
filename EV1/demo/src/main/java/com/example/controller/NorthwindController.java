package com.example.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.example.dao.ProductoDaoImp;
import com.example.modelos.ProductoResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NorthwindController {
private ProductoDaoImp pd = new ProductoDaoImp();
public void menu(){
    int opcion ;
    Scanner sc = new Scanner(System.in);
    NorthwindController nc = new NorthwindController();
    
    System.out.println("\\\\Bienvenido al gestor del almacen de Northwind////");
    System.out.printf("MENU: \n 1)Crear la base de datos almacén. \n 2)Agregar productos desde el JSON \n ");
    System.out.printf("3)Mostrar productos por consola. \n 4)Mostrar favoritos por consola. \n ");
    System.out.printf("5)Productos por menos de 600€ \n 6)Insertar favoritos. \n 7)Salir \n");
    System.out.print("Indica el número de la opción deseada: ");
    
    
    do {
        opcion = sc.nextInt();                       
        switch (opcion) {
            case 1:
                nc.crearBBDD();
                menu();
                break;
            case 2:
                nc.cargarDatos();
                break;
            case 3:
                nc.mostrarProductos();
                break;
            case 4:
                nc.mostrarFavoritos();
                break;
            case 5:
                nc.mostrar600();
                break;
            case 6:
                nc.cargarFavoritos();
                break;
            case 7:
                System.out.println("Hasta la próxima.");
                break;
            default:
                System.out.println("Número incorrecto, vuelve a intentarlo.");
        }
        } while (opcion!=7);
        
}
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
                
                    if(response.getProducts()!=null){
                        pd.actualizarBBDD(response.getProducts());
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
