package backing;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Car;
import entities.Customer;
import entities.Customercar;

@Stateless
public class LeasingCarBeanEJB {

	@PersistenceContext(unitName="leasingcar")
	private EntityManager em;
	
	private List<Car> carList;
	private List<Customer> customerList;
	
	
	//Car-metoder
	
	public List<Car> getCarList(){
		if(carList == null) {
			TypedQuery<Car> q = em.createQuery("SELECT c FROM Car AS c", Car.class); //FROM Car är från entityklassen
			carList = q.getResultList();
		}
		
		return carList;
	}
	
	public List<Car> getAvailableOrLeasedCarList(boolean isleased){
		List<Car> maybeAvailableCarList = new ArrayList<Car>();
		
		TypedQuery<Car> q = em.createQuery("SELECT c FROM Car AS c WHERE c.isleased = :isleased", Car.class);
		q.setParameter("isleased", isleased);
		maybeAvailableCarList = q.getResultList();
		
		return maybeAvailableCarList;
	}

	public Car findByLicensenumber(String licensenumber) {
		TypedQuery<Car> q = em.createQuery("SELECT c FROM Car AS c WHERE c.licensenumber = :licensenumber", Car.class);
		q.setParameter("licensenumber", licensenumber);
		return q.getSingleResult();
	}

	public Car mergeCar(Car car) {
		return em.merge(car);
	}
	
	public void deleteCar(String licensenumber) {
		Car ent = findByLicensenumber(licensenumber);
		em.remove(ent);
	}
	
	
	//Customer-metoder
	
	public List<Customer> getCustomerList(){
		if(customerList == null) {
			TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer AS c", Customer.class); //FROM Customer är från entityklassen
			customerList = q.getResultList();
		}
		
		return customerList;
	}
	
	public Customer findCustomerById(int id) {
		TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer AS c WHERE c.customerid = :id", Customer.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}
	
	public Customer mergeCustomer(Customer customer) {
		return em.merge(customer);
	}
	
	public void deleteCustomer(int id) {
		Customer ent = findCustomerById(id);
		em.remove(ent);
	}
	
	//Customer-car
	
	public List<Car> getCarsForCustomer(int customerid){
		//Hämta alla bilar en kund leasar
		/*
		TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c, Customercar cc WHERE cc.customerid = :customerid AND c.licensenumber = cc.licensenumber", Car.class); 
		q.setParameter("customerid", customerid);
		carList = q.getResultList();
		*/
		Customer customer = findCustomerById(customerid);
		return customer.getCars();
	}
	
	public Customercar leaseCar(Customercar customercar) {
		Car car = findByLicensenumber(customercar.getLicensenumber());
		car.setIsleased(true);
		
		em.merge(car);
		
		return em.merge(customercar);
	}

	public void deleteCustomercar(int ccid) {
		Customercar ent = findCustomercarById(ccid);
		
		Car car = findByLicensenumber(ent.getLicensenumber());
		car.setIsleased(false);
		
		em.merge(car);
		em.remove(ent);
	}
	
	private Customercar findCustomercarById(int ccid) {
		TypedQuery<Customercar> q = em.createQuery("SELECT c FROM Customercar AS c WHERE c.ccid = :ccid", Customercar.class);
		q.setParameter("ccid", ccid);
		return q.getSingleResult();
	}
}
