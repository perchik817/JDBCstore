package models;

public class ProdBill {
    private Long id;
    private Bills bill;
    private Product product;

    @Override
    public String toString() {
        return "ProdBill{" +
                "id=" + id +
                ", bill=" + bill +
                ", product=" + product +
                '}';
    }

    public ProdBill(Long id, Bills bill, Product product) {
        this.id = id;
        this.bill = bill;
        this.product = product;
    }
    public ProdBill(){}

    public Bills getBill() {
        return bill;
    }

    public void setBill(Bills bill) {
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
