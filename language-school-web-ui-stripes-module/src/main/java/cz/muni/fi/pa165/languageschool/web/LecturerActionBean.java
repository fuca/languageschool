package cz.muni.fi.pa165.languageschool.web;

import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.service.LectureService;
import cz.muni.fi.pa165.languageschool.service.LecturerService;
import static cz.muni.fi.pa165.languageschool.web.BaseActionBean.escapeHTML;
import java.util.List;
import java.util.Set;
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
@UrlBinding("/lecturers/{$event}/{activity.id}")
public class LecturerActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(LecturerActionBean.class);
    
    @SpringBean
    protected LecturerService lecturerService;
    
    @SpringBean
    protected LectureService lectureService;

    private List<LecturerTO> lecturers;
    
    private LecturerTO lecturer;
    
    private List<LectureTO> lectures;
    
    private List<Language> languages;

    public List<Language> getLanguages() {
        return languages;
    }
    
    public List<LectureTO> getLectures() {
        return lectures;
    }

    public void setLectures(List<LectureTO> lectures) {
        this.lectures = lectures;
    }
    
    public List<LecturerTO> getLecturers() {
        return lecturers;
    }
    
    public LecturerTO getLecturer(){
        return lecturer;
    }

    public void setLecturers(List<LecturerTO> lecturers) {
        this.lecturers = lecturers;
    }
    
    public void setLecturer(LecturerTO lecturer){
        this.lecturer = lecturer;
    }

    //--- part for showing a list of lecturers ----
    @DefaultHandler 
    public Resolution list() {
	log.info("Default handler list");
        lecturers = (List<LecturerTO>) lecturerService.getAllLecturers();
        return new ForwardResolution("/jsp/lecturer/list.jsp");
    }

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "firstName", required = true),
            @Validate(on = {"add", "save"}, field = "lastName", required = true),
            @Validate(on = {"add", "save"}, field = "nativeSpeaker", required = true),
            @Validate(on = {"add", "save"}, field = "languages", required = true),
    })
    
    public Resolution add() {
        log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ADDING lecturer={}", lecturer);
        lecturerService.createLecturer(lecturer);
        getContext().getMessages().add(new LocalizableMessage("lecturer.add.success",escapeHTML(lecturer.getFirstName()+" "+lecturer.getLastName())));
        return new RedirectResolution(this.getClass(), "list");
    }
     
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        lecturers = (List<LecturerTO>) lecturerService.getAllLecturers();
        //return null to let the event handling continue
        return null;
    }
    
    //--- part for deleting a student ----
    public Resolution delete() {
        log.debug("delete({})", lecturer.getId());
        //only id is filled by the form
        lecturer = lecturerService.getLecturerById(lecturer.getId());
        lecturerService.deleteLecturer(lecturer);
        getContext().getMessages().add(new LocalizableMessage("lecturer.delete.success",escapeHTML(escapeHTML(lecturer.getFirstName()+" "+lecturer.getLastName()))));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadLecturerFromDatabase() {
       log.debug("LOADING lecturer={}", lecturer);
        String id = getContext().getRequest().getParameter("lecturer.id");
        if (id == null) 
            return;
        lecturer = lecturerService.getLecturerById(Long.parseLong(id));
        lectures = (List<LectureTO>) lectureService.getAllLectures();
        languages = lecturer.getLanguages();
    }
    
    public Resolution edit() {
        log.info("EDIT Lecturer={}", lecturer);
        return new ForwardResolution("/jsp/lecturer/edit.jsp");
    }
    
    public Resolution save() {
        log.debug("UPDATING lecturer={}", lecturer);
       
        lecturerService.updateLecturer(lecturer);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancelEdit() {
        log.info("Student editing cancelled");
        return new RedirectResolution(this.getClass(), "list");
    }
}
