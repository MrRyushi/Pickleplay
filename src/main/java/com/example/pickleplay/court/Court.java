package com.example.pickleplay.court;

import com.example.pickleplay.booking.Booking;
import com.example.pickleplay.openplay.OpenPlay;
import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.List;

@Entity
public class Court {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String location;
    private int hourly_rate;
    private Integer owner_id;
    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<Booking> bookingList;
    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<OpenPlay> openPlays;

    public Court() {
    }

    public Court(Integer id, String name, String location, int hourly_rate, Integer owner_id) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hourly_rate = hourly_rate;
        this.owner_id = owner_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHourly_rate() {
        return hourly_rate;
    }

    public void setHourly_rate(int hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }
}
