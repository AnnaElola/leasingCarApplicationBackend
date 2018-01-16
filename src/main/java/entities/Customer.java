package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dto.Cardto;
import dto.Customerdto;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int customerid;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column
	private String address;
	
	@Column
	private String phonenumber;
	
	@OneToMany
	@JoinTable(name="Customercar",
			joinColumns = @JoinColumn(name = "customerid", 
            referencedColumnName = "customerid"), 
			inverseJoinColumns = @JoinColumn(name = "licensenumber", 
            referencedColumnName = "licensenumber"))
	private List<Car> cars;

	public Customer() {}
	
	public Customer(String firstname, String lastname, String address, String phonenumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phonenumber = phonenumber;
	}
	
	public Customer(Customerdto customer) {
		this.customerid = customer.getCustomerid();
		this.firstname = customer.getFirstname();
		this.lastname = customer.getLastname();
		this.address = customer.getAddress();
		this.phonenumber = customer.getPhonenumber();
		
		if(customer.getCars() == null) {
			for(Cardto c : customer.getCars()) {
				this.cars.add(new Car(c));
			}	
		}

	}

	public int getCustomerid() {
		return customerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
