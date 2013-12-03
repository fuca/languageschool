package cz.muni.fi.pa165.languageschool.service;

import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import java.util.Collection;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public interface LectureService {
    /**
     * Create a new lecture. Lecture cannot be null
     * @param lecture The lecture entity. Lecture cannot be null 
     */
    void createLecture(LectureTO lecture);
    
    /**
     * Fetches all lectures from the database
     * @return collection of lectures
     */
    Collection<LectureTO> getAllLectures();
    
    /**
     * Get lecture by lecture id. Lecture cannot be null
     * @param id Id cannot be null 
     */
    LectureTO getLectureByID(Long id);
    
    /**
     * Fetches lecture by given label
     * @param label
     * @return lecture
     */
    Collection<LectureTO> getLectureByLabel(String label);

    /**
     * Updates lecture entity within the database
     * @param lecture 
     */
    void updateLecture(LectureTO lecture);
    
    /**
     * Removes given entity from the database
     * @param lecture 
     */
    void deleteLecture(LectureTO lecture);  
    
    /**
     * Fetches lecture's lecturer
     * @param lecture
     * @return lecturer
     */
    LecturerTO getLecturesLecturer(LectureTO lecture);
}
