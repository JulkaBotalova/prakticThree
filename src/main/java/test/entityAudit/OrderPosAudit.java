package test.entityAudit;

import test.entity.Order;
import test.entity.OrderPos;
import test.entity.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERPOS_AUDIT", schema = "public")

public class OrderPosAudit {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auditId;

    @Column(name = "operation")
    private String operation;

    @Column(name = "stamp")
    private Date stamp;

    @Column(name = "userid")
    private String userid;

    @Column(name = "read")
    private boolean isRead;

    @Column(name = "ORDERPOS_ID")
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

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderPos getOrderPos(){
        OrderPos orderPos = new OrderPos();
        orderPos.setId(id);
        orderPos.setGoodName(goodName);
        orderPos.setPrice(price);
        orderPos.setQuantity(quantity);
        orderPos.setProduct(product);
        orderPos.setOrder(order);
        return orderPos;
    }
}
