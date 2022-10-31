package model;

import contracts.iCommissionable;

public class ForSell extends Apartment implements iCommissionable {
	private double price;
	private double commissionPrecentage = 0.05;
	
	public ForSell(String addres, double area, double numOfRooms, int rank, double price)throws IllegalArgumentException, Exception{
		super(addres, area, numOfRooms, rank);
		setPrice(price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws IllegalArgumentException{
		if(price <= 0 ) {
			throw new IllegalArgumentException("invalid price");
		}
		this.price = price;
	}

	@Override
	public double commission() {
		return price * commissionPrecentage;
	}
	
	@Override
	public String toString() {
		String str = "Type: ForSell\n"
				.concat(super.toString())
				.concat(String.format("Price: %f \n", price))
				.concat(String.format("Commission: %f \n", commission()));
		return str;
	}
}

