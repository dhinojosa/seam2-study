package com.evolutionnext.room;

import com.evolutionnext.hotel.Hotel;

import javax.persistence.*;

@Entity
public class Room {
   private Long id;
   private Integer number;
   private boolean isOccupied;
   private Hotel hotel;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Integer getNumber() {
      return number;
   }

   public void setNumber(Integer number) {
      this.number = number;
   }

   public boolean isOccupied() {
      return isOccupied;
   }

   public void setOccupied(boolean occupied) {
      isOccupied = occupied;
   }

   @Override
   public String toString() {
      return "Room{" +
              "id=" + id +
              ", number=" + number +
              ", isOccupied=" + isOccupied +
              ", city=" + hotel.getCity() +
              '}';
   }

   @ManyToOne()
   @JoinColumn(name = "hotel_id")
   public Hotel getHotel() {
      return hotel;
   }

   public void setHotel(Hotel hotel) {
      this.hotel = hotel;
   }
}
