package net.Myproj.employee_mgmt_sys.mapper;

import net.Myproj.employee_mgmt_sys.dto.EmployeeDto;
import net.Myproj.employee_mgmt_sys.entity.Employee;

public class EmployeeMapper
{
    public static EmployeeDto mapToEmployeeDto(Employee e){
        return new EmployeeDto(e.getId(),e.getName(), e.getSalary(), e.getAge(), e.getGender());
    }

    public static Employee mapToEmployee(EmployeeDto dto){
     return new Employee(dto.getId(),
        dto.getName(),
        dto.getSalary(),
        dto.getAge(),
        dto.getGender());
    }
}


/*
SEPARATION OF CONCERNS
#1 Employee → is for database only.
# 2EmployeeDto → is for the API/Client layer.
It represents the data you send to the frontend or receive from it.

You may want to:

Use different field names (firstname → name)

Hide certain fields

Format data (e.g. Date → formatted string)

Add extra info (e.g. fullName, isActive, etc.)

#3If you expose your entity directly:

Any change in your DB breaks your API.

You might accidentally expose sensitive fields (like password, internalId, etc.).

Let’s say tomorrow your boss says:

“Don’t expose age anymore in the API, but keep it in the database.”

If you used DTO:

java
Copy
Edit
public class EmployeeDto {
   private Long id;
   private String name;
   private long Salary;
   private String gender; // ❌ Removed age!
}
You just adjust the EmployeeMapper, and your DB stays the same.
If you used Employee directly in the controller, now you’d have to:

Annotate/hide fields

Create views manually

Deal with JPA errors

Test all places Employee is used

Why not just use constructor or setter?
Because:

Mapper centralizes the logic: so if field names/structures change, update it in one place.

Helps with complex conversions: like nested objects, enum translations, formatting, etc.

Keeps your services/controllers clean and focused.

👇 In Your Code
Employee (Entity)
Used only for:

Storing data in DB

Fetching data from DB

EmployeeDto
Used for:

Sending data in API responses

Receiving data from requests (POST/PUT)

EmployeeMapper
Converts between the two

What Is a "Wrapper"?
In programming, a wrapper is an object that encapsulates another object, often to add more information or control.

Think of it like this:

🧦 EmployeeDto is the actual data (your foot)
🧥 ResponseEntity<EmployeeDto> is the wrapping jacket — it adds extra info (status, headers, etc.) over that data.

Suppose you have:

java
Copy
Edit
@Entity
public class Employee {
  private Long id;
  private String firstname;
  private String salary;
  private String sensitiveInternalNote;
  // getters/setters
}
Your API should never expose sensitiveInternalNote.

If you use only Employee with different constructors/getters/setters, how do you exclude this field safely?

Using EmployeeDto:

java
Copy
Edit
public class EmployeeDto {
  private Long id;
  private String firstname;
  private String salary;
  // no sensitive note here!
}

LOOSE COUPLING
API layer(DTOs) independent from DB layer

*/
