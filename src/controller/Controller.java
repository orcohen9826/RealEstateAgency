package controller;

import java.util.ArrayList;
import java.util.Arrays;

import contracts.*;
import enums.ApartmentType;
import model.*;

public class Controller {
	private iOfficeManager officeManager;
	private iViewManager viewManager;
	private static final String F_NAME = "OfficeDataBase";
	
	public Controller() {
		officeManager = new OfficeManager(F_NAME);
		viewManager = new ViewManager(this);
	}
	
	public void start() {
		officeManager.init();
		viewManager.showMainScreen();
	}
	
	// addApartment
	public void addApartment(ApartmentType type, String addres, double area, double numOfRooms, int rank , double price, int rentPeriod) {
		boolean res = false;
		String errMsg = "apartment already exists";
		try {
			Apartment apartment;
			switch (type) {
			case Abnb: {
				apartment = new Abnb(addres, area, numOfRooms, rank, rentPeriod, price);
				break;
			}
			case  LongTermRent: {
				apartment = new LongTermRent(addres, area, numOfRooms, rank, rentPeriod, price);
				break;
			}
			case  ForSell: {
				apartment = new ForSell(addres, area, numOfRooms, rank, price);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
			}
		
			res = officeManager.addApartment(apartment);
		}catch (Exception e) {
			errMsg = "An error occured while trying to add your apartment: ";		
			errMsg += e.toString();
		}
		
		if(res) {
			viewManager.showApartmentAddedSuccessfully();
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void addClient(long id,String name, String phoneNumber){
		Client client;
        Apartment apartment = officeManager.getApartmentById(id);
        String errMsg = "Apartment cannot be found";
        boolean isSuccess = false;
        if(apartment != null){
    		try {        			
    			client = new Client(name, phoneNumber);
    			apartment.addClient(client);	
    			isSuccess = true;
			} catch (IllegalArgumentException e) {
				errMsg = "An error occured while trying to add client: ";	
				errMsg += e.toString();				
			}catch (Exception e) {
				errMsg = e.toString();				
			}
        }  
        
        if(isSuccess) {
			viewManager.showClientAddedSuccessfully();
		}else {
			viewManager.popUpError(errMsg);
		}
    }
	
	public void displayAllApartment() {
		ArrayList<Apartment> apartmentList = officeManager.getApartmentList();
		String errMsg = "no apartments to show";
		String apartmentListToShow = "";
        boolean isSuccess = false;
        
		if(apartmentList != null) {
			if(!apartmentList.isEmpty()) {
				apartmentListToShow = officeManager.prepareApartmentsToShow(apartmentList);
				isSuccess = true;
			}
		}
		
		if(isSuccess) {
			viewManager.showApartments(apartmentListToShow, "All Apartments");
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void showApartmentsOfSpecificType(ApartmentType type) {
		String errMsg = "no apartments to show";
		String apartmentListToShow = "";
		boolean isSuccess = false;
		ArrayList<Apartment> apartmentsByType = null;
		apartmentsByType = officeManager.getApartmentsByType(type.toString());
        
		if(apartmentsByType != null) {
			apartmentListToShow = officeManager.prepareApartmentsToShow(apartmentsByType);
			isSuccess = true;
		}
		
		if(isSuccess) {
			viewManager.showApartments(apartmentListToShow, "Apartments of type");
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void showPriceForApartment(long id) {
		String errMsg = "apartment for rent cannot be found";
		String apartmentToShow = "";
		double price = 0;
		boolean isSuccess = false;
		Apartment apartment = officeManager.getApartmentById(id);
        
        if(apartment != null && apartment instanceof ForRent){ // only apartments for rent
        	apartmentToShow = apartment.toString();
        	price = ((ForRent)apartment).totaRentalPrice();
            isSuccess = true;
        }
        
        if(isSuccess) {
			viewManager.showApartmentAndPrice(apartmentToShow, price);
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void theMostExpensive(ApartmentType type, int period) {
		String errMsg = "most expensive apartement for this type cannot be found";
		String apartmentToShow = "";
		boolean isSuccess = false; 
		Apartment apartment = officeManager.mostExpensive(type.name(), period);
		
		if(apartment != null && apartment instanceof ForRent) {
			apartmentToShow = apartment.toString();	
            isSuccess = true;
		}
		
		if(isSuccess) {
			viewManager.showMostExpensive(apartmentToShow);
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void printAllClientForApartment(long id) {
		String errMsg = "apartment cannot be found";
		String clientsToShow = "";
		boolean isSuccess = false; 
		Apartment apartment = officeManager.getApartmentById(id);
		
		if(apartment != null) {
			ArrayList<Client> clients = apartment.getClientList();
			if(clients != null) {
				clientsToShow = officeManager.prepareClientsToShow(clients); 
				isSuccess  =true;
			}else {
				errMsg = "no clients to show";
			}
		}
		
		if(isSuccess) {
			viewManager.showClients(clientsToShow);
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void printSortedClientList(long id) {
		String errMsg = "apartment cannot be found";
		String clientsToShow = "";
		boolean isSuccess = false; 
		Apartment apartment = officeManager.getApartmentById(id);
		
		if(apartment != null) {
			Client[] sortedArr = apartment.sortedClientList();
			if(sortedArr != null) {
				clientsToShow = officeManager.prepareClientsToShow(new ArrayList<Client>(Arrays.asList(sortedArr)));
				isSuccess = true;
			}else {
				errMsg = "no clients to show";
			}
		}
		
		if(isSuccess) {
			viewManager.showClients(clientsToShow);
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void printCommissions() {
		String apartmentsToShow = officeManager.prepareApartmentsToShow(officeManager.allCommission());
		viewManager.showApartments(apartmentsToShow, "Apartments with commission");
	}
	
	public void copyApartment(long id) throws CloneNotSupportedException, Exception {
		Apartment originalApt = officeManager.getApartmentById(id);
		String errMsg = "apartment cannot be found";
		String apartmemtToShow = "";
		boolean isSuccess = false; 

		if(originalApt != null) {
			Apartment apt = originalApt.clone();
			if(apt != null) {
				apartmemtToShow = officeManager.prepareApartmentWithClientsToShow(apt);
				isSuccess = true;
			}else {
				errMsg = "Failed to copt apartment";
			}
		}
		if(isSuccess) {
			viewManager.showApartments(apartmemtToShow, "Copied Apartment");
		}else {
			viewManager.popUpError(errMsg);
		}
	}
	
	public void exit(boolean isNeedToSave) {
		boolean saveWork = isNeedToSave;
		String errMsg = "apartment cannot be found";
		boolean isSuccess = false; 
		boolean isSaved = false;
		try {
			if(saveWork) {
				isSaved = officeManager.saveProgToBin();				
			}	
			isSuccess = true;
		} catch (Exception e) {
			errMsg = "File could not be saved: " + e.toString();
		}
		
		if(isSuccess) {
			viewManager.showExit(isSaved);				
		}else {
			viewManager.popUpError(errMsg);
		}
	}
}
