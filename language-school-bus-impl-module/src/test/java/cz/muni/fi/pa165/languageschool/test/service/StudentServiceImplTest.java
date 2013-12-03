package cz.muni.fi.pa165.languageschool.test.service;


import cz.muni.fi.pa165.languageschool.Student;
import cz.muni.fi.pa165.languageschool.dto.StudentTO;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.service.impl.StudentServiceImpl;
import cz.muni.fi.pa165.languageschool.dao.StudentDao;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collection;
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
public class StudentServiceImplTest {

    StudentServiceImpl studentService;
    StudentDao studentDao;


    /**
     * Method which set up new student service
     */
    @Before
    public void setUp() throws Exception {
        studentDao = Mockito.mock(StudentDao.class);
        studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);

    }

    /**
     * Method which we use for cleaning
     */
    @After
    public void tearDown() {
        studentDao = null;
        studentService = null;
    }

    @Test
    public void testCreateStudent() throws IOException {
        StudentTO student = new StudentTO(new Long(5),"Maki", "T", new HashSet<LectureTO>());
	Student s = ETOConverter.convertStudentTO(student);
        s.setId(new Long(5));
	s.setFirstName("Maki");
        s.setLastName("T");
        studentService.createStudent(student);
	Mockito.verify(studentDao, Mockito.times(1)).createStudent(s);

    }

    @Test
    public void testGetAllStudents() throws IOException {
	Collection<Student> exc = new ArrayList<Student>();
	exc.add(new Student());
	exc.add(new Student());
	
	Mockito.when(studentDao.getAllStudents()).thenReturn(exc);
	Collection<StudentTO> res = studentService.getAllStudents();
	Mockito.verify(studentDao, Mockito.times(1)).getAllStudents();
	assertEquals(exc.size(), res.size());
    }

    @Test
    public void testGetStudentId() throws IOException {
        StudentTO student = new StudentTO(new Long(5),"Maki", "T", new HashSet<LectureTO>());
        Student s = ETOConverter.convertStudentTO(student);
        s.setId(new Long(5));
	s.setFirstName("Maki");
        s.setLastName("T");
        studentService.createStudent(student);
  
	Long id = student.getId();
	Mockito.when(studentDao.getStudentById(id)).thenReturn(s);
	StudentTO res = studentService.getStudent(id);
	Mockito.verify(studentDao, Mockito.times(1)).getStudentById(id);
	assertEquals(student.getId(), res.getId());
    }

    @Test
    public void testGetStudentByName() throws IOException {
	StudentTO student = new StudentTO(new Long(5),"Maki", "T", new HashSet<LectureTO>());
	Collection<Student> exc = new ArrayList<Student>();
	exc.add(new Student());
	Mockito.when(studentDao.getStudentsByName("Maki T")).thenReturn(exc);
	Collection<StudentTO> res = studentService.getStudent("Maki T");
	Mockito.verify(studentDao, Mockito.times(1)).getStudentsByName("Maki T");
	assertEquals(exc.size(), res.size());
    }

    @Test
    public void testDeleteStudent() {
        StudentTO student = new StudentTO(new Long(5),"Maki", "T", new HashSet<LectureTO>());
        Student s = ETOConverter.convertStudentTO(student);
        s.setId(new Long(5));
	s.setFirstName("Maki");
        s.setLastName("T");
        studentService.deleteStudent(student);
	Mockito.verify(studentDao, Mockito.times(1)).deleteStudent(s);

    }

    @Test
    public void testUpdateStudent() {
	StudentTO student = new StudentTO(new Long(5),"Maki", "T", new HashSet<LectureTO>());
        Student s = ETOConverter.convertStudentTO(student);
        s.setId(new Long(5));
	s.setFirstName("Maki");
        s.setLastName("T");
        studentService.updateStudent(student);
	Mockito.verify(studentDao, Mockito.times(1)).updateStudent(s);       

    }
}
