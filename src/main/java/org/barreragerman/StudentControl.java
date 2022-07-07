package org.barreragerman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barreragerman.entity.Address;
import org.barreragerman.entity.BehaviourAttentionCall;
import org.barreragerman.exceptions.DAO_exception;
import org.barreragerman.repositoryDAO.daoImpl.BehaviourAttentionCallDAOImpl;
import org.barreragerman.service.serviceImpl.AddressServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class StudentControl {

    private static final Logger LOGGER = LogManager.getLogger(StudentControl.class);

    public static void main(String[] args) {

        BehaviourAttentionCallDAOImpl service = new BehaviourAttentionCallDAOImpl();
        List<BehaviourAttentionCall> callList = new ArrayList<>();
        callList = service.list();
      //  LOGGER.info(callList);

        try {
            BehaviourAttentionCall becall = new BehaviourAttentionCall();
            becall = service.getById(2);
            LOGGER.info(becall);
        } catch (DAO_exception e) {
            e.printStackTrace();
        }




//        AddressServiceImpl service = new AddressServiceImpl();
//        Address address = new Address(3,"LAzaro",124);
//        try {
//            service.insert(address);
//            service.update(address);
//        } catch (DAO_exception | SQLException e) {
//            e.printStackTrace();
//        }
//        service.deleteAddress(3);
//
//        List<Address> addressList = new ArrayList<>();
//
//        addressList = service.findAllAddresss();
//        LOGGER.info(addressList);

//        List<Student> studentList = new ArrayList<>();
//        StudentServiceImpl service = new StudentServiceImpl();
//        studentList = service.findAllStudents();
//        LOGGER.info(studentList);

//        CourseServiceImpl service = new CourseServiceImpl();
//        Course nuevo  = new Course(8, "4Q", "Sofia");
//        service.updateCourse(nuevo);
//
//
//        List<Course> courseList = new ArrayList<>();
//        courseList = service.findAllCourses();
//        System.out.println(courseList);
//

        // service.deleteCourse(nuevo);

        LOGGER.info("Hello from logger");
        System.out.println("Hello World!");

//
//        List<Teacher> teacherList = new ArrayList<>();
//        TeacherServiceImpl service = new TeacherServiceImpl();
//        teacherList = service.findAllTeachers();
//        for (Teacher teacher : teacherList) {
//            System.out.println(teacher.getName());
//        }
    }
}


//        Teacher Juan = new Teacher(2);
//        Juan =  service.getTeacherById(2);
//        System.out.println(Juan.getName());
//        Teacher Juan = new Teacher(5,"Tomas", "Flores", "32165487",7);
//        service.insertTeacher(Juan);

//        service.deleteTeacherById(Juan);


//        StudentServiceImpl service = new StudentServiceImpl();
//        Student student2 = service.getStudentById(5);
//        LOGGER.info(student2.getName());


//       TutorServiceImpl service = new TutorServiceImpl();
//       Tutor miguel = new Tutor(5,"Miguel","Carrascosa","78946512");
//        service.insertTutor(miguel);
//
//       Tutor Miguel = new Tutor(5);
//        miguel = service.getTutorById(5);
//        System.out.println(miguel.getName() + " " + miguel.getSurname() + " his phone is " + miguel.getPhoneNumber());

//        service.deleteTutorById(Miguel);
//        List<Tutor> tutorList = new ArrayList<>();
//        tutorList = service.findAllTutors();
//        for (Tutor tutor : tutorList) {
//            LOGGER.info(tutor.toString()+tutor.getName()+tutor.getSurname()+tutor.getPhoneNumber());
//        }


//                StudentServiceImpl service = new StudentServiceImpl();
//                Student student1 = new Student("Sebastian","Kulfprit","789465");
//        service.insertStudentDAO(student1);

//        StudentServiceImpl service = new StudentServiceImpl();
//        List<Student> studentList1 = service.listStudentSDAO();
//        for (Student student : studentList1) {
//            LOGGER.info(student.toString() + student.getName());
//        }

/**
 * Here I INSERT a new student to the DB
 * <p>
 * Here I UPDATE an existen element into the DB
 * <p>
 * Here I DELETE an element
 * <p>
 * Here I find an element by ID
 * <p>
 * Here we create a list of student from the DB
 * <p>
 * Here I UPDATE an existen element into the DB
 * <p>
 * Here I DELETE an element
 * <p>
 * Here I find an element by ID
 * <p>
 * Here we create a list of student from the DB
 * <p>
 * Here I UPDATE an existen element into the DB
 * <p>
 * Here I DELETE an element
 * <p>
 * Here I find an element by ID
 * <p>
 * Here we create a list of student from the DB
 */
//        StudentServiceImpl service = new StudentServiceImpl();
//        Student student1 = new Student("Tim","Ibraimovich","789465");
//        service.insertStudentDAO(student1);
/**
 * Here I UPDATE an existen element into the DB
 */
// IStudentService studentDAO = new IStudentService();
//        Student modifyingStudent = new Student(7, "Patrick", "Schutts", "78946513");
//        IStudentService updateDAO1 = new IStudentService();
//        updateDAO1.updateEntity(modifyingStudent);

/**
 * Here I DELETE an element
 */
//        Student student3 = new Student(21);
//        service.deleteStudentDAO(student3);

/**
 * Here I find an element by ID
 */
//        Student student2 = new Student(7);
//        service.getStudentDAOById(7);
//        LOGGER.info(student2.toString() + student2.getName());

/**
 * Here we create a list of student from the DB
 */
//        StudentServiceImpl service = new StudentServiceImpl();
//        List<Student> studentList = service.findAllStudents();
//        for (Student student : studentList) {
//            LOGGER.info(student.toString() + student.getName());
//        }