package webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import backing.LeasingCarBeanEJB;
import dto.Cardto;
import dto.Customercardto;
import dto.Customerdto;
import entities.Car;
import entities.Customer;
import entities.Customercar;

@RequestScoped
@Path("/leasingcar")
public class LeasingCarRSService {

	@EJB private LeasingCarBeanEJB leasingCarEJB;
	
	
	//Car
	
	@GET
	@Path("/carList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cardto> getCars(){
		List<Cardto> result = new ArrayList<Cardto>();
		
		for(Car car : leasingCarEJB.getCarList()) {
			Cardto cardto = new Cardto(car);
			result.add(cardto);
		}
		
		return result;
	}
	
	@GET
	@Path("/availableOrNotCarList/{isleased}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cardto> getAvailableOrLeasedCars(@PathParam("isleased") boolean isleased){
		List<Cardto> result = new ArrayList<Cardto>();
		
		for(Car car : leasingCarEJB.getAvailableOrLeasedCarList(isleased)) {
			Cardto cardto = new Cardto(car);
			result.add(cardto);
		}
		
		return result;
	}
	
	@DELETE
	@Path("/deleteCar/{licensenumber}")
	public void deleteCar(@PathParam("licensenumber") String licensenumber) {
		leasingCarEJB.deleteCar(licensenumber);
	}
	
	@POST
	@Path("/newCar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cardto postCar(Cardto car) {
		Car ent = new Car(car);
		ent = leasingCarEJB.mergeCar(ent); // Merge returnerar en ny kopia av entityn med nya värden (t.ex. autoincrement IDn)
		return new Cardto(ent);
	}
	
	
	//Customer
	
	@GET
	@Path("/customerList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customerdto> getCustomers(){
		List<Customerdto> result = new ArrayList<Customerdto>();
		
		for(Customer customer : leasingCarEJB.getCustomerList()) {
			Customerdto customerdto = new Customerdto(customer);
			result.add(customerdto);
		}
		
		return result;
	}
	
	@DELETE
	@Path("/deleteCustomer/{id}")
	public void deleteCustomer(@PathParam("id") int id) {
		leasingCarEJB.deleteCustomer(id);
	}
	
	@POST
	@Path("/newCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customerdto postCustomer(Customerdto customer) {
		Customer ent = new Customer(customer);
		ent = leasingCarEJB.mergeCustomer(ent);
		return new Customerdto(ent);
	}
	
	//Customercar LEASE
	
	@GET
	@Path("/customerCars/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cardto> getCarsForCustomer(@PathParam("id")int id){
		List<Cardto> result = new ArrayList<Cardto>();
		
		for(Car car : leasingCarEJB.getCarsForCustomer(id)) {
			Cardto cardto = new Cardto(car);
			result.add(cardto);
		}
		
		return result;
	}
	
	@POST
	@Path("/newLease")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customercardto postCustomercar(Customercardto customercar) {
		Customercar ent = new Customercar(customercar);
		ent = leasingCarEJB.leaseCar(ent);
		return new Customercardto(ent);
	}
	
	@DELETE
	@Path("/deleteLease/{ccid}")
	public void deleteCustomercar(@PathParam("ccid") int ccid) {
		leasingCarEJB.deleteCustomercar(ccid);	
	}
}
