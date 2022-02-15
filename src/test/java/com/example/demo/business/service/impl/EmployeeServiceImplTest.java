package com.example.demo.business.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void get_all_employee_ok() {
        //mocked test data
        List<EmployeeEntity> employees = new ArrayList<>();
        EmployeeEntity employee1 = new EmployeeEntity(1,"Ashot","Karapetyan","karapetyan@mail.ru");
        EmployeeEntity employee2 = new EmployeeEntity(2,"Karen","Simonyan","simonyan@mail.ru");
        EmployeeEntity employee3 = new EmployeeEntity(3,"Narek","Martirosyan","martirosyan@mail.ru");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Employee employee4 = new Employee(1,"Ashot","Karapetyan","karapetyan@mail.ru");
        Employee employee5 = new Employee(2,"Karen","Simonyan","simonyan@mail.ru");
        Employee employee6 = new Employee(3,"Narek","Martirosyan","martirosyan@mail.ru");

        when(employeeRepo.findAll()).thenReturn(employees);
        when(modelMapper.map(employee1, Employee.class)).thenReturn(employee4);
        when(modelMapper.map(employee2, Employee.class)).thenReturn(employee5);
        when(modelMapper.map(employee3, Employee.class)).thenReturn(employee6);

        //test- call the method that u test
        List<Employee> employeeList = employeeService.getAllEmployee();

        //assertion - validate the results
        assertEquals(3, employeeList.size());
        Employee employee = employeeList.get(0);
        assertEquals(1, employee.getId());
        assertEquals("Ashot", employee.getFirstName());
        assertEquals("Karapetyan", employee.getLastName());
        assertEquals("karapetyan@mail.ru", employee.getEmail());

        verify(employeeRepo, times(1)).findAll();
    }

    @Test
    public void get_employee_by_id_ok() {
        EmployeeEntity employeeEntity = new EmployeeEntity(1,"Ashot","Karapetyan","karapetyan@mail.ru");
        Employee employee = new Employee(1,"Ashot","Karapetyan","karapetyan@mail.ru");

        when(employeeRepo.findById(1)).thenReturn(Optional.of(employeeEntity));
        when(modelMapper.map(any(), any())).thenReturn(employee, Employee.class);

        Optional<Employee> employee2 = employeeService.getEmployeeById(1);

        assertTrue(employee2.isPresent());
        assertEquals(1, employee2.get().getId());
        verify(employeeRepo, times(1)).findById(1);
    }

    @Test
    public void test_create_employee_exception() {

        NewEmployee employee = new NewEmployee("Ashot","Karapetyan","karapetyan@mail.ru");
        EmployeeEntity b = new EmployeeEntity( 1,"Ashot","Karapetyan","karapetyan@mail.ru");
        when(modelMapper.map(any(), any())).thenReturn(b);
        when(employeeRepo.save(any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(EmployeeRuntimeException.class, () ->{
            employeeService.createEmployee(employee);
        }, "Was expending to save EmployeeRuntimeException but didn't save");
    }

    @Test
    public void create_employee_ok() {

        NewEmployee newEmployee = new NewEmployee("Ashot","Karapetyan","karapetyan@mail.ru");
        EmployeeEntity employeeEntity = modelMapper.map(newEmployee, EmployeeEntity.class);

        Employee employee= new Employee(1,"Ashot","Karapetyan","karapetyan@mail.ru");

        when(employeeRepo.save(employeeEntity)).thenReturn(employeeEntity);
        when(modelMapper.map(newEmployee, EmployeeEntity.class)).thenReturn(employeeEntity);
        when(modelMapper.map(employeeEntity, Employee.class)).thenReturn(employee);

        Employee employee2 = employeeService.createEmployee(newEmployee);

        assertEquals(1, employee2.getId());
        assertEquals("Ashot", employee2.getFirstName());
        assertEquals("Karapetyan", employee2.getLastName());
        assertEquals("karapetyan@mail.ru", employee2.getEmail());

        verify(employeeRepo, times(1)).save(employeeEntity);
    }

    @Test
    public void update_employee_ok() {
        Employee employees = new Employee(1,"Ashot","Karapetyan","karapetyan@mail.ru");
        EmployeeEntity employeeEntity = modelMapper.map(employees, EmployeeEntity.class);
        Employee employees2 = new Employee(1,"Ashot","Karapetyan","karapetyan@mail.ru");

        when(employeeRepo.save(employeeEntity)).thenReturn(employeeEntity);
        when(modelMapper.map(employees, EmployeeEntity.class)).thenReturn(employeeEntity);
        when(modelMapper.map(employeeEntity, Employee.class)).thenReturn(employees2);

        Employee employee = employeeService.updateEmployee(1, employees2);

        assertEquals(1, employee.getId());
        assertEquals("Ashot", employee.getFirstName());
        assertEquals("Karapetyan", employee.getLastName());
        assertEquals("karapetyan@mail.ru", employee.getEmail());

        verify(employeeRepo, times(1)).save(employeeEntity);
    }

    @Test
    public void delete_employee_ok() {
        final Integer id  = 1;
        employeeRepo.deleteById(id);
    }
}
