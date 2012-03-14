package com.evolutionnext.role;

import com.evolutionnext.user.User;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Entity(name = "Role")
@Table(name = "datacell_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role implements Serializable {
    private String name;
    private String notes;
    private Long id;
    private Calendar createdDate;
    private Calendar updatedDate;
    private List<User> users;

    public Role(String name) {
        this();
        this.name = name;
    }

    public Role() {
        this.users = new ArrayList<User>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Lob
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Role)) return false;
        Role role = (Role) object;
        return new EqualsBuilder()
                .append(name, role.name)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .toHashCode();
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, mappedBy = "roles")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}

