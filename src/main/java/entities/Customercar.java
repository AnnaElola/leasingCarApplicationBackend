package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dto.Customercardto;

@Entity
public class Customercar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int ccid;
	
	@Column(nullable = false)
	private int customerid;
	
	@Column(nullable = false)
	private String licensenumber;

	public Customercar() {}
	
	public Customercar(Customercardto ccdto) {
		this.ccid = ccdto.getCcid();
		this.customerid = ccdto.getCustomerid();
		this.licensenumber = ccdto.getLicensenumber();
	}
	
	public Customercar(int ccid, int customerid, String licensenumber) {
		this.ccid = ccid;
		this.customerid = customerid;
		this.licensenumber = licensenumber;
	}
	
	public Customercar(int customerid, String licensenumber) {
		this.customerid = customerid;
		this.licensenumber = licensenumber;
	}

	public int getCcid() {
		return ccid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getLicensenumber() {
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}
	
	
	
	
}
