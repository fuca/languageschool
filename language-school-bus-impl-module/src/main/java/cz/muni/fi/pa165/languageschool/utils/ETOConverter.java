package cz.muni.fi.pa165.languageschool.utils;

import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.Student;
import cz.muni.fi.pa165.languageschool.dto.StudentTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class ETOConverter {

    // -------------------------- TO's to JPA Entities -------------------------
    // 
    /**
     * LectureTO to Lecture convert (whole content)
     * @param lto
     * @return result of conversion
     */
    public static Lecture convertLectureTO(LectureTO lto) {
        if (lto == null) return null;
	Lecture res = new Lecture();
	
	res.setId(lto.getId());
	res.setTpDay(lto.getTpDay());
	res.setTpTime(lto.getTpTime());
	res.setLabel(lto.getLabel());
	res.setTopicDescription(lto.getTopicDescription());
	res.setCourse(ETOConverter.convertCourseTO(lto.getCourse()));

	for (StudentTO s : lto.getEnrolledStudents()) {
	    res.enroll(ETOConverter.convertStudentTO(s));
	}
	return res;
    }

    /**
     * LectuterTO to Lecturer convert (whole content)
     * @param inlto
     * @return result of conversion
     */
    public static Lecturer convertLecturerTO(LecturerTO inlto) {
        if (inlto == null) return null;
	Lecturer res = new Lecturer();
	res.setId(inlto.getId());
	res.setFirstName(inlto.getFirstName());
	res.setLastName(inlto.getLastName());

	for (Language lan : inlto.getLanguages()) {
	    res.addLanguage(lan);
	}

	for (LectureTO lto : inlto.getLectures()) {
	    Lecture l = new Lecture();
	    l.setLabel(lto.getLabel());
	    l.setTopicDescription(lto.getTopicDescription());
	    l.setTpDay(lto.getTpDay());
	    l.setTpTime(lto.getTpTime());
	    l.setLecturer(res);

	    res.addLecture(l);
	}
	res.setNativeSpeaker(inlto.getNativeSpeaker());

	return res;
    }

    /**
     * StudentTO to Student convert (whole content)
     * @param sto
     * @return result of conversion
     */
    public static Student convertStudentTO(StudentTO sto) {
        if (sto == null) return null;
	Student res = new Student();
	res.setId(sto.getId());
	res.setFirstName(sto.getFirstName());
	res.setLastName(sto.getLastName());

	for (LectureTO lto : sto.getEnrolledLectures()) {
	    Lecture l = new Lecture();
	    l.setLabel(lto.getLabel());
	    l.setTopicDescription(lto.getTopicDescription());
	    l.setTpDay(lto.getTpDay());
	    l.setTpTime(lto.getTpTime());
	    l.setLecturer(ETOConverter.convertLecturerTO(lto.getLecturer()));

	    res.enroll(l);
	}

	return res;
    }
 
    /**
     * CourseTO to Course convert (whole content)
     * @param cto
     * @return result of conversion
     */
    public static Course convertCourseTO(CourseTO cto) {
	if (cto == null) return null;
	Course res = new Course();
	res.setId(cto.getId());
	res.setCode(cto.getCode());
	res.setCourseLanguage(cto.getTaughtLanguage());
	res.setLevel(cto.getRequiredLevel());
	res.setName(cto.getName());
//	for (LectureTO l : cto.getRelatedLectures()) {
//	    res.addLecture(ETOConverter.convertLectureTO(l));
//	}
	return res;
    }

    // -------------------------- JPA Entities to TO'S -------------------------
    /**
     * Course to CourseTO convert (whole content)
     *
     * @param crs
     * @return result of conversion
     */
    public static CourseTO convertCourse(Course crs) {
        if (crs == null) return null;
	CourseTO res = new CourseTO(crs.getId(), crs.getCode(), crs.getName(),
		crs.getCourseLanguage(), crs.getLevel(), new HashSet<LectureTO>());
//	Set<LectureTO> sto = new HashSet<LectureTO>();
	
//        for (Lecture l : crs.getRelatedLectures()) {
//            LectureTO lect = ETOConverter.convertLecture(l);
//	    sto.add(lect);
//	}
        
//	res.setRelatedLectures(sto);
	return res;
    }

    /**
     * Lecturer to LecturerTO convert (whole content)
     *
     * @param lrr
     * @return result of conversion
     */
    public static LecturerTO convertLecturer(Lecturer lrr) {
        if (lrr == null) return null;
	LecturerTO res = new LecturerTO(lrr.getId(), lrr.getFirstName(), lrr.getLastName(),
		lrr.getNativeSpeaker(), new ArrayList<Language>(), new HashSet<LectureTO>());
	Set<LectureTO> ltos = new HashSet<>();
	for (Lecture l : lrr.getLectures()) {
	    ltos.add(ETOConverter.convertLecture(l));
	}
	res.setLectures(ltos);
	return res;
    }

    /**
     * Student to StudentTO convert (whole content)
     *
     * @param std
     * @return result of conversion
     */
    public static StudentTO convertStudent(Student std) {
        if (std == null) return null;
	StudentTO res = new StudentTO(std.getId(), std.getFirstName(), std.getLastName(), new HashSet<LectureTO>());
	Set<LectureTO> lto = new HashSet<>();
	for (Lecture l : std.getEnrolledLectures()) {
	    lto.add(ETOConverter.convertLecture(l));
	}
	res.setEnrolledLectures(lto);
	return res;
    }

    /**
     * Lecture to LectureTO conversion (whole content)
     *
     * @param lr
     * @return result of conversion
     */
    public static LectureTO convertLecture(Lecture lr) {
        if (lr == null) return null;
	CourseTO cour = convertCourse(lr.getCourse());
        
        LectureTO res = new LectureTO(lr.getId(), lr.getLabel(), lr.getTopicDescription(),
		cour, lr.getTpTime(), lr.getTpDay());
	
        Set<StudentTO> stos = new HashSet<>();
	for (Student s : lr.getEnrolledStudents()) {
	    stos.add(ETOConverter.convertStudent(s));
	}
	res.setEnrolledStudents(stos);
	return res;
    }
}
