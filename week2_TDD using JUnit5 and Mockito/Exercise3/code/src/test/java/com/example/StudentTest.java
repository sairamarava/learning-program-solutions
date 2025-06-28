package com.example;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Student Test Class demonstrating practical assertion usage
 */
public class StudentTest {
    
    private Student student;
    private Student honorStudent;
    private Student strugglingStudent;
    
    @Before
    public void setUp() {
        student = new Student("John Doe", 20, 3.0);
        honorStudent = new Student("Jane Smith", 22, 3.8);
        strugglingStudent = new Student("Bob Wilson", 19, 1.5);
    }
    
    @Test
    public void testStudentCreation() {
        // Test object creation and initial values
        assertNotNull("Student object should not be null", student);
        assertEquals("Student name should match", "John Doe", student.getName());
        assertEquals("Student age should match", 20, student.getAge());
        assertEquals("Student GPA should match", 3.0, student.getGpa(), 0.01);
        assertTrue("New student should be active", student.isActive());
    }
    
    @Test
    public void testGradeLevelCalculation() {
        // Test grade level based on GPA
        assertEquals("Honor student should be on Honor Roll", 
                    "Honor Roll", honorStudent.getGradeLevel());
        assertEquals("Regular student should be in Good Standing", 
                    "Good Standing", student.getGradeLevel());
        assertEquals("Struggling student should be on Probation", 
                    "Probation", strugglingStudent.getGradeLevel());
    }
    
    @Test
    public void testCanGraduate() {
        // Test graduation eligibility
        assertTrue("Honor student should be able to graduate", honorStudent.canGraduate());
        assertTrue("Regular student should be able to graduate", student.canGraduate());
        assertFalse("Struggling student should not be able to graduate", strugglingStudent.canGraduate());
        
        // Test with inactive student
        Student inactiveStudent = new Student("Alice Brown", 21, 3.5);
        inactiveStudent.setActive(false);
        assertFalse("Inactive student should not be able to graduate", inactiveStudent.canGraduate());
    }
    
    @Test
    public void testSettersAndGetters() {
        // Test setter methods
        student.setName("Updated Name");
        student.setAge(21);
        student.setGpa(3.5);
        student.setActive(false);
        
        assertEquals("Name should be updated", "Updated Name", student.getName());
        assertEquals("Age should be updated", 21, student.getAge());
        assertEquals("GPA should be updated", 3.5, student.getGpa(), 0.01);
        assertFalse("Student should be inactive", student.isActive());
    }
    
    @Test
    public void testStudentEquality() {
        // Test equals method
        Student sameStudent = new Student("John Doe", 20, 3.0);
        Student differentStudent = new Student("Jane Doe", 20, 3.0);
        
        assertEquals("Students with same data should be equal", student, sameStudent);
        assertNotEquals("Students with different names should not be equal", student, differentStudent);
        
        // Test reference equality
        assertSame("Student should be same as itself", student, student);
        assertNotSame("Different student objects should not be same reference", student, sameStudent);
    }
    
    @Test
    public void testToString() {
        String expectedString = "Student{name='John Doe', age=20, gpa=3.00, active=true}";
        assertEquals("toString should return formatted string", expectedString, student.toString());
        
        // Test that toString is not null
        assertNotNull("toString should not return null", student.toString());
        assertTrue("toString should contain student name", student.toString().contains("John Doe"));
    }
    
    @Test
    public void testBoundaryConditions() {
        // Test GPA boundaries for grade levels
        Student boundaryStudent = new Student("Test Student", 20, 3.5);
        assertEquals("GPA of exactly 3.5 should be Honor Roll", "Honor Roll", boundaryStudent.getGradeLevel());
        
        boundaryStudent.setGpa(2.0);
        assertEquals("GPA of exactly 2.0 should be Good Standing", "Good Standing", boundaryStudent.getGradeLevel());
        
        boundaryStudent.setGpa(1.9);
        assertEquals("GPA below 2.0 should be Probation", "Probation", boundaryStudent.getGradeLevel());
        
        // Test age boundary for graduation
        Student youngStudent = new Student("Young Student", 17, 3.0);
        assertFalse("Student under 18 should not be able to graduate", youngStudent.canGraduate());
        
        youngStudent.setAge(18);
        assertTrue("Student exactly 18 should be able to graduate", youngStudent.canGraduate());
    }
    
    @Test
    public void testNullHandling() {
        // Test with null name (this might throw exception depending on implementation)
        try {
            Student nullNameStudent = new Student(null, 20, 3.0);
            assertNull("Student with null name should have null name", nullNameStudent.getName());
        } catch (Exception e) {
            // If constructor throws exception for null name, that's also valid
            assertNotNull("Exception should be thrown for null name", e);
        }
    }
}
