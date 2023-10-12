package services;

import models.Bills;

import java.time.LocalDateTime;
import java.util.List;

public interface BillsService {
    String createBill(Bills bill);
    Bills updateBill(Long id, Long id_employee, double total, LocalDateTime time);
    Long deleteBill(Long id);
    Bills findBillById(Long id);
    List<Bills> showAllBills();
}
