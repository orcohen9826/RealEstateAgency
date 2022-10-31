package contracts;

import java.util.ArrayList;

import model.Apartment;
import model.Client;

public interface iOfficeManager {
	void init();
	boolean addApartment(Apartment apartment);
	Apartment getApartmentById(long id);
	ArrayList<Apartment> getApartmentList();
	ArrayList<Apartment> getApartmentsByType(String ApartmentType);
	Apartment mostExpensive(String ApartmentType, int period);
	ArrayList<Apartment> allCommission();
	boolean saveProgToBin();
	String prepareApartmentsToShow(ArrayList<Apartment> apartments);
	String prepareClientsToShow(ArrayList<Client> clients);
	String prepareApartmentWithClientsToShow(Apartment apartment);
}
