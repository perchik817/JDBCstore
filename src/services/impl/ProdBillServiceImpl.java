package services.impl;

import models.ProdBill;
import models.Product;
import services.BillsService;
import services.DBHelper;
import services.ProdBillService;
import services.ProductService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdBillServiceImpl implements ProdBillService {
    DBHelper dbHelper = new DBHelperImpl();
    @Override
    public List<ProdBill> prodBills(Long id_bill) {
        List<ProdBill> prodBills = new ArrayList<>();
        BillsService billsService = new BillsServiceImpl();
        ProductService productService = new ProductServiceImpl();

        try (PreparedStatement ps = dbHelper.getStatement(
                "SELECT bp.id_bill, bp.id_prod, b.id \n" +
                        "FROM tb_bill_prod bp\n" +
                        "JOIN tb_bill b ON bp.id_bill = b.id\n" +
                        "WHERE b.id = ?")) {
            ps.setLong(1, id_bill);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ProdBill prodBill = new ProdBill();
                prodBill.setBill(billsService.findBillById(resultSet.getLong("id_bill")));
                Product product = productService.findProductById(resultSet.getLong("id_prod"));
                prodBill.setProduct(product);
                prodBills.add(prodBill);
            }

        }catch (SQLException e){
            throw new RuntimeException(e + " произошла ошибка при выводе списка продуктов в чеке");
        }
        return prodBills;
    }
}
