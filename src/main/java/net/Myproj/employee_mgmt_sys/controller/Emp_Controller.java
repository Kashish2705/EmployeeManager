package net.Myproj.employee_mgmt_sys.controller;

import net.Myproj.employee_mgmt_sys.dto.EmployeeDto;
import net.Myproj.employee_mgmt_sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
/*
is a Spring Boot annotation used in the backend, and it means:

    ⚙️ Allow requests from any origin (domain) — CORS policy.
    CORS = Cross-Origin Resource Sharing

    Web browsers block requests from one origin (domain/port) to another unless the server explicitly allows it.

    Example:

        Frontend: http://localhost:3000

        Backend: http://localhost:8080

        This is cross-origin, and your browser won’t allow it unless permitted.
 */
@RestController
@RequestMapping("/api/employees")
public class Emp_Controller {

    @Autowired
    private EmployeeService eService;

    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto empDto){
        EmployeeDto savedEmployee = eService.createEmployee(empDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    /*The @RequestBody annotation tells Spring:
“Take the raw HTTP request body (usually JSON), and convert it into a Java object using the appropriate deserializer.”
*/

    //Build Get Employee REST API

    /*ResponseEntity is a class in Spring used to represent the entire HTTP response, including:
status code (like 200, 404, etc.)
headers (optional)
body (data you're returning)*/

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeByID(@PathVariable("id") Long empID){
        EmployeeDto E = eService.getEmployeeById(empID);
        return new ResponseEntity<>(E, HttpStatus.OK);

    }

    //Build Get All Employees REST API
    @GetMapping("getAll")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> l1 = eService.getAllEmployees();
        return new ResponseEntity<>(l1, HttpStatus.OK);
    }

    //Build Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empID,@RequestBody EmployeeDto empDto){
        /*You're missing @RequestBody on the empDto parameter in your PUT endpoint.

Spring doesn't know that the incoming JSON should be converted into the EmployeeDto object.

*/
        EmployeeDto updated= eService.updateEmployee(empDto,empID);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    //Build delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId){
        eService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}
