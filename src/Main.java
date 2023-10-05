import services.BillsService;
import services.StoreService;
import services.impl.BillsServiceImpl;
import services.impl.StoreServiceImpl;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StoreService storeService = new StoreServiceImpl();
        BillsService billsService = new BillsServiceImpl();

        //saveBill
        /*System.out.print("enter employee's id: ");
        Long id_emp = scanner.nextLong();
        System.out.print("enter total: ");
        double total = scanner.nextDouble();
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime customizedTime = time.plusHours(6);
        System.out.println(billsService.saveBill(id_emp, total, customizedTime));
        */

        //findById
        /*System.out.print("enter bill's id: ");
        Long id=scanner.nextLong();
        System.out.println(billsService.findBillById(id));
        */

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
        System.out.print("enter bill's id: ");
        Long id = scanner.nextLong();
        System.out.println(billsService.deleteBill(id));
    }
}