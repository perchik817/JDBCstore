package services;

import java.sql.PreparedStatement;

public interface DBHelper {

    PreparedStatement getStatement(String sql);
}