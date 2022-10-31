package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public abstract class Apartment implements Cloneable,Serializable {
	protected static long idGenerator = 1000000;
	protected String address;
	protected double area;
	protected double numOfRooms;
	protected int rank;
	protected ArrayList<Client> clientList;
	protected long id;

	public Apartment(String address, double area, double numOfRooms, int rank) throws IllegalArgumentException, Exception {
		setAddress(address);
		setArea(area);
		setNumOfRooms(numOfRooms);
		setRank(rank);
		this.clientList = new ArrayList<Client>();
		this.id = ++idGenerator;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws IllegalArgumentException{
		if(address == null || address.length() == 0) {
			throw new IllegalArgumentException("invalid address");
		}
		this.address = address;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) throws IllegalArgumentException{
		if(area <= 0) {
			throw new IllegalArgumentException("invalid area");
		}
		this.area = area;
	}

	public double getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(double numOfRooms) throws IllegalArgumentException{
		if(numOfRooms <= 0) {
			throw new IllegalArgumentException("invalid number of rooms");
		}
		this.numOfRooms = numOfRooms;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) throws IllegalArgumentException{
		if(rank > 10 || rank < 1) {
			throw new IllegalArgumentException("invalid rank");
		}
		this.rank = rank;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public long getId() {
		return id;
	}
	
	public static long getIdGenerator() {
		return idGenerator;
	}
	
	public static void setIdGenerator(long id) {
		idGenerator = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartment other = (Apartment) obj;
		return Objects.equals(address, other.address);
	}

	@Override
	public String toString() {
		String str = String.format("ID: %d \n", id)
		.concat(String.format("Addres: %s \n", address))
		.concat(String.format("Area: %f \n", area))
		.concat(String.format("Rooms: %f \n", numOfRooms))
		.concat(String.format("Rank: %d \n", rank));
		return str;
	}
	
	@Override
	public Apartment clone()throws CloneNotSupportedException{
		Apartment apt = (Apartment)super.clone();
		ArrayList<Client> clonedClients = new ArrayList<Client>();
		for(Client c : clientList) {
			clonedClients.add((Client)c.clone());
		}
		apt.clientList = clonedClients;			
		return apt;
	}
	
	// functions 14 20 19
	// 14
	public boolean addClient(Client client) {// need validations first
		if (hasClient(client)) {
			return false;
		} else
			clientList.add(client);
		return true;
	}

	public boolean hasClient(Client client) {
		return clientList.contains(client);
	}

	public Client[] sortedClientList() {
		Client[] arr = clientList.toArray(new Client[0]);
		for (int  i=arr.length-1 ; i > 0 ; i-- ) {
			for (int j=0 ; j < i ; j++ )  {
				if (arr[j].getName().compareTo(arr[j+1].getName())> 0)
					swap(arr, j, j+1);
			}
		}
		return arr;
	}
	
	private static void swap(Client[] arr, int i, int j)  {
	     Client temp = arr[i];
	     arr[i] = arr[j];
	     arr[j] = temp;
	}
}
