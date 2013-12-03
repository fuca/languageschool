package cz.muni.fi.pa165.languageschool.web;

import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.service.LectureService;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.service.CourseService;
import cz.muni.fi.pa165.languageschool.service.LecturerService;
import static cz.muni.fi.pa165.languageschool.web.BaseActionBean.escapeHTML;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@UrlBinding("/lectures/{$event}/{activity.id}")
public class LectureActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(LectureActionBean.class);
    
    @SpringBean
    protected LectureService lectureService;
    
    @SpringBean
    protected LecturerService lecturerService;
    
    @SpringBean
    protected CourseService courseService;

    private List<LecturerTO> lecturers;
    
    private List<LectureTO> lectures;
    
    private List<CourseTO> courses;
    
    @Validate(on = {"addLecture", "saveLecture"}, required = true)
    private Long courseId;
    
    private CourseTO courseTO;

    public List<LecturerTO> getLecturers() {
        return lecturers;
    }
    
    public Long getCourseId() {
        return courseId;
    }

    public void setCourse(Long courseId) {
        this.courseId = courseId;
    }
    
    @ValidateNestedProperties(value = {
        //@Validate(on = {"addLecture", "saveLecture"}, field = "course", required = true),
        @Validate(on = {"addLecture", "saveLecture"}, field = "topicDescription", required = true),
        //@Validate(on = {"addLecture", "saveLecture"}, field = "tpTime", required = true),
        @Validate(on = {"addLecture", "saveLecture"}, field = "tpDay", required = true),
        @Validate(on = {"addLecture", "saveLecture"}, field = "label", required = true)
    })
    private LectureTO lecture;

    public LectureTO getLecture() {
        return lecture;
    }

    public void setLecture(LectureTO lecture) {
        this.lecture = lecture;
    }

    public List<CourseTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTO> courses) {
        this.courses = courses;
    }

    public List<LectureTO> getLectures() {
        return lectures;
    }

    public void setLectures(List<LectureTO> lectures) {
        this.lectures = lectures;
    }

    @DefaultHandler
    public Resolution list() {
	log.info("Default handler list was called just now.");
        lecturers = (List<LecturerTO>) lecturerService.getAllLecturers();
        lectures = (List<LectureTO>) lectureService.getAllLectures();
        courses = (List<CourseTO>) courseService.getAllCourses();
        return new ForwardResolution("/jsp/lecture/list.jsp");
    }
    
    public Resolution addLecture() {
        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ found course id {}", courseId);
        courseTO = courseService.getCourseById(courseId);
        lecture.setCourse(courseTO);
        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ course setted up {}", lecture.getCourse().getId());
        lectureService.createLecture(lecture);
        this.getContext().getMessages().add(
                new LocalizableMessage("lecture.add.success", 
                                        escapeHTML(lecture.getLabel())));
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Lecture has been successfully added. " + lecture);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution deleteLecture() {
        /* TODO kontrola, jestli se pri smazani lecture, kterou ma nekdo zapsany, 
         * smaze i od nich.. nebo se to musi udelat v dau ... disenroll */
        String id = this.getContext().getRequest().getParameter("lecture.id");
        lecture = lectureService.getLectureByID(Long.parseLong(id));
        lectureService.deleteLecture(lecture);
        this.getContext().getMessages().add(
                new LocalizableMessage("lecture.delete.success",
                                        escapeHTML(lecture.getLabel())));
        log.info("Lecture bas been successfuly deleted. " + lecture);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution editLecture() {
        log.info("editLecture called " + lecture);
        String id = getContext().getRequest().getParameter("lecture.id");
        if (id == null)
            return new ForwardResolution(this.getClass(), "list");
        lecture = lectureService.getLectureByID(Long.parseLong(id));
        lecturers = (List<LecturerTO>) lecturerService.getAllLecturers();
        courses = (List<CourseTO>) courseService.getAllCourses();
        return new ForwardResolution("/jsp/lecture/edit.jsp");
    }
    
    public Resolution updateLecture() {
        log.debug("UPDATING lecture={}", lecture);
        lectureService.updateLecture(lecture);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancelEdit() {
        log.info("Lecture editing cancelled");
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        lectures = (List) lectureService.getAllLectures();
        return null;
    }
}
