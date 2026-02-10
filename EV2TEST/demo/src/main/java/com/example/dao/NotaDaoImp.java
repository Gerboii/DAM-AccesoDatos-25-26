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
        /*TODO  Método guardarNota() recibe las notas de la tabla y las guarda en BD
          * OPCIONAL- Método mostrarEvaluacion() recibe dniAlumno, devuelve todas las notas asociadas.*/

    //Recibe una asignatura y devuelve el listado de alumnos con su nota
    public List<Nota> alumAsignatura(Asignatura asignatura){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        // Sentencia HQL
        Query<Nota> query = session.createQuery("FROM Nota n WHERE n.asignatura = :asig", Nota.class);
        query.setParameter("asig", asignatura);
        List<Nota> listaResultante = query.getResultList();
        // Impactar y cerrar
        transaction.commit();
        session.close();

        return listaResultante;
    }

    //Recibe dniAlumno y asignatura, devuelve la nota.
    public Nota mostrarNota(Asignatura asignatura, Alumno alumno){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        // Sentencia HQL
        Query<Nota> query = session.createQuery("FROM Nota n WHERE n.asignatura = :asig AND n.alumno = :alum", Nota.class);
        query.setParameter("asig", asignatura);
        query.setParameter("alum", alumno);
        Nota notaResultante = query.getSingleResult();
        // Impactar y cerrar
        transaction.commit();
        session.close();

        return notaResultante;
    }
    //TODO Arreglar
    /*public List<Nota> mostrarEvaluacion(Asignatura asignatura){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        // Sentencia HQL
        Query<Nota> query = session.createQuery("FROM Nota n WHERE n.alumno = :alum", Nota.class);
        query.setParameter("alum", );
        List<Nota> listaResultante = query.getResultList();
        // Impactar y cerrar
        transaction.commit();
        session.close();

        return listaResultante;
    }*/
}
