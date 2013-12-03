package cz.muni.fi.pa165.languageschool.dao.impl;

import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.dao.CourseDao;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class CourseDaoImpl implements CourseDao {

    private EntityManager entityManager;

    public CourseDaoImpl() { }
    
    public CourseDaoImpl(EntityManager em) {
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

    public void createCourse(Course course) {
	if (course == null)
	    throw new NullPointerException("Argument course cannot be null.");
	entityManager.persist(course);
    }

    public List<Course> getAllCourses() {
	TypedQuery<Course> tq = entityManager.createQuery(
                "SELECT c FROM Course c", Course.class);
	return tq.getResultList();
    }

    public Course getCourseById(Long id) {
	Course res = null;
	if (id == null)
	    throw new NullPointerException("Argument id cannot be null.");
	return entityManager.find(Course.class, id);
    }

    public Course getCourseByCode(String code) {
	Course res = null;
	if (code == null)
	    throw new NullPointerException("Argument course id cannot be null.");
            
        if (code.length() == 0)
            throw new IllegalArgumentException("Passed agument code is zero length");
        
	TypedQuery<Course> tq = entityManager.createQuery(
		"SELECT c FROM Course c WHERE c.code = ?1", Course.class)
		.setParameter(1, code);
	try {
	    res = tq.getSingleResult();
	} catch (NoResultException ex) {
	    return res;
	}
	return res;
    }

    public Course getCourseByName(String name) {
	Course res = null;
	if (name == null)
	    throw new NullPointerException("Argument course name cannot be null.");

	TypedQuery<Course> tq = entityManager.createQuery(
		"SELECT c FROM Course c WHERE c.name = ?1", Course.class)
		.setParameter(1, name);
	try {
	    res = tq.getSingleResult();
	} catch (NoResultException ex) {
	    return res;
	}
	return res;
    }

    public List<Course> getCourseByLanguage(Language taughtLanguage) {
	if (taughtLanguage == null)
	    throw new NullPointerException("Argument taught language cannot be null.");
        
	TypedQuery<Course> tq = entityManager.createQuery(
		"SELECT c FROM Course c WHERE c.taughtLanguage = ?1", Course.class)
		.setParameter(1, taughtLanguage);
	return tq.getResultList();
    }

    public List<Course> getCourseByEntryLevel(String level) {
	if (level == null)
	    throw new IllegalArgumentException("Argument level language cannot be null.");
        
	TypedQuery<Course> tq = entityManager.createQuery(
		"SELECT c FROM Course c WHERE c.requiredLevel = ?1", Course.class)
		.setParameter(1, level);
	return tq.getResultList();
    }

    public Course updateCourse(Course course) {
	if (course == null)
	    throw new IllegalArgumentException("Argument course cannot be null.");
        
        Course crs = entityManager.find(Course.class, course.getId());
        if (crs == null)
            throw new IllegalArgumentException("Passed course doesn't exist in database");
        
        crs.setCode(course.getCode());
        crs.setName(course.getName());
        crs.setLevel(course.getLevel());
        crs.setCourseLanguage(course.getCourseLanguage());
        crs.setRelatedLectures(course.getRelatedLectures());
        
	entityManager.persist(crs);
	return crs;
    }

    public void deleteCourse(Course course) {
	if (course == null) 
	    throw new NullPointerException("Argument course cannot be null.");
	
        Course crs = entityManager.find(Course.class, course.getId());
        if (crs == null)
            throw new IllegalArgumentException("Passed course doesn't exist in database");
        
	entityManager.remove(crs);
    }

    // tady uz je jedno, kde to sebereme, bud takto a nebo rozbijeme vazbu 
    // a budeme to tezit primo z databaze
    public Set<Lecture> getCourseRelatedLectures(Course crs) {
	return Collections.unmodifiableSet(crs.getRelatedLectures());
    }
}