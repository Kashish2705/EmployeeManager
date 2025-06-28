package net.Myproj.employee_mgmt_sys.service.impl;

import lombok.AllArgsConstructor;
import net.Myproj.employee_mgmt_sys.dto.EmployeeDto;
import net.Myproj.employee_mgmt_sys.entity.Employee;
import net.Myproj.employee_mgmt_sys.exception.ResourceNotFoundException;
import net.Myproj.employee_mgmt_sys.mapper.EmployeeMapper;
import net.Myproj.employee_mgmt_sys.repository.EmployeeRepository;
import net.Myproj.employee_mgmt_sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.Myproj.employee_mgmt_sys.mapper.EmployeeMapper.mapToEmployeeDto;

@Service
// @AllArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeRepository eR;
    public EmployeeServiceIMPL(EmployeeRepository eR) {
    this.eR = eR;
}   //DONE USING ANNOTATION

    public EmployeeDto createEmployee(EmployeeDto Dto) {
       Employee e= EmployeeMapper.mapToEmployee(Dto);
       Employee savedEmployee = eR.save(e);
       return mapToEmployeeDto(savedEmployee);

    }


    //Throw custom exception when employee with a given ID does not exist
    @Override
    public EmployeeDto getEmployeeById(Long id) {
       Employee e = eR.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee with id " + id + " not found"));
/*  SAME AS :
Optional<Employee> emp = eR.findById(id);
if (emp.isPresent()) {
    return emp.get();
} else {
    throw new ResourceNotFoundException("Employee with id " + id + " not found");
}
*/
        return mapToEmployeeDto(e);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> ent =eR.findAll();
        return ent.stream().map((en)-> EmployeeMapper.mapToEmployeeDto(en)).collect(Collectors.toList());
        /* SAME AS
        @Override
public List<EmployeeDto> getAllEmployees() {
    List<Employee> employees = eR.findAll();
    List<EmployeeDto> employeeDtos = new ArrayList<>();

    for (Employee e : employees) {
        employeeDtos.add(EmployeeMapper.mapToEmployeeDto(e));
    }

    return employeeDtos;
}
         */
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto updatedDto , Long empId) {
        // We need to throw exception if employee with Id does not exist
       Employee e=  eR.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee with id " + empId + " not found"));
        e.setName(updatedDto.getName()); //USED SETTER
        e.setAge(updatedDto.getAge());
        e.setSalary(updatedDto.getSalary());
        e.setGender(updatedDto.getGender());

        Employee updatedEmpObj = eR.save(e);

        return mapToEmployeeDto(updatedEmpObj);
    }

    @Override
    //if emp id doesn't exist in db then throw error
    public void deleteEmployee(Long id) {
        /*eR.deleteById(id) returns void, not an Optional, not an Employee, and not anything at all.
You're trying to call .orElseThrow(...) on a void result, which is invalid — you can only call methods like .orElseThrow() on Optional.*/
        Employee e = eR.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with id "+id+" not found"));
        eR.delete(e);
        // OR eR.seleteById(id);

        /*Method is called with some id:

deleteEmployee(7L);

The code attempts to find the employee in the database:

eR.findById(id)

    This returns an Optional<Employee>.
    If the employee exists, the Optional contains the Employee.
    If it doesn't exist, the Optional is empty.
You call .orElseThrow(...) on that Optional:

.orElseThrow(() -> new ResourceNotFoundException(...));

    If the Optional is empty, a ResourceNotFoundException is thrown immediately.
    🚫 No further lines in the method are executed.
    The method exits abruptly due to the exception.
    So eR.delete(...) or eR.deleteById(...) is never reached in this case.

    Optional<T> is a container object from Java 8 that may or may not contain a non-null value.

It is used to avoid null checks and prevent NullPointerException.
Employee e = eR.findById(id);
if (e != null) {
    // do something
} else {
    throw new Exception();
}
Optional makes this safer and cleaner:

Employee e = eR.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException(...));
*/
    }
}
