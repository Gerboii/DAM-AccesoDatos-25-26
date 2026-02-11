package com.example.dao;

import com.example.modelos.Asignatura;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AsignaturaDaoImp {
    private Session session;
    private Transaction transaction;

    //Método búsqueda listado asignaturas en funcion dniprofe/dnialumno
    public List<Asignatura> listarPorProfesor(String dniProfe) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();


        String hql = "FROM Asignatura a WHERE a.profesor.dniProfesor = :dni";
        Query<Asignatura> query = session.createQuery(hql, Asignatura.class);
        query.setParameter("dni", dniProfe);

        List<Asignatura> lista = query.getResultList();

        transaction.commit();
        session.close();
        return lista;
    }

    public List<Asignatura> listarPorAlumno(String dniAlumno) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();


        String hql = "SELECT n.asignatura FROM Nota n WHERE n.alumno.dniAlumno = :dni";
        Query<Asignatura> query = session.createQuery(hql, Asignatura.class);
        query.setParameter("dni", dniAlumno);

        List<Asignatura> lista = query.getResultList();

        transaction.commit();
        session.close();
        return lista;
    }
}
