package cz.muni.fi.pa165.languageschool.dao.impl;

import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dao.LectureDao;
import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * DAO class for managing lecture entities
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class LectureDaoImpl implements LectureDao {
       
    private EntityManager entityManager;
    
    public LectureDaoImpl() { }
    
    public LectureDaoImpl(EntityManager em) {
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
      
    public Collection<Lecture> getAllLectures() {
        TypedQuery<Lecture> tq = entityManager.createQuery(
                "SELECT l FROM Lecture l", Lecture.class);
        return tq.getResultList();
    }
    
    public void createLecture(Lecture lecture) {
        if (lecture == null)
            throw new NullPointerException("Argument lecture cannot be null.");
        entityManager.persist(lecture); 
    }
    
    public Lecture getLectureByID(Long id) {
        Lecture res = null;
        if (id == null)
            throw new NullPointerException("Argument lecture id cannot be null.");
        
        return entityManager.find(Lecture.class, id);
    }
    
    public List<Lecture> getLectureByLabel(String label) {
        if (label == null)
            throw new NullPointerException("Argument lecture label cannot be null.");
        if (label.length() == 0)
            throw new IllegalArgumentException("Passed label was zero length");
        TypedQuery<Lecture> tq = entityManager.createQuery(
                "SELECT l FROM Lecture l WHERE l.label LIKE ?1", Lecture.class).setParameter(1, "%"+label+"%");
        return tq.getResultList();
    }
    
    public void updateLecture(Lecture lecture) {
        if (lecture == null)
            throw new NullPointerException("Argument lecture cannot be null.");
        
        Lecture lr = entityManager.find(Lecture.class, lecture.getId());
        if (lr == null)
            throw new IllegalArgumentException("Passed entity doesn't exist in database");
        
        lr.setCourse(lecture.getCourse());
        lr.setEnrolledStudents(lecture.getEnrolledStudents());
        lr.setLabel(lecture.getLabel());
        lr.setLecturer(lecture.getLecturer());
        lr.setTopicDescription(lecture.getTopicDescription());
        lr.setTpDay(lecture.getTpDay());
        lr.setTpTime(lecture.getTpTime());

        entityManager.persist(lr);
    }
    
    public void deleteLecture(Lecture lecture) {
        if (lecture == null)
            throw new NullPointerException("Argument lecture cannot be null.");
        Lecture lr = entityManager.find(Lecture.class, lecture.getId());
        if (lr == null)
            throw new IllegalArgumentException("Passed entity doesn't exist in database");
       entityManager.remove(lr);
    } 

    public Lecturer getLecturesLecturer(Lecture lec) {
	if (lec == null)
		throw new NullPointerException("Argument lec was null");
	return lec.getLecturer();
    }
}
