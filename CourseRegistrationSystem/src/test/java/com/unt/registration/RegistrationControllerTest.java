package com.unt.registration;

import com.unt.registration.controller.RegistrationController;
import com.unt.registration.dao.RegistrationDao;
import com.unt.registration.service.RegistrationServiceImpl;
import com.unt.registration.util.Course;
import com.unt.registration.util.Department;
import com.unt.registration.util.EnrollObject;
import com.unt.registration.util.Grade;
import com.unt.registration.util.Payment;
import com.unt.registration.util.SelectCriteria;
import com.unt.registration.util.SwapCourse;
import com.unt.registration.util.User;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.*;

/**
 *
 * @author
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class RegistrationControllerTest {
    
    @InjectMocks
    RegistrationController registrationController;
    
    @Mock
    RegistrationDao registrationDao;
    
    public RegistrationControllerTest() {
    }

    /**
     * Test of userValidate method, of class RegistrationController.
     */
//    @Test
//    public void testUserValidate() {
//        System.out.println("userValidate");
//        
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        //when(registrationDao.userValidate(any(RegistrationServiceImpl.class)));
//        when(registrationDao.userValidate(any(RegistrationServiceImpl.class))).thenReturn(true);
//
//        //Employee employee = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
//        RegistrationServiceImpl registrationServiceImpl = new RegistrationServiceImpl(registrationDao.userValidate(id));
//        ResponseEntity<Object> responseEntity = registrationController.userValidate(user);
//        
//        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
//        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
//        
//        User user = null;
//        RegistrationController instance = new RegistrationController();
//        User expResult = null;
//        User result = instance.userValidate(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of signup method, of class RegistrationController.
     */
    @Test
    public void testSignup() {
        System.out.println("signup");
        User user = null;
        RegistrationController instance = new RegistrationController();
        String expResult = "";
        String result = instance.signup(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAllDepartments method, of class RegistrationController.
     */
    @Test
    public void testFetchAllDepartments() {
        System.out.println("fetchAllDepartments");
        RegistrationController instance = new RegistrationController();
        List<Department> expResult = null;
        List<Department> result = instance.fetchAllDepartments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPassword method, of class RegistrationController.
     */
    @Test
    public void testResetPassword() {
        System.out.println("resetPassword");
        User user = null;
        RegistrationController instance = new RegistrationController();
        String expResult = "";
        String result = instance.resetPassword(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourses method, of class RegistrationController.
     */
    @Test
    public void testGetCourses() {
        System.out.println("getCourses");
        SelectCriteria selectcriteria = null;
        RegistrationController instance = new RegistrationController();
        List<Course> expResult = null;
        List<Course> result = instance.getCourses(selectcriteria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCourse method, of class RegistrationController.
     */
    @Test
    public void testFindCourse() {
        System.out.println("findCourse");
        String courseId = "";
        RegistrationController instance = new RegistrationController();
        Course expResult = null;
        Course result = instance.findCourse(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enroll method, of class RegistrationController.
     */
    @Test
    public void testEnroll() {
        System.out.println("enroll");
        EnrollObject enrollObject = null;
        RegistrationController instance = new RegistrationController();
        int expResult = 0;
        int result = instance.enroll(enrollObject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drop method, of class RegistrationController.
     */
    @Test
    public void testDrop() {
        System.out.println("drop");
        EnrollObject enrollObject = null;
        RegistrationController instance = new RegistrationController();
        boolean expResult = false;
        boolean result = instance.drop(enrollObject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchEnrolledCourses method, of class RegistrationController.
     */
    @Test
    public void testFetchEnrolledCourses() {
        System.out.println("fetchEnrolledCourses");
        User user = null;
        RegistrationController instance = new RegistrationController();
        List<Course> expResult = null;
        List<Course> result = instance.fetchEnrolledCourses(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swap method, of class RegistrationController.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        SwapCourse swapCourse = null;
        RegistrationController instance = new RegistrationController();
        int expResult = 0;
        int result = instance.swap(swapCourse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postPayment method, of class RegistrationController.
     */
    @Test
    public void testPostPayment() {
        System.out.println("postPayment");
        Payment payment = null;
        RegistrationController instance = new RegistrationController();
        int expResult = 0;
        int result = instance.postPayment(payment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewGrades method, of class RegistrationController.
     */
    @Test
    public void testViewGrades() {
        System.out.println("viewGrades");
        User user = null;
        RegistrationController instance = new RegistrationController();
        List<Grade> expResult = null;
        List<Grade> result = instance.viewGrades(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pastPayments method, of class RegistrationController.
     */
    @Test
    public void testPastPayments() {
        System.out.println("pastPayments");
        User user = null;
        RegistrationController instance = new RegistrationController();
        List<Payment> expResult = null;
        List<Payment> result = instance.pastPayments(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewDues method, of class RegistrationController.
     */
    @Test
    public void testViewDues() {
        System.out.println("viewDues");
        Payment payment = null;
        RegistrationController instance = new RegistrationController();
        float expResult = 0.0F;
        float result = instance.viewDues(payment);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mandatoryCoursesDone method, of class RegistrationController.
     */
    @Test
    public void testMandatoryCoursesDone() {
        System.out.println("mandatoryCoursesDone");
        User user = null;
        RegistrationController instance = new RegistrationController();
        List<Course> expResult = null;
        List<Course> result = instance.mandatoryCoursesDone(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mandatoryCoursesNotDone method, of class RegistrationController.
     */
    @Test
    public void testMandatoryCoursesNotDone() {
        System.out.println("mandatoryCoursesNotDone");
        User user = null;
        RegistrationController instance = new RegistrationController();
        List<Course> expResult = null;
        List<Course> result = instance.mandatoryCoursesNotDone(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchAvailableCourses method, of class RegistrationController.
     */
    @Test
    public void testFetchAvailableCourses() {
        System.out.println("fetchAvailableCourses");
        SelectCriteria selectCriteria = null;
        RegistrationController instance = new RegistrationController();
        List<Course> expResult = null;
        List<Course> result = instance.fetchAvailableCourses(selectCriteria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchNotEnrolledCourses method, of class RegistrationController.
     */
    @Test
    public void testFetchNotEnrolledCourses() {
        System.out.println("fetchNotEnrolledCourses");
        User user = null;
        RegistrationController instance = new RegistrationController();
        List<Course> expResult = null;
        List<Course> result = instance.fetchNotEnrolledCourses(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCourse method, of class RegistrationController.
     */
    @Test
    public void testAddCourse() {
        System.out.println("addCourse");
        Course course = null;
        RegistrationController instance = new RegistrationController();
        int expResult = 0;
        int result = instance.addCourse(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
