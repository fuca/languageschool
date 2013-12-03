package cz.muni.fi.pa165.languageschool.service;

import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.Language;
import java.util.List;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public interface CourseService {
    
     /**
     * 
     * @param course 
     */
    void createCourse(CourseTO course);
    
    /**
     * 
     * @return list of courses
     */
    List<CourseTO> getAllCourses();
    
    /**
     * 
     * @param id
     * @return 
     */
    CourseTO getCourseById(Long id);
    
    /**
     * 
     * @param name
     * @return 
     */
    CourseTO getCourseByName(String name);
    
    /**
     * 
     * @param entryLevel
     * @return 
     */
    List<CourseTO> getCourseByEntryLevel(String level);
    
    /**
     * 
     * @param taughtLanguage
     * @return 
     */
    List<CourseTO> getCourseByLanguage(Language taughtLanguage);
    
    /**
     * 
     * @param course 
     * @return course
     */
    void updateCourse(CourseTO course);
    
    /**
     * 
     * @param course 
     */
    void deleteCourse(CourseTO course);
}
