package view;

import controller.Controller;
import enums.ApartmentType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {
	private static Controller controller;

	// global components

	Stage primaryRootStage;
	Pane root;
	Pane menuLayoutLeftPane;
	Pane menuLayoutRightPane;
	GridPane addApartmentGrid;
	GridPane addClientGrid;
	GridPane showAllApartmentsOfTypeGrid;
	GridPane totlaPriceForRentGrid;
	GridPane mostExpensiveGrid;
	GridPane showAllClientsForApartmentGrid;
	GridPane showSortedClientsForApartmentGrid;
	GridPane showApartmentDeepCopyGrid;

	Scene addApartmentScene;
	Scene mostExpensiveScene;
	Scene menuScene;

	VBox layout;
	HBox menuLayout;
	VBox menuList;
	Label title;
	Button addApartment;
	Button addClient;
	Button showAllApartments;
	Button showAllApartmentsOfType;
	Button totalPriceForRent;
	Button mostExpensive;
	Button allClients;
	Button sortedClients;
	Button printCommissions;
	Button apartmentDeepCopy;
	Button exit;

	//

	public View() {
	}

	public View(Controller c) {
		controller = c;
	}

	private void initComponents() {
		root = new Pane();
		menuScene = new Scene(root, 900, 600);
		menuScene.getStylesheets().add(getCss());
		layout = new VBox();
		layout.setPrefWidth(900);
		title = new HeaderLabel(ConstStrings.TITLE_MAIN_PAGE);

		// mainGrid = new GridPane();
		menuLayout = new HBox();
		menuList = new VBox();

		// init
		addApartment = new MenuButton(ConstStrings.BUTTON_ADD_APARTMENT);
		addClient = new MenuButton(ConstStrings.BUTTON_ADD_CLIENT);
		showAllApartments = new MenuButton(ConstStrings.BUTTON_SHOW_ALL_APARTMENTS);
		showAllApartmentsOfType = new MenuButton(ConstStrings.BUTTON_SHOW_ALL_APARTMENTS_BY_TYPE);
		totalPriceForRent = new MenuButton(ConstStrings.BUTTON_TOTAL_PRICE_FOR_RENT);
		mostExpensive = new MenuButton(ConstStrings.BUTTON_MOST_EXPENSIVE);
		allClients = new MenuButton(ConstStrings.BUTTON_ALL_CLIENTS);
		sortedClients = new MenuButton(ConstStrings.BUTTON_SORTED_CLIENTS);
		printCommissions = new MenuButton(ConstStrings.BUTTON_PRINT_COMMISSIONS);
		apartmentDeepCopy = new MenuButton(ConstStrings.BUTTON_APARTMENT_DEEP_COPY);
		exit = new MenuButton(ConstStrings.BUTTON_EXIT);

		// locations
		menuList.setPadding(new Insets(10));
		menuList.setAlignment(Pos.CENTER_LEFT);
		menuList.setSpacing(5);
		menuList.getChildren().addAll(addApartment, addClient, showAllApartments, showAllApartmentsOfType,
				totalPriceForRent, mostExpensive, allClients, sortedClients, printCommissions, apartmentDeepCopy, exit);
		menuLayoutRightPane = new GridPane();
		menuLayoutLeftPane = new GridPane();
		menuLayoutLeftPane.getChildren().add(menuList);
		menuLayout.getChildren().addAll(menuLayoutLeftPane, menuLayoutRightPane);
		menuLayout.setSpacing(200);

		// insert to vbox
		layout.setAlignment(Pos.TOP_CENTER);
		layout.getChildren().addAll(title, menuLayout);

		// put in pane
		root.getChildren().add(layout);
		root.getStyleClass().add(ConstStrings.STYLE_ROOT);

		initActions();
	}

	private void initActions() {
		// actions
		addApartment.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createAddApatmentPane());
		});
		addClient.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createAddClientPane());
		});
		showAllApartments.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(new GridPane());
			controller.displayAllApartment();
		});
		showAllApartmentsOfType.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createShowAllApartmentsOfTypeGrid());
		});
		totalPriceForRent.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createTotlaPriceForRentGrid());
		});
		mostExpensive.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createMostExpensiveGrid());
		});
		allClients.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createShowAllClientsForApartmentGrid());
		});
		sortedClients.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createShowSortedClientsForApartmentGrid());
		});
		printCommissions.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(new GridPane());
			controller.printCommissions();
		});
		apartmentDeepCopy.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(createShowApartmentDeepCopyGrid());
		});
		exit.setOnAction(e -> {
			menuLayoutRightPane.getChildren().setAll(new GridPane());
			createExitPopup();
		});
	}

	public void run() {
		launch();
	}

	private String getCss() {
		return getClass().getResource("viewStyle.css").toExternalForm();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			initComponents();
			primaryRootStage = primaryStage;
			primaryStage.setScene(menuScene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setResizable(false);
			primaryStage.setTitle(ConstStrings.TITLE_MAIN_PAGE);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createExitPopup() {
		Stage exitStage = new Stage();
		Label popUpTxt = new PopupLabel(ConstStrings.TEXT_DO_YOU_WANT_TO_SAVE_LABEL);
		Button bYes = new Button(ConstStrings.BUTTON_YES_ACTION);
		Button bNo = new Button(ConstStrings.BUTTON_NO_ACTION);
		bYes.setAlignment(Pos.CENTER);
		bNo.setAlignment(Pos.CENTER);
		VBox exitPopUpPane = new VBox();
		HBox buttonsPane = new HBox();

		buttonsPane.getChildren().addAll(bYes, bNo);
		buttonsPane.setAlignment(Pos.CENTER);
		exitPopUpPane.setAlignment(Pos.CENTER);
		exitPopUpPane.getChildren().addAll(popUpTxt, buttonsPane);
		buttonsPane.setSpacing(20);
		exitPopUpPane.setSpacing(20);

		bYes.setOnAction(e -> {
			controller.exit(true);
			exitStage.close();
			primaryRootStage.close();
		});
		bNo.setOnAction(e -> {
			controller.exit(false);
			exitStage.close();
			primaryRootStage.close();
		});

		Scene exitScene = new Scene(exitPopUpPane, 250, 250);
		exitScene.getStylesheets().add(getCss());
		exitStage.setScene(exitScene);
		exitStage.setAlwaysOnTop(true);
		exitStage.setResizable(false);
		exitStage.setTitle(ConstStrings.TITLE_EXIT_POPUP);
		exitStage.show();
	}

	private GridPane createShowApartmentDeepCopyGrid() {
		showApartmentDeepCopyGrid = new GridPane();
		showApartmentDeepCopyGrid.setPadding(new Insets(10));
		showApartmentDeepCopyGrid.setHgap(10);
		showApartmentDeepCopyGrid.setVgap(10);

		showApartmentDeepCopyGrid.add(new MenuLabel(ConstStrings.TEXT_APARTMENT_ID_LABEL), 0, 1);
		TextField apartmentId = new TextField();
		showApartmentDeepCopyGrid.add(apartmentId, 1, 1);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		showApartmentDeepCopyGrid.add(errLabel, 0, 3);

		Button showButton = new Button(ConstStrings.BUTTON_DEEP_COPY_ACTION);
		showButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String idStr = apartmentId.getText();

				if (idStr != null && idStr != "") {
					long id = Long.parseLong(idStr);
					controller.copyApartment(id);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString();
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			} else {
				errLabel.setVisible(false);
			}
		});
		showApartmentDeepCopyGrid.add(showButton, 0, 2);
		return showApartmentDeepCopyGrid;
	}

	private GridPane createShowSortedClientsForApartmentGrid() {
		showSortedClientsForApartmentGrid = new GridPane();
		showSortedClientsForApartmentGrid.setPadding(new Insets(10));
		showSortedClientsForApartmentGrid.setHgap(10);
		showSortedClientsForApartmentGrid.setVgap(10);

		showSortedClientsForApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_APARTMENT_ID_LABEL), 0, 1);
		TextField apartmentId = new TextField();
		showSortedClientsForApartmentGrid.add(apartmentId, 1, 1);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		showSortedClientsForApartmentGrid.add(errLabel, 0, 3);

		Button showButton = new Button(ConstStrings.BUTTON_SHOW_SORTED_ACTION);
		showButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String idStr = apartmentId.getText();

				if (idStr != null && idStr != "") {
					long id = Long.parseLong(idStr);
					controller.printSortedClientList(id);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString();
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			} else {
				errLabel.setVisible(false);
			}
		});
		showSortedClientsForApartmentGrid.add(showButton, 0, 2);
		return showSortedClientsForApartmentGrid;
	}

	private GridPane createShowAllClientsForApartmentGrid() {
		showAllClientsForApartmentGrid = new GridPane();
		showAllClientsForApartmentGrid.setPadding(new Insets(10));
		showAllClientsForApartmentGrid.setHgap(10);
		showAllClientsForApartmentGrid.setVgap(10);

		showAllClientsForApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_APARTMENT_ID_LABEL), 0, 1);
		TextField apartmentId = new TextField();
		showAllClientsForApartmentGrid.add(apartmentId, 1, 1);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		showAllClientsForApartmentGrid.add(errLabel, 0, 3);

		Button showButton = new Button(ConstStrings.BUTTON_SHOW_ACTION);
		showButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String idStr = apartmentId.getText();

				if (idStr != null && idStr != "") {
					long id = Long.parseLong(idStr);
					controller.copyApartment(id);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString();
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			} else {
				errLabel.setVisible(false);
			}
		});
		showAllClientsForApartmentGrid.add(showButton, 0, 2);
		return showAllClientsForApartmentGrid;
	}

	private GridPane createMostExpensiveGrid() {
		mostExpensiveGrid = new GridPane();
		mostExpensiveGrid.setPadding(new Insets(10));
		mostExpensiveGrid.setHgap(10);
		mostExpensiveGrid.setVgap(10);

		Label rentPeriod = new MenuLabel(ConstStrings.TEXT_ENTER_RENT_PERIOD_LABEL);
		TextField appRentPeriodTextField = new TextField();

		mostExpensiveGrid.add(rentPeriod, 0, 2);
		mostExpensiveGrid.add(appRentPeriodTextField, 1, 2);

		mostExpensiveGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_TYPE_LABEL), 0, 4, 2, 1);
		ComboBox<String> apTypes = new ComboBox<String>();
		apTypes.getItems().addAll(ApartmentType.LongTermRent.toString(), ApartmentType.Abnb.toString());
		mostExpensiveGrid.add(apTypes, 1, 4);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		mostExpensiveGrid.add(errLabel, 0, 7);

		Button shoButton = new Button(ConstStrings.BUTTON_SHOW_ACTION);
		shoButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String rentPeriodStr = appRentPeriodTextField.getText();
				String typeStr = apTypes.getValue();

				if (rentPeriodStr != null && typeStr != null) {
					ApartmentType type = ApartmentType.valueOf(typeStr);
					int period = Integer.parseInt(rentPeriodStr);

					controller.theMostExpensive(type, period);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString();
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			} else {
				errLabel.setVisible(false);
			}
		});
		mostExpensiveGrid.add(shoButton, 0, 6);

		return mostExpensiveGrid;
	}

	private GridPane createTotlaPriceForRentGrid() {
		totlaPriceForRentGrid = new GridPane();
		totlaPriceForRentGrid.setPadding(new Insets(10));
		totlaPriceForRentGrid.setHgap(10);
		totlaPriceForRentGrid.setVgap(10);

		totlaPriceForRentGrid.add(new MenuLabel(ConstStrings.TEXT_APARTMENT_ID_LABEL), 0, 1);
		TextField apartmentId = new TextField();
		totlaPriceForRentGrid.add(apartmentId, 1, 1);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		totlaPriceForRentGrid.add(errLabel, 0, 3);

		Button shoButton = new Button(ConstStrings.BUTTON_GET_PRICE_ACTION);
		shoButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String idStr = apartmentId.getText();

				if (idStr != null && idStr != "") {
					long id = Long.parseLong(idStr);
					controller.showPriceForApartment(id);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString();
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			} else {
				errLabel.setVisible(false);
			}
		});
		totlaPriceForRentGrid.add(shoButton, 0, 2);
		return totlaPriceForRentGrid;
	}

	private GridPane createShowAllApartmentsOfTypeGrid() {
		// showAllApartmentsOfTypeGrid
		showAllApartmentsOfTypeGrid = new GridPane();
		showAllApartmentsOfTypeGrid.setPadding(new Insets(10));
		showAllApartmentsOfTypeGrid.setHgap(10);
		showAllApartmentsOfTypeGrid.setVgap(10);

		showAllApartmentsOfTypeGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_TYPE_LABEL), 0, 4, 2, 1);
		ComboBox<String> apartmentTypes = new ComboBox<String>();
		apartmentTypes.getItems().addAll(ApartmentType.ForSell.toString(), ApartmentType.LongTermRent.toString(),
				ApartmentType.Abnb.toString());
		showAllApartmentsOfTypeGrid.add(apartmentTypes, 1, 7);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		showAllApartmentsOfTypeGrid.add(errLabel, 0, 9);

		Button shoButton = new Button(ConstStrings.BUTTON_SHOW_ACTION);
		shoButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String typeStr = apartmentTypes.getValue();

				if (typeStr != null) {
					ApartmentType type = ApartmentType.valueOf(typeStr);
					controller.showApartmentsOfSpecificType(type);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString();
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			} else {
				errLabel.setVisible(false);
			}
		});
		showAllApartmentsOfTypeGrid.add(shoButton, 0, 7);

		return showAllApartmentsOfTypeGrid;
	}

	private GridPane createAddClientPane() {
		// add client
		addClientGrid = new GridPane();
		addClientGrid.setPadding(new Insets(10));
		addClientGrid.setHgap(10);
		addClientGrid.setVgap(10);

		addClientGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_NAME_LABEL), 0, 0);
		TextField clientName = new TextField();
		addClientGrid.add(clientName, 1, 0);

		addClientGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_PHONE_NUMBER_LABEL), 0, 1);
		TextField clientPhoneNumber = new TextField();
		addClientGrid.add(clientPhoneNumber, 1, 1);

		addClientGrid.add(new MenuLabel(ConstStrings.TEXT_APARTMENT_ID_LABEL), 0, 2);
		TextField apartmentId = new TextField();
		addClientGrid.add(apartmentId, 1, 2);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		addClientGrid.add(errLabel, 0, 9);

		Button addClientButton = new Button(ConstStrings.BUTTON_ADD_CLIENT_ACTION);
		addClientButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String name = clientName.getText();
				String phone = clientPhoneNumber.getText();
				String idStr = apartmentId.getText();

				if (name != null && phone != null && idStr != null && idStr != "") {
					long id = Long.parseLong(idStr);
					controller.addClient(id, name, phone);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS;
				}
			} catch (Exception ex) {
				errMsg = ex.toString() + " \n" + ConstStrings.ERROR_MESSAGE_RESET_AND_TRY_AGAIN; 
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			}
		});
		addClientGrid.add(addClientButton, 0, 7);

		Button resetAddClientButton = new Button(ConstStrings.BUTTON_RESET_ACTION);
		resetAddClientButton.setOnAction(e -> {
			clientName.setText(null);
			clientPhoneNumber.setText(null);
			apartmentId.setText(null);
			errLabel.setText(null);

			errLabel.setVisible(false);

		});
		addClientGrid.add(resetAddClientButton, 0, 8);

		return addClientGrid;
	}

	private GridPane createAddApatmentPane() {
		addApartmentGrid = new GridPane();
		addApartmentGrid.setPadding(new Insets(10));
		addApartmentGrid.setHgap(10);
		addApartmentGrid.setVgap(10);

		addApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_APARTMENT_AREA_LABEL), 0, 0);
		TextField appAreaTextField = new TextField();
		addApartmentGrid.add(appAreaTextField, 1, 0);

		addApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_CHOOSE_RANK_LABEL), 0, 1, 2, 1);
		ComboBox<Integer> rankComboBox = new ComboBox<Integer>();
		rankComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		addApartmentGrid.add(rankComboBox, 1, 1);

		addApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_NUMBER_OF_ROOMS_LABEL), 0, 2);
		TextField numOfRooms = new TextField();
		addApartmentGrid.add(numOfRooms, 1, 2);

		addApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_ADDRESS_LABEL), 0, 3);
		TextField addressTextField = new TextField();
		addApartmentGrid.add(addressTextField, 1, 3);

		addApartmentGrid.add(new MenuLabel(ConstStrings.TEXT_ENTER_TYPE_LABEL), 0, 4, 2, 1);
		ComboBox<String> apTypes = new ComboBox<String>();
		apTypes.getItems().addAll(ApartmentType.ForSell.toString(), ApartmentType.LongTermRent.toString(),
				ApartmentType.Abnb.toString());
		addApartmentGrid.add(apTypes, 1, 4);

		TextField appPriceTextField = new TextField();
		TextField appRentPeriodTextField = new TextField();
		TextField appRentPriceTextField = new TextField();

		Label priceForPeriod = new MenuLabel(ConstStrings.TEXT_ENTER_RENT_PRICE_FOR_PERIOD_LABEL);
		Label enterPrice = new MenuLabel(ConstStrings.TEXT_ENTER_PRICE_LABEL);
		Label rentPeriod = new MenuLabel(ConstStrings.TEXT_ENTER_RENT_PERIOD_LABEL);

		addApartmentGrid.add(enterPrice, 1, 5);
		addApartmentGrid.add(appPriceTextField, 1, 6);

		addApartmentGrid.add(rentPeriod, 1, 5);
		addApartmentGrid.add(appRentPeriodTextField, 1, 6);
		addApartmentGrid.add(priceForPeriod, 1, 7);
		addApartmentGrid.add(appRentPriceTextField, 1, 8);

		enterPrice.setVisible(false);
		appPriceTextField.setVisible(false);
		rentPeriod.setVisible(false);
		appRentPeriodTextField.setVisible(false);
		priceForPeriod.setVisible(false);
		appRentPriceTextField.setVisible(false);

		Label errLabel = new ErrorLabel();
		errLabel.setVisible(false);
		addApartmentGrid.add(errLabel, 0, 9);

		apTypes.setOnAction(e -> {
			if (apTypes.getValue() == null) {
				return;
			}
			switch (ApartmentType.valueOf(apTypes.getValue())) {
			case ForSell: {
				enterPrice.setVisible(true);
				appPriceTextField.setVisible(true);
				rentPeriod.setVisible(false);
				appRentPeriodTextField.setVisible(false);
				priceForPeriod.setVisible(false);
				appRentPriceTextField.setVisible(false);
				break;
			}
			case LongTermRent: {
				enterPrice.setVisible(false);
				appPriceTextField.setVisible(false);
				rentPeriod.setVisible(true);
				appRentPeriodTextField.setVisible(true);
				priceForPeriod.setVisible(true);
				appRentPriceTextField.setVisible(true);
				break;
			}
			case Abnb: {
				enterPrice.setVisible(false);
				appPriceTextField.setVisible(false);
				rentPeriod.setVisible(true);
				appRentPeriodTextField.setVisible(true);
				priceForPeriod.setVisible(true);
				appRentPriceTextField.setVisible(true);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + apTypes);
			}
		});

		Button addAppartmentButton = new Button(ConstStrings.BUTTON_ADD_APARTMENT_ACTION);
		addAppartmentButton.setOnAction(e -> {
			boolean success = false;
			String errMsg = "";
			try {
				errLabel.setText(null);
				String typeStr = apTypes.getValue();
				String addressStr = addressTextField.getText();
				String areaStr = appAreaTextField.getText();
				String roomsStr = numOfRooms.getText();
				Boolean reskChosenBoolean = rankComboBox.getSelectionModel().isEmpty();
				if (typeStr != null && addressStr != null && areaStr != null && roomsStr != null
						&& !reskChosenBoolean) {
					ApartmentType type = ApartmentType.valueOf(typeStr);
					String address = addressStr;
					double area = Double.parseDouble(areaStr);
					int rooms = Integer.parseInt(roomsStr);
					int rank = rankComboBox.getValue();
					double price = type == ApartmentType.ForSell ? Double.parseDouble(appPriceTextField.getText())
							: Double.parseDouble(appRentPriceTextField.getText());
					int period = type == ApartmentType.ForSell ? 0 : Integer.parseInt(appRentPeriodTextField.getText());

					controller.addApartment(ApartmentType.valueOf(apTypes.getValue()), address, area, rooms, rank,
							price, period);
					success = true;
				} else {
					errMsg = ConstStrings.ERROR_MESSAGE_FILL_ALL_OPTIONS; 
				}
			} catch (Exception ex) {
				errMsg = ex.toString() + " \n" + ConstStrings.ERROR_MESSAGE_RESET_AND_TRY_AGAIN; 
			}
			if (!success) {
				errLabel.setText(errMsg);
				errLabel.setVisible(true);
			}
		});
		addApartmentGrid.add(addAppartmentButton, 0, 7);

		Button resetAppartmentButton = new Button(ConstStrings.BUTTON_RESET_ACTION);
		resetAppartmentButton.setOnAction(e -> {
			apTypes.setValue(null);
			addressTextField.setText(null);
			appAreaTextField.setText(null);
			numOfRooms.setText(null);
			rankComboBox.setValue(null);
			appPriceTextField.setText(null);
			appRentPriceTextField.setText(null);
			appRentPeriodTextField.setText(null);
			errLabel.setText(null);

			errLabel.setVisible(false);
			enterPrice.setVisible(false);
			appPriceTextField.setVisible(false);
			rentPeriod.setVisible(false);
			appRentPeriodTextField.setVisible(false);
			priceForPeriod.setVisible(false);
			appRentPriceTextField.setVisible(false);
		});
		addApartmentGrid.add(resetAppartmentButton, 0, 8);

		return addApartmentGrid;
	}

	public void showPopup(String msg) {
		Stage popUpStage = new Stage();
		Label popUpTxt = new PopupLabel(msg);
		Button b = new Button(ConstStrings.BUTTON_OK_ACTION);
		b.setAlignment(Pos.CENTER);
		VBox popUpPane = new VBox();
		popUpPane.setAlignment(Pos.CENTER);
		popUpPane.getChildren().addAll(popUpTxt, b);
		popUpPane.setSpacing(20);
		b.setOnAction(e -> {
			popUpStage.close();
		});
		Scene errScene = new Scene(popUpPane, 250, 250);
		errScene.getStylesheets().add(getCss());
		popUpStage.setScene(errScene);
		popUpStage.setAlwaysOnTop(true);
		popUpStage.setResizable(false);
		popUpStage.show();
	}

	public void showOutput(String data, String title) {
		Stage apartmentsStage = new Stage();
		Label apartmentsTxt = new Label(data);
		apartmentsTxt.setAlignment(Pos.TOP_LEFT);
		ScrollPane apartmentsPane = new ScrollPane();
		apartmentsPane.setContent(apartmentsTxt);
		apartmentsPane.setPadding(new Insets(10));
		Scene errScene = new Scene(apartmentsPane, 500, 500);
		apartmentsStage.setScene(errScene);
		apartmentsStage.setAlwaysOnTop(true);
		apartmentsStage.setTitle(title);
		apartmentsStage.show();
	}
}
