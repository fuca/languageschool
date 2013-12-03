package cz.muni.fi.pa165.languageschool.test.dao;

import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.test.BaseTest;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.dao.impl.CourseDaoImpl;
import cz.muni.fi.pa165.languageschool.dao.impl.LectureDaoImpl;
import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class LectureDaoImplTest extends BaseTest{

    private LectureDaoImpl lectureDao;
    private CourseDaoImpl courseDao;

    public void setCourseDao(CourseDaoImpl courseDao) {
        this.courseDao = courseDao;
    }
    
    public void setLectureDao(LectureDaoImpl lectureDao) {
        this.lectureDao = lectureDao;
    }
    
    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        em = emf.createEntityManager();
        lectureDao = new LectureDaoImpl(em);
        courseDao = new CourseDaoImpl(em);
    }    

    @Test
    public void testCreateSingleLecture() {
        Lecture lec = new Lecture();
        String label = "Nejaka sikovna prednaska";
        String desc = "Kecy prdy";
        lec.setLabel(label);
        lec.setTopicDescription(desc);
        lec.setTpDay(DayOfWeek.WED);
        lec.setTpTime(new Date());
        
        em.getTransaction().begin();
        lectureDao.createLecture(lec);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Lecture res = lectureDao.getLectureByID(lec.getId());
        em.getTransaction().commit();
        
        assertEquals(lec.getId(), res.getId());
        assertEquals(lec.getCourse(), res.getCourse());
        assertEquals(lec.getEnrolledStudents(), res.getEnrolledStudents());
        assertEquals(label, res.getLabel());
        assertEquals(lec.getTpTime(), res.getTpTime());
        assertEquals(lec.getTpDay(), res.getTpDay());
        assertEquals(desc, res.getTopicDescription());
    }
    
    @Test
    public void testCreateLecture() throws Exception {
        Course c1 = new Course();
        c1.setCode("BRR");
        c1.setCourseLanguage(Language.ENGLISH);
        c1.setName("Sracka");
        c1.setLevel("b1");
        
        em.getTransaction().begin();
        courseDao.createCourse(c1);
        em.getTransaction().commit();
        
        Lecture l1 = new Lecture();
        l1.setCourse(c1);
        l1.setLabel("Lecture1");
        l1.setTopicDescription("some nice lecture");
        
        em.getTransaction().begin();
        lectureDao.createLecture(l1);
        em.getTransaction().commit();
        
        Long lectureId = l1.getId();
        assertNotNull(lectureId);
        
        em.getTransaction().begin();
        Lecture result = lectureDao.getLectureByID(lectureId);
        em.getTransaction().commit();
        
        assertEquals(l1, result);
        assertDeepEquals(l1, result);
    }

    @Test
    public void testGetLectureByID() throws Exception {
        em.getTransaction().begin();
        try {
            assertEquals(0, lectureDao.getLectureByLabel(new String()).size());
            assertEquals(1, 2); // just fail
        } catch(IllegalArgumentException ex) {
            assertEquals(true, true); // just ok
        }
        
        em.getTransaction().commit();
        
        Lecture lecture1 = new Lecture();
        lecture1.setLabel("Lecture1");
        lecture1.setTopicDescription("some nice lecture");
        
        em.getTransaction().begin();
        lectureDao.createLecture(lecture1);
        em.getTransaction().commit();
        
        Long lectureId = lecture1.getId();
        
        em.getTransaction().begin();
        Lecture result = lectureDao.getLectureByID(lectureId);
        em.getTransaction().commit();
        
        assertEquals(lecture1, result);
        assertDeepEquals(lecture1, result);
    }

    @Test
    public void testGetLectureByLabel() throws Exception {
        Lecture lecture1 = new Lecture();
        Lecture lecture2 = new Lecture();
        String label1 = "Lecture1";
        String label2 = "Lecture2";
        
        lecture1.setLabel(label1);
        lecture1.setTopicDescription("some nice lecture");
        
        lecture2.setLabel(label2);
        lecture2.setTopicDescription("some bad lecture");
        
        em.getTransaction().begin();
        lectureDao.createLecture(lecture1);
        lectureDao.createLecture(lecture2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        List<Lecture> result = lectureDao.getLectureByLabel("Lec");
        em.getTransaction().commit();
        
        List<Lecture> actual = new ArrayList<>();
        actual.add(lecture1);
        actual.add(lecture2);
        
        assertCollectionEquals((Collection) actual, (Collection) result);
        
    }

    @Test
    public void testDeleteLecture() throws Exception {
        Lecture lecture1 = new Lecture();
        Lecture lecture2 = new Lecture();
        int before, after;

        lecture1.setLabel("Lecture1");
        lecture1.setTopicDescription("some nice lecture");
        
        lecture2.setLabel("Lecture2");
        lecture2.setTopicDescription("some bad lecture");
        
        em.getTransaction().begin();
        lectureDao.createLecture(lecture1);
        lectureDao.createLecture(lecture2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        assertNotNull(lectureDao.getLectureByID(lecture1.getId()));
        assertNotNull(lectureDao.getLectureByID(lecture2.getId()));

        before = lectureDao.getAllLectures().size();
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        lectureDao.deleteLecture(lecture1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        after = lectureDao.getAllLectures().size();
        assertNull(lectureDao.getLectureByID(lecture1.getId()));
        assertNotNull(lectureDao.getLectureByID(lecture2.getId()));
        em.getTransaction().commit();
        
        assertEquals(before - after, 1);
    }

    private void assertDeepEquals(Lecture expected, Lecture actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLabel(), actual.getLabel());
        assertEquals(expected.getTopicDescription(), actual.getTopicDescription());
    }
    
}