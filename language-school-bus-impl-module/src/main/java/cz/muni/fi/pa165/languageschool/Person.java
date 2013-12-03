package cz.muni.fi.pa165.languageschool;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Entity
public abstract class Person implements Comparable<Person>, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Person_id")
    private Long Id;
    private String firstName;
    private String lastName;

    public void setId(Long Id){
        this.Id = Id;
    }
    public Long getId() {
        return Id;
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
    
    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
    
    @Override
    public int compareTo(Person obj) {
        int last = obj.getLastName().compareTo(this.getLastName());
        int first = 0;
        if (last == 0) {
            first = obj.getFirstName().compareTo(this.getFirstName());
            if (first == 0)
                return obj.getId().compareTo(getId());
            else return first;
        } else return last;
    }
    
    @Override
    public String toString() {
        return "[Person {id=" + getId() + ", lastName=" + getLastName() + ", firstName=" + getFirstName() +"}]";
    }
}