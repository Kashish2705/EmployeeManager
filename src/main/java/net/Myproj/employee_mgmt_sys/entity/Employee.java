package net.Myproj.employee_mgmt_sys.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity /*What does @Entity mean?
It marks a class as a JPA entity, meaning this class is mapped to a database table.

When you annotate a class with @Entity, JPA knows it should persist instances of this class in the database.*/
@Table(name="ems")
/*Allows you to customize how the table is mapped:

Specify a different table name

Add unique constraints or indexes*/



public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FN",nullable = false)
    private String name;

    /*These fields are automatically mapped to columns named salary, age, and gender respectively (case-sensitive depending on DB).*/
    private Long salary;
    private int age;
    private String gender;


    public Employee(Long id, String name, Long salary ,int age , String gender) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public Employee() {
        // JPA needs this
        //JPA uses reflection to create objects from the database.
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
/*public class Employee {
    private Long id;
    private String name;
    private long Salary;
    private int age;
    private String gender;

    public Employee() {}

    public Employee(Long id, String name, long Salary, int age, String gender) {
        this.id = id;
        this.name = name;
        this.Salary = Salary;
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters for all fields
}
*/
/* Constructors vs Getters/Setters serve different purposes
Constructor: Used to create and initialize an object once at the time of instantiation.

Getters/Setters: Used to read or modify the object's properties after it's created.

*/