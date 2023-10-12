package services;

import models.Employee;
import models.Store;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeService {
    String createEmp(Employee employee);
    Employee updateEmp(Long id, String name, Long id_store, int age);

    Long deleteEmp(Long id);
    List<Employee> showAllEmps();
    Employee findEmpById(Long id);
    List<Employee> showEmpsInaStore(Long id);
}
