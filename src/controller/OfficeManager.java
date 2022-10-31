package controller;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import contracts.*;
import model.*;

public class OfficeManager implements iOfficeManager{
	private Office office;
	private final String dbFileName; // Blank final - can be initialized in constructor
	
	public OfficeManager(String dbFileName) {
		office = new Office();
		this.dbFileName = dbFileName;
	}
	
	@Override
	public boolean addApartment(Apartment apartment) {
		boolean res = office.addApartment(apartment);
		return res;
	}

	@Override
	public void init() {
		Office of = readOfficeFromFile(dbFileName);
		if(of != null) {
			office = of;
		}else {
			initDefaultValues();
		}
	}
	
	@Override
	public Apartment getApartmentById(long id) {
		Apartment apartment = office.getApartmentById(id);
		return apartment;
	}
	
	@Override
	public ArrayList<Apartment> getApartmentList(){
		return office.getApartmentList();
	}
	
	@Override
	public ArrayList<Apartment> getApartmentsByType(String ApartmentType){
		return office.getApartmentsByType(ApartmentType);
	}

	@Override
	public Apartment mostExpensive(String ApartmentType, int period) {
		Apartment apartment = office.mostExpensive(ApartmentType, period);
		return apartment;
	}
	
	@Override
	public ArrayList<Apartment> allCommission(){
		return office.allCommission();
	}
	
	@Override
	public boolean saveProgToBin() {
		boolean isSaved = false;
		try {
			saveToFile(office, dbFileName);
			isSaved = true;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return isSaved;
	}
	
	private void saveToFile(Office of, String fileName) throws FileNotFoundException, IOException {
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream(fileName));
			o.writeObject(of);
			o.writeUTF(Long.toString( Apartment.getIdGenerator()));
		} finally {
			closeStream(o);
		}
	}
	
	private void closeStream(Closeable o) {
		if(o != null) {
			try {
				o.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}				
		}
	}
	
	private Office readOfficeFromFile(String fileName) {
		ObjectInputStream o = null;
		Office office = null;
		long lastIdVal= 0;
		try{
			File file = new File(fileName);
			if(file != null && file.exists() && file.length() > 0) {
				o = new ObjectInputStream(new FileInputStream(file));
				office = (Office)o.readObject();
				lastIdVal = Long.parseLong(o.readUTF()); 
				if(office != null && lastIdVal > 0) {
					Apartment.setIdGenerator(lastIdVal);
				}				
			}
		}catch(FileNotFoundException e) {
			//we already protect it in the condition above 
		}catch(IOException e) {
			System.err.println(e);
		}catch(ClassNotFoundException e) {
			System.err.println(e);
		}catch(Exception e) {
			System.err.println(e);
		}finally {			
			closeStream(o);
		}
		
		return office;
	}
	
	private void initDefaultValues() {
		ArrayList<Apartment> apartments = new ArrayList<>(); 
		// Create & add 4 apartments of each type
		try {
			ForSell forSellAp1 = new ForSell("Mivtza kadesh 38", 120, 4, 8, 1000000);
			ForSell forSellAp2 = new ForSell("Begin 20", 80, 3, 7, 1000000);
			ForSell forSellAp3 = new ForSell("Hertzel 1", 120, 5, 4, 2560000);
			ForSell forSellAp4 = new ForSell("Alenbi 101", 50, 2, 3, 1500000);
			apartments.add(forSellAp1);
			apartments.add(forSellAp2);
			apartments.add(forSellAp3);
			apartments.add(forSellAp4);
			
			LongTermRent longTerm1 = new LongTermRent("Mivtza kadesh 38", 120, 4, 10, 12, 3000);
			LongTermRent longTerm2 = new LongTermRent("Ehad Haam 1", 80, 2, 3, 6, 4500);
			LongTermRent longTerm3 = new LongTermRent("Orlov 15", 150, 5, 8, 24, 5000);
			LongTermRent longTerm4 = new LongTermRent("Hayarkon 54", 100, 3, 8, 12, 6000);
			apartments.add(longTerm1);
			apartments.add(longTerm2);
			apartments.add(longTerm3);
			apartments.add(longTerm4);
			
			Abnb abnb1 = new Abnb("Jerusalem 40", 95, 3, 8, 12, 3500);
			Abnb abnb2 = new Abnb("Gordon 13", 103, 4, 8, 24, 4500);
			Abnb abnb3 = new Abnb("Begin 33", 55, 2, 5, 1, 3500);
			Abnb abnb4 = new Abnb("Trumpeldor 10", 70, 3, 6, 1, 4000);
			apartments.add(abnb1);
			apartments.add(abnb2);
			apartments.add(abnb3);
			apartments.add(abnb4);
			
			// create clients
			Client c1 = new Client("Or", "972524498712");
			Client c2 = new Client("David", "972524678113");
			Client c3 = new Client("Keren", "972549187335");
			Client c4 = new Client("Avi", "972501978553");
			Client c5 = new Client("Avihau", "9725273778414");
			Client c6 = new Client("Avihu", "9725273778414");
			Client c7 = new Client("Beni", "9725288877414");
			Client[][] clients = new Client[][] {
				{c1,c2,c3,c4},
				
				{c2,c5,c6,c1},
				
				{c5,c3,c4,c7},
				
				{c4,c1,c3,c5},
				
				{c1,c4,c7,c6},
				
				{c3,c5,c2,c1},
				
				{c1,c4,c2,c6},
				
				{c1,c5,c7,c6},
				
				{c2,c4,c7,c5},
				
				{c1,c4,c7,c6},
				
				{c2,c1,c3,c7},
				
				{c7,c6,c5,c3},
			};
			
			int i = 0;
			for(Apartment a : apartments){
				for(int j = 0; j < clients[i].length; j++) {
					a.addClient(clients[i][j]);				
				}
				i++;
				office.addApartment(a);
			}	
		}catch (Exception e) {
			System.err.println("Failed to init default values to office: ");		
			System.err.println(e);		
		}
	}
	
	@Override
	public String prepareApartmentsToShow(ArrayList<Apartment> apartments) {
		String result = "";
		for(Apartment apartment : apartments){
			String formattedString = "--------------------\n" + apartment.toString() + "--------------------\n";
			result += formattedString;
		}
		return result;
	}

	@Override
	public String prepareClientsToShow(ArrayList<Client> clients) {
		String result = "";
		for(Client c : clients){
			String formattedString = "--------------------\n" + c.toString() + "--------------------\n";
			result += formattedString;
		}
		return result;
	}
	
	@Override
	public String prepareApartmentWithClientsToShow(Apartment apartment) {
		String result = "";
		String apString = apartment.toString();
		String clientString = prepareClientsToShow(apartment.getClientList());
		result = String.format("%s \nClients: \n%s", apString, clientString);
		return result;
	}
}
