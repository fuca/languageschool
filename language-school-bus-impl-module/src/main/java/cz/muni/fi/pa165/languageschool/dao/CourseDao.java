package cz.muni.fi.pa165.languageschool.dao;

import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecture;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Milos petrovic (2456790) mpetrovickg(at)gmail.com
 */
public interface CourseDao {
    
    /**
     * 
     * @param course 
     */
    void createCourse(Course course);
    
    /**
     * 
     * @return list of courses
     */
    List<Course> getAllCourses();
    
    /**
     * 
     * @param id
     * @return 
     */
    Course getCourseById(Long id);
    
    /**
     * 
     * @param name
     * @return 
     */
    Course getCourseByName(String name);
    
    /**
     * 
     * @param code
     * @return 
     */
    Course getCourseByCode(String code);
    
    /**
     * 
     * @param level
     * @return 
     */
    List<Course> getCourseByEntryLevel(String level);
    
    /**
     * 
     * @param taughtLanguage
     * @return 
     */
    List<Course> getCourseByLanguage(Language taughtLanguage);
    
    /**
     * 
     * @param course 
     * @return course
     */
    Course updateCourse(Course course);
    
    /**
     * 
     * @param course 
     */
    void deleteCourse(Course course);
    
    /**
     * 
     * @param crs
     * @return set of lectures
     */
    Set<Lecture> getCourseRelatedLectures (Course crs);
}