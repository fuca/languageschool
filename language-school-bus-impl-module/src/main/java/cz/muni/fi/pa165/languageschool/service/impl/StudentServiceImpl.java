package cz.muni.fi.pa165.languageschool.service.impl;

import cz.muni.fi.pa165.languageschool.DataFetchException;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.Student;
import cz.muni.fi.pa165.languageschool.dao.StudentDao;
import cz.muni.fi.pa165.languageschool.dto.StudentTO;
import cz.muni.fi.pa165.languageschool.service.StudentService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marketa Trachtova (255498) marketa.tr(at)gmail.com
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl() {
    }

    public StudentServiceImpl(StudentDao dao) {
        if (dao == null) {
            throw new NullPointerException("Argument dao was null");
        }
        this.studentDao = dao;
    }

    public void setStudentDao(StudentDao studentDao) {
        if (studentDao == null) {
            throw new NullPointerException("Argumnt studentDao was null");
        }
        this.studentDao = studentDao;
    }

    public StudentDao getStudentDao() {
        return this.studentDao;
    }

    @Transactional
    @Override
    public void createStudent(StudentTO student) {
        Student std = ETOConverter.convertStudentTO(student);
        if (student == null) {
            throw new NullPointerException("Argument student was null");
        }
        try {
            studentDao.createStudent(std);
            student.setId(std.getId());
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public Collection<StudentTO> getAllStudents() {
        Collection<Student> got = null;
        Collection<StudentTO> res = new ArrayList<>();
        try {
            got = studentDao.getAllStudents();
            for (Student st : got) {
                res.add(ETOConverter.convertStudent(st));
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return res;
    }

    @Transactional
    @Override
    public StudentTO getStudent(Long id) {
        Student std = null;
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        try {
            std = studentDao.getStudentById(id);
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return ETOConverter.convertStudent(std);
    }

    @Transactional
    @Override
    public Collection<StudentTO> getStudent(String name) {
        Collection<Student> got = null;
        Collection<StudentTO> res = new ArrayList<>();
        if (name == null) {
            throw new NullPointerException("Argument id was null");
        }
        try {
            got = studentDao.getStudentsByName(name);
            for (Student s : got) {
                res.add(ETOConverter.convertStudent(s));
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return res;
    }

    @Transactional
    @Override
    public void updateStudent(StudentTO student) {
        if (student == null) {
            throw new NullPointerException("Argument student was null");
        }
        try {
            studentDao.updateStudent(ETOConverter.convertStudentTO(student));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public void deleteStudent(StudentTO student) {
        studentDao.deleteStudent(ETOConverter.convertStudentTO(student));
    }
}
