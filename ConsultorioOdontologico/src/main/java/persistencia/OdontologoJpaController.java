/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import logica.Odontologo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Johnny
 */
public class OdontologoJpaController implements Serializable {

    public OdontologoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Odontologo odontologo) {
        if (odontologo.getTurnosOdon() == null) {
            odontologo.setTurnosOdon(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedTurnosOdon = new ArrayList<Turno>();
            for (Turno turnosOdonTurnoToAttach : odontologo.getTurnosOdon()) {
                turnosOdonTurnoToAttach = em.getReference(turnosOdonTurnoToAttach.getClass(), turnosOdonTurnoToAttach.getId_turno());
                attachedTurnosOdon.add(turnosOdonTurnoToAttach);
            }
            odontologo.setTurnosOdon(attachedTurnosOdon);
            em.persist(odontologo);
            for (Turno turnosOdonTurno : odontologo.getTurnosOdon()) {
                Odontologo oldOdontoOfTurnosOdonTurno = turnosOdonTurno.getOdonto();
                turnosOdonTurno.setOdonto(odontologo);
                turnosOdonTurno = em.merge(turnosOdonTurno);
                if (oldOdontoOfTurnosOdonTurno != null) {
                    oldOdontoOfTurnosOdonTurno.getTurnosOdon().remove(turnosOdonTurno);
                    oldOdontoOfTurnosOdonTurno = em.merge(oldOdontoOfTurnosOdonTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Odontologo odontologo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo persistentOdontologo = em.find(Odontologo.class, odontologo.getId());
            List<Turno> turnosOdonOld = persistentOdontologo.getTurnosOdon();
            List<Turno> turnosOdonNew = odontologo.getTurnosOdon();
            List<Turno> attachedTurnosOdonNew = new ArrayList<Turno>();
            for (Turno turnosOdonNewTurnoToAttach : turnosOdonNew) {
                turnosOdonNewTurnoToAttach = em.getReference(turnosOdonNewTurnoToAttach.getClass(), turnosOdonNewTurnoToAttach.getId_turno());
                attachedTurnosOdonNew.add(turnosOdonNewTurnoToAttach);
            }
            turnosOdonNew = attachedTurnosOdonNew;
            odontologo.setTurnosOdon(turnosOdonNew);
            odontologo = em.merge(odontologo);
            for (Turno turnosOdonOldTurno : turnosOdonOld) {
                if (!turnosOdonNew.contains(turnosOdonOldTurno)) {
                    turnosOdonOldTurno.setOdonto(null);
                    turnosOdonOldTurno = em.merge(turnosOdonOldTurno);
                }
            }
            for (Turno turnosOdonNewTurno : turnosOdonNew) {
                if (!turnosOdonOld.contains(turnosOdonNewTurno)) {
                    Odontologo oldOdontoOfTurnosOdonNewTurno = turnosOdonNewTurno.getOdonto();
                    turnosOdonNewTurno.setOdonto(odontologo);
                    turnosOdonNewTurno = em.merge(turnosOdonNewTurno);
                    if (oldOdontoOfTurnosOdonNewTurno != null && !oldOdontoOfTurnosOdonNewTurno.equals(odontologo)) {
                        oldOdontoOfTurnosOdonNewTurno.getTurnosOdon().remove(turnosOdonNewTurno);
                        oldOdontoOfTurnosOdonNewTurno = em.merge(oldOdontoOfTurnosOdonNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = odontologo.getId();
                if (findOdontologo(id) == null) {
                    throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo odontologo;
            try {
                odontologo = em.getReference(Odontologo.class, id);
                odontologo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.", enfe);
            }
            List<Turno> turnosOdon = odontologo.getTurnosOdon();
            for (Turno turnosOdonTurno : turnosOdon) {
                turnosOdonTurno.setOdonto(null);
                turnosOdonTurno = em.merge(turnosOdonTurno);
            }
            em.remove(odontologo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Odontologo> findOdontologoEntities() {
        return findOdontologoEntities(true, -1, -1);
    }

    public List<Odontologo> findOdontologoEntities(int maxResults, int firstResult) {
        return findOdontologoEntities(false, maxResults, firstResult);
    }

    private List<Odontologo> findOdontologoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Odontologo.class));
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

    public Odontologo findOdontologo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Odontologo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOdontologoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Odontologo> rt = cq.from(Odontologo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
