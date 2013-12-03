package cz.muni.fi.pa165.languageschool.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public class StudentTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private Set<LectureTO> enrolledLectures = new HashSet<>();

    public StudentTO() { }
    
    public StudentTO(Long id, String firstName, String lastName, Set<LectureTO> lec) {
	this.id = id;
        this.firstName = firstName;
	this.lastName = lastName;
	this.enrolledLectures = lec;
    }

    public Set<LectureTO> getEnrolledLectures() {
	return enrolledLectures;
    }

    public void setEnrolledLectures(Set<LectureTO> enrolledLectures) {
	this.enrolledLectures = enrolledLectures;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
	final StudentTO other = (StudentTO) obj;
	if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }    
}
