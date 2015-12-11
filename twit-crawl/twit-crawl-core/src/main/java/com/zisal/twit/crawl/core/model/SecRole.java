package com.zisal.twit.crawl.core.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

/**
 * Created by Ladies Man on 11/16/2015.
 */
@Entity(name = "sec_role")
@Table(name = "sec_role", schema = "security")
public class SecRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private BigInteger id;

    @Column(name = "role_name", length = 50)
    private String roleName;

    @OneToMany(mappedBy = "secRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<SecUser> secUser;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<SecUser> getSecUser() {
        return secUser;
    }

    public void setSecUser(Collection<SecUser> secUser) {
        this.secUser = secUser;
    }

    @Override
    public String toString() {
        return "SecRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", secUser=" + secUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecRole secRole = (SecRole) o;

        if (id != null ? !id.equals(secRole.id) : secRole.id != null) return false;
        if (roleName != null ? !roleName.equals(secRole.roleName) : secRole.roleName != null) return false;
        if (secUser != null ? !secUser.equals(secRole.secUser) : secRole.secUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (secUser != null ? secUser.hashCode() : 0);
        return result;
    }
}
