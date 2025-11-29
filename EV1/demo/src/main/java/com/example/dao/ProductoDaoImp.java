package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.database.DBConnection;
import com.example.database.SchemeDB;
import com.example.modelos.Producto;
import com.mysql.cj.protocol.Resultset;

public class ProductoDaoImp implements ProductoDao{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Resultset rs;
    
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
            String query3 = String.format("CREATE TABLE %s.%s (%s INT PRIMARY KEY, %s VARCHAR(50), %s VARCHAR(200), %s INT, %s DOUBLE)",
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
    public void actualizarBBDD(List<Producto> listaproducto) {
        /*o Productos: id (pk), nombre, descripción, cantidad, precio.
        o Productos_Fav: id (pk), id_producto (fk)*/
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
        SchemeDB.TAB_PROD,SchemeDB.COL_ID,SchemeDB.COL_NAME,SchemeDB.COL_DESCRIP,SchemeDB.COL_CANTIDAD,SchemeDB.COL_PRECIO);
        try {
            //preparedStatement = connection.prepareStatement(query);
            //Antes apuntabamos al servidor, hay que conectar a la BD
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            for (Producto p : listaproducto) {
                preparedStatement.setInt(1,p.getId());
                preparedStatement.setString(2, p.getTitle());
                preparedStatement.setString(3,p.getDescription());
                preparedStatement.setInt(4, p.getStock());
                preparedStatement.setDouble(5, p.getPrice());
                //Insercion del obj
                preparedStatement.executeUpdate();
            }
            
        } catch (Exception e) {
            System.out.println("Error en la carga de datos."+e.getMessage());
        }
    }

    @Override
    public void listarProducto(String tipo) {        
        if (tipo.equals("productos")){
        //SELECT de productos
        String query = String.format("SELECT * FROM %s", SchemeDB.TAB_PROD);
            try {
                preparedStatement = DBConnection.getConnection().prepareStatement(query);
                ResultSet rs= preparedStatement.executeQuery();
                boolean hayDatos = false;
                while(rs.next()){
                    hayDatos = true;
                    int id = rs.getInt(SchemeDB.COL_ID);
                    String nombre = rs.getString(SchemeDB.COL_NAME);
                    String desc = rs.getString(SchemeDB.COL_DESCRIP);
                    int cant = rs.getInt(SchemeDB.COL_CANTIDAD);
                    double precio = rs.getDouble(SchemeDB.COL_PRECIO);

                  String p = String.format("ID: %s | Nombre: %s | Descripción: %s | Cantidad: %s | Precio: %s",id,nombre, desc,cant,precio);
                  System.out.println(p);
                }
                if(!hayDatos){
                    System.out.println("No hay datos en la tabla.");
                }
                
            } catch (SQLException ex) {
                System.out.println("Fallo en la conexión."+ex.getMessage());
            }
        }
        else {
        //SELECT de favoritos
        //SELECT * FROM productos WHERE (id EQUALS prod_fav.id_producto)
        String query = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s = %s.%s",
        SchemeDB.TAB_PROD, SchemeDB.TAB_FAV,SchemeDB.TAB_PROD,SchemeDB.COL_ID,SchemeDB.TAB_FAV,SchemeDB.COL_ID_PROD);
            try {
                preparedStatement = DBConnection.getConnection().prepareStatement(query);
                ResultSet rs= preparedStatement.executeQuery();
                boolean hayDatos = false;
                while(rs.next()){
                    hayDatos=true; 
                    int id = rs.getInt(SchemeDB.COL_ID);
                    String nombre = rs.getString(SchemeDB.COL_NAME);
                    String desc = rs.getString(SchemeDB.COL_DESCRIP);
                    int cant = rs.getInt(SchemeDB.COL_CANTIDAD);
                    double precio = rs.getDouble(SchemeDB.COL_PRECIO);

                  String p = String.format("ID: %s | Nombre: %s | Descripción: %s | Cantidad: %s | Precio: %s",id,nombre, desc,cant,precio);
                  System.out.println(p);
                }
                if(!hayDatos){
                    System.out.println("No hay datos en la tabla.");
                }
                
            } catch (SQLException ex) {
                System.out.println("Fallo en la conexión."+ex.getMessage());
            }
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
