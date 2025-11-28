package com.example;

import java.util.Scanner;

import com.example.controller.NorthwindController;

public class Main {
    public static void main(String[] args) {

        int opcion;
        Scanner sc = new Scanner(System.in);
        NorthwindController nc = new NorthwindController();

        System.out.println("\\\\Bienvenido al gestor del almacen de Northwind////");
        System.out.printf("MENU: \n 1)Crear la base de datos almacén. \n 2)Agregar productos desde el JSON \n ");
        System.out.printf("3)Mostrar productos por consola. \n 4)Mostrar favoritos por consola. \n ");
        System.out.printf("5)Productos por menos de 600€ \n 6)Insertar favoritos. \n 7)Salir \n");
        System.out.println("Indica el número de la opción deseada: ");
        opcion = sc.nextInt();
        do {                       
            switch (opcion) {
                case 1:
                    nc.crearBBDD();
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
                    throw new AssertionError();
            }
            } while (opcion!=7);
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