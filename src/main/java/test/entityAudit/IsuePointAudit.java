package test.entityAudit;

import test.entity.IsuePoint;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ISUEPOINT_AUDIT", schema = "public")

public class IsuePointAudit {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer auditId;

    @Column(name = "operation")
    private String operation;

    @Column(name = "stamp")
    private Date stamp;

    @Column(name = "userid")
    private String userid;

    @Column(name = "read")
    private boolean isRead;

    @Column(name = "ISUEPOINT_ID", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 100)
    private String isuePointName;

    @Column(name = "ADDRESS", length = 255)
    private String address;

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

    public IsuePoint getIsuePoint(){
        IsuePoint isuePoint = new IsuePoint();
        isuePoint.setId(id);
        isuePoint.setIsuePointName(isuePointName);
        isuePoint.setAddress(address);
        return isuePoint;
    }
}
