package ems.voxa_ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ems.voxa_ems.entity.EmployeesDetails;
import ems.voxa_ems.repository.EmployeeRepository;

/**
 * REST API Controller for Employee Management
 * 
 * This replaces the old Thymeleaf controller (VoxaController).
 * Instead of returning HTML views, this returns JSON data that Angular will
 * consume.
 * 
 * @RestController - Combines @Controller + @ResponseBody (automatically
 *                 converts return values to JSON)
 * @CrossOrigin - Allows Angular app (running on port 4200) to call this API
 *              (running on port 8080)
 */
@RestController
@RequestMapping("/api/employees") // Base URL: http://localhost:8080/api/employees
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository repository;

    /**
     * GET /api/employees
     * Returns all employees as JSON array
     * 
     * Example Response: [{"id":1,"name":"John","email":"john@example.com",...},
     * ...]
     */
    @GetMapping
    public ResponseEntity<List<EmployeesDetails>> getAllEmployees() {
        List<EmployeesDetails> employees = repository.findAll();
        return ResponseEntity.ok(employees); // Returns 200 OK with employee list
    }

    /**
     * GET /api/employees/{id}
     * Returns a single employee by ID
     * 
     * @PathVariable - Extracts {id} from URL
     *               Example: GET /api/employees/5 returns employee with ID 5
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeesDetails> employee = repository.findById(id);

        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get()); // 200 OK with employee data
        } else {
            // Return 404 Not Found with error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + id);
        }
    }

    /**
     * POST /api/employees
     * Creates a new employee
     * 
     * @RequestBody - Converts JSON from Angular to EmployeesDetails object
     *              Example Request Body:
     *              {"name":"John","email":"john@example.com","mobile":1234567890,...}
     */
    @PostMapping
    public ResponseEntity<EmployeesDetails> addEmployee(@RequestBody EmployeesDetails employee) {
        // Save to database
        EmployeesDetails savedEmployee = repository.save(employee);

        // Return 201 Created with the saved employee (includes generated ID)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /**
     * PUT /api/employees/{id}
     * Updates an existing employee
     * 
     * Example: PUT /api/employees/5 with JSON body updates employee with ID 5
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,
            @RequestBody EmployeesDetails employeeDetails) {
        // Check if employee exists
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + id);
        }

        // Set the ID to ensure we update the correct record
        employeeDetails.setId(id);

        // Save (update) the employee
        EmployeesDetails updatedEmployee = repository.save(employeeDetails);

        return ResponseEntity.ok(updatedEmployee); // 200 OK with updated employee
    }

    /**
     * DELETE /api/employees/{id}
     * Deletes an employee
     * 
     * Example: DELETE /api/employees/5 deletes employee with ID 5
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        // Check if employee exists
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + id);
        }

        // Delete the employee
        repository.deleteById(id);

        // Return 200 OK with success message
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
    }
}
