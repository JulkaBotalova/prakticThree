package test.entityAudit;

import test.entity.IsuePoint;
import test.entity.Order;
import test.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDER_AUDIT", schema = "public")

public class OrderAudit {

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

    @Column (name = "ORDER_ID", nullable = false)
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IsuePoint getIsuePoint() {
        return isuePoint;
    }

    public void setIsuePoint(IsuePoint isuePoint) {
        this.isuePoint = isuePoint;
    }

    public Order getOrder(){
        Order order = new Order();
        order.setId(id);
        order.setPhone(phone);
        order.setRemark(remark);
      //  order.setUser(user);
       // order.setIsuePoint(isuePoint);
        return order;
    }
}
