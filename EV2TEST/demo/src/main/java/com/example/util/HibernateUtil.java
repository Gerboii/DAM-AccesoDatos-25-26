package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory ;
    //No puedo/quiero crear las session en el main porque se va a encargar de lanzar la vista
    //TODO Completar metodo getSession devuelve una conexion
    public static SessionFactory getSession() {
        if(sessionFactory == null){
            try {
                // 1-> Creas una session factory
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                // 2-> Crea la session
                Session session = sessionFactory.openSession();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

}
