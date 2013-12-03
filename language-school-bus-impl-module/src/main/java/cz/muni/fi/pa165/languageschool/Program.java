package cz.muni.fi.pa165.languageschool;

import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.service.impl.LectureServiceImpl;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author xfucik1
 */
public class Program {
    public static void main (String[] args) {
    LectureServiceImpl lservice = new LectureServiceImpl();
    CourseTO cto = new CourseTO(0L, "Prdel kurz", "Prdel jak cyp", Language.ENGLISH, "shit", new HashSet<LectureTO>());
    LectureTO lto = new LectureTO(0L, "Lecture", "nejaka picovina", cto, new Date(), DayOfWeek.WED);
    lservice.createLecture(lto);
    }
}
