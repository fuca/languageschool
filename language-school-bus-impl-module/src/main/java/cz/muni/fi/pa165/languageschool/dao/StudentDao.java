package cz.muni.fi.pa165.languageschool.dao;

import cz.muni.fi.pa165.languageschool.Student;
import java.util.Collection;

/**
 * DAO class for managing student entities
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
public interface StudentDao {

    /**
     * Creates new student.
     * @param student The student entity. Student cannot be null.
     */
    void createStudent(Student student);
   
    /**
     * Fetches all students within the database
     * @return collection
     */
    Collection<Student> getAllStudents();
    
    /**
     * Fetch student by given id.
     * @param id Id of searched student.
     * @return The found student or null if there is not anyone with given id.
     */
    Student getStudentById(Long id);
    
    /**
     * Fetch student by given name
     * @param name Name of the student.
     * @return List of students with given name.
     */
    Collection<Student> getStudentsByName(String name);
    
    /**
     * Update student.
     * @param student Student to be updated.
     */
    void updateStudent(Student student);
    
    /**
     * Delete student
     * @param student Student to be deleted.
     */
    void deleteStudent(Student student);

}