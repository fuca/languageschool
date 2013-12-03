package cz.muni.fi.pa165.languageschool.service.impl;

import cz.muni.fi.pa165.languageschool.DataFetchException;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.Lecture;
import cz.muni.fi.pa165.languageschool.dto.LectureTO;
import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.dao.LectureDao;
import cz.muni.fi.pa165.languageschool.service.LectureService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Service
public class LectureServiceImpl implements LectureService {

    private LectureDao lectureDao;

    public LectureServiceImpl() {
    }

    public LectureServiceImpl(LectureDao dao) {
        if (dao == null) {
            throw new NullPointerException("Argument dao was null");
        }
        this.lectureDao = dao;
    }

    public void setLectureDao(LectureDao lectureDao) {
        if (lectureDao == null) {
            throw new NullPointerException("Argument lectureDao was null");
        }
        this.lectureDao = lectureDao;
    }

    public LectureDao getLectureDao() {
        return this.lectureDao;
    }

    @Transactional
    @Override
    public void createLecture(LectureTO lecture) {
        Lecture lto = null;
        if (lecture == null) {
            throw new NullPointerException("Argument lecture is null");
        }
        try {
            lto = ETOConverter.convertLectureTO(lecture);
            lectureDao.createLecture(lto);
            lecture.setId(lto.getId());
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public Collection<LectureTO> getAllLectures() {
        Collection<Lecture> got = null;
        Collection<LectureTO> res = new ArrayList<>();
        LectureTO lto = null;
        try {
            got = lectureDao.getAllLectures();
            for (Lecture l : got) {
                lto = ETOConverter.convertLecture(l);
                lto.setLecturer(_getLecturerTO(l));
                res.add(lto);
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return res;
    }

    @Transactional
    @Override
    public LectureTO getLectureByID(Long id) {
        LectureTO lto = null;
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        try {
            Lecture l = lectureDao.getLectureByID(id);
            lto = ETOConverter.convertLecture(l);
            lto.setLecturer(_getLecturerTO(l));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return lto;

    }

    @Transactional
    private LecturerTO _getLecturerTO(Lecture lec) {
        Lecturer lrr = null;
        if (lec == null) {
            throw new NullPointerException("Argument lec was null");
        }
        try {
            lrr = lectureDao.getLecturesLecturer(lec);
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return (lrr == null) ? null : ETOConverter.convertLecturer(lrr);
    }

    @Transactional
    @Override
    public Collection<LectureTO> getLectureByLabel(String label) {
        Collection<Lecture> got = null;
        Collection<LectureTO> res = new ArrayList<>();
        LectureTO lto = null;
        if (label == null) {
            throw new NullPointerException("Argument label was null");
        }
        try {
            got = lectureDao.getLectureByLabel(label);

            for (Lecture l : got) {
                lto = ETOConverter.convertLecture(l);
                lto.setLecturer(_getLecturerTO(l));
                res.add(lto);
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return res;

    }

    @Transactional
    @Override
    public void updateLecture(LectureTO lecture) {
        if (lecture == null) {
            throw new NullPointerException("Argument lecture was null");
        }
        try {
            lectureDao.updateLecture(ETOConverter.convertLectureTO(lecture));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public void deleteLecture(LectureTO lecture) {
        if (lecture == null) {
            throw new NullPointerException("Argument lecture was null");
        }
        try {
            lectureDao.deleteLecture(ETOConverter.convertLectureTO(lecture));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }
    
    @Transactional
    @Override
    public LecturerTO getLecturesLecturer(LectureTO lecture) {
        Lecture lto = null;
        LecturerTO lrrto = null;
        if (lecture == null)
            throw new NullPointerException("Argument lecture was null");
        try {
            lto = ETOConverter.convertLectureTO(lecture);
            Lecturer lrr = lectureDao.getLecturesLecturer(lto);
            lrrto = ETOConverter.convertLecturer(lrr);
        } catch (Exception ex) {
            throw new DataFetchException(ex.getMessage(), ex);
        }
        return lrrto;
    }
}

