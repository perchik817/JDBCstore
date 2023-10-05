package services.impl;

import models.Store;
import services.DBHelper;
import services.StoreService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreServiceImpl implements StoreService {
    DBHelper dbHelper = new DBHelperImpl();

    @Override
    public String saveStore(String nameStore) {
        PreparedStatement preparedStatement=dbHelper.getStatement("insert into tb_store (name) values (?)");
        try {
            preparedStatement.setString(1,nameStore);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при сохранении магазина");
        }
    }

    @Override
    public Store findById(Long id) {
        Store store = new Store();
        PreparedStatement ps= dbHelper.getStatement("select * from tb_store where id=?");
        try {
            ps.setLong(1,id.intValue());
            ResultSet resultSet=ps.executeQuery();

            while (resultSet.next()){
                store.setId((long) resultSet.getInt(1));
                store.setName(resultSet.getString(2));
            }
            return store;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Store> findAll() {
        List<Store> store=new ArrayList<>();
        PreparedStatement ps= dbHelper.getStatement("select * from tb_store ");
        try {

            ResultSet resultSet=ps.executeQuery();

            while (resultSet.next()){
                Store result=new Store();
                result.setId((long) resultSet.getInt(1));
                result.setName(resultSet.getString(2));
                store.add(result);
            }
            return store;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Store update(Long id, String name) {
        PreparedStatement preparedStatement=dbHelper.getStatement("update tb_store set name=? where id=? ");
        try {
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при Обновлении магазина");
        }

        return findById(id);
    }
}