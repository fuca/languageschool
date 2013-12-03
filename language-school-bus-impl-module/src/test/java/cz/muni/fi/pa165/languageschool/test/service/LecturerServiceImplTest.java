package cz.muni.fi.pa165.languageschool.test.service;

import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.service.impl.LecturerServiceImpl;
import cz.muni.fi.pa165.languageschool.dao.LecturerDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class LecturerServiceImplTest {

    private LecturerDao ldaomock;
    private LecturerServiceImpl lsi;
    private LecturerTO lec0;
    private LecturerTO lec1;
    
    /**
     * Method which set up new course service
     */
    @Before
    public void setUp() throws Exception {
	ldaomock = Mockito.mock(LecturerDao.class);
	lsi = new LecturerServiceImpl();
	lsi.setLecturerDao(ldaomock); 
	lec0 = new LecturerTO(0L,"M", "F", Language.ENGLISH, new ArrayList<Language>(), new HashSet<LectureTO>());
	lec1 = new LecturerTO(1L,"M", "F", Language.ENGLISH, new ArrayList<Language>(), new HashSet<LectureTO>());
    }
    
    /**
     * Test of createLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testCreateLecturer() {

	LecturerTO lec = lec0;
	Lecturer l = ETOConverter.convertLecturerTO(lec);
	
        
	l.setFirstName("M");
	l.setLastName("F");
	l.setNativeSpeaker(Language.ENGLISH);
	
	lsi.createLecturer(lec);
	
	Mockito.verify(ldaomock, Mockito.times(1)).createLecturer(l);
    }

    /**
     * Test of updateLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testUpdateLecturer() {
	LecturerTO lec = lec1;
	Lecturer l = ETOConverter.convertLecturerTO(lec);
        
	l.setFirstName("M");
	l.setLastName("F");
	l.setNativeSpeaker(Language.ENGLISH);
	lsi.updateLecturer(lec);
	Mockito.verify(ldaomock, Mockito.times(1)).updateLecturer(l);
    }

    /**
     * Test of deleteLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testDeleteLecturer() {
	LecturerTO lec = lec1;
	Lecturer l = ETOConverter.convertLecturerTO(lec);
       
	l.setFirstName("M");
	l.setLastName("F");
	l.setNativeSpeaker(Language.ENGLISH);
	lsi.deleteLecturer(lec);
	Mockito.verify(ldaomock, Mockito.times(1)).deleteLecturer(l);
    }

    /**
     * Test of getLecturerById method, of class LecturerServiceImpl.
     */
    @Test
    public void testGetLecturerById() {
	LecturerTO lec = lec1;
	Long id = lec.getId();
	Lecturer lrr = ETOConverter.convertLecturerTO(lec);
        
	lrr.setId(id);
	lrr.setFirstName(lec.getFirstName());
	lrr.setLastName(lec.getLastName());
	lrr.setNativeSpeaker(lec.getNativeSpeaker());
	Mockito.when(ldaomock.getLecturerById(id)).thenReturn(lrr);
	LecturerTO res = lsi.getLecturerById(id);
	Mockito.verify(ldaomock, Mockito.times(1)).getLecturerById(id);
	assertEquals(lec.getId(), res.getId());
    }

    /**
     * Test of getLecturerByName method, of class LecturerServiceImpl.
     */
    @Test
    public void testGetLecturerByName() {
	LecturerTO lec = lec1;
	Collection<Lecturer> exc = new ArrayList<Lecturer>();
	exc.add(new Lecturer());
	Mockito.when(ldaomock.getLecturerByName("M F")).thenReturn(exc);
	Collection<LecturerTO> res = lsi.getLecturerByName("M F");
	Mockito.verify(ldaomock, Mockito.times(1)).getLecturerByName("M F");
	assertEquals(exc.size(), res.size());
    }

    /**
     * Test of getAllLecturers method, of class LecturerServiceImpl.
     */
    @Test
    public void testGetAllLecturers() {
	Collection<Lecturer> exc = new ArrayList<Lecturer>();
	exc.add(new Lecturer());
	exc.add(new Lecturer());
	
	Mockito.when(ldaomock.getAllLecturers()).thenReturn(exc);
	Collection<LecturerTO> res = lsi.getAllLecturers();
	Mockito.verify(ldaomock, Mockito.times(1)).getAllLecturers();
	assertEquals(exc.size(), res.size());
    }
}
