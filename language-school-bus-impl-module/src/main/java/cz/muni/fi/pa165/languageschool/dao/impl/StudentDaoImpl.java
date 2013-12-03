package cz.muni.fi.pa165.languageschool.dao.impl;

import cz.muni.fi.pa165.languageschool.Student;
import cz.muni.fi.pa165.languageschool.dao.StudentDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Milos Petrovic (256790) mpetrovickg(at)gmail.com
 */
public class StudentDaoImpl implements StudentDao {
    
    private EntityManager entityManager;
    
    public StudentDaoImpl() { }
    
    public StudentDaoImpl(EntityManager em) {
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
    
    public List<Student> getAllStudents() {
        TypedQuery<Student> tq = entityManager.createQuery(
                "SELECT s FROM Student s", Student.class);
        return tq.getResultList();
    }
    
    public void createStudent(Student student) {
        if (student == null)
            throw new NullPointerException("Argument student cannot be null.");
        entityManager.persist(student);        
    }
   
    public Student getStudentById(Long id) {
        Student res = null;
        if (id == null)
            throw new NullPointerException("Passed argument id was null");
        return entityManager.find(Student.class, id);
    }
    
    public List<Student> getStudentsByName(String name) {
        if (name == null)
            throw new NullPointerException("Argument student name cannot be null.");
        if (name.length() == 0)
            throw new IllegalArgumentException("Argument name was zero length");
        TypedQuery<Student> tq = entityManager.createQuery(
                "SELECT s FROM Student s WHERE CONCAT(s.lastName,' ',s.firstName) LIKE ?1 OR CONCAT(s.firstName,' ',s.lastName) LIKE ?1", Student.class).setParameter(1, name);
        return tq.getResultList();
    }
    
    public void updateStudent(Student student) {  
        if (student == null)
            throw new NullPointerException("Argument student cannot be null.");
        
        Student std = entityManager.find(Student.class, student.getId());
        if (std == null)
            throw new IllegalArgumentException("Passed student doesn't exist in database");
        
        std.setFirstName(student.getFirstName());
        std.setLastName(student.getLastName());
        
        entityManager.persist(std);        
    }
    
    public void deleteStudent(Student student) {
        if (student == null)
            throw new NullPointerException("Argument student cannot be null.");
        
        Student std = entityManager.find(Student.class, student.getId());
        if (std == null)
            throw new IllegalArgumentException("Passed student doesn't exist in database");
        entityManager.remove(std);         
    }
}