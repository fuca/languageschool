package cz.muni.fi.pa165.languageschool.service;

import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import java.util.Collection;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public interface LecturerService {
    
    /**
     * Persists lecturer into the database
     * @param lecturerTO 
     */
    public void createLecturer(LecturerTO lecturer);
    
    /**
     * Updates lecturer within the database
     * @param lecturerTO 
     */
    public void updateLecturer(LecturerTO lecturer);
    
    /**
     * Removes given entity from database
     * @param lecturerTO 
     */
    public void deleteLecturer(LecturerTO lecturer);
    
    /**
     * Fetches lecturer with given id
     * @param id
     * @return lecturerTO
     */
    public LecturerTO getLecturerById(Long id);
    
    /**
     * Fetches all lecturers with given name
     * @param name
     * @return collection
     */
    public Collection<LecturerTO> getLecturerByName(String name);
    
    /**
     * Fetches all lecturer entities from the database
     * @return collection of lecturers
     */
    public Collection<LecturerTO> getAllLecturers();
}
