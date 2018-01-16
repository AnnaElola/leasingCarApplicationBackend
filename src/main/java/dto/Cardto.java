package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entities.Car;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cardto {

	@XmlElement
	private String licensenumber;
	
	@XmlElement
	private String brand;
	
	@XmlElement
	private String model;
	
	@XmlElement
	private String color;
	
	@XmlElement
	private int year;
	
	@XmlElement
	private boolean isleased;
	
	public Cardto() {}
	
	public Cardto(String licensenumber, String brand, String model, String color, int year, boolean isleased) {
		this.licensenumber = licensenumber;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		this.isleased = isleased;
	}
	
	public Cardto(Car car) {
		this.licensenumber = car.getLicensenumber();
		this.brand = car.getBrand();
		this.model = car.getModel();
		this.color = car.getColor();
		this.year = car.getYear();
		this.isleased = car.getIsleased();
	}

	public String getLicensenumber() {
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Boolean getIsleased() {
		return isleased;
	}

	public void setIsleased(Boolean isleased) {
		this.isleased = isleased;
	}
	
	
}
