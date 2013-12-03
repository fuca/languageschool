package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Language;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public class LecturerTO {
    
    private Long Id;
    private String firstName;
    private String lastName;
    private Set<LectureTO> lectures = new HashSet<>();
    private Language nativeSpeaker;
    private List<Language> languages = new ArrayList<>();

    public LecturerTO() { }
    
    public LecturerTO(Long Id, String firstName, String lastName, Language nativeSpeaker, List<Language> languages, Set<LectureTO> lectures) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nativeSpeaker = nativeSpeaker;
	this.languages = languages;
	//this.lectures = lectures;
    }
    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
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

    public Set<LectureTO> getLectures() {
        return lectures;
    }

    public void setLectures(Set<LectureTO> lectures) {
        this.lectures = lectures;
    }

    public Language getNativeSpeaker() {
        return nativeSpeaker;
    }

    public void setNativeSpeaker(Language nativeSpeaker) {
        this.nativeSpeaker = nativeSpeaker;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 97 * hash + (this.Id != null ? this.Id.hashCode() : 0);
	hash = 97 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
	hash = 97 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
	hash = 97 * hash + (this.nativeSpeaker != null ? this.nativeSpeaker.hashCode() : 0);
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
	final LecturerTO other = (LecturerTO) obj;
	if (this.Id != other.Id && (this.Id == null || !this.Id.equals(other.Id))) {
	    return false;
	}
	if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
	    return false;
	}
	if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
	    return false;
	}
	if (this.nativeSpeaker != other.nativeSpeaker) {
	    return false;
	}
	return true;
    }
}
