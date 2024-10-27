/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Persistencia.exceptions.NonexistentEntityException;
import com.mycompany.taller.Model.TarjetaCredito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Agus
 */
public class TarjetaCreditoJpaController implements Serializable {

    public TarjetaCreditoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TarjetaCreditoJpaController() {
        emf = (EntityManagerFactory) Persistence.createEntityManagerFactory("persisJPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TarjetaCredito tarjetaCredito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tarjetaCredito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TarjetaCredito tarjetaCredito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tarjetaCredito = em.merge(tarjetaCredito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tarjetaCredito.getIdTarjeta();
                if (findTarjetaCredito(id) == null) {
                    throw new NonexistentEntityException("The tarjetaCredito with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TarjetaCredito tarjetaCredito;
            try {
                tarjetaCredito = em.getReference(TarjetaCredito.class, id);
                tarjetaCredito.getIdTarjeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarjetaCredito with id " + id + " no longer exists.", enfe);
            }
            em.remove(tarjetaCredito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TarjetaCredito> findTarjetaCreditoEntities() {
        return findTarjetaCreditoEntities(true, -1, -1);
    }

    public List<TarjetaCredito> findTarjetaCreditoEntities(int maxResults, int firstResult) {
        return findTarjetaCreditoEntities(false, maxResults, firstResult);
    }

    private List<TarjetaCredito> findTarjetaCreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TarjetaCredito.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TarjetaCredito findTarjetaCredito(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TarjetaCredito.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarjetaCreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TarjetaCredito> rt = cq.from(TarjetaCredito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
