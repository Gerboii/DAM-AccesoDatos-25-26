package com.example.dao;

import com.example.modelos.Alumno;
import com.example.modelos.Asignatura;
import com.example.modelos.Nota;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class NotaDaoImp {
    private Session session;
    private Transaction transaction;

    //Recibe una asignatura y devuelve el listado de alumnos con su nota
    public List<Nota> alumAsignatura(Asignatura asignatura){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            // Sentencia HQL
            Query<Nota> query = session.createQuery("FROM Nota n WHERE n.asignatura = :asig", Nota.class);
            query.setParameter("asig", asignatura);
            List<Nota> listaResultante = query.getResultList();
            // Impactar y cerrar
            transaction.commit();
            return listaResultante;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    //Recibe dniAlumno y asignatura, devuelve la nota.
    public Nota mostrarNota(Asignatura asignatura, Alumno alumno) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            // Sentencia HQL
            Query<Nota> query = session.createQuery("FROM Nota n WHERE n.asignatura = :asig AND n.alumno = :alum", Nota.class);
            query.setParameter("asig", asignatura);
            query.setParameter("alum", alumno);
            Nota notaResultante = query.getSingleResult();
            // Impactar y cerrar
            transaction.commit();
            return notaResultante;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    //Recibe dniAlumno devuelve todas las notas
    public List<Nota> mostrarEvaluacion(String dniAlumno){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            // Sentencia HQL
            Query<Nota> query = session.createQuery("FROM Nota n WHERE n.alumno.dniAlumno = :alum", Nota.class);
            query.setParameter("alum", dniAlumno);
            List<Nota> listaResultante = query.getResultList();
            // Impactar y cerrar
            transaction.commit();
            return listaResultante;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    //Toma una lista de notas y actualiza la BD
    public void guardarNota(List<Nota> listaNotas){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try{
            for(Nota n : listaNotas){
                session.merge(n); //Merge porque solo vamos a actualizar la base de datos no añadir nuevos registros
            }
        }
        catch(Exception ex){
            if(transaction!=null){//Se guarda completa o no se guarda nada
                transaction.rollback();}

        }finally {
            session.close();
        }
    }
}
