package cz.muni.fi.pa165.languageschool.dao;

import cz.muni.fi.pa165.languageschool.Lecturer;
import java.util.Collection;

/**
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public interface LecturerDao {
    
    /**
     * Persists lecturer into the database
     * @param lecturer 
     */
    void createLecturer(Lecturer lecturer);
    
    /**
     * Updates lecturer within the database
     * @param lecturer 
     */
    void updateLecturer(Lecturer lecturer);
    
    /**
     * Removes given entity from database
     * @param lecturer 
     */
    void deleteLecturer(Lecturer lecturer);
    
    /**
     * Fetches lecturer with given id
     * @param id
     * @return lecturer entity
     */
    Lecturer getLecturerById(Long id);
    
    /**
     * Fetches all lecturers with given name
     * @param name
     * @return collection
     */
    Collection<Lecturer> getLecturerByName(String name);
    
    /**
     * Fetches all lecturer entities from the database
     * @return collection of lecturers
     */
    Collection<Lecturer> getAllLecturers();
}