package backing;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Accountroles;
import entities.Car;
import entities.Customer;
import entities.Customercar;

@Stateless
public class LeasingCarBeanEJB {

	@PersistenceContext(unitName="leasingcar")
	private EntityManager em;
	
	//Car-metoder
	
	public List<Car> getCarList(){
		TypedQuery<Car> q = em.createQuery("SELECT c FROM Car AS c", Car.class); //FROM Car är från entityklassen
		return q.getResultList();
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
		
		if(ent.getIsleased() == true)
			deleteOnlyCustomercar(licensenumber);
	}
	
	
	//Customer-metoder
	
	public List<Customer> getCustomerList(){
		TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer AS c", Customer.class); //FROM Customer är från entityklassen
		return q.getResultList();
	}
	
	public Customer findCustomerById(int id) {
		TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer AS c WHERE c.customerid = :id", Customer.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}
	
	public Customer findCustomerByUsername(String username) {
		TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer AS c WHERE c.email = :username", Customer.class);
		q.setParameter("username", username);
		return q.getSingleResult();
	}
	
	public Customer mergeCustomer(Customer customer) {
		return em.merge(customer);
	}
	
	public void deleteCustomer(int id) {
		Customer ent = findCustomerById(id);
		em.remove(ent);
		Accountroles aroles = findAccountrolesByUsername(ent.getEmail());
		em.remove(aroles);
		for(Car car : ent.getCars()) {
			deleteCustomercar(car.getLicensenumber());
		}
	}
	
	//Accountroles
	
	public Accountroles findAccountrolesByUsername(String username) {
		TypedQuery<Accountroles> q = em.createQuery("SELECT ar FROM Accountroles AS ar WHERE ar.username = :username", Accountroles.class);
		q.setParameter("username", username);
		return q.getSingleResult();
	}
	
	public void addCustomerToLogin(Customer customer) {
		Accountroles aroles = new Accountroles(customer.getEmail(), "user");
		em.merge(aroles);
	}
	
	//Customer-car LEASE
	
	public List<Car> getCarsForCustomer(int customerid){
		Customer customer = findCustomerById(customerid);
		return customer.getCars();
	}
	
	public Customercar leaseCar(Customercar cc) {
		Car car = findByLicensenumber(cc.getLicensenumber());
		car.setIsleased(true);
		
		em.merge(car); //Set car isleased to true
		return em.merge(cc); //Merge customercar
	}

	public void deleteCustomercar(int ccid) {
		Customercar ent = findCustomercarById(ccid);
		
		Car car = findByLicensenumber(ent.getLicensenumber());
		car.setIsleased(false);
		
		em.merge(car);
		em.remove(ent);
	}
	
	public void deleteCustomercar(String licensenumber) {
		deleteOnlyCustomercar(licensenumber);
		
		Car car = findByLicensenumber(licensenumber);
		car.setIsleased(false);
		
		em.merge(car);

	}
	
	public void deleteOnlyCustomercar(String licensenumber) {
		Customercar ent = findCustomercarByLicensenumber(licensenumber);

		em.remove(ent);
	}
	
	private Customercar findCustomercarById(int ccid) {
		TypedQuery<Customercar> q = em.createQuery("SELECT c FROM Customercar AS c WHERE c.ccid = :ccid", Customercar.class);
		q.setParameter("ccid", ccid);
		return q.getSingleResult();
	}
	
	private Customercar findCustomercarByLicensenumber(String licensenumber) {
		TypedQuery<Customercar> q = em.createQuery("SELECT c FROM Customercar AS c WHERE c.licensenumber = :licensenumber", Customercar.class);
		q.setParameter("licensenumber", licensenumber);
		return q.getSingleResult();
	}
}
