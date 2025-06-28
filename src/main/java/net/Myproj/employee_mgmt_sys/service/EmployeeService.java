package net.Myproj.employee_mgmt_sys.service;

import net.Myproj.employee_mgmt_sys.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService
{
    EmployeeDto createEmployee(EmployeeDto Dto);

    EmployeeDto getEmployeeById(Long EmployeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(EmployeeDto Dto , Long id);

    void deleteEmployee(Long id);
}
