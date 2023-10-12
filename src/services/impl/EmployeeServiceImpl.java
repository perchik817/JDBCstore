package services.impl;

import models.Employee;
import models.Store;
import services.EmployeeService;
import services.StoreService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    DBHelperImpl dbHelper = new DBHelperImpl();
    StoreService storeService = new StoreServiceImpl();

    @Override
    public String createEmp(Employee employee) {
        try (PreparedStatement preparedStatement = dbHelper.getStatement("insert into tb_employees(name, " +
                "price) values(?, ?)")){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setLong(2, employee.getId_store().getId());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при сохранении данных сотрудника");
        }
    }

    @Override
    public Employee updateEmp(Long id, String name, Long id_store, int age) {
        try (PreparedStatement preparedStatement=dbHelper.getStatement("update tb_employees set name = ?, age = ? " +
                "where id = ?")){
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id_store);
            preparedStatement.setInt(3, age);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при обновлении данных сотрудника");
        }
        return findEmpById(id);
    }

    @Override
    public Long deleteEmp(Long id) {
        try (PreparedStatement ps = dbHelper.getStatement("DELETE FROM tb_employees WHERE id = ?")){
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return id;
        }catch (SQLException e){
            throw new RuntimeException("Произошла ошибка при удалении данных сотрудника", e);
        }
    }

    @Override
    public List<Employee> showAllEmps() {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement ps = dbHelper.getStatement("select * from tb_employees")){
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                Employee result = new Employee();
                result.setId((long) resultSet.getInt(1));
                result.setName(resultSet.getString(2));
                result.setId_store(storeService.findStoreById(resultSet.getLong("id_store")));
                result.setAge(resultSet.getInt(4));
                employeeList.add(result);
            }
            return employeeList;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " произошла ошибка при показе списка сотрудников");
        }
    }
    @Override
    public List<Employee> showEmpsInaStore(Long id_store) {
        List<Employee> employeeInaStoreList = new ArrayList<>();
        try (PreparedStatement ps = dbHelper.getStatement(
                "SELECT e.id, e.name, e.age, s.name AS store_name " +
                "FROM tb_employees e " +
                "JOIN tb_store s ON e.id_store = s.id " +
                "WHERE e.id_store = ?")) {
            ps.setLong(1, id_store);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Employee emp = new Employee();
                Store store = new Store();
                emp.setId(resultSet.getLong("id"));
                emp.setName(resultSet.getString("name"));
                emp.setAge(resultSet.getInt("age"));
                emp.setId_store(storeService.findStoreById(id_store));
                store.setName(resultSet.getString("name"));
                employeeInaStoreList.add(emp);
            }

        }catch (SQLException e){
            throw new RuntimeException(e + " произошла ошибка выводе сотрудников из магазина");
        }
        return employeeInaStoreList;

    }

    @Override
    public Employee findEmpById(Long id) {
        Employee employee = new Employee();

        try (PreparedStatement ps = dbHelper.getStatement("select * from tb_employees where id = ?")){
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            employee.setId(resultSet.getLong("id"));

        }catch (SQLException e){
            throw new RuntimeException(e + " произошла ошибка при поиске сотрудника");
        }
        return employee;
    }
}
