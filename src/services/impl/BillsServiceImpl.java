package services.impl;

import models.Bills;
import models.Store;
import services.BillsService;
import services.DBHelper;
import services.EmployeeService;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BillsServiceImpl implements BillsService {
    DBHelper dbHelper = new DBHelperImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    public String createBill(Bills bill) {

        try (PreparedStatement preparedStatement = dbHelper.getStatement("insert into tb_bill(id_employees, total, time) " +
                "values(?, ?, datetime('now'))")){
            preparedStatement.setLong(1, bill.getId_employee().getId());
            preparedStatement.setDouble(2, bill.getTotal());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при сохранении чека");
        }
    }

    @Override
    public Bills updateBill(Long id, Long id_employee, double total, LocalDateTime time) {
        try (PreparedStatement preparedStatement=dbHelper.getStatement("update tb_bill set id_employees = ?, total = ?, " +
                "time = datetime('now') where id = ? ")){

            preparedStatement.setLong(1, id_employee);
            preparedStatement.setDouble(2, total);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при обновлении чека");
        }
        return findBillById(id);
    }

    @Override
    public Long deleteBill(Long id) {

        try (PreparedStatement ps = dbHelper.getStatement("DELETE FROM tb_bill WHERE id = ?")){
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return id;
        }catch (SQLException e){
            throw new RuntimeException("Произошла ошибка при удалении чека", e);
        }

    }

    @Override
    public Bills findBillById(Long id) {
        Bills bill = new Bills();

        try (PreparedStatement ps = dbHelper.getStatement("select * from tb_bill where id = ?")){
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            bill.setId(resultSet.getLong("id"));
            bill.setId_employee(employeeService.findEmpById(resultSet.getLong("id_employees")));
            bill.setTotal(resultSet.getDouble("total"));
            String timestampStr = resultSet.getString("time");
            if (timestampStr != null) {
                LocalDateTime localDateTime = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                bill.setTime(localDateTime);
            }
        }catch (SQLException e){
            throw new RuntimeException(e + " такой чек не найден");
        }
        return bill;
    }

    @Override
    public List<Bills> showAllBills() {
        List<Bills> billsList = new ArrayList<>();
        try (PreparedStatement ps = dbHelper.getStatement("select * from tb_bill")){
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                Bills result = new Bills();
                result.setId((long) resultSet.getInt(1));
                result.setId_employee(employeeService.findEmpById(resultSet.getLong("id_employees")));
                result.setTotal(resultSet.getInt(3));
                String timestampStr = resultSet.getString("time");
                if (timestampStr != null) {
                    LocalDateTime localDateTime = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    result.setTime(localDateTime);
                }
                billsList.add(result);
            }
            return billsList;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " произошла ошибка при показе списка чеков");
        }
    }
}
