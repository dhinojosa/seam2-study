package com.evolutionnext.user;

import com.evolutionnext.role.Role;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Entity(name = "User")
public class User implements Serializable {
   private String firstName;
   private String middleName;
   private String lastName;
   private String password;
   @Email
   private String email;
   private String notes;
   private Long id;
   private List<Role> roles;
   private Calendar createdDate;
   private Calendar updatedDate;


   public User() {
      this.roles = new ArrayList<Role>();
   }

   public User(String email, String password) {
      this();
      this.email = email;
      this.password = password;
   }

   public User(String email, String password, String firstName, String lastName) {
      this(email, password);
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPassword() {
      return password;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getEmail() {
      return email;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public Long getId() {
      return id;
   }

   @Transient
   public void addRole(Role role) {
      this.roles.add(role);
   }

   @Transient
   public boolean containsRole(Role role) {
      return this.roles.contains(role);
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public String getMiddleName() {
      return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   @JoinTable(name = "datacell_user_roles",
           joinColumns = {@JoinColumn(name = "userID")},
           inverseJoinColumns = {@JoinColumn(name = "roleID")})
   public List<Role> getRoles() {
      return roles;
   }


   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }

   public String toString() {
      return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
              .append("email", email)
              .toString();
   }

   public boolean equals(Object object) {
      if (!(object instanceof User)) return false;
      User user = (User) object;
      return new EqualsBuilder()
              .append(email, user.email)
              .isEquals();
   }

   public int hashCode() {
      return new HashCodeBuilder(49943, 1203945)
              .append(email)
              .toHashCode();
   }

   public void setCreatedDate(Calendar createdDate) {
      this.createdDate = createdDate;
   }

   @Temporal(value = TemporalType.TIMESTAMP)
   public Calendar getCreatedDate() {
      return createdDate;
   }

   @Temporal(value = TemporalType.TIMESTAMP)
   public Calendar getUpdatedDate() {
      return updatedDate;
   }

   public void setUpdatedDate(Calendar updatedDate) {
      this.updatedDate = updatedDate;
   }
}

