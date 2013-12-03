package cz.muni.fi.pa165.languageschool.web;



import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.dto.StudentTO;
import cz.muni.fi.pa165.languageschool.service.LectureService;
import cz.muni.fi.pa165.languageschool.service.StudentService;
import static cz.muni.fi.pa165.languageschool.web.LectureActionBean.log;
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
@UrlBinding("/students/{$event}/{activity.id}")
public class StudentActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(StudentActionBean.class);
    
    @SpringBean
    protected StudentService studentService;
    
    @SpringBean
    protected LectureService lectureService;

    private List<StudentTO> students;

    private List<LectureTO> lectures;
    
    private StudentTO student;
    
    public List<LectureTO> getLectures(){
        return lectures;
    }
        
    public List<StudentTO> getStudents() {
        return students;
    }
    
    public StudentTO getStudent(){
        return student;
    }

    public void setLectures(List<StudentTO> students) {
        this.students = students;
    }
    
    public void setStudent(StudentTO student){
        this.student = student;
    }
    
   
//--- part for showing a list of students ----
    @DefaultHandler
    public Resolution list() {
	log.info("Default handler students list");
        students = (List<StudentTO>) studentService.getAllStudents();
        return new ForwardResolution("/jsp/student/list.jsp");
    }
    
//--- part for adding a book ----
     @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "firstName", required = true),
            @Validate(on = {"add", "save"}, field = "lastName", required = true),
           
    })
     
    public Resolution add() {
        log.debug("add() student={}", student);
        studentService.createStudent(student);
        getContext().getMessages().add(new LocalizableMessage("student.add.success",escapeHTML(student.getFirstName()+" "+student.getLastName()),escapeHTML(student.getFirstName()+" "+student.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }
     
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        students = (List<StudentTO>) studentService.getAllStudents();
        //return null to let the event handling continue
        return null;
    }
    
//--- part for deleting a student ----
    public Resolution delete() {
        log.debug("delete({})", student.getId());
        //only id is filled by the form
        student = studentService.getStudent(student.getId());
        studentService.deleteStudent(student);
        getContext().getMessages().add(new LocalizableMessage("student.delete.success",escapeHTML(student.getFirstName()+" "+student.getLastName()),escapeHTML(student.getFirstName()+" "+student.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    
//--- part for editing a book ----
    
    
   @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadStudentFromDatabase() {
       log.debug("LOADING student={}", student);
        String ids = getContext().getRequest().getParameter("student.id");
        if (ids == null) 
            return;
        student = studentService.getStudent(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("EDIT student={}", student);
      
        return new ForwardResolution("/jsp/student/edit.jsp");
    }

    public Resolution save() {
        log.debug("UPDATING student={}", student);
       
        studentService.updateStudent(student);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancelEdit() {
        log.info("Student editing cancelled");
        return new RedirectResolution(this.getClass(), "list");
    }
    
  
}
