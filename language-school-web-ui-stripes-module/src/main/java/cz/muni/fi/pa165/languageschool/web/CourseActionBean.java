package cz.muni.fi.pa165.languageschool.web;

import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.service.CourseService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
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
@UrlBinding("/courses/{$event}/{activity.id}")
public class CourseActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(CourseActionBean.class);
    
    @SpringBean
    protected CourseService courseService;

    private List<CourseTO> courses;
    
    private CourseTO course;
    
    public CourseTO getCourse(){
        return course;
    }

    public List<CourseTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTO> courses) {
        this.courses = courses;
    }
    
    public void setCourse(CourseTO course){
        this.course = course;
    }

    public CourseService getCourseService() {
        return courseService;
    }
    
    

   //--- part for showing a list of courses ----
    @DefaultHandler
    public Resolution list() {
	log.info("Default handler list");
        courses = (List<CourseTO>) courseService.getAllCourses();
        return new ForwardResolution("/jsp/course/list.jsp");
    }
    
    //--- part for adding a course ----
  /*   private Long id;
    private String code;
    private String name;
    private Language taughtLanguage;
    private String requiredLevel;
    private Set<LectureTO> relatedLectures;*/
     @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "code", required = true),
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "taughtLanguage", required = true),
            @Validate(on = {"add", "save"}, field = "requiredLevel", required = true),
           
    })
     
    @Validate
    public Resolution add() {
        log.debug("add() course={}", course);
        courseService.createCourse(course);
        getContext().getMessages().add(new LocalizableMessage("course.add.success",escapeHTML(course.getCode()+" "+course.getName()),escapeHTML(course.getCode()+" "+course.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
     
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        courses = (List<CourseTO>) courseService.getAllCourses();
        //return null to let the event handling continue
        return null;
    }
   
    //--- part for deleting a course ----
    public Resolution delete() {
        log.debug("delete({})", course.getId());
        //only id is filled by the form
        course = courseService.getCourseById(course.getId());
        courseService.deleteCourse(course);
        getContext().getMessages().add(new LocalizableMessage("course.delete.success",escapeHTML(course.getCode()+" "+course.getName()),escapeHTML(course.getCode()+" "+course.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadStudentFromDatabase() {
       log.debug("LOADING course={}", course);
        String ids = getContext().getRequest().getParameter("course.id");
        if (ids == null) 
            return;
        course = courseService.getCourseById(Long.parseLong(ids));
    }
    
    public Resolution edit() {
        log.info("EDIT Course={}", course);
        return new ForwardResolution("/jsp/course/edit.jsp");
    }
    
    public Resolution save() {
        log.debug("UPDATING course={}", course);
       
        courseService.updateCourse(course);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancelEdit() {
        log.info("Course editing cancelled");
        return new RedirectResolution(this.getClass(), "list");
    }
}
