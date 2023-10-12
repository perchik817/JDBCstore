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
    public String createStore(String nameStore) {

        try (PreparedStatement preparedStatement=dbHelper.getStatement("insert into tb_store (name) values (?)")){
            preparedStatement.setString(1,nameStore);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "Success";
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при сохранении магазина");
        }
    }

    @Override
    public Store findStoreById(Long id) {
        Store store = new Store();

        try (PreparedStatement ps= dbHelper.getStatement("select * from tb_store where id=?")){
            ps.setLong(1,id.intValue());
            ResultSet resultSet=ps.executeQuery();

            while (resultSet.next()){
                store.setId((long) resultSet.getInt(1));
                store.setName(resultSet.getString(2));
            }
            return store;

        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при поиске магазина");
        }
    }

    @Override
    public List<Store> showAllStores() {
        List<Store> store=new ArrayList<>();

        try (PreparedStatement ps= dbHelper.getStatement("select * from tb_store ")){

            ResultSet resultSet=ps.executeQuery();

            while (resultSet.next()){
                Store result=new Store();
                result.setId((long) resultSet.getInt(1));
                result.setName(resultSet.getString(2));
                store.add(result);
            }
            return store;

        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при выводе списка магазинов");
        }

    }

    @Override
    public Long deleteStore(Long id) {
        try (PreparedStatement ps = dbHelper.getStatement("DELETE FROM tb_store WHERE id = ?")){
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return id;
        }catch (SQLException e){
            throw new RuntimeException("Произошла ошибка при удалении магазина", e);
        }
    }

    @Override
    public Store updateStore(Long id, String name) {

        try (PreparedStatement preparedStatement=dbHelper.getStatement("update tb_store set name=? where id=? ")){
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при обновлении магазина");
        }

        return findStoreById(id);
    }
}