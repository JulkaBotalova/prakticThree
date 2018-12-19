package test.entity;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PRODUCT", schema = "public")

public class Product {

    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)

    private Integer id;

    @Column(name = "PRODUCT_NAME", length = 255)
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Integer productPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderPos> orderPoss;

    @ManyToOne(targetEntity = ProductGroup.class)
    @JoinColumn(name = "PRODUCTGROUP_ID", nullable = false)
    private ProductGroup productGroup;

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

    public Set<OrderPos> getOrderPoss() {
        return orderPoss;
    }

    public void setOrderPoss(Set<OrderPos> orderPoss) {
        this.orderPoss = orderPoss;
    }

  /*  public ProductGroup getProductGroup() {
        return productGroup;
    }*/

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
}