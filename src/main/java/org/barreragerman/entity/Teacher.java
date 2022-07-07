package org.barreragerman.entity;

import java.util.List;
import java.util.Objects;

public class Teacher extends Person {

    private int idTeacher;
    private String teacherDepartment;
    private int teacherAddress;
    private MedicalCertificates teacherMedicalCertificate;
    private Subject teacherSubject;
    private List<Course> courseList;

    public Teacher() {
    }

    public Teacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Teacher(int idTeacher, String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
        this.idTeacher = idTeacher;
    }

    public Teacher(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }
    public Teacher(String name, String surname, String phoneNumber,   int teacherAddress) {
        super(name, surname, phoneNumber);
        this.teacherAddress = teacherAddress;
    }

    public Teacher(int idTeacher,
                   String teacherDepartment,
                   int teacherAddress,
                   MedicalCertificates teacherMedicalCertificate,
                   Subject teacherSubject,
                   List<Course> courseList) {
        this.idTeacher = idTeacher;
        this.teacherDepartment = teacherDepartment;
        this.teacherAddress = teacherAddress;
        this.teacherMedicalCertificate = teacherMedicalCertificate;
        this.teacherSubject = teacherSubject;
        this.courseList = courseList;
    }

    public Teacher(String name,
                   String surname,
                   String phoneNumber,
                   int idTeacher,
                   String teacherDepartment,
                   int teacherAddress,
                   MedicalCertificates teacherMedicalCertificate,
                   Subject teacherSubject,
                   List<Course> courseList) {
        super(name, surname, phoneNumber);
        this.idTeacher = idTeacher;
        this.teacherDepartment = teacherDepartment;
        this.teacherAddress = teacherAddress;
        this.teacherMedicalCertificate = teacherMedicalCertificate;
        this.teacherSubject = teacherSubject;
        this.courseList = courseList;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    public int getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(int teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public MedicalCertificates getTeacherMedicalCertificate() {
        return teacherMedicalCertificate;
    }

    public void setTeacherMedicalCertificate(MedicalCertificates teacherMedicalCertificate) {
        this.teacherMedicalCertificate = teacherMedicalCertificate;
    }

    public Subject getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(Subject teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher=" + idTeacher +
                ", teacherDepartment='" + teacherDepartment + '\'' +
                ", teacherAddress=" + teacherAddress +
                ", teacherMedicalCertificate=" + teacherMedicalCertificate +
                ", teacherSubject=" + teacherSubject +
                ", courseList=" + courseList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getIdTeacher() == teacher.getIdTeacher() && getTeacherAddress() == teacher.getTeacherAddress() && Objects.equals(getTeacherDepartment(), teacher.getTeacherDepartment()) && Objects.equals(getTeacherMedicalCertificate(), teacher.getTeacherMedicalCertificate()) && Objects.equals(getTeacherSubject(), teacher.getTeacherSubject()) && Objects.equals(getCourseList(), teacher.getCourseList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTeacher(), getTeacherDepartment(), getTeacherAddress(), getTeacherMedicalCertificate(), getTeacherSubject(), getCourseList());
    }
}
