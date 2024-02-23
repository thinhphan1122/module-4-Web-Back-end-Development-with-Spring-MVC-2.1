package com.module4test.exam.controller;

import com.module4test.exam.dto.EmployeeDTO;
import com.module4test.exam.service.BranchService;
import com.module4test.exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.module4test.exam.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    BranchService branchService;

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees/add")
    public String getFormNewEmployee(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        model.addAttribute("branches", branchService.findAll());
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String newEmployee(
            @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setEmployeeCode(employeeDTO.getEmployeeCode());
        employee.setBranch(branchService.findById(employeeDTO.getBranchId()).get());
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setSalary(employeeDTO.getSalary());

        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/update/{id}")
    public String updateEmployeeById(@PathVariable long id, Model model) {
        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmployeeCode(employee.getEmployeeCode());
            employeeDTO.setBranchId(employee.getBranch().getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setAge(employee.getAge());
            employeeDTO.setSalary(employee.getSalary());

            model.addAttribute("employeeDTO", employeeDTO);
            model.addAttribute("branchs", branchService.findAll());
            return "updateEmployee";
        }
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployeeById(@PathVariable long id) {
        employeeService.deleteById(id);
        return "redirect:/admin/employees";
    }

    @GetMapping("/user-list")
    public ResponseEntity<Iterable<EmployeeDTO>> getUserList() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }
}
