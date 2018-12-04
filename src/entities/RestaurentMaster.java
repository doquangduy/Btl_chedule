package entities;
// Generated Dec 15, 2018 6:18:31 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * RestaurentMaster generated by hbm2java
 */
public class RestaurentMaster  implements java.io.Serializable {


     private Integer masterId;
     private String masterName;
     private Set restaurents = new HashSet(0);

    public RestaurentMaster() {
    }

    public RestaurentMaster(String masterName, Set restaurents) {
       this.masterName = masterName;
       this.restaurents = restaurents;
    }
   
    public Integer getMasterId() {
        return this.masterId;
    }
    
    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
    public String getMasterName() {
        return this.masterName;
    }
    
    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
    public Set getRestaurents() {
        return this.restaurents;
    }
    
    public void setRestaurents(Set restaurents) {
        this.restaurents = restaurents;
    }




}


