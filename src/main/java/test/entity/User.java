package test.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER", schema = "public")

public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "USER_NAME", length = 255)
    private String username;

    @Column(name = "USER_PHONE", length = 20)
    private String userphone;


   /* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orderUs;

    public Set<Order> getOrderUs() {
        return orderUs;
    }

    public void setOrderUs(Set<Order> orderUs) {
        this.orderUs = orderUs;
    }
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
}
