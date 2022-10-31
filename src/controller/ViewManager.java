package controller;

import contracts.iViewManager;
import view.View;

public class ViewManager implements iViewManager{
	private View view;

	public ViewManager(Controller controller) {
		view = new View(controller);
	}
	@Override
	public void showMainScreen() {
		view.run();
	}

	@Override
	public void popUpError(String errMsg) {
		view.showPopup(errMsg);
	}

	@Override
	public void showApartmentAddedSuccessfully() {
		view.showPopup("apartment added succussfully");
	}
	
	@Override
	public void showClientAddedSuccessfully() {
		view.showPopup("client added succussfully");
	}

	@Override
	public void showApartments(String apartmentListToShow, String title){
		view.showOutput(apartmentListToShow, title);
	}
	
	@Override
	public void showApartmentAndPrice(String apartmentToShow, double price){
		String str = apartmentToShow.concat(String.format("----------\nTotal price: %f\n----------\n", price));
		
		view.showOutput(str, "Apartment Price");
	}

	@Override
	public void showMostExpensive(String apartmentToShow) {
		view.showOutput(apartmentToShow, "Most Expensive");
	}
	
	@Override
	public	void showClients(String clientsToShow){
		view.showOutput(clientsToShow, "All Clients");
	}
	
	@Override
	public void showSortedClients(String clientsToShow){
		view.showOutput(clientsToShow, "Sorted Clients");
	}

	@Override
	public 	void showApartmentWithClients(String apartmentToShow, String clientsToShow){
		
	}
	
	@Override
	public void showExit(boolean isSaved) {
		
	}
}

