package cz.muni.fi.pa165.languageschool.test.di;

import cz.muni.fi.pa165.languageschool.dto.StudentTO;
import cz.muni.fi.pa165.languageschool.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Fučík
 */
public class StudentSpringInjectionTest extends BaseSpringInjectionTest {
    
    @Autowired
    private StudentService studentService;
    
    @Test
    public void testCreate() {
//        StudentTO lto = new StudentTO();
//        lto.setFirstName("M");
//        lto.setLastName("F");
//        assertNotNull(studentService);
//        studentService.createStudent(lto);
//        
//        assertNotNull(lto.getId());
//       
//        StudentTO ltoDb = studentService.getStudent(lto.getId());
//        assertEquals(lto.getId(), ltoDb.getId());
//        assertEquals(lto.getFirstName(), ltoDb.getFirstName());
//        assertEquals(lto.getLastName(), ltoDb.getLastName());
//        
    }    
}
