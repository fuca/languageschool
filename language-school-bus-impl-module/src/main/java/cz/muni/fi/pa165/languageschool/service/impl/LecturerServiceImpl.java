package cz.muni.fi.pa165.languageschool.service.impl;

import cz.muni.fi.pa165.languageschool.DataFetchException;
import cz.muni.fi.pa165.languageschool.utils.ETOConverter;
import cz.muni.fi.pa165.languageschool.Lecturer;
import cz.muni.fi.pa165.languageschool.dto.LecturerTO;
import cz.muni.fi.pa165.languageschool.dao.LecturerDao;
import cz.muni.fi.pa165.languageschool.service.LecturerService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Fučík (395624) michal.fuca.fucik(at)gmail.com
 */
@Service
public class LecturerServiceImpl implements LecturerService {

    private LecturerDao lecturerDao;

    public LecturerServiceImpl() {
    }

    public LecturerServiceImpl(LecturerDao dao) {
        if (dao == null) {
            throw new NullPointerException("Argument dao was null");
        }
        this.lecturerDao = dao;
    }

    public void setLecturerDao(LecturerDao lecturerDao) {
        if (lecturerDao == null) {
            throw new NullPointerException("Argument lecturerDao was null");
        }
        this.lecturerDao = lecturerDao;
    }

    public LecturerDao getLecturerDao() {
        return this.lecturerDao;
    }

    @Transactional
    @Override
    public void createLecturer(LecturerTO lecturer) {
        Lecturer lec = ETOConverter.convertLecturerTO(lecturer);
        if (lecturer == null) {
            throw new NullPointerException("Argument lecturer was null");
        }
        try {
            lecturerDao.createLecturer(lec);
            lecturer.setId(lec.getId());
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public void updateLecturer(LecturerTO lecturer) {
        if (lecturer == null) {
            throw new NullPointerException("Argument lecturere was null");
        }
        try {
            lecturerDao.updateLecturer(ETOConverter.convertLecturerTO(lecturer));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public void deleteLecturer(LecturerTO lecturer) {
        if (lecturer == null) {
            throw new NullPointerException("Argument lecturere was null");
        }
        try {
            lecturerDao.deleteLecturer(ETOConverter.convertLecturerTO(lecturer));
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
    }

    @Transactional
    @Override
    public LecturerTO getLecturerById(Long id) {
        Lecturer lecturer = null;
        if (id == null) {
            throw new NullPointerException("Argument id was null");
        }
        try {
            lecturer = lecturerDao.getLecturerById(id);
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return ETOConverter.convertLecturer(lecturer);
    }

    @Transactional
    @Override
    public Collection<LecturerTO> getLecturerByName(String name) {
        Collection<Lecturer> got = null;
        List<LecturerTO> res = new ArrayList<>();
        if (name == null) {
            throw new NullPointerException("Argument name was null");
        }
        try {
            got = lecturerDao.getLecturerByName(name);
            for (Lecturer l : got) {
                res.add(ETOConverter.convertLecturer(l));
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return Collections.unmodifiableList(res);
    }

    @Transactional
    @Override
    public Collection<LecturerTO> getAllLecturers() {
        Collection<Lecturer> got = null;
        List<LecturerTO> res = new ArrayList<>();
        try {
            got = lecturerDao.getAllLecturers();
            for (Lecturer l : got) {
                res.add(ETOConverter.convertLecturer(l));
            }
        } catch (Exception ex) {
            throw new DataFetchException(ex.toString(), ex);
        }
        return Collections.unmodifiableList(res);
    }
}
