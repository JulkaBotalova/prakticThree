package test.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ORDERPOS", schema = "public")

public class OrderPos {
    @Id
    @Column(name = "ORDERPOS_ID")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "ORDERPOS_PRICE", nullable = false)
    private Integer price;

    @Column(name = "ORDERPOS_QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "ORDERPOS_GOODNAME")
    private String goodName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn (name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn (name = "PRODUCT_ID", nullable = false)
    private Product product;

   /* public Product getProduct() {
        return product;
    }*/

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /*public Order getOrder() {
        return order;
    }*/

    public void setOrder(Order order) {
        this.order = order;
    }
}
