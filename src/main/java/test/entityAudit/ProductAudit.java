package test.entityAudit;

import test.entity.Product;
import test.entity.ProductGroup;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_AUDIT", schema = "public")

public class ProductAudit {

    @Id
    @Column(name = "ID", nullable = false)
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

    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer id;

    @Column(name = "PRODUCT_NAME", length = 255)
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Integer productPrice;

   /* @Column(name = "PRODUCTGROUP_ID", nullable = false)
    private ProductGroup productGroup;*/

    @ManyToOne(targetEntity = ProductGroup.class)
    @JoinColumn(name = "PRODUCTGROUP_ID", nullable = false)
    private ProductGroup productGroup;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Product getProduct(){
        Product product = new Product();
        product.setId(id);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductGroup(productGroup);
        return product;
    }
}