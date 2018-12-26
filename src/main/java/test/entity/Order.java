package test.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ORDER", schema = "public")

public class Order {

    @Id
    @Column (name = "ORDER_ID")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "ORDER_PHONE", length = 20)
    private String phone;

    @Column(name = "ORDER_REMARK", length = 1000)
    private String remark;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn (name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn (name = "ISUEPOINT_ID", nullable = false)
    private IsuePoint isuePoint;

    /*@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderPos> orderPoses;

    public Set<OrderPos> getOrderPoses() {
        return orderPoses;
    }*/

   /* public User getUser() {
        return user;
    }
*/
    public void setUser(User user) {
        this.user = user;
    }

/*    public IsuePoint getIsuePoint() {
        return isuePoint;
    }*/

    public void setIsuePoint(IsuePoint isuePoint) {
        this.isuePoint = isuePoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /*public void setOrderPoses(Set<OrderPos> orderPoses) {
        this.orderPoses = orderPoses;
    }*/
}
