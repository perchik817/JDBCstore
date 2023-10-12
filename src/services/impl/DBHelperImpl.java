package services.impl;

import org.sqlite.SQLiteException;
import services.DBHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelperImpl implements DBHelper {
    @Override
    public PreparedStatement getStatement(String sql) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Desktop\\Kyz Saikal\\My works\\Database\\megalab\\storeDB.db");
            PreparedStatement ps = connection.prepareStatement(sql);
            return ps;
        }catch (SQLiteException e){
            throw new RuntimeException("Произошла ошибка при подключении драйвера -> " + e.getMessage());
        }
        catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при подключении к бд -> " + e.getMessage());
        }finally {
            if (connection == null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}