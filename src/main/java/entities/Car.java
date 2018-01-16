package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import dto.Cardto;

@Entity
public class Car {

	@Id
	@Column(nullable = false, unique = true)
	private String licensenumber;
	
	@Column
	private String brand;
	
	@Column
	private String model;
	
	@Column
	private String color;
	
	@Column
	private int year;
	
	@Column(nullable = false)
	private boolean isleased;

	public Car() {}
	
	public Car(String licensenumber, String brand, String model, String color, int year) {
		this.licensenumber = licensenumber;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		isleased = false;
	}
	
	public Car(Cardto car) {
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
