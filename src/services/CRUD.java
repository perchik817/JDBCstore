package services;

import services.DBHelper;
import services.impl.DBHelperImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class CRUD {
    DBHelper dbHelper = new DBHelperImpl();
    Long deleteBill(Long id, String tb){
        PreparedStatement ps = dbHelper.getStatement("DELETE FROM " + tb + " WHERE id = ?");
        try{
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return id;
        }catch (SQLException e){
            throw new RuntimeException("Error while deleting object from " + tb, e);
        }
    }
    <T> T findById(Long id, Function<ResultSet, T> resultSetTFunction, String tb){
        String query = "SELECT * FROM " + tb + " WHERE id = ?";
        try (PreparedStatement ps = dbHelper.getStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetTFunction.apply(resultSet);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error processing ResultSet", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating PreparedStatement", e);
        }
        return null;
    }
//    List<T> showAllBills(<T> T,){
//        List<T> T = new ArrayList<>();
//        PreparedStatement ps = dbHelper.getStatement("select * from tb_bill");
//        try {
//            ResultSet resultSet=ps.executeQuery();
//            while (resultSet.next()){
//                Bills result = new Bills();
//                result.setId((long) resultSet.getInt(1));
//                result.setId_employee((long) resultSet.getInt(2));
//                result.setTotal(resultSet.getInt(3));
//                String timestampStr = resultSet.getString("time");
//                if (timestampStr != null) {
//                    LocalDateTime localDateTime = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                    result.setTime(localDateTime);
//                }
//                billsList.add(result);
//            }
//            return billsList;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
