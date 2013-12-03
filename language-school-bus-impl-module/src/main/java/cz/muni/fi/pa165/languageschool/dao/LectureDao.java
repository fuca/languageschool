package cz.muni.fi.pa165.languageschool.dao;

import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.Lecturer;
import java.util.Collection;

/**
 *
 * @author Milos Petrovic (256790) mpetrovickg(at)gmail.com
 */
public interface LectureDao {
      
    /**
     * Create a new lecture. Lecture cannot be null
     * @param lecture The lecture entity. Lecture cannot be null 
     */
    void createLecture(Lecture lecture);
    
    /**
     * Fetches all lectures from the database
     * @return collection of lectures
     */
    Collection<Lecture> getAllLectures();
    
    /**
     * Get lecture by lecture id. Lecture cannot be null
     * @param id Id cannot be null 
     */
    Lecture getLectureByID(Long id);
    
    /**
     * Fetches lecture by given label
     * @param label
     * @return collection
     */
    Collection<Lecture> getLectureByLabel(String label);

    /**
     * Updates lecture entity within the database
     * @param lecture 
     */
    void updateLecture(Lecture lecture);
    
    /**
     * Removes given entity from the database
     * @param lecture 
     */
    void deleteLecture(Lecture lecture); 
    
    /**
     * Fetches lecture's lecturer
     * @param lecture
     * @return related lecturer
     */
    Lecturer getLecturesLecturer(Lecture lecture);
}