package cz.muni.fi.pa165.languageschool.test.dao;

import cz.muni.fi.pa165.languageschool.test.BaseTest;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dao.impl.LecturerDaoImpl;
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
public class LecturerDaoImplTest extends BaseTest {

    private LecturerDaoImpl lecturerDao;
    
    public void setLecturerDao(LecturerDaoImpl lecturerDao) {
        this.lecturerDao = lecturerDao;
    }
    
    @Before
    public void SetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test");
        em = emf.createEntityManager();
        lecturerDao = new LecturerDaoImpl(em);
    }    
    
    
    @Test
    public void testCreateSingle() {
        Lecturer lec = new Lecturer();
        String name = "Máma";
        String last = "Táta";
        lec.setFirstName(name);
        lec.setLastName(last);
        em.getTransaction().begin();
        lecturerDao.createLecturer(lec);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Lecturer res = lecturerDao.getLecturerById(lec.getId());
        em.getTransaction().commit();
        assertEquals(res.getFirstName(), lec.getFirstName());
        assertEquals(res.getLastName(), lec.getLastName());
    }
    
    @Test
    public void testCreateLecturer() throws Exception {
        int before, after;
        Lecturer lecturer1 = new Lecturer();
        lecturer1.setNativeSpeaker(Language.DUTCH);
        em.getTransaction().begin();
        before = lecturerDao.getAllLecturers().size();
        
        lecturerDao.createLecturer(lecturer1);
        
        after = lecturerDao.getAllLecturers().size();
        assertEquals(after - before, 1);
        
        Long lecturerId = lecturer1.getId();
        assertNotNull(lecturerId);
        Lecturer result = lecturerDao.getLecturerById(lecturerId);
        em.getTransaction().commit();
        assertEquals(lecturer1, result);
        assertDeepEquals(lecturer1,result);
    }

    @Test
    public void testGetLecturerByID() throws Exception {
        Lecturer lecturer1 = new Lecturer();
        em.getTransaction().begin();
        assertNull(lecturerDao.getLecturerById(50L));
        
        lecturerDao.createLecturer(lecturer1);
        
        Long lecturerId = lecturer1.getId();
        Lecturer result = lecturerDao.getLecturerById(lecturerId);
        em.getTransaction().commit();
        assertEquals(lecturer1, result);
        assertDeepEquals(lecturer1, result);
    }

    @Test
    public void testGetlecturerByName() throws Exception {
        Lecturer lecturer1 = new Lecturer();
        Lecturer lecturer2 = new Lecturer();
        String fName = "Michal";
        String lName = "Fucik";
        
        lecturer1.setFirstName(fName);
        lecturer1.setLastName(lName);
        
        lecturer2.setFirstName(fName);
        lecturer2.setLastName(lName);
        em.getTransaction().begin();
        lecturerDao.createLecturer(lecturer1);
        lecturerDao.createLecturer(lecturer2);
        
        List<Lecturer> result = lecturerDao.getLecturerByName(fName + " " + lName);
        List<Lecturer> actual = new ArrayList<Lecturer>();
        actual.add(lecturer1);
        actual.add(lecturer2);
        em.getTransaction().commit();
        assertCollectionEquals((Collection) actual, (Collection) result);
    }

    @Test
    public void testUpdateLecturer() throws Exception {
        Lecturer lecturer1 = new Lecturer();
        Lecturer lecturer2 = new Lecturer();
        Lecturer result1, result2;
        Long id1, id2;
        String fName = "Michal";
        String lName = "Fucik";
        String nfName = "Pavlina";
        String nlName = "Hola";
        
        lecturer1.setFirstName(fName);
        lecturer1.setLastName(lName);
        lecturer1.setNativeSpeaker(Language.ARABIC);
        
        lecturer2.setFirstName(fName);
        lecturer2.setLastName(lName);
        lecturer2.setNativeSpeaker(Language.HINDI);
        
        em.getTransaction().begin();
        lecturerDao.createLecturer(lecturer1);
        lecturerDao.createLecturer(lecturer2);
        em.getTransaction().commit();
        
        id1 = lecturer1.getId();
        id2 = lecturer2.getId();
        
        lecturer1.setFirstName(nfName);
        lecturer1.setLastName(nlName);
        lecturer1.setNativeSpeaker(Language.ARABIC);
        
        em.getTransaction().begin();
        lecturerDao.updateLecturer(lecturer1);
        
        result1 = lecturerDao.getLecturerById(id1);
        result2 = lecturerDao.getLecturerById(id2);
        em.getTransaction().commit();
        
        assertEquals(lecturer1.getFirstName(), nfName);
        assertEquals(lecturer1.getLastName(), nlName);
        assertDeepEquals(lecturer1, result1);
        assertDeepEquals(lecturer2, result2);
    }

    @Test
    public void testDeleteLecturer() throws Exception {
        int before, after;
        Lecturer lecturer1 = new Lecturer();
        Lecturer lecturer2 = new Lecturer();
        
        em.getTransaction().begin();
        lecturerDao.createLecturer(lecturer1);
        lecturerDao.createLecturer(lecturer2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        assertNotNull(lecturerDao.getLecturerById(lecturer1.getId()));
        assertNotNull(lecturerDao.getLecturerById(lecturer2.getId()));
        before = lecturerDao.getAllLecturers().size();
        lecturerDao.deleteLecturer(lecturer1);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        after = lecturerDao.getAllLecturers().size();
        assertNull(lecturerDao.getLecturerById(lecturer1.getId()));
        assertNotNull(lecturerDao.getLecturerById(lecturer2.getId()));
        em.getTransaction().commit();
        
        assertEquals(before - after, 1);
        
    }

    private void assertDeepEquals(Lecturer expected, Lecturer actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNativeSpeaker(), actual.getNativeSpeaker());
    }
    
 }