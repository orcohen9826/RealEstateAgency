package model;
import contracts.iCommissionable;

import java.io.Serializable;
import java.util.ArrayList;


public class Office implements Serializable {
	private ArrayList<Apartment> apartmentList;

	public Office() {
		this.apartmentList = new ArrayList<Apartment>();
	}

	public Apartment getApartmentById(long id) {
		Apartment p = null;
		for (int i = 0; i < apartmentList.size(); i++) {
			if (id == apartmentList.get(i).getId())
				p = apartmentList.get(i);
		}
		return p;
	}
	// functions 13 15 16 18

	// 13
	public boolean addApartment(Apartment apartment) {
		if (hasApartment(apartment)) {
			return false;
		} else
			apartmentList.add(apartment);
		return true;
	}

	private boolean hasApartment(Apartment apartment) {
		return apartmentList.contains(apartment);
	}

	// 15
	public ArrayList<Apartment> getApartmentList() {
		return this.apartmentList;
	}

	// 18
	public Apartment mostExpensive(String ApartmentType, int period) {
		double max = 0;
		Apartment p = null;
		if (ApartmentType != null) {
			for (Apartment apartment : apartmentList) {
				if (apartment instanceof ForRent && ApartmentType.equals(apartment.getClass().getSimpleName())) {
					if (max < (period * ((ForRent) apartment).getRentPrice())) {
						max = (period * ((ForRent) apartment).getRentPrice());
						p = apartment;
					}
				}
			}
		}
		return p;
	}

	// 16
	public ArrayList<Apartment> getApartmentsByType(String apartmentType) {
		ArrayList<Apartment> apartments = new ArrayList<>();
		if (apartmentType != null) {
			for (int i = 0; i < apartmentList.size(); i++) {
				if (apartmentType.equals(apartmentList.get(i).getClass().getSimpleName()))
					apartments.add(apartmentList.get(i));
			}
		}
		return apartments;
	}

	public ArrayList<Apartment> allCommission() {
		ArrayList<Apartment> commissionableApt = new ArrayList<>();
		for (Apartment apartment : apartmentList) {
			if(apartment instanceof iCommissionable) 
				commissionableApt.add(apartment);										
		}
		return commissionableApt;
	}
}
