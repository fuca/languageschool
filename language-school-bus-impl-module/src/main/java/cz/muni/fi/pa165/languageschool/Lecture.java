package cz.muni.fi.pa165.languageschool;

import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import cz.muni.fi.pa165.languageschool.utils.DayOfWeek;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Entity
@Table(name = "Lecture")
public class Lecture implements Comparable<Lecture>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Lecture_id")
    private Long id;
    private String label;
    private String topicDescription;
    
    @ManyToOne
    private Course course;
    
    @ManyToOne
    private Lecturer lecturer;
    
    @ManyToMany(mappedBy = "enrolledLectures", fetch = FetchType.EAGER)
    private Set<Student> enrolledStudents = new TreeSet<>();
    @Temporal(TemporalType.TIME)
    private Date tpTime;
    private DayOfWeek tpDay;

    // -------------------------------------------------------------------------
    public Lecture() {
        this.label = "No label yet";
        this.topicDescription = "No topic description yet";
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        if (enrolledStudents != null) {
            for (Student s : enrolledStudents) {
                enroll(s);
            }
        }
    }

    public void enroll(Student s) {
        this.enrolledStudents.add(s);
        if (s != null && !s.getEnrolledLectures().contains(this)) {
            s.getEnrolledLectures().add(this);
        }
    }

    public void disenroll(Student s) {
        this.enrolledStudents.remove(s);
        if (s != null && s.getEnrolledLectures().contains(this)) {
            s.getEnrolledLectures().remove(this);
        }
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
        if (lecturer != null && !lecturer.getLectures().contains(this)) {
            lecturer.addLecture(this);
        }
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if (course != null && !course.getRelatedLectures().contains(this)) {
            course.addLecture(this);
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String name) {
        this.label = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += 7 * getId().hashCode();
        hash += 7 * getLabel().hashCode();
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
        final Lecture other = (Lecture) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Lecture lec) {
        if (lec == null) {
            throw new NullPointerException("Argument lec was null");
        }

        if (getId() == null) {
            if (getLabel() != null) {
                return getLabel().compareToIgnoreCase(lec.getLabel());
            }
        }
        return getId().compareTo(lec.getId());

    }
}