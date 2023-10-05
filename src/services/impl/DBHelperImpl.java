package services.impl;

import services.DBHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelperImpl implements DBHelper {
    @Override
    public PreparedStatement getStatement(String sql) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Desktop\\Kyz Saikal\\My works\\Database\\megalab\\storeDB.db");
            PreparedStatement ps = connection.prepareStatement(sql);
            return ps;
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при подключении к бд");
        }
    }
}