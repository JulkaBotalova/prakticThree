package test.entity;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TEST")

public class TestEntity {

    @Id
    @Column(name= "TEST_ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "TEST_NAME", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "testEntity")
    private Set<Test2> test2s;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Test2> getTest2s() {
        return test2s;
    }

    public void setTest2s(Set<Test2> test2s) {
        this.test2s = test2s;
    }
}
