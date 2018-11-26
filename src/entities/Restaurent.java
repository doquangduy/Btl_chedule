package entities;
// Generated Nov 24, 2018 11:18:59 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Restaurent generated by hbm2java
 */
@Entity
@Table(name="restaurent"
    ,catalog="btl_schedule"
)
public class Restaurent  implements java.io.Serializable {


     private Integer restaurentId;
     private RestaurentMaster restaurentMaster;
     private String restaurentName;
     private String address;
     private Set<User> users = new HashSet<User>(0);

    public Restaurent() {
    }

	
    public Restaurent(RestaurentMaster restaurentMaster) {
        this.restaurentMaster = restaurentMaster;
    }
    public Restaurent(RestaurentMaster restaurentMaster, String restaurentName, String address, Set<User> users) {
       this.restaurentMaster = restaurentMaster;
       this.restaurentName = restaurentName;
       this.address = address;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="restaurent_id", unique=true, nullable=false)
    public Integer getRestaurentId() {
        return this.restaurentId;
    }
    
    public void setRestaurentId(Integer restaurentId) {
        this.restaurentId = restaurentId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="master_id", nullable=false)
    public RestaurentMaster getRestaurentMaster() {
        return this.restaurentMaster;
    }
    
    public void setRestaurentMaster(RestaurentMaster restaurentMaster) {
        this.restaurentMaster = restaurentMaster;
    }

    
    @Column(name="restaurent_name", length=45)
    public String getRestaurentName() {
        return this.restaurentName;
    }
    
    public void setRestaurentName(String restaurentName) {
        this.restaurentName = restaurentName;
    }

    
    @Column(name="address", length=45)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="restaurent")
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}

