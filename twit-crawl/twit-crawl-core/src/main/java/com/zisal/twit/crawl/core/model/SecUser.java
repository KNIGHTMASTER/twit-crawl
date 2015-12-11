package com.zisal.twit.crawl.core.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Ladies Man on 11/16/2015.
 */
@Entity(name = "sec_user")
@Table(name = "sec_user", schema = "security")
public class SecUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private BigInteger id;

    @Column(name = "user_name", length = 50)
    private String name;

    @Column(name = "user_password", length = 255)
    private String userPassword;

    @Column(name = "user_enable")
    private Integer userEnabled;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, unique = true)
    private SecRole secRole;

    public SecRole getSecRole() {
        return secRole;
    }

    public void setSecRole(SecRole secRole) {
        this.secRole = secRole;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(Integer userEnabled) {
        this.userEnabled = userEnabled;
    }

    @Override
    public String toString() {
        return "SecUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEnabled=" + userEnabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecUser secUser = (SecUser) o;

        if (id != null ? !id.equals(secUser.id) : secUser.id != null) return false;
        if (name != null ? !name.equals(secUser.name) : secUser.name != null) return false;
        if (userEnabled != null ? !userEnabled.equals(secUser.userEnabled) : secUser.userEnabled != null) return false;
        if (userPassword != null ? !userPassword.equals(secUser.userPassword) : secUser.userPassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userEnabled != null ? userEnabled.hashCode() : 0);
        return result;
    }
}
