package test.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ISUEPOINT", schema = "public")

public class IsuePoint {

    @Id
    @Column(name = "ISUEPOINT_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "ISUEPOINT_NAME", length = 100)
    private String isuePointName;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @OneToMany(mappedBy = "isuePoint", cascade = CascadeType.ALL)
    private Set<Order> order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsuePointName() {
        return isuePointName;
    }

    public void setIsuePointName(String isuePointName) {
        this.isuePointName = isuePointName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public Set<Order> getOrder() {
        return order;
    }
}
