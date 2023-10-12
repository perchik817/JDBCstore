package models;
import java.time.LocalDateTime;

public class Bills {
    private Long id;
    private Employee id_employee;
    private double total;
    private LocalDateTime time;

    @Override
    public String toString() {
        return "Bills{" +
                "id=" + id +
                ", id_employees=" + id_employee +
                ", total=" + total +
                ", time=" + time +
                '}';
    }

    public Bills(Long id, Employee id_employee, double total, LocalDateTime time) {
        this.id = id;
        this.id_employee = id_employee;
        this.total = total;
        this.time = time;
    }
    public Bills(Employee id_employee, double total, LocalDateTime time) {
        this.id_employee = id_employee;
        this.total = total;
        this.time = time;
    }
    public Bills(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getId_employee() {
        return id_employee;
    }

    public void setId_employee(Employee id_employee) {
        this.id_employee = id_employee;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
