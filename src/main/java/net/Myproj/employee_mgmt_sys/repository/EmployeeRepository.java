package net.Myproj.employee_mgmt_sys.repository;

import net.Myproj.employee_mgmt_sys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
}
