package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearBBDD'");
    }
    
    @Override
    public int actualizarBBDD() {
        /*o Productos: id (pk), nombre, descripción, cantidad, precio.
        o Productos_Fav: id (pk), id_producto (fk)*/
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)", SchemeDB.TAB_PROD,SchemeDB.COL_ID,SchemeDB.COL_NAME,SchemeDB.COL_DESCRIP,SchemeDB.COL_CANTIDAD,SchemeDB.COL_PRECIO);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarProducto'");
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
