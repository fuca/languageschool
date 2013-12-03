package cz.muni.fi.pa165.languageschool.test.service;

import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.service.impl.LectureServiceImpl;
import cz.muni.fi.pa165.languageschool.dao.LectureDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Milos
 */
@RunWith(MockitoJUnitRunner.class)
public class LectureServiceImplTest {

    LectureDao lectureDao;
    LectureServiceImpl lectureService;
    CourseTO courseTO;
    LecturerTO lecturerTO;
    LectureTO lectureTO;

    /**
     * Method which set up new course service
     */
    @Before
    public void setUp() throws Exception {
	lectureDao = Mockito.mock(LectureDao.class);
	lectureService = new LectureServiceImpl();
	lectureService.setLectureDao(lectureDao);
	courseTO = new CourseTO(1L, "WWW", "XXX", Language.ARABIC, "B1", new HashSet<LectureTO>());
	lecturerTO = new LecturerTO(2L, "M", "F", Language.CZECH, new ArrayList<Language>(), new HashSet<LectureTO>());
	lectureTO = new LectureTO(3L, "Lecture1", "some nice lecture", courseTO, new Date(), DayOfWeek.FRI);
	lectureTO.setLecturer(lecturerTO);
    }

    /**
     * Method which we use for cleaning
     */
    @After
    public void tearDown() {
	lectureDao = null;
	lectureService = null;
    }

    @Test
    public void testCreateLecture() {
	LectureTO lecture = lectureTO;
	Lecture l = ETOConverter.convertLectureTO(lecture);

	lectureService.createLecture(lecture);
	Mockito.verify(lectureDao, Mockito.times(1)).createLecture(l);

    }

    @Test
    public void testGetAllLectures() throws IOException {
	Collection<Lecture> exc = new ArrayList<>();
	Lecture l = ETOConverter.convertLectureTO(lectureTO);
	exc.add(l);

	Mockito.when(lectureDao.getAllLectures()).thenReturn(exc);
	Mockito.when(lectureDao.getLecturesLecturer(l))
		.thenReturn(ETOConverter.convertLecturerTO(lecturerTO));
	Collection<LectureTO> res = lectureService.getAllLectures();
	Mockito.verify(lectureDao, Mockito.times(1)).getAllLectures();
	assertEquals(exc.size(), res.size());

    }

    @Test
    public void testGetLectureById() throws IOException {
	LectureTO lecture = lectureTO;
	Long id = lecture.getId();
	Lecture l = ETOConverter.convertLectureTO(lecture);

	Mockito.when(lectureDao.getLectureByID(id)).thenReturn(l);
	
	LectureTO res = lectureService.getLectureByID(id);
	Mockito.verify(lectureDao, Mockito.times(1)).getLectureByID(id);
	assertEquals(lecture.getId(), res.getId());
    }

    @Test
    public void testGetLectureByLabel() throws IOException {
	LectureTO lecture = lectureTO;
	Collection<Lecture> exc = new ArrayList<Lecture>();
	exc.add(ETOConverter.convertLectureTO(lectureTO));
	Mockito.when(lectureDao.getLectureByLabel("Lecture2")).thenReturn(exc);
	Collection<LectureTO> res = lectureService.getLectureByLabel("Lecture2");
	Mockito.verify(lectureDao, Mockito.times(1)).getLectureByLabel("Lecture2");
	assertEquals(exc.size(), res.size());
    }

    @Test
    public void testDeleteLecture() {
	LectureTO lecture = lectureTO;
	Lecture l = ETOConverter.convertLectureTO(lecture);

	lectureService.deleteLecture(lecture);
	Mockito.verify(lectureDao, Mockito.times(1)).deleteLecture(l);
    }

    @Test
    public void testUpdateLecture() {

	LectureTO lecture = lectureTO;
	Lecture l = ETOConverter.convertLectureTO(lecture);

	lectureService.updateLecture(lecture);
	Mockito.verify(lectureDao, Mockito.times(1)).updateLecture(l);


    }
}