package services.impl;

import models.Employee;
import models.Product;
import models.Store;
import services.DBHelper;
import services.ProductService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    DBHelper dbHelper = new DBHelperImpl();
    @Override
    public String createProduct(String name, double price) {

        try (PreparedStatement preparedStatement = dbHelper.getStatement("insert into tb_product(name, price, " +
                "values(?, ?)")){
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при сохранении продукта");
        }
    }

    @Override
    public Product updateProduct(Long id, String name, double price) {
        try (PreparedStatement preparedStatement=dbHelper.getStatement("update tb_product set id_employees = ?, total" +
                " = ?, " +
                "time = datetime('now') where id = ? ")){

            preparedStatement.setString(1,"name");
            preparedStatement.setDouble(2, price);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Произошла ошибка при обновлении продукта");
        }
        return findProductById(id);
    }

    @Override
    public Long deleteProduct(Long id) {
        try (PreparedStatement ps = dbHelper.getStatement("DELETE FROM tb_product WHERE id = ?")){
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            return id;
        }catch (SQLException e){
            throw new RuntimeException("Произошла ошибка при удалении продукта", e);
        }
    }

    @Override
    public Product findProductById(Long id) {
        Product product = new Product();

        try (PreparedStatement ps = dbHelper.getStatement("select * from tb_product where id = ?")){
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage() + " такой продукт не найден");
        }
        return product;
    }

    @Override
    public List<Product> showAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement ps = dbHelper.getStatement("select * from tb_product")){
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                Product result = new Product();
                result.setId((long) resultSet.getInt(1));
                result.setName(resultSet.getString(2));
                result.setPrice(resultSet.getDouble(3));
                productList.add(result);
            }
            return productList;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + " произошла ошибка при показе списка продуктов");
        }
    }
}
