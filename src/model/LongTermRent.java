package model;

import contracts.iCommissionable;

public class LongTermRent extends ForRent implements iCommissionable {
	private static final long serialVersionUID = 271019091447646689L;
	private static double commission = 4000;
	
	public LongTermRent(String addres, double area, double numOfRooms, int rank , int rentPeriod , double rentPrice)throws IllegalArgumentException, Exception {
		super(addres, area, numOfRooms, rank, rentPeriod, rentPrice);
	}

	@Override
	public double totaRentalPrice() {
		return super.totaRentalPrice() + commission();
	}
	
	@Override
	public double commission() {
		return commission;
	}
	
	@Override
	public String toString() {
		String str = "Type: LongTermRent\n"
				.concat(super.toString())
				.concat(String.format("RentPrice: %f \n", getRentPrice()))
				.concat(String.format("RentPeriod: %d Months\n", getRentPeriod()))
				.concat(String.format("Commission: %f \n", commission() ));
		return str;		
	}
}

