package com.evolutionnext.hotel;

import com.evolutionnext.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NamedQueries({@NamedQuery(name = "findAllHotelCities",
        query = "SELECT h.city FROM Hotel h")})
public class Hotel {

   private Long id;
   private String city;
   private List<Room> rooms;


   public Hotel() {
      this.rooms = new ArrayList<Room>();
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   @Override
   public String toString() {
      return "Hotel[" + city + "]";
   }

   @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
   public List<Room> getRooms() {
      return rooms;
   }

   protected void setRooms(List<Room> rooms) {
      this.rooms = rooms;
   }

   public void addRoom(Room room) {
      this.rooms.add(room);
   }
}
