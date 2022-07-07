package org.barreragerman.service;


import org.barreragerman.entity.Teacher;

import java.util.List;

public interface ITeacherService {

    List<Teacher> findAllTeachers();

    Teacher getTeacherById(Integer integer);

    void updateTeacher(Teacher teacher);

    void insertTeacher(Teacher teacher);

    void deleteTeacherById(Teacher integer);
}
