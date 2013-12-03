package cz.muni.fi.pa165.languageschool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Entity
@Table(name = "Lecturer")
public class Lecturer extends Person implements Serializable {

    @ElementCollection
    private List<Language> languages = new ArrayList<>();
    @OneToMany(mappedBy = "lecturer")
//	@JoinColumn(name = "LR_ID", referencedColumnName = "LECTURER_ID")
    private Set<Lecture> lectures = new HashSet<>();
    
    private Language nativeSpeaker;

    // -------------------------------------------------------------------------
    public Set<Lecture> getLectures() {
        return lectures;
    }
    
    public void setLectures(Collection<Lecture> lrs) {
        if (lrs != null)
            for (Lecture l : lrs)
                addLecture(l);
    }

    public void addLecture(Lecture lecture) {
        if (lecture != null) {
            this.lectures.add(lecture);
            if (lecture.getLecturer() != this) {
                lecture.setLecturer(this);
            }
        }
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void addLanguage(Language lan) {
        if (lan != null)
            this.languages.add(lan);
    }
    
    public void setLanguages(Collection<Language> ls) {
        if (ls != null)
            for (Language l : ls)
                addLanguage(l);
    }

    public Language getNativeSpeaker() {
        return nativeSpeaker;
    }

    public void setNativeSpeaker(Language nativeSpeaker) {
        this.nativeSpeaker = nativeSpeaker;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Lecturer other = (Lecturer) obj;
        if (this.getId() == null || !this.getId().equals(other.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += 13 * getId();
        hash += 13 * getFirstName().hashCode();
        hash += 13 * getLastName().hashCode();
        return hash;
    }
}