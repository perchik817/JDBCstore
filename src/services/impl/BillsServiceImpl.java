package services.impl;

import models.Bills;
import models.Store;
import services.BillsService;
import services.DBHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BillsServiceImpl implements BillsService {
    DBHelper dbHelper = new DBHelperImpl();
    @Override
    public String saveBill(Long id_employee, double total, LocalDateTime time) {
        PreparedStatement preparedStatement = dbHelper.getStatement("insert into tb_bill(id_employees, total, time) " +
                "values(?, ?, datetime('now'))");
        try {
            preparedStatement.setLong(1, id_employee);
            preparedStatement.setDouble(2, total);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при сохранении чека");
        }
    }

    @Override
    public Bills updateBill(Long id, Long id_employee, double total, LocalDateTime time) {
        PreparedStatement preparedStatement=dbHelper.getStatement("update tb_bill set id_employees = ?, total = ?, " +
                "time = datetime('now') where id = ? ");
        try {

            preparedStatement.setLong(1, id_employee);
            preparedStatement.setDouble(2, total);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при Обновлении чека");
        }
        return findBillById(id);
    }

    @Override
    public Long deleteBill(Long id) {
        PreparedStatement ps = dbHelper.getStatement("DELETE FROM tb_bill WHERE id = ?");
        try{
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return id;
        }catch (SQLException e){
            throw new RuntimeException("Error while deleting bill", e);
        }

    }

    @Override
    public Bills findBillById(Long id) {
        Bills bill = new Bills();
        PreparedStatement ps = dbHelper.getStatement("select * from tb_bill where id = ?");
        try{
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            bill.setId(resultSet.getLong("id"));
            bill.setId_employee(resultSet.getLong("id_employees"));
            bill.setTotal(resultSet.getDouble("total"));
            String timestampStr = resultSet.getString("time");
            if (timestampStr != null) {
                LocalDateTime localDateTime = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                bill.setTime(localDateTime);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return bill;
    }

    @Override
    public List<Bills> showAllBills() {
        List<Bills> billsList = new ArrayList<>();
        PreparedStatement ps = dbHelper.getStatement("select * from tb_bill");
        try {
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                Bills result = new Bills();
                result.setId((long) resultSet.getInt(1));
                result.setId_employee((long) resultSet.getInt(2));
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
            throw new RuntimeException(e);
        }
    }
}
