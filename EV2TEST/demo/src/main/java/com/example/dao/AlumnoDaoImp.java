package com.example.dao;

import com.example.modelos.Alumno;
import com.example.util.HibernateUtil;
import org.hibernate.Session;

public class AlumnoDaoImp {

    public Alumno buscarPorDni(String dni){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //get deprecado a partir de v7
            return session.find(Alumno.class, dni);

        } catch (Exception e) {
            System.out.println("Error en búsqueda por DNIa"+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
