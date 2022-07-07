package org.barreragerman.service.serviceImpl;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barreragerman.entity.Student;
import org.barreragerman.exceptions.DAO_exception;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImplTest extends TestCase {

    private static Logger LOGGER = LogManager.getLogger(StudentServiceImplTest.class);
    StudentServiceImpl studentServiceUnderTest = new StudentServiceImpl();

    @Test
    public void testFindAllStudents() {
        //given
        List<Student> studentList = new ArrayList<>();
        //when
        studentList = studentServiceUnderTest.findAllStudents();
        boolean expected = false;
        if (!studentList.isEmpty()) {
            expected = true;
        }
        //then
        assertTrue(expected);
    }

    @Test
    public void testGetStudentById() {
        //given
        Student marti = new Student(2, "Marti", "Barreda", "'457812'");
        //when
        Student martiUnderTest = new Student();
        martiUnderTest = studentServiceUnderTest.getStudentById(2);

        //then
        assertEquals(marti, martiUnderTest);
    }

    @Test
    public void testUpdateStudent() {
        //given
        Student tomasToUpdate = new Student(2, "Tomas", "Frias", "456789");
        //when
        studentServiceUnderTest.updateStudent(tomasToUpdate);
        Student afterUpdated = new Student();
        afterUpdated = studentServiceUnderTest.getStudentById(2);

        //then
        assertEquals(tomasToUpdate, afterUpdated);
    }

    @Test
    public void testInsertStudent() {
        //given
        Student jose = new Student(9, "Jose", "Martinez", "123465");
        studentServiceUnderTest.insertStudent(jose);

        //when
        Student joseUnderTest = new Student();
        joseUnderTest = studentServiceUnderTest.getStudentById(9);

        //then
        assertEquals(jose, joseUnderTest);
    }

    public void testDeleteStudentById() {
        //given
        Student studentToDelete = new Student(5);
        //when
        studentServiceUnderTest.deleteStudentById(studentToDelete);
        Student studentDoesntExists = new Student();
        studentDoesntExists = studentServiceUnderTest.getStudentById(5);

        //then
        Assert.assertNull(studentDoesntExists);
    }
}