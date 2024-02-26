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
import logica.Paciente;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Johnny
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        if (paciente.getTurnosPac() == null) {
            paciente.setTurnosPac(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedTurnosPac = new ArrayList<Turno>();
            for (Turno turnosPacTurnoToAttach : paciente.getTurnosPac()) {
                turnosPacTurnoToAttach = em.getReference(turnosPacTurnoToAttach.getClass(), turnosPacTurnoToAttach.getId_turno());
                attachedTurnosPac.add(turnosPacTurnoToAttach);
            }
            paciente.setTurnosPac(attachedTurnosPac);
            em.persist(paciente);
            for (Turno turnosPacTurno : paciente.getTurnosPac()) {
                Paciente oldPacienteOfTurnosPacTurno = turnosPacTurno.getPaciente();
                turnosPacTurno.setPaciente(paciente);
                turnosPacTurno = em.merge(turnosPacTurno);
                if (oldPacienteOfTurnosPacTurno != null) {
                    oldPacienteOfTurnosPacTurno.getTurnosPac().remove(turnosPacTurno);
                    oldPacienteOfTurnosPacTurno = em.merge(oldPacienteOfTurnosPacTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            List<Turno> turnosPacOld = persistentPaciente.getTurnosPac();
            List<Turno> turnosPacNew = paciente.getTurnosPac();
            List<Turno> attachedTurnosPacNew = new ArrayList<Turno>();
            for (Turno turnosPacNewTurnoToAttach : turnosPacNew) {
                turnosPacNewTurnoToAttach = em.getReference(turnosPacNewTurnoToAttach.getClass(), turnosPacNewTurnoToAttach.getId_turno());
                attachedTurnosPacNew.add(turnosPacNewTurnoToAttach);
            }
            turnosPacNew = attachedTurnosPacNew;
            paciente.setTurnosPac(turnosPacNew);
            paciente = em.merge(paciente);
            for (Turno turnosPacOldTurno : turnosPacOld) {
                if (!turnosPacNew.contains(turnosPacOldTurno)) {
                    turnosPacOldTurno.setPaciente(null);
                    turnosPacOldTurno = em.merge(turnosPacOldTurno);
                }
            }
            for (Turno turnosPacNewTurno : turnosPacNew) {
                if (!turnosPacOld.contains(turnosPacNewTurno)) {
                    Paciente oldPacienteOfTurnosPacNewTurno = turnosPacNewTurno.getPaciente();
                    turnosPacNewTurno.setPaciente(paciente);
                    turnosPacNewTurno = em.merge(turnosPacNewTurno);
                    if (oldPacienteOfTurnosPacNewTurno != null && !oldPacienteOfTurnosPacNewTurno.equals(paciente)) {
                        oldPacienteOfTurnosPacNewTurno.getTurnosPac().remove(turnosPacNewTurno);
                        oldPacienteOfTurnosPacNewTurno = em.merge(oldPacienteOfTurnosPacNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<Turno> turnosPac = paciente.getTurnosPac();
            for (Turno turnosPacTurno : turnosPac) {
                turnosPacTurno.setPaciente(null);
                turnosPacTurno = em.merge(turnosPacTurno);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
