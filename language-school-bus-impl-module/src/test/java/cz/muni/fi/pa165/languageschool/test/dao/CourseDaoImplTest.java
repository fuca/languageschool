package cz.muni.fi.pa165.languageschool.test.dao;

import cz.muni.fi.pa165.languageschool.test.BaseTest;
import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.dao.impl.CourseDaoImpl;
import cz.muni.fi.pa165.languageschool.dao.impl.LectureDaoImpl;
import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
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
 * @author Marketa Trachtova (255498) marketa_tr(at)gmail.com
 */
public class CourseDaoImplTest extends BaseTest {
    
    private CourseDaoImpl courseDao;
    private LectureDaoImpl lectureDao;

    public void setLectureDao(LectureDaoImpl lectureDao) {
        this.lectureDao = lectureDao;
    }
    
    public void setCourseDao(CourseDaoImpl courseDao) {
        this.courseDao = courseDao;
    }    
    
    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        em = emf.createEntityManager();
        courseDao = new CourseDaoImpl(em);
        lectureDao = new LectureDaoImpl(em);
    }    
   
    @Test
    public void testCreateCourse() {
        Lecture l1 = new Lecture();
        
        l1.setLabel("LABEL");
        l1.setTopicDescription("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        l1.setTpDay(DayOfWeek.WED);
        
        em.getTransaction().begin();
        lectureDao.createLecture(l1);
        em.getTransaction().commit();
        
        Course c1 = new Course();

        c1.setLevel("A1");
        c1.setName("Angličtina 1");
        c1.setCode("VCJ-A1");
        c1.setCourseLanguage(Language.GERMAN);
        c1.addLecture(l1);
        
        em.getTransaction().begin();
        courseDao.createCourse(c1);
        em.getTransaction().commit();
        
        Long id = c1.getId();
        assertNotNull(id);
        
        em.getTransaction().begin();
        Course res = courseDao.getCourseById(id);
        em.getTransaction().commit();
        
        assertEquals("VCJ-A1", res.getCode());
        assertDeepEquals(c1, res);
    }

    @Test
    public void testGetCourseByID() {
        try {
            assertNull(courseDao.getCourseByCode(""));
            assertEquals(1, 2); // simple fail
        } catch (IllegalArgumentException ex) {
            assertEquals(true, true);
        }
        Course c1 = new Course();
        c1.setLevel("A1");
        c1.setName("Angličtina 1");
        c1.setCode("VCJ-A1");
        c1.setCourseLanguage(Language.HINDI);
        
        em.getTransaction().begin();
        courseDao.createCourse(c1);
        em.getTransaction().commit();
        
        Long id = c1.getId();
        assertNotNull(id);
        em.getTransaction().begin();
        Course res = courseDao.getCourseById(id);
        em.getTransaction().commit();
        
        assertEquals("VCJ-A1", res.getCode());
        assertDeepEquals(c1, res);
    }

    @Test
    public void testGetCourseByName() throws Exception {       
        Course course1 = new Course();
        course1.setCode("PV888");
        course1.setCourseLanguage(Language.MANDARIN);
        course1.setLevel("beginner");
        course1.setName("Crash course");
        
        em.getTransaction().begin();
        courseDao.createCourse(course1);
        em.getTransaction().commit();
        
        String courseName = course1.getName();
        
        em.getTransaction().begin();
        Course result = courseDao.getCourseByName(courseName);
        em.getTransaction().commit();
        assertEquals(course1, result);
        assertDeepEquals(course1, result);
    }

    @Test
    public void testGetCourseEnteryLevel() throws Exception {
        Course course1 = new Course();
        course1.setCode("PV888");
        course1.setCourseLanguage(Language.TAMIL);
        course1.setLevel("beginner");
        course1.setName("Crash course");
        
        em.getTransaction().begin();
        courseDao.createCourse(course1);
        em.getTransaction().commit();
        
        String courseLevel = course1.getLevel();
        
        em.getTransaction().begin();
        List<Course> result = courseDao.getCourseByEntryLevel(courseLevel);
        em.getTransaction().commit();
        
        List<Course> actual = new ArrayList<>();
        actual.add(course1);
        assertCollectionEquals((Collection) actual, (Collection) result);
    }

    @Test
    public void testGetCourseByLanguage() throws Exception {
        Course course1 = new Course();
        course1.setCode("PV888");
        course1.setCourseLanguage(Language.FRENCH);
        course1.setLevel("beginner");
        course1.setName("Crash course");
        
        em.getTransaction().begin();
        courseDao.createCourse(course1);
        em.getTransaction().commit();
        
        Language courseLang = course1.getCourseLanguage();
        em.getTransaction().begin();
        List<Course> result = courseDao.getCourseByLanguage(courseLang);
        em.getTransaction().commit();
        
        List<Course> actual = new ArrayList<>();
        actual.add(course1);
        assertCollectionEquals((Collection) actual, (Collection) result);
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course course1 = new Course();
        String newCode = "X";
        String newLevel = "XX";
        String newName = "XXX";
        
        course1.setCode("PV888");
        course1.setCourseLanguage(Language.ITALIAN);
        course1.setLevel("beginner");
        course1.setName("Crash course");
        
        em.getTransaction().begin();
        courseDao.createCourse(course1);
        em.getTransaction().commit();
        
        Long id = course1.getId();
        course1.setCode(newCode);
        course1.setCourseLanguage(Language.ITALIAN);
        course1.setLevel(newLevel);
        
        course1.setName(newName);
        em.getTransaction().begin();
        courseDao.updateCourse(course1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        course1 = courseDao.getCourseById(id);
        em.getTransaction().commit();
        
        assertEquals(id, course1.getId());
        assertEquals(newCode, course1.getCode());
        assertEquals(newLevel, course1.getLevel());
        assertEquals(newName, course1.getName());
        assertEquals(Language.ITALIAN, course1.getCourseLanguage());
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Course course1 = new Course();
        Course course2 = new Course();
        int before, after;

        course1.setCode("PV888");
        course1.setCourseLanguage(Language.ARABIC);
        course1.setLevel("beginner");
        course1.setName("Crash course");
        
        course2.setCode("PV888");
        course2.setCourseLanguage(Language.ARABIC);
        course2.setLevel("beginner");
        course2.setName("Crash course"); 
        
        em.getTransaction().begin();
        courseDao.createCourse(course2);
        courseDao.createCourse(course1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        assertNotNull(courseDao.getCourseById(course1.getId()));
        assertNotNull(courseDao.getCourseById(course2.getId()));

        before = courseDao.getAllCourses().size();
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        courseDao.deleteCourse(course1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        after = courseDao.getAllCourses().size();
        assertNull(courseDao.getCourseById(course1.getId()));
        assertNotNull(courseDao.getCourseById(course2.getId()));
        em.getTransaction().commit();
        
        assertEquals(before - after, 1);
    }

    private void assertDeepEquals(Course expected, Course actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCourseLanguage(), actual.getCourseLanguage());
        assertEquals(expected.getLevel(), actual.getLevel());
    }
    
}