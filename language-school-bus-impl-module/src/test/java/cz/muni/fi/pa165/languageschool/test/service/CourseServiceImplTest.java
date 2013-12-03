package cz.muni.fi.pa165.languageschool.test.service;

import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.service.impl.CourseServiceImpl;
import cz.muni.fi.pa165.languageschool.dao.CourseDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Milos added org.mockito and org.hamcrest
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest{

    CourseServiceImpl courseService;
    CourseDao courseDao;
    LectureTO lectureTO;
    CourseTO courseTO;
    
    /**
     * Method which set up new course service
     */
    @Before
    public void setUp() throws Exception {
        courseDao = Mockito.mock(CourseDao.class);
        courseService = new CourseServiceImpl();
        courseService.setCourseDao(courseDao);
        
        lectureTO = Mockito.mock(LectureTO.class);
	courseTO = new CourseTO(5L, "code", "name", Language.ENGLISH, "level", new HashSet<LectureTO>());
    }

    /**
     * Method which we use for cleaning
     */
    @After
    public void tearDown() {
        courseDao = null;
        courseService = null;
    }

    @Test
    public void testCreateCourse() throws IOException {
        CourseTO course = courseTO;
	Course c = new Course();
        c.setId(new Long(5));
	c.setCode("code");
        c.setCourseLanguage(Language.ENGLISH);
        c.setLevel("level");
        c.setName("name");
        courseService.createCourse(course);
	Mockito.verify(courseDao, Mockito.times(1)).createCourse(c);

    }

    @Test
    public void testGetAllCourses() throws IOException {
        List<Course> exc = new ArrayList<Course>();
	exc.add(new Course());
	exc.add(new Course());
	
	Mockito.when(courseDao.getAllCourses()).thenReturn(exc);
	Collection<CourseTO> res = courseService.getAllCourses();
	Mockito.verify(courseDao, Mockito.times(1)).getAllCourses();
	assertEquals(exc.size(), res.size());
    }

    @Test
    public void testGetCourseById() throws IOException {
        CourseTO course = courseTO;
	Course c = new Course();
        Long id = course.getId();
        c.setId(new Long(5));
	c.setCode("code");
        c.setCourseLanguage(Language.ENGLISH);
        c.setLevel("level");
        c.setName("name");
        
        Mockito.when(courseDao.getCourseById(id)).thenReturn(c);
	CourseTO res = courseService.getCourseById(id);
	Mockito.verify(courseDao, Mockito.times(1)).getCourseById(id);
	assertEquals(course.getId(), res.getId());
    }
    
    @Test
    public void testGetCourseByName() throws IOException {
        CourseTO course = courseTO;
	Course c = new Course();
        String name = course.getName();
        c.setId(new Long(5));
	c.setCode("code");
        c.setCourseLanguage(Language.ENGLISH);
        c.setLevel("level");
        c.setName("name");
        
        Mockito.when(courseDao.getCourseByName(name)).thenReturn(c);
	CourseTO res = courseService.getCourseByName(name);
	Mockito.verify(courseDao, Mockito.times(1)).getCourseByName(name);
	assertEquals(course.getId(), res.getId());       
    }
    
    @Test
    public void testGetCourseByLevel() throws IOException {
         CourseTO course = courseTO;
        		      
        List<Course> exc = new ArrayList<Course>();
	exc.add(new Course());
	Mockito.when(courseDao.getCourseByEntryLevel("level")).thenReturn(exc);
	List<CourseTO> res = courseService.getCourseByEntryLevel("level");
	Mockito.verify(courseDao, Mockito.times(1)).getCourseByEntryLevel("level");
	assertEquals(exc.size(), res.size()); 
        
    }    

    @Test
    public void testGetCourseByLanguage() throws IOException {
        CourseTO course = courseTO;
        		      
        List<Course> exc = new ArrayList<Course>();
	exc.add(new Course());
	Mockito.when(courseDao.getCourseByLanguage(Language.ENGLISH)).thenReturn(exc);
	List<CourseTO> res = courseService.getCourseByLanguage(Language.ENGLISH);
	Mockito.verify(courseDao, Mockito.times(1)).getCourseByLanguage(Language.ENGLISH);
	assertEquals(exc.size(), res.size()); 

    }

     @Test
     public void testDeleteCourse(){
        CourseTO course = courseTO;
	Course c = new Course();
        c.setId(new Long(5));
        c.setCode("code");
        c.setCourseLanguage(Language.ENGLISH);
        c.setLevel("level");
        c.setName("name");
      
	courseService.deleteCourse(course);
	Mockito.verify(courseDao, Mockito.times(1)).deleteCourse(c);
           
       }    
}
