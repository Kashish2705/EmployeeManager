package net.Myproj.employee_mgmt_sys.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Setter
public class EmployeeDto {
    private Long id;
    private String name;
    private Long salary;
    private int age;
    private String gender;


    public EmployeeDto( Long id, String name, Long salary ,int age , String gender) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.salary = salary;
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
