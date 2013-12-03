package cz.muni.fi.pa165.languageschool;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Course entity
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Entity
@Table(name ="Course")
//@NamedQuery(name = "findAllCourses", query = "SELECT c FROM Course c")
public class Course implements Comparable<Course>,Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;
    
    private Language taughtLanguage;
    
    private String requiredLevel;
    
    @OneToMany(mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Lecture> relatedLectures;
    
    // -------------------------------------------------------------------------
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Lecture> getRelatedLectures() {
        return relatedLectures;
    }
    
    public void setRelatedLectures(Set<Lecture> lcs) {
        if (lcs != null)
            for (Lecture l : lcs) {
                relatedLectures.add(l); // mohl bych pouzit addAll, ale to uz bych neosetril relaci
            if (l.getCourse() != this)
                l.setCourse(this);
        }
    }
    
    public void addLecture(Lecture lec) {
        if (lec != null) {
            this.relatedLectures.add(lec);
            if (lec.getCourse() != this) {
                lec.setCourse(this);
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Define language of the course
     * @param taughtLanguage taughtLanguage cannot be null
     */
    public void setCourseLanguage(Language courseLanguage) {
        this.taughtLanguage = courseLanguage;
    }
    
    /**
     * Get language of the course
     * @return taughtLanguage
     */
    public Language getCourseLanguage() {
        return taughtLanguage;
    }
    
    /**
     * Define requiredLevel of the course.
     * @param requiredLevel requiredLevel cannot be null
     */
    public void setLevel(String courseLevel) {
        this.requiredLevel = courseLevel;
    }
    
    /**
     * Get requiredLevel of the course
     * @return requiredLevel
     */
    public String getLevel() {
        return requiredLevel;
    }
    
    @Override
    public int hashCode() {
        return (this.id != null ? this.id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Course c) {
        if (c == null) 
            throw new NullPointerException("Argument lec was null");
        
        if (getId() == null) {
            if (getCode() != null) {
                return getCode().compareToIgnoreCase(c.getCode());
            }
        }
        return getId().compareTo(c.getId());
    }
    
    @Override
    public String toString() {
        return "[Course {id="+ getId() + ", code=" + getCode() + ", name=" + getName() + ", level=" + getLevel() + ", language="+ getCourseLanguage() + "}]";
    }
}
