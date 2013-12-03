package cz.muni.fi.pa165.languageschool.test.di;

import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.service.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Fučík
 */
public class CourseSpringInjectionTest extends BaseSpringInjectionTest {
    
    @Autowired
    private CourseService courseService;
    
    @Test
    public void testCreate() {
//        CourseTO lto = new CourseTO();
//        lto.setCode("PV180");
//        assertNotNull(courseService);
//        courseService.createCourse(lto);
//        
//        assertNotNull(lto.getId());
//       
//        CourseTO ltoDb = courseService.getCourseById(lto.getId());
//        assertEquals(lto.getId(), ltoDb.getId());
//        assertEquals(lto.getCode(), ltoDb.getCode());
    }    
}
