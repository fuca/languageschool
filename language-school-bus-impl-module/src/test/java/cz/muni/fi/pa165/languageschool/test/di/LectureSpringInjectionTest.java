package cz.muni.fi.pa165.languageschool.test.di;

import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.service.LectureService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Michal Fučík
 */
public class LectureSpringInjectionTest extends BaseSpringInjectionTest {
    
    @Autowired
    private LectureService lectureService;
    
    @Test
    public void testCreate() {
//        LectureTO lto = new LectureTO();
//
//        assertNotNull(lectureService);
//        lectureService.createLecture(lto);
//        
//        assertNotNull(lto.getId());
//       
//        LectureTO ltoDb = lectureService.getLectureByID(lto.getId());
//        assertEquals(lto.getId(), ltoDb.getId());
//        assertEquals(lto.getLabel(), ltoDb.getLabel());
//        assertEquals(lto.getTopicDescription(), ltoDb.getTopicDescription());
//        assertEquals(lto.getTpDay(), ltoDb.getTpDay());
    }    
}
