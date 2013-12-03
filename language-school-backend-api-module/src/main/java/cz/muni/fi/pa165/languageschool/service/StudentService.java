package cz.muni.fi.pa165.languageschool.service;

import cz.muni.fi.pa165.languageschool.dto.StudentTO;
import java.util.Collection;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
public interface StudentService {
    /**
     * Creates new student.
     * @param student The student entity. Student cannot be null.
     */
    void createStudent(StudentTO student);
   
    /**
     * Fetches all students within the database
     * @return collection
     */
    Collection<StudentTO> getAllStudents();
    
    /**
     * Fetch student by given id.
     * @param id Id of searched student.
     * @return The found student or null if there is not anyone with given id.
     */
    StudentTO getStudent(Long id);
    
    /**
     * Fetch student by given name
     * @param name Name of the student.
     * @return List of students with given name.
     */
    Collection<StudentTO> getStudent(String name);
    
    /**
     * Update student.
     * @param student Student to be updated.
     */
    void updateStudent(StudentTO student);
    
    /**
     * Delete student
     * @param student Student to be deleted.
     */
    void deleteStudent(StudentTO student);
    
}
