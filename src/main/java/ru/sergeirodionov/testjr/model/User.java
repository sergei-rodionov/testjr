package ru.sergeirodionov.testjr.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SergeiRodionov on 03.08.2015.
 */


@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", unique = true,
            nullable = false, length = 25)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "isAdmin")
    private boolean admin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "User [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", admin=" + admin +
                ", createDate=" + createDate +
                ']' + isNew();
    }
}
