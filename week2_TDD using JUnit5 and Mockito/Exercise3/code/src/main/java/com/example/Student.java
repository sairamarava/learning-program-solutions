package com.example;

/**
 * A simple Student class to demonstrate assertions with real objects
 */
public class Student {
    private String name;
    private int age;
    private double gpa;
    private boolean isActive;
    
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.isActive = true;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    // Business methods
    public String getGradeLevel() {
        if (gpa >= 3.5) return "Honor Roll";
        else if (gpa >= 2.0) return "Good Standing";
        else return "Probation";
    }
    
    public boolean canGraduate() {
        return age >= 18 && gpa >= 2.0 && isActive;
    }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, gpa=%.2f, active=%s}", 
                           name, age, gpa, isActive);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Student student = (Student) obj;
        return age == student.age &&
               Double.compare(student.gpa, gpa) == 0 &&
               isActive == student.isActive &&
               name.equals(student.name);
    }
}
