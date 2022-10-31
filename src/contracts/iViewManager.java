package contracts;

public interface iViewManager {
	void showMainScreen();
	void popUpError(String errMsg);
	void showApartmentAddedSuccessfully();
	void showClientAddedSuccessfully();
	void showApartments(String apartmentListToShow, String title);
	void showApartmentAndPrice(String apartmentToShow, double price);
	void showMostExpensive(String apartmentToShow);
	void showClients(String clientsToShow);
	void showSortedClients(String clientsToShow);
	void showApartmentWithClients(String apartmentToShow, String clientsToShow);
	void showExit(boolean isSaved);
}
