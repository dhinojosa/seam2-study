package com.evolutionnext.hotel;


import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;

import javax.persistence.EntityManager;

@Name("hotelServiceBean")
@Scope(ScopeType.CONVERSATION)
public class HotelServiceBean {

   private EntityManager entityManager;
   private Events events;

   @In
   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   public void persist(Hotel hotel) {
      events.raiseEvent("com.evolutionnext.preHotelCreate", hotel);
      entityManager.persist(hotel);
      events.raiseEvent("com.evolutionnext.postHotelCreate", hotel);
   }

   public void update(Hotel hotel) {
      events.raiseEvent("com.evolutionnext.preHotelCreate", hotel);
      entityManager.flush();
      events.raiseEvent("com.evolutionnext.postHotelCreate", hotel);
   }

   @In
   public void setEvents(Events events) {
      this.events = events;
   }
}
