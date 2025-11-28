package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.example.controller.NorthwindController;
import com.example.database.DBConnection;
import com.example.database.SchemeDB;
import com.example.modelos.Producto;

public class ProductoDaoImp implements ProductoDao{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Producto p;
    
//Constructor con la conexion
    public ProductoDaoImp(){
        connection = DBConnection.getConnection();
    }
//Definimos las sentencias SQL para que las pueda usar el controlador

    @Override
    public int crearBBDD() {
        final String urlServer = "jdbc:mysql://localhost:3306";
        
        //Bypass al singletone para crear la base de datos
        try (Connection tempConn = DriverManager.getConnection(urlServer, "root", "")) {
            String query = String.format("DROP DATABASE IF EXISTS %s", SchemeDB.DB_NAME);
            String query2 = String.format("CREATE DATABASE  %s", SchemeDB.DB_NAME);
            String query3 = String.format("CREATE TABLE %s.%s (%s INT PRIMARY KEY, %s VARCHAR(50), %s VARCHAR(100), %s INT, %s DOUBLE)",
             SchemeDB.DB_NAME,SchemeDB.TAB_PROD,SchemeDB.COL_ID,SchemeDB.COL_NAME,SchemeDB.COL_DESCRIP,SchemeDB.COL_CANTIDAD,SchemeDB.COL_PRECIO);
            String query4 = String.format("CREATE TABLE %s.%s (%s INT PRIMARY KEY, %s INT, FOREIGN KEY (%s) REFERENCES %s.%s(%s))",
             SchemeDB.DB_NAME,SchemeDB.TAB_FAV,SchemeDB.COL_ID,SchemeDB.COL_ID_PROD,SchemeDB.COL_ID_PROD,SchemeDB.DB_NAME,SchemeDB.TAB_PROD,SchemeDB.COL_ID);
            preparedStatement= tempConn.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement= tempConn.prepareStatement(query2);
            preparedStatement.executeUpdate();
            preparedStatement= tempConn.prepareStatement(query3);
            preparedStatement.executeUpdate();
            preparedStatement= tempConn.prepareStatement(query4);
            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error conectando al servidor"+e.getMessage());
            return 0;
        }       
        
    }
    
    @Override
    public int actualizarBBDD() {
        /*o Productos: id (pk), nombre, descripción, cantidad, precio.
        o Productos_Fav: id (pk), id_producto (fk)*/
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
        SchemeDB.TAB_PROD,SchemeDB.COL_ID,SchemeDB.COL_NAME,SchemeDB.COL_DESCRIP,SchemeDB.COL_CANTIDAD,SchemeDB.COL_PRECIO);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,p.getId());
            preparedStatement.setString(2, p.getTitle());
            preparedStatement.setString(3,p.getDescription());
            preparedStatement.setInt(4, p.getStock());
            preparedStatement.setDouble(4, p.getPrice());
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public void listarProducto(String tipo) {        
        if (tipo.equals("productos")){
        //SELECT de productos
        String query = String.format("SELECT * FROM %s", SchemeDB.TAB_PROD);
        }
        else {
        //SELECT de favoritos
        String query = String.format("SELECT * FROM %s", SchemeDB.TAB_FAV);

        }
    }


    @Override
    public void listarFiltrado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarFiltrado'");
    }

    @Override
    public void actualizarFav() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarFav'");
    }

}
