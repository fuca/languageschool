package cz.muni.fi.pa165.languageschool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Entity
@Table(name = "Person")
public class Student extends Person implements Serializable {
    
    @ManyToMany
    @JoinTable(
      name="STUD_LEC",
      joinColumns={@JoinColumn(name="STUD_ID", referencedColumnName="PERSON_ID")},
      inverseJoinColumns={@JoinColumn(name="LEC_ID", referencedColumnName="LECTURE_ID")})
    private Set<Lecture> enrolledLectures = new HashSet<>();
    
    // -------------------------------------------------------------------------
    
    public Set<Lecture> getEnrolledLectures() {
        return enrolledLectures;
    }

    public void enroll(Lecture l) {
        this.enrolledLectures.add(l);
        if (!l.getEnrolledStudents().contains(this))
            l.getEnrolledStudents().add(this);
    }
    
    public void disenroll(Lecture l) {
        this.enrolledLectures.remove(l);
        if (l.getEnrolledStudents().contains(this))
            l.getEnrolledStudents().remove(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        
        if (obj == this)
            return true;
        
        if (getClass() != obj.getClass()) 
            return false;
        
        final Student other = (Student) obj;
        if (this.getId() == null || !this.getId().equals(other.getId()))
            return false;

        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
            hash += 13 * getId().hashCode();
            hash += 13 * getFirstName().hashCode();
            hash += 13 * getLastName().hashCode();
        return hash;
    }
}