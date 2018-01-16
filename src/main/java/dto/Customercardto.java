package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entities.Customercar;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customercardto {

	@XmlElement
	private int ccid;
	
	@XmlElement
	private int customerid;
	
	@XmlElement
	private String licensenumber;
	
	public Customercardto() {}

	public Customercardto(int ccid, int customerid, String licensenumber) {
		super();
		this.ccid = ccid;
		this.customerid = customerid;
		this.licensenumber = licensenumber;
	}
	
	public Customercardto(Customercar cc) {
		this.ccid = cc.getCcid();
		this.customerid = cc.getCustomerid();
		this.licensenumber = cc.getLicensenumber();
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

	public int getCcid() {
		return ccid;
	}
	
	
}
