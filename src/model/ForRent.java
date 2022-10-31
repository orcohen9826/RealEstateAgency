package model;


public abstract class ForRent extends Apartment {
	private int rentPeriod;
	private double rentPrice;
	
	public ForRent(String addres, double area, double numOfRooms, int rank, int rentPeriod , double rentPrice) throws IllegalArgumentException, Exception{
		super(addres, area, numOfRooms, rank);
		setRentPeriod(rentPeriod);
		setRentPrice(rentPrice);
	}
	
	public int getRentPeriod() {
		return rentPeriod;
	}


	public void setRentPeriod(int rentPeriod) throws IllegalArgumentException{
		if (rentPeriod <= 0) {
			throw new IllegalArgumentException("invalid Period");
		}
		this.rentPeriod = rentPeriod;
	}


	public double getRentPrice() {
		return rentPrice;
	}


	public void setRentPrice(double rentPrice) throws IllegalArgumentException{
		if(rentPrice <= 0 ) {
			throw new IllegalArgumentException("invalid price");
		}
		this.rentPrice = rentPrice;
	}
	
	//17
	public double totaRentalPrice() {
		return (double)(rentPeriod*rentPrice);
	}
	
}