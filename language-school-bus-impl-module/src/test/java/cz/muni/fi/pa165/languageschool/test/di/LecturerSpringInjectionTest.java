package cz.muni.fi.pa165.languageschool.test.di;

import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.service.LecturerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Fučík
 */
public class LecturerSpringInjectionTest extends BaseSpringInjectionTest {
    
    @Autowired
    private LecturerService lecturerService;
    
    @Test
    public void testCreate() {
//        LecturerTO lto = new LecturerTO();
//        lto.setFirstName("M");
//        lto.setLastName("F");
//        assertNotNull(lecturerService);
//        lecturerService.createLecturer(lto);
//        
//        assertNotNull(lto.getId());
//       
//        LecturerTO ltoDb = lecturerService.getLecturerById(lto.getId());
//        assertEquals(lto.getId(), ltoDb.getId());
//        assertEquals(lto.getFirstName(), ltoDb.getFirstName());
//        assertEquals(lto.getLastName(), ltoDb.getLastName());
        
    }    
}
