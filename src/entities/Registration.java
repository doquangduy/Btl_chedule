package entities;
// Generated Dec 15, 2018 6:18:31 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Registration generated by hbm2java
 */
public class Registration  implements java.io.Serializable {


     private int registerId;
     private Shift shift;
     private User user;
     private Date day;
     private Boolean active;
     private Date checkIn;
     private Date checkOut;

    public Registration() {
    }

	
    public Registration(int registerId, Shift shift, User user) {
        this.registerId = registerId;
        this.shift = shift;
        this.user = user;
    }
    public Registration(int registerId, Shift shift, User user, Date day, Boolean active, Date checkIn, Date checkOut) {
       this.registerId = registerId;
       this.shift = shift;
       this.user = user;
       this.day = day;
       this.active = active;
       this.checkIn = checkIn;
       this.checkOut = checkOut;
    }
   
    public int getRegisterId() {
        return this.registerId;
    }
    
    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }
    public Shift getShift() {
        return this.shift;
    }
    
    public void setShift(Shift shift) {
        this.shift = shift;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDay() {
        return this.day;
    }
    
    public void setDay(Date day) {
        this.day = day;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Date getCheckIn() {
        return this.checkIn;
    }
    
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }
    public Date getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }




}


