package services;

import models.ProdBill;

import java.util.List;

public interface ProdBillService {
    List<ProdBill> prodBills(Long id_bill);
}
