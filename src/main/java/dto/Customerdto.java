package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entities.Car;
import entities.Customer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customerdto {

	@XmlElement
	private int customerid;
	
	@XmlElement
	private String firstname;
	
	@XmlElement
	private String lastname;
	
	@XmlElement
	private String address;
	
	@XmlElement
	private String phonenumber;
	
	@XmlElement
	private List<Cardto> cars;
	
	public Customerdto() {}
	
	public Customerdto(String firstname, String lastname, String address, String phonenumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phonenumber = phonenumber;
	}
	
	public Customerdto(Customer customer) {
		this.customerid = customer.getCustomerid();
		this.firstname = customer.getFirstname();
		this.lastname = customer.getLastname();
		this.address = customer.getAddress();
		this.phonenumber = customer.getPhonenumber();
		
		if(customer.getCars() == null) {
			for(Car c : customer.getCars()) {
				this.cars.add(new Cardto(c));
			}
		}

	
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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

	public List<Cardto> getCars() {
		return cars;
	}

	public void setCars(List<Cardto> cars) {
		this.cars = cars;
	}
	
	
}
