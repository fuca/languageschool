package cz.muni.fi.pa165.languageschool.service.impl;

import cz.muni.fi.pa165.languageschool.Course;
import cz.muni.fi.pa165.languageschool.DataFetchException;
import cz.muni.fi.pa165.languageschool.service.CourseService;
import cz.muni.fi.pa165.languageschool.dto.CourseTO;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.Language;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.dao.CourseDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Service
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    public CourseServiceImpl() {
    }

    public CourseServiceImpl(CourseDao dao) {
        if (dao == null) {
            throw new NullPointerException("Argument dao was null");
        }
        this.courseDao = dao;
    }

    public void setCourseDao(CourseDao courseDao) {
        if (courseDao == null) {
            throw new NullPointerException("Argument courseDao was null");
        }
        this.courseDao = courseDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    @Transactional
    @Override
    public void createCourse(CourseTO course) {
        Course crs = ETOConverter.convertCourseTO(course);
        if (course == null) {
            throw new NullPointerException("Argument course was null");
        }
        try {
            courseDao.createCourse(crs);
            course.setId(crs.getId());
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public List<CourseTO> getAllCourses() {
        List<CourseTO> res = new ArrayList<>();
        CourseTO cto = null;
        try {
            for (Course c : courseDao.getAllCourses()) {
                cto = ETOConverter.convertCourse(c);
                cto.setRelatedLectures(_getCourseLecturesTO(c));
                res.add(cto);
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }

        return Collections.unmodifiableList(res);
    }

    private Set<LectureTO> _getCourseLecturesTO(Course c) {
        if (c == null) {
            throw new NullPointerException("Argument c was null");
        }

        Set<LectureTO> ltos = new HashSet<>();
        try {
            Set<Lecture> lrs = courseDao.getCourseRelatedLectures(c);
            for (Lecture l : lrs) {
                LectureTO lto = (l == null) ? null : ETOConverter.convertLecture(l);
                ltos.add(lto);
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return ltos;
    }

    @Transactional
    @Override
    public CourseTO getCourseById(Long id) {
        CourseTO res = null;
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        try {
            Course course = courseDao.getCourseById(id);
            res = ETOConverter.convertCourse(course);
            res.setRelatedLectures(_getCourseLecturesTO(course));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return res;
    }

    @Transactional
    @Override
    public CourseTO getCourseByName(String name) {
        Course course = null;
        CourseTO res = null;
        if (name == null) {
            throw new NullPointerException("Argument name was null");
        }

        try {
            course = courseDao.getCourseByName(name);
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        res = ETOConverter.convertCourse(course);
        res.setRelatedLectures(_getCourseLecturesTO(course));
        return res;
    }

    @Transactional
    @Override
    public List<CourseTO> getCourseByEntryLevel(String level) {
        List<CourseTO> res = new ArrayList<>();
        if (level == null) {
            throw new NullPointerException("Argument level was null");
        }
        try {
            List<Course> list = courseDao.getCourseByEntryLevel(level);
            res = new ArrayList<>();
            for (Course c : list) {
                CourseTO cto = ETOConverter.convertCourse(c);
                cto.setRelatedLectures(_getCourseLecturesTO(c));
                res.add(cto);
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return Collections.unmodifiableList(res);
    }

    @Transactional
    @Override
    public List<CourseTO> getCourseByLanguage(Language taughtLanguage) {
        List<Course> list = null;
        List<CourseTO> res = null;
        if (taughtLanguage == null) {
            throw new NullPointerException("Argument taughtLanguage was null");
        }
        try {
            list = courseDao.getCourseByLanguage(taughtLanguage);
            res = new ArrayList<>();
            for (Course c : list) {
                CourseTO cto = ETOConverter.convertCourse(c);
                cto.setRelatedLectures(_getCourseLecturesTO(c));
                res.add(cto);
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return Collections.unmodifiableList(res);
    }

    @Transactional
    @Override
    public void updateCourse(CourseTO course) {
        if (course == null) {
            throw new NullPointerException("Argument course was null");
        }
        try {
            courseDao.updateCourse(ETOConverter.convertCourseTO(course));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public void deleteCourse(CourseTO course) {
        if (course == null) {
            throw new NullPointerException("Argument course was null");
        }
        try {
            courseDao.deleteCourse(ETOConverter.convertCourseTO(course));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }
}
