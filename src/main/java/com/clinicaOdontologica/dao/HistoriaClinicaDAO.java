package com.clinicaOdontologica.dao;

import com.clinicaOdontologica.entities.historiaClinica;
import jakarta.persistence.*;

import java.util.List;

public class HistoriaClinicaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ClinicaOdontologicaPersistencia_jar_1.0-SNAPSHOTPU");

    public void guardar(historiaClinica hc) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(hc);
        em.getTransaction().commit();
        em.close();
    }

    public historiaClinica buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        historiaClinica hc = em.find(historiaClinica.class, id);
        em.close();
        return hc;
    }

    public List<historiaClinica> listar() {
        EntityManager em = emf.createEntityManager();
        List<historiaClinica> lista = em.createQuery("SELECT h FROM historiaClinica h", historiaClinica.class).getResultList();
        em.close();
        return lista;
    }

    public void actualizar(historiaClinica hc) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(hc);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        historiaClinica hc = em.find(historiaClinica.class, id);
        if (hc != null) {
            em.getTransaction().begin();
            em.remove(hc);
            em.getTransaction().commit();
        }
        em.close();
    }
}
