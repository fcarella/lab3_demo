/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecture5.jpa.controllers;

import lecture5.jpa.controllers.exceptions.NonexistentEntityException;
import lecture5.jpa.entities.Pencil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author fcarella
 */
public class PencilJpaController implements Serializable {

    public PencilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pencil pencil) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pencil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pencil pencil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pencil = em.merge(pencil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pencil.getId();
                if (findPencil(id) == null) {
                    throw new NonexistentEntityException("The pencil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pencil pencil;
            try {
                pencil = em.getReference(Pencil.class, id);
                pencil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pencil with id " + id + " no longer exists.", enfe);
            }
            em.remove(pencil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pencil> findPencilEntities() {
        return findPencilEntities(true, -1, -1);
    }

    public List<Pencil> findPencilEntities(int maxResults, int firstResult) {
        return findPencilEntities(false, maxResults, firstResult);
    }

    private List<Pencil> findPencilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pencil as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pencil findPencil(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pencil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPencilCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Pencil as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
