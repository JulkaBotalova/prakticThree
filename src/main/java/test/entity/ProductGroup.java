package test.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PRODUCT_GROUP", schema = "public")

public class ProductGroup {

    @Id
    @Column(name = "PRODUCTGROUP_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "PRODUCTGROUP_NAME", length = 255)
    private String productGroupName;

    @Column(name = "REMARK", length = 255)
    private String remark;

    @OneToMany(mappedBy = "productGroup", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroup) {
        this.productGroupName = productGroupName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}