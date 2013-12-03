package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public class LectureTO {
    
    private Long id;
    private String label;
    private String topicDescription;
    private CourseTO course;
    private LecturerTO lecturer;
    private Set<StudentTO> enrolledStudents = new HashSet<StudentTO>();
    private Date tpTime;
    private DayOfWeek tpDay;
    
    public LectureTO() {
        
    }

    /**
     * Constructor for the Lecture
     * @param id id of the lecture
     * @param label label of the lecture
     * @param topicDescription description of the lecture
     * @param course course to which this lecture belongs
     * @param lecturer lecturer of the course
     * @param tpTime time of the lecture
     * @param tpDay  day of the lecture
     */
    public LectureTO(Long id, String label, String topicDescription, CourseTO course, Date tpTime, DayOfWeek tpDay) {
        this.id = id;
        this.label = label;
        this.topicDescription = topicDescription;
        this.course = course;
        this.tpTime = tpTime;
        this.tpDay = tpDay;
    }

    /**
     * Method which returns id of the lecture
     * @return id of the lecture
     */
    public Long getId() {
        return id;
    }

    /**
     * Method is used for setting Id of the lecture
     * @param id id of the lecture. Id cannot be null   
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method which returns label of the lecture
     * @return label of the lecture
     */
    public String getLabel() {
        return label;
    }

    /**
     * Method is used for setting label of the lecture
     * @param label label of the lecture. Lecture cannot be null 
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Method which returns topic of the lecture
     * @return topicDescription
     */
    public String getTopicDescription() {
        return topicDescription;
    }

    /**
     * Method is used for setting topic description of the lecture
     * @param topicDescription 
     */
    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    /**
     * Method which returns course to which lecture belongs
     * @return course
     */
    public CourseTO getCourse() {
        return course;
    }

    /**
     * Method is used for setting course to which lecture belongs
     * @param course 
     */
    public void setCourse(CourseTO course) {
        this.course = course;
    }

    public LecturerTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerTO lecturer) {
        this.lecturer = lecturer;
    }

    public Set<StudentTO> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<StudentTO> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Date getTpTime() {
        return tpTime;
    }

    public void setTpTime(Date tpTime) {
        this.tpTime = tpTime;
    }

    public DayOfWeek getTpDay() {
        return tpDay;
    }

    public void setTpDay(DayOfWeek tpDay) {
        this.tpDay = tpDay;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final LectureTO other = (LectureTO) obj;
	if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }
}
