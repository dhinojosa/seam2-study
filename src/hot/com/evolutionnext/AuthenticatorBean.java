package com.evolutionnext;

import com.evolutionnext.role.Role;
import com.evolutionnext.user.User;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator {

   @Logger
   private Log log;
   private Credentials credentials;
   private Identity identity;
   private EntityManager entityManager;

   public void setLog(Log log) {
      this.log = log;
   }

   @In
   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @In
   public void setIdentity(Identity identity) {
      this.identity = identity;
   }

   @In
   public void setCredentials(Credentials credentials) {
      this.credentials = credentials;
   }

   public boolean authenticate() {
      log.info("authenticating #0", credentials.getUsername());
      Query query = entityManager.createQuery("SELECT u from User u " +
              "WHERE u.email = #{credentials.username} AND u.password = #{credentials.password}");

      User user;
      try {
         user = (User) query.getSingleResult();
      } catch (NoResultException e) {
         return false;
      }

      //Get user-defined roles
      for (Role role : user.getRoles()) {
         identity.addRole(role.getName());
      }

      return true;
   }
}
