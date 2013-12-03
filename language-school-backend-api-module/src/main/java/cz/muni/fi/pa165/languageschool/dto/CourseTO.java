package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.Language;
import java.util.Set;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public class CourseTO {
    
    private Long id;
    private String code;
    private String name;
    private Language taughtLanguage;
    private String requiredLevel;
    private Set<LectureTO> relatedLectures;
    
    public CourseTO() { }

    /**
     * Constructor for creating new course
     * @param id id of the course
     * @param code code of the course
     * @param name name of the course
     * @param taughtLanguage language for which is that course
     * @param requiredLevel level of the course
     * @param lectures lectures of course
     */
    public CourseTO(Long id, String code, String name, Language taughtLanguage, String requiredLevel, Set<LectureTO> relatedLectures) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.taughtLanguage = taughtLanguage;
        this.requiredLevel = requiredLevel;
        this.relatedLectures = relatedLectures;
    }
    
    
    /**
    * Method which returns id of the course
    * @return id of the course
    */
    public Long getId() {
        return id;
    }
    /**
     * Method is used for setting Id of the course
     * @param id id of the course. Id cannot be null
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method which returns code of the course
     * @return code of the course
     */
    public String getCode() {
        return code;
    }

    /**
     * Method is used for setting code of the course 
     * @param code code of the course. Code cannot be null
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Method which returns name of the course
     * @return name of the course
     */
    public String getName() {
        return name;
    }

    /***
     * Method is used for setting the name of the course
     * @param name name of the course. Name cannot be null
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method which returns language of the course
     * @return taughtLanguage
     */
    public Language getTaughtLanguage() {
        return taughtLanguage;
    }

    /**
     * Method is used for setting taught language of the course
     * @param taughtLanguage taught language. TaughtLanugage cannot be null
     */
    public void setTaughtLanguage(Language taughtLanguage) {
        this.taughtLanguage = taughtLanguage;
    }

    /**
     * Method which returns required level of the language
     * @return requiredLevel
     */
    public String getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Method is used for setting required level of the course
     * @param requiredLevel requiredLevel cannot be null
     */
    public void setRequiredLevel(String requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    /**
     * Method which returns set of the lectures for that course
     * @return relatedLectures
     */
    public Set<LectureTO> getRelatedLectures() {
        return relatedLectures;
    }

    /**
     * Method is used for setting lectures for the course
     * @param relatedLectures lectures which we want to add to the course. relatedLectures cannot be null 
     */
    public void setRelatedLectures(Set<LectureTO> relatedLectures) {
        this.relatedLectures = relatedLectures;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
	hash = 71 * hash + (this.code != null ? this.code.hashCode() : 0);
	hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
	hash = 71 * hash + (this.taughtLanguage != null ? this.taughtLanguage.hashCode() : 0);
	hash = 71 * hash + (this.requiredLevel != null ? this.requiredLevel.hashCode() : 0);
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
	final CourseTO other = (CourseTO) obj;
	if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
	    return false;
	}
	if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
	    return false;
	}

	return true;
    }    
}
