package com.example.dao;

import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
import org.hibernate.Session;


public class ProfesorDaoImp {

    public Profesor buscarPorDni(String dni){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //get deprecado a partir de v7
            return session.find(Profesor.class, dni);

        } catch (Exception e) {
            System.out.println("Error en búsqueda por DNIp"+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
