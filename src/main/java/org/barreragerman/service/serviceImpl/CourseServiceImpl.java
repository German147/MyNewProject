package org.barreragerman.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barreragerman.entity.Course;
import org.barreragerman.exceptions.DAO_exception;
import org.barreragerman.repositoryDAO.daoImpl.CourseDAOImpl;
import org.barreragerman.service.ICourseService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements ICourseService {

    private static final Logger LOGGER  = LogManager.getLogger(CourseServiceImpl.class);
    private static final CourseDAOImpl courseDAOImpl = new CourseDAOImpl();

    @Override
    public List<Course> findAllCourses() {
       List<Course> courseList = new ArrayList<>();
       courseList = courseDAOImpl.list();
        return courseList;
    }

    @Override
    public Course getCourseById(Integer integer) {
       Course course = new Course();
        try {
            course = courseDAOImpl.getById(integer);
        } catch (DAO_exception e) {
            LOGGER.error(e.getMessage());
        }
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        try {
            courseDAOImpl.update(course);
        } catch (DAO_exception e) {
          LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void insertCourse(Course course) {
        try {
            courseDAOImpl.insert(course);
        } catch (DAO_exception | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteCourse(Course course) {
        try {
            courseDAOImpl.delete(course);
        } catch (DAO_exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
