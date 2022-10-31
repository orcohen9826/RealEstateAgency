package model;

import java.io.Serializable;

public class Client implements Serializable, Cloneable{
	private String name;
	private String phoneNumber;
	
	public Client (String name , String phoneNumber) throws IllegalArgumentException, Exception {
		setName(name);
		setPhoneNumber(phoneNumber);
	}
	
	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException{
		if(phoneNumber == null || phoneNumber.length() == 0) {
			throw new IllegalArgumentException("Client phone number is missing");
		}
		this.phoneNumber = phoneNumber;
	}

	public void setName(String name) throws IllegalArgumentException{
		if(name == null || name.length() <= 0) {
			throw new IllegalArgumentException("Client name is missing");
		}
		this.name = name;
	}

	@Override
	public Client clone()throws CloneNotSupportedException{
		return(Client)super.clone();
	}
	
	@Override
	public String toString() {
		String str = "Client \n"
				.concat(String.format("Name: %s \n", name))
				.concat(String.format("Phone number: %s \n", phoneNumber ));
		return str;	
	}

	@Override // for checking by phone number
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (phoneNumber != other.phoneNumber)
			return false;
		return true;
	}
}