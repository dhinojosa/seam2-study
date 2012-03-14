package com.evolutionnext.hotel;


import com.evolutionnext.hotel.Hotel;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.core.Events;

import javax.persistence.EntityManager;

import static com.google.common.base.Preconditions.checkState;

@Name("loadedHotelFactory")
@Scope(ScopeType.CONVERSATION)
public class LoadedHotelFactory {
   private EntityManager entityManager;
   private Long id;
   private Events events;

   @In
   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @RequestParameter(value = "hotel.id")
   public void setId(Long id) {
      this.id = id;
   }

   @Factory(value = "loadedHotel", scope = ScopeType.CONVERSATION)
   public Hotel create() {
      checkState(id != null, "Id is not set");
      events.raiseEvent("com.evolutionnext.preLoadHotel", id);
      Hotel hotel = entityManager.find(Hotel.class, id);
      events.raiseEvent("com.evolutionnext.postLoadHotel", hotel);
      return hotel;
   }

   @In
   public void setEvents(Events events) {
      this.events = events;
   }
}
