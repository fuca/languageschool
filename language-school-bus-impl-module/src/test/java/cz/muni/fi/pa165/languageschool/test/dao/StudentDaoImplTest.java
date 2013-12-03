package cz.muni.fi.pa165.languageschool.test.dao;

import cz.muni.fi.pa165.languageschool.test.BaseTest;
import cz.muni.fi.pa165.languageschool.Student;
import cz.muni.fi.pa165.languageschool.dao.impl.StudentDaoImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class StudentDaoImplTest extends BaseTest {

    private StudentDaoImpl studentDao;
    
    public void setStudentDao(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }
    
    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        em = emf.createEntityManager();
        studentDao = new StudentDaoImpl(em);
    }    
    
    
    @Test
    public void testCreateSingle() {
        Student std = new Student();
        std.setFirstName("Pavel");
        std.setLastName("Novak");
        
        em.getTransaction().begin();
        studentDao.createStudent(std);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Student res = studentDao.getStudentById(std.getId());
        em.getTransaction().commit();
        
        assertEquals(std.getEnrolledLectures(), res.getEnrolledLectures());
        assertEquals(std.getFirstName(), res.getFirstName());
        assertEquals(std.getLastName(), res.getLastName());
    }
    
    @Test
    public void testCreateStudent() {
        Student student1 = new Student();

        student1.setFirstName("Pavel");
        student1.setLastName("Novak");
        
        em.getTransaction().begin();
        studentDao.createStudent(student1);
        em.getTransaction().commit();
        
        Long studentId = student1.getId();
        assertNotNull(studentId);
        
        em.getTransaction().begin();
        Student result = studentDao.getStudentById(studentId);
        em.getTransaction().commit();
        
        assertEquals(student1, result);
        assertDeepEquals(student1, result);
    }

    @Test
    public void testGetstudentByName() {
        
        Student student1 = new Student();
        List<Student> result;
        
        String fName = "Michal";
        String lName = "Fucik";
        student1.setFirstName(fName);
        student1.setLastName(lName);
        
        em.getTransaction().begin();
        studentDao.createStudent(student1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        result = studentDao.getStudentsByName(fName + " " + lName);
        em.getTransaction().commit();
        
        List<Student> actual = new ArrayList<>();
        actual.add(student1);
        
        assertCollectionEquals((Collection) actual, (Collection) result);
    }

    @Test
    public void testUpdateStudent() {
        
        Student student1 = new Student();
        Student student2 = new Student();
        Long id1;
        
        em.getTransaction().begin();
        studentDao.createStudent(student1);
        studentDao.createStudent(student2);
        em.getTransaction().commit();
        
        id1 = student1.getId();
        student1.setFirstName("Michal");
        student1.setLastName("Fučík");
       
        em.getTransaction().begin();
        studentDao.updateStudent(student1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Student result = studentDao.getStudentById(id1);
        em.getTransaction().commit();
        
        assertNotNull(result);
        assertDeepEquals(result, student1);
    }

    @Test
    public void testDeleteStudent() {
        int before, after;
        Student student1 = new Student();
        Student student2 = new Student();
        
        em.getTransaction().begin();
        studentDao.createStudent(student1);
        studentDao.createStudent(student2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        assertNotNull(studentDao.getStudentById(student1.getId()));
        assertNotNull(studentDao.getStudentById(student2.getId()));
        
        before = studentDao.getAllStudents().size();
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        studentDao.deleteStudent(student1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        after = studentDao.getAllStudents().size();

        assertNull(studentDao.getStudentById(student1.getId()));
        assertNotNull(studentDao.getStudentById(student2.getId()));
        em.getTransaction().commit();
        assertEquals(before - after, 1);
    }

    private void assertDeepEquals(Student expected, Student actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEnrolledLectures(), actual.getEnrolledLectures());

    }
    
}