package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory ;
    //No puedo/quiero crear las session en el main porque se va a encargar de lanzar la vista

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try {
                // 1-> Creas una session factory
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable e) {
                System.err.println("Fallo el crear sessionFactory: "+e.getMessage());
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

}
