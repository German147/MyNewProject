package org.barreragerman.service;


import org.barreragerman.entity.Student;

import java.util.List;


public interface IStudentServicegettingLists {
    // TODO: 20/05/2022
    ICreateSomething<List<Student>> studentList();

   //  Optional<Student> getStudentById(Integer integer);

    void getBehaviourCallByStudentId();

    void getMedicalCertificateByStudentId();

    void getExamsByStudentId();

    void getTutorByStudentId();




}
