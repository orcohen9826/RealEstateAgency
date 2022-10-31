package model;


public class Abnb extends ForRent {

	public Abnb(String addres, double area, double numOfRooms, int rank, int rentPeriod, double rentPrice)throws IllegalArgumentException, Exception {
		super(addres, area, numOfRooms, rank, rentPeriod, rentPrice);
	}

	@Override
	public String toString() {
		String str = "Type: Abnb\n"
				.concat(super.toString())
				.concat(String.format("RentPrice: %f \n", getRentPrice()))
				.concat(String.format("RentPeriod: %d Days\n", getRentPeriod()));
		return str;		
	}
}
