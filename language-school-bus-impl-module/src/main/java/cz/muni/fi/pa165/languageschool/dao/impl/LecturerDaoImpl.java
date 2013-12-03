package cz.muni.fi.pa165.languageschool.dao.impl;

import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dao.LecturerDao;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * DAO class for managing lecturer entities
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class LecturerDaoImpl implements LecturerDao {
    
    private EntityManager entityManager;
    
    public LecturerDaoImpl() { }
    
    public LecturerDaoImpl(EntityManager em) {
	if (em == null)
	    throw new NullPointerException("Argument em was null");
	this.entityManager = em;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void createLecturer(Lecturer lecturer) {
        if (lecturer == null)
            throw new NullPointerException("Argument lecturer cannot be null.");
        entityManager.persist(lecturer);    
    }

    public void updateLecturer(Lecturer lecturer) {
        if (lecturer == null)
            throw new NullPointerException("Argument lecturer cannot be null.");
        
        Lecturer lrr = entityManager.find(Lecturer.class, lecturer.getId());
        if (lrr == null)
            throw new IllegalArgumentException("Entity does not exist in db");
        
        lrr.setFirstName(lecturer.getFirstName());
        lrr.setLastName(lecturer.getLastName());
        lrr.setNativeSpeaker(lecturer.getNativeSpeaker());
        lrr.setLanguages(lecturer.getLanguages());
        lrr.setLectures(lecturer.getLectures());
        entityManager.persist(lrr);    
    }

    public void deleteLecturer(Lecturer lecturer) {
        if (lecturer == null)
            throw new NullPointerException("Argument lecturer cannot be null.");
        
        Lecturer lrr = entityManager.find(Lecturer.class, lecturer.getId());
        if (lrr == null)
            throw new IllegalArgumentException("Passed entity doesn't exist in database");
        entityManager.remove(lrr);     
    }
   
    public Lecturer getLecturerById(Long id) {
        Lecturer res = null;
        if (id == null)
            throw new NullPointerException("Argument lecturer id cannot be null.");

        return entityManager.find(Lecturer.class, id);
    }
    
    public List<Lecturer> getLecturerByName(String name) {
        if (name == null)
            throw new NullPointerException("Argument lecturer name cannot be null.");
        if (name.length() == 0)
            throw new IllegalArgumentException("Passed name argument was zero length");
        TypedQuery<Lecturer> tq = entityManager.createQuery(
                "SELECT l FROM Lecturer l WHERE CONCAT(l.lastName,' ',l.firstName) LIKE ?1 OR CONCAT(l.firstName,' ',l.lastName) LIKE ?1", Lecturer.class).setParameter(1, name);
        return tq.getResultList();
    }
    
    public Collection<Lecturer> getAllLecturers() {
        TypedQuery<Lecturer> tq = entityManager.createQuery(
                "SELECT l FROM Lecturer l", Lecturer.class);
        return tq.getResultList();
    }
}