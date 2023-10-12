import models.Bills;
import services.*;
import services.impl.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StoreService storeService = new StoreServiceImpl();
        BillsService billsService = new BillsServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        ProductService productService = new ProductServiceImpl();
        ProdBillService prodBillService = new ProdBillServiceImpl();

        System.out.println(prodBillService.prodBills(1L));

        //System.out.println(employeeService.showEmpsInaStore(1L));

        //createBill
//        System.out.print("enter employee's id: ");
//        Long id_emp = scanner.nextLong();
//        System.out.print("enter total: ");
//        double total = scanner.nextDouble();
//        LocalDateTime time = LocalDateTime.now();
//        LocalDateTime customizedTime = time.plusHours(6);
//        Bills bill = new Bills(employeeService.findEmpById(id_emp), total, customizedTime);
//        System.out.println(billsService.createBill(bill));


        //findById
//        System.out.print("enter bill's id: ");
//        Long id = scanner.nextLong();
//        System.out.println(billsService.findBillById(id));


        //updateBill
        /*System.out.print("enter bill's id: ");
        Long id=scanner.nextLong();
        System.out.print("enter employee's id: ");
        Long id_emp = scanner.nextLong();
        System.out.print("enter total: ");
        double total = scanner.nextDouble();
        LocalDateTime time = LocalDateTime.now();
        System.out.println(billsService.updateBill(id, id_emp, total, time));
         */

        //showAllBills
        //System.out.println(billsService.showAllBills());

        //deleteBill
//        System.out.print("enter bill's id: ");
//        Long id = scanner.nextLong();
//        System.out.println(billsService.deleteBill(id));
    }
}