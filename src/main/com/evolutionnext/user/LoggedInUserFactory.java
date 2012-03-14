package com.evolutionnext.user;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Name("loggedInUserFactory")
@Scope(ScopeType.CONVERSATION)
public class LoggedInUserFactory {
   private String email;
   private EntityManager entityManager;


   @In(value = "#{identity.username}", create = true)
   public void setEmail(String email) {
      this.email = email;
   }

   @In
   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @Factory("loggedInUser")
   public User getLoggedInUser() {
      Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.email = :email");
      query.setParameter("email", email);
      return (User) query.getSingleResult();
   }
}
