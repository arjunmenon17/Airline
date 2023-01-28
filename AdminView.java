
/**
 * [ICS4U] Airline Reservation | AdminView.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminView extends JFrame {

    // instantiate all view components
    private JPanel mainContentPane, addFlightPane, editFlightPane,
            removeFlightPane, addPassengerPane, removePassengerPane;

    private JLabel lblAdminPanel, lblColumn, lblName, lblFlightView, lblFood, lblFlightEdit, lblAddFlight, lblDestination, lblAirline, lblAddPassenger, lblETD, lblETA,
            lblRemoveFlight, txtAreaFlights, lblPassport, lblRow, lblFlightAdd, removeFlightTextArea, lblAddressAddPassenger, lblVegetarian,
            lblRemovePassenger, lblEditFlight, lblDrinks, addPassengerTextArea, lblFlightToRemove, lblAge;

    private JButton btnAddFlight, btnRemove, btnRemoveFlight, btnAddPassenger, btnRemovePassenger,
            btnEditFlight, btnConfirmAddFlight, btnEdit, buttonViewPassengers, btnConfirmRemove, btnAdd, btnBackToHome, btnViewPassengers;

    private JTextField addFlightDestinationInput, passportInput, vegetarianInput, drinksInput, editFlightEtdInput, editFlightDestinationInput,
            editFlightNumberInput, foodInput, numberInput, addressInput, addFlightAirlineInput,
            addFlightEtdInput, addFlightEtaInput, rowInput, columnInput, numberInputRemoveFlight,
            editFlightEtaInput, nameInput, editFlightAirlineInput, ageInput;

    private JScrollPane removeFlightScrollPane, addPassengerScrollPane, sclpPassengers, removePassengerScrollPane, editFlightScrollPane;
    //instantiate all swing components for use in methods

    private AdminController controller;

    public AdminView() {
        // init controller, label, panel, buttons, textfields, and more

        this.controller = new AdminController(this);

        this.mainContentPane = new JPanel();
        this.addFlightPane = new JPanel();
        this.editFlightPane = new JPanel();
        this.removeFlightPane = new JPanel();
        this.addPassengerPane = new JPanel();
        this.removePassengerPane = new JPanel();

        this.lblAdminPanel = new JLabel("Miami International Airport Flights Admin (MIA)");
        this.lblColumn = new JLabel("Column");
        this.lblName = new JLabel("Passenger Name:");
        this.lblFlightView = new JLabel("Flight to View:");

        this.lblFood = new JLabel("Food (comma seperated):");
        this.lblFlightEdit = new JLabel("Flight to Edit:");
        this.lblAddFlight = new JLabel("Add Flight");
        this.lblDestination = new JLabel("Destination:");
        this.lblAirline = new JLabel("Airline:");
        this.lblAddPassenger = new JLabel("Add Passenger");
        this.lblETD = new JLabel("Departure Time:");
        this.lblETA = new JLabel("Estimated Arrival Time:");
        this.lblRemoveFlight = new JLabel("Remove Flight");
        this.lblRow = new JLabel("Row:");
        this.lblFlightAdd = new JLabel("Flight to Add To:");
        this.lblAddressAddPassenger = new JLabel("Address:");
        this.lblVegetarian = new JLabel("Vegetarian (true/false):");
        this.lblRemovePassenger = new JLabel("Remove Passenger");
        this.lblEditFlight = new JLabel("Edit Flight");
        this.lblFlightToRemove = new JLabel("Flight to Remove:");
        this.lblDrinks = new JLabel("Drinks (comma seperated):");
        this.lblPassport = new JLabel("Passport #:");
        this.addPassengerTextArea = new JLabel();
        this.txtAreaFlights = new JLabel();

        this.btnAddFlight = new JButton("Add Flight");
        btnAddFlight.addActionListener(controller.getAddFlight());
        this.btnRemove = new JButton("Remove");
        btnRemove.addActionListener(controller.getRemove());
        this.btnAddFlight = new JButton("Add Flight");
        btnAddFlight.addActionListener(controller.getAddFlight());
        this.btnRemoveFlight = new JButton("Remove Flight");
        btnRemoveFlight.addActionListener(controller.getRemove());
        this.btnAddPassenger = new JButton("Add Passenger");
        btnAddPassenger.addActionListener(controller.getAddPassenger());
        this.btnRemovePassenger = new JButton("Remove Passenger");
        btnRemovePassenger.addActionListener(controller.getRemovePassenger());
        this.btnEditFlight = new JButton("Edit Flight");
        btnEditFlight.addActionListener(controller.getEditFlight());
        this.btnConfirmAddFlight = new JButton("Confirm");
        btnConfirmAddFlight.addActionListener(controller.getConfirmAddFlight());

        this.btnEdit = new JButton();
        btnEdit.addActionListener(controller.getEdit());
        this.btnBackToHome = new JButton();
        btnBackToHome.addActionListener(controller.getBackToHome());
        this.btnConfirmRemove = new JButton();
        btnConfirmRemove.addActionListener(controller.getConfirmRemove());
        this.btnAdd = new JButton();
        btnAdd.addActionListener(controller.getAdd());
        this.btnViewPassengers = new JButton();
        btnViewPassengers.addActionListener(controller.getViewPassenger());

        this.addFlightDestinationInput = new JTextField();
        this.vegetarianInput = new JTextField();
        this.drinksInput = new JTextField();
        this.editFlightEtdInput = new JTextField();
        this.editFlightDestinationInput = new JTextField();
        this.editFlightNumberInput = new JTextField();
        this.foodInput = new JTextField();
        this.addressInput = new JTextField();
        this.addFlightAirlineInput = new JTextField();
        this.addFlightEtdInput = new JTextField();
        this.addFlightEtaInput = new JTextField();
        this.numberInput = new JTextField();
        this.rowInput = new JTextField();
        this.columnInput = new JTextField();
        this.numberInputRemoveFlight = new JTextField();

        this.editFlightEtaInput = new JTextField();
        this.nameInput = new JTextField();
        this.editFlightAirlineInput = new JTextField();
        this.passportInput = new JTextField();
        this.ageInput = new JTextField();

        this.removeFlightScrollPane = new JScrollPane();
        this.addPassengerScrollPane = new JScrollPane();
        this.sclpPassengers = new JScrollPane();
        this.removePassengerScrollPane = new JScrollPane();
        this.editFlightScrollPane = new JScrollPane();

        try {
            createHomeView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Creates the menu for when the user chooses to add a flight
     */
    public void addFlightView() {

        getContentPane().remove(mainContentPane);
        addFlightPane = new JPanel();
        add(addFlightPane);
        setContentPane(addFlightPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Flight");
        setBounds(500, 500, 1000, 700);
        setSize(1000, 600);
        addFlightPane.setLayout(null);
        addFlightPane.setBackground(SystemColor.activeCaption);
        addFlightPane.setForeground(SystemColor.activeCaption);
        setLocationRelativeTo(null);

        lblAddFlight.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblAddFlight.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddFlight.setBounds(120, 30, 625, 30);
        addFlightPane.add(lblAddFlight);

        lblDestination.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDestination.setBounds(300, 100, 500, 50);
        addFlightPane.add(lblDestination);

        lblAirline.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAirline.setBounds(300, 150, 500, 50);
        addFlightPane.add(lblAirline);

        lblETD.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblETD.setBounds(300, 200, 500, 50);
        addFlightPane.add(lblETD);

        lblETA.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblETA.setBounds(300, 250, 500, 50);
        addFlightPane.add(lblETA);

        addFlightDestinationInput = new JTextField();
        addFlightDestinationInput.setBounds(450, 110, 100, 25);
        addFlightPane.add(addFlightDestinationInput);

        addFlightAirlineInput = new JTextField();
        addFlightAirlineInput.setBounds(450, 160, 100, 25);
        addFlightPane.add(addFlightAirlineInput);

        addFlightEtdInput = new JTextField();
        addFlightEtdInput.setBounds(450, 210, 100, 25);
        addFlightPane.add(addFlightEtdInput);

        addFlightEtaInput = new JTextField();
        addFlightEtaInput.setBounds(450, 260, 100, 25);
        addFlightPane.add(addFlightEtaInput);

        btnConfirmAddFlight = new JButton("Confirm");
        btnConfirmAddFlight.addActionListener(controller.getConfirmAddFlight()); // on click confirm button
        btnConfirmAddFlight.setBounds(455, 310, 90, 30);
        addFlightPane.add(btnConfirmAddFlight);

        btnBackToHome = new JButton("Back");
        btnBackToHome.addActionListener(controller.getBackToHome()); // on click back button
        btnBackToHome.setBounds(455, 360, 90, 30);
        addFlightPane.add(btnBackToHome);


    }

    /**
     * Creates the menu for when the user chooses to remove a flight
     */
    public void removeFlightView() {
        getContentPane().remove(mainContentPane);
        removeFlightPane = new JPanel();
        add(removeFlightPane);
        setContentPane(removeFlightPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Remove Flight");
        setBounds(500, 500, 1000, 700);
        setSize(1000, 600);
        removeFlightPane.setLayout(null);
        removeFlightPane.setBackground(SystemColor.activeCaption);
        removeFlightPane.setForeground(SystemColor.activeCaption);
        setLocationRelativeTo(null);

        lblRemoveFlight = new JLabel("Remove Flight");
        lblRemoveFlight.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblRemoveFlight.setHorizontalAlignment(SwingConstants.CENTER);
        lblRemoveFlight.setBounds(110, 10, 625, 30);
        removeFlightPane.add(lblRemoveFlight);

        removeFlightTextArea = new JLabel("<html><br>");
        removeFlightTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));

        controller.trimWhiteSpace("flights.txt");

        removeFlightTextArea.setText(removeFlightTextArea.getText() + controller.updateScrollPane()[0] + "</html>");
        // updates jlabel with longstring containing flight data
        btnBackToHome = new JButton("Back");
        btnBackToHome.addActionListener(controller.getBackToHome()); // on click back button
        btnBackToHome.setBounds(455, 280, 100, 30);
        removeFlightPane.add(btnBackToHome);

        removeFlightScrollPane = new JScrollPane();
        removeFlightScrollPane.setViewportView(removeFlightTextArea);
        removeFlightScrollPane.setBounds(55, 70, 350, 250);
        removeFlightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        removeFlightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        removeFlightScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        removeFlightPane.add(removeFlightScrollPane);

        lblFlightToRemove = new JLabel("Flight to Remove:");
        lblFlightToRemove.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFlightToRemove.setBounds(450, 130, 150, 25);
        removeFlightPane.add(lblFlightToRemove);

        numberInputRemoveFlight = new JTextField();
        numberInputRemoveFlight.setBounds(475, 180, 50, 25);
        removeFlightPane.add(numberInputRemoveFlight);

        btnConfirmRemove = new JButton("Remove");
        btnConfirmRemove.addActionListener(controller.getConfirmRemove()); // on click remove button
        btnConfirmRemove.setBounds(455, 230, 90, 30);
        removeFlightPane.add(btnConfirmRemove);

    }

    /**
     * Creates the menu for when the user chooses to edit a flight
     */
    public void editFlightView() {

        getContentPane().remove(mainContentPane);
        editFlightPane = new JPanel();
        add(editFlightPane);
        setContentPane(editFlightPane);
        editFlightPane.setLayout(null);
        setSize(1000, 600);
        setTitle("Edit Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 500, 1000, 700);

        editFlightPane.setBackground(SystemColor.activeCaption);
        editFlightPane.setLayout(null);

        setLocationRelativeTo(null);
        lblEditFlight = new JLabel("Edit Flight");
        lblEditFlight.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEditFlight.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditFlight.setBounds(200, 10, 625, 30);
        editFlightPane.add(lblEditFlight);

        addPassengerTextArea = new JLabel("<html><br>");
        addPassengerTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));

        addPassengerTextArea.setText(addPassengerTextArea.getText() + controller.updateScrollPane()[0] + "</html>");
        // updates jlabel with long string containing flight list

        addPassengerTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));
        editFlightScrollPane = new JScrollPane();
        editFlightScrollPane.setViewportView(addPassengerTextArea);
        editFlightScrollPane.setBounds(35, 70, 350, 250);

        editFlightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        editFlightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        editFlightScrollPane.getVerticalScrollBar().setUnitIncrement(20);

        editFlightPane.add(editFlightScrollPane);

        lblFlightEdit = new JLabel("Flight to Edit:");
        lblFlightEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFlightEdit.setBounds(450, 280, 150, 25);
        editFlightPane.add(lblFlightEdit);

        editFlightNumberInput = new JTextField();
        editFlightNumberInput.setBounds(680, 280, 100, 25);
        editFlightPane.add(editFlightNumberInput);

        lblETA = new JLabel("Arrival Time:");
        lblETA.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblETA.setBounds(450, 80, 500, 50);
        editFlightPane.add(lblETA);

        lblDestination = new JLabel("Destination:");
        lblDestination.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDestination.setBounds(450, 130, 500, 50);
        editFlightPane.add(lblDestination);

        lblAirline = new JLabel("Airline:");
        lblAirline.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAirline.setBounds(450, 180, 500, 50);
        editFlightPane.add(lblAirline);

        lblETD = new JLabel("Departure Time:");
        lblETD.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblETD.setBounds(450, 230, 500, 50);
        editFlightPane.add(lblETD);

        editFlightEtaInput = new JTextField();
        editFlightEtaInput.setBounds(680, 100, 100, 25);
        editFlightPane.add(editFlightEtaInput);

        editFlightDestinationInput = new JTextField();
        editFlightDestinationInput.setBounds(680, 150, 100, 25);
        editFlightPane.add(editFlightDestinationInput);

        editFlightAirlineInput = new JTextField();
        editFlightAirlineInput.setBounds(680, 200, 100, 25);
        editFlightPane.add(editFlightAirlineInput);

        editFlightEtdInput = new JTextField();
        editFlightEtdInput.setBounds(680, 250, 100, 25);
        editFlightPane.add(editFlightEtdInput);

        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(controller.getEdit()); // on click edit button
        btnEdit.setBounds(680, 320, 100, 30);
        editFlightPane.add(btnEdit);

        btnBackToHome = new JButton("Back");
        btnBackToHome.addActionListener(controller.getBackToHome()); // on click back button
        btnBackToHome.setBounds(680, 380, 100, 30);
        editFlightPane.add(btnBackToHome);

    }

    /**
     * Creates the menu for when the user chooses to add a passenger
     */
    public void addPassengerView() {

        getContentPane().remove(mainContentPane);
        addPassengerPane = new JPanel();
        add(addPassengerPane);
        setContentPane(addPassengerPane);
        addPassengerPane.setLayout(null);

        setTitle("Add Passenger");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 500, 1000, 700);
        setSize(1000, 600);

        addPassengerPane.setBackground(SystemColor.activeCaption);
        addPassengerPane.setLayout(null);
        setLocationRelativeTo(null);

        lblAddPassenger = new JLabel("Add Passenger");
        lblAddPassenger.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblAddPassenger.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddPassenger.setBounds(170, 10, 625, 30);
        addPassengerPane.add(lblAddPassenger);

        addPassengerTextArea = new JLabel("<html><br>");
        addPassengerTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));

        addPassengerTextArea.setText(addPassengerTextArea.getText() + controller.updateScrollPane()[0] + "</html>");
        addPassengerTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));

        addPassengerScrollPane = new JScrollPane();
        addPassengerScrollPane.setViewportView(addPassengerTextArea);
        addPassengerScrollPane.setBounds(35, 60, 350, 225);
        addPassengerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        addPassengerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        addPassengerScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        addPassengerPane.add(addPassengerScrollPane);

        lblFlightAdd = new JLabel("Flight to Add To:");
        lblFlightAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFlightAdd.setBounds(450, 100, 150, 25);
        addPassengerPane.add(lblFlightAdd);

        numberInput = new JTextField();
        numberInput.setBounds(595, 100, 50, 25);
        addPassengerPane.add(numberInput);

        sclpPassengers = new JScrollPane();
        sclpPassengers.setBounds(35, 300, 350, 225);
        sclpPassengers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sclpPassengers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sclpPassengers.getVerticalScrollBar().setUnitIncrement(20);
        addPassengerPane.add(sclpPassengers);

        lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblName.setBounds(450, 230, 150, 25);
        addPassengerPane.add(lblName);

        nameInput = new JTextField();
        nameInput.setBounds(700, 230, 100, 25);
        addPassengerPane.add(nameInput);

        lblRow.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRow.setBounds(450, 260, 150, 25);
        addPassengerPane.add(lblRow);

        rowInput = new JTextField();
        rowInput.setBounds(700, 260, 100, 25);
        addPassengerPane.add(rowInput);

        lblColumn = new JLabel("Column:");
        lblColumn.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblColumn.setBounds(450, 290, 150, 25);
        addPassengerPane.add(lblColumn);

        columnInput = new JTextField();
        columnInput.setBounds(700, 290, 100, 25);
        addPassengerPane.add(columnInput);

        lblAddressAddPassenger = new JLabel("Address:");
        lblAddressAddPassenger.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAddressAddPassenger.setBounds(450, 320, 150, 25);
        addPassengerPane.add(lblAddressAddPassenger);

        addressInput = new JTextField();
        addressInput.setBounds(700, 320, 100, 25);
        addPassengerPane.add(addressInput);

        lblVegetarian.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVegetarian.setBounds(450, 350, 150, 25);
        addPassengerPane.add(lblVegetarian);

        vegetarianInput = new JTextField();
        vegetarianInput.setBounds(700, 350, 100, 25);
        addPassengerPane.add(vegetarianInput);

        lblFood = new JLabel("Food (comma seperated):");
        lblFood.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFood.setBounds(450, 380, 200, 25);
        addPassengerPane.add(lblFood);

        foodInput = new JTextField();
        foodInput.setBounds(700, 380, 100, 25);
        addPassengerPane.add(foodInput);

        lblDrinks = new JLabel("Drinks (comma seperated):");
        lblDrinks.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDrinks.setBounds(450, 410, 200, 25);
        addPassengerPane.add(lblDrinks);

        drinksInput = new JTextField();
        drinksInput.setBounds(700, 410, 100, 25);
        addPassengerPane.add(drinksInput);

        lblPassport.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPassport.setBounds(450, 440, 200, 25);
        addPassengerPane.add(lblPassport);

        passportInput = new JTextField();
        passportInput.setBounds(700, 440, 100, 25);
        addPassengerPane.add(passportInput);

        lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAge.setBounds(450, 470, 200, 25);
        addPassengerPane.add(lblAge);

        ageInput = new JTextField();
        ageInput.setBounds(700, 470, 100, 25);
        addPassengerPane.add(ageInput);

        txtAreaFlights = new JLabel("<html><br>");
        txtAreaFlights.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnViewPassengers = new JButton("View Passengers");
        btnViewPassengers.addActionListener(controller.getViewPassenger()); // on click view passenger button
        btnViewPassengers.setBounds(440, 180, 140, 30);
        addPassengerPane.add(btnViewPassengers);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(controller.getAdd()); // on click add button
        btnAdd.setBounds(830, 350, 100, 30);
        addPassengerPane.add(btnAdd);

        btnBackToHome = new JButton("Back");
        btnBackToHome.addActionListener(controller.getBackToHome()); // on click back button
        btnBackToHome.setBounds(590, 180, 100, 30);
        addPassengerPane.add(btnBackToHome);


    }

    /**
     * Creates the menu for when the user chooses to remove a passenger
     */
    public void removePassengerView() {
        removePassengerPane = new JPanel();

        add(removePassengerPane);
        setContentPane(removePassengerPane);
        removePassengerPane.setLayout(null);
        setSize(1000, 600);

        setTitle("Remove Passenger");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 500, 1000, 700);

        removePassengerPane.setBackground(SystemColor.activeCaption);
        removePassengerPane.setLayout(null);
        setLocationRelativeTo(null);

        lblRemovePassenger = new JLabel("Remove Passenger");
        lblRemovePassenger.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblRemovePassenger.setHorizontalAlignment(SwingConstants.CENTER);
        lblRemovePassenger.setBounds(140, 10, 625, 30);
        removePassengerPane.add(lblRemovePassenger);

        addPassengerTextArea = new JLabel("<html><br>");
        addPassengerTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));

        addPassengerTextArea.setText(addPassengerTextArea.getText() + controller.updateScrollPane()[0] + "</html>");
        // update the jlabel to reflect the flight list

        addPassengerTextArea.setFont(new Font("Tahoma", Font.BOLD, 15));
        removePassengerScrollPane = new JScrollPane();
        removePassengerScrollPane.setViewportView(addPassengerTextArea);
        removePassengerScrollPane.setBounds(35, 70, 350, 225);
        removePassengerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        removePassengerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        removePassengerScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        removePassengerPane.add(removePassengerScrollPane);

        lblFlightView = new JLabel("Flight to View:");
        lblFlightView.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFlightView.setBounds(450, 150, 150, 25);
        removePassengerPane.add(lblFlightView);

        numberInput = new JTextField();
        numberInput.setBounds(475, 200, 50, 25);
        removePassengerPane.add(numberInput);

        sclpPassengers = new JScrollPane();

        sclpPassengers.setBounds(35, 350, 350, 200);
        sclpPassengers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sclpPassengers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sclpPassengers.getVerticalScrollBar().setUnitIncrement(20);
        removePassengerPane.add(sclpPassengers);

        lblName = new JLabel("Passenger Name:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblName.setBounds(450, 400, 150, 25);
        removePassengerPane.add(lblName);

        nameInput = new JTextField();
        nameInput.setBounds(455, 450, 100, 25);
        removePassengerPane.add(nameInput);

        buttonViewPassengers = new JButton("View Passengers");
        buttonViewPassengers.addActionListener(controller.getViewPassenger()); // on click view passengers button
        buttonViewPassengers.setBounds(580, 150, 140, 30);
        removePassengerPane.add(buttonViewPassengers);

        btnRemove = new JButton("Remove");
        btnRemove.addActionListener(controller.getRemovePassengerConfirm()); // on click remove button
        btnRemove.setBounds(450, 500, 110, 30);
        removePassengerPane.add(btnRemove);


        btnBackToHome = new JButton("Back");
        btnBackToHome.addActionListener(controller.getBackToHome()); // on click back button
        btnBackToHome.setBounds(580, 210, 140, 30);
        removePassengerPane.add(btnBackToHome);

    }

    /**
     * Creates the main menu, what the user initally sees on login. consitsts of 5 buttons and a title
     * @throws IOException file not found
     */
    public void createHomeView() throws IOException {

        setTitle("Miami International Airport");
        setBounds((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1024) / 2), (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 600) / 2), 1024, 1000);
        setSize(1000, 600);
        mainContentPane = new JPanel();
        mainContentPane.setBackground(SystemColor.activeCaption);
        mainContentPane.setForeground(SystemColor.activeCaption);

        getContentPane().removeAll();

        setContentPane(mainContentPane);
        mainContentPane.setLayout(null);
        setLocationRelativeTo(null); // centers to middle of screen

        setVisible(true);

        lblAdminPanel.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdminPanel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblAdminPanel.setBounds(10, 20, 1000, 100);
        mainContentPane.add(lblAdminPanel);

        btnAddFlight.setBounds(200, 150, 200, 50);
        btnAddFlight.setFont(new Font("Tahoma", Font.PLAIN, 18));
        mainContentPane.add(btnAddFlight);

        btnRemoveFlight.setBounds(500, 150, 200, 50);
        btnRemoveFlight.setFont(new Font("Tahoma", Font.PLAIN, 18));
        mainContentPane.add(btnRemoveFlight);

        btnEditFlight.setBounds(200, 300, 200, 50);
        btnEditFlight.setFont(new Font("Tahoma", Font.PLAIN, 18));
        mainContentPane.add(btnEditFlight);

        btnRemovePassenger.setBounds(500, 300, 200, 50);
        btnRemovePassenger.setFont(new Font("Tahoma", Font.PLAIN, 18));
        mainContentPane.add(btnRemovePassenger);

        btnAddPassenger.setBounds(350, 450, 200, 50);
        btnAddPassenger.setFont(new Font("Tahoma", Font.PLAIN, 18));
        mainContentPane.add(btnAddPassenger);

    }



    /**
     * Updates the scrollpane with the information of the flight in the numberinput
     */
    public void viewPassengerView() {
        controller.trimWhiteSpace("flights.txt");
        controller.trimWhiteSpace(controller.findFileName(numberInput.getText())); // remove blank lines in passenger file of selected flight

        txtAreaFlights = new JLabel("<html><br>");
        txtAreaFlights.setFont(new Font("Tahoma", Font.BOLD, 15));

        txtAreaFlights.setText(txtAreaFlights.getText() + controller.updateScrollPanePassengers(numberInput.getText()));
        // append a long string to the jlabel that has flight data
        if (txtAreaFlights.getText().equals("<html><br>")) { // no passengers in file
            txtAreaFlights.setText(txtAreaFlights.getText() + "No passengers yet...");
        }

        txtAreaFlights.setFont(new Font("Tahoma", Font.BOLD, 15));
        sclpPassengers.setViewportView(txtAreaFlights);
    }


    /**
     * Display an error when the user enters invalid input
     */
    public void errorMsg() {
        JOptionPane.showMessageDialog(getComponent(0), "Something went wrong... check your input again.", "Warning", 0);
    }

    /**
     * Retrieve data from row input text field
     * @return rowInput data
     */
    public String getRowInput() {
        return rowInput.getText();
    }

    /**
     * Retrieve data from passport input text field
     * @return passportInput data
     */
    public String getPassportInput() {
        return passportInput.getText();
    }

    /**
     * Retrieve data from age input text field
     * @return ageInput data
     */
    public String getAgeInput() {
        return ageInput.getText();
    }

    /**
     * Retrieve data from column input text field
     * @return columnInput data
     */
    public String getColumnInput() {
        return columnInput.getText();
    }

    /**
     * Retrieve data from drinks input text field
     * @return drinksInput data
     */
    public String getDrinksInput() {
        return drinksInput.getText();
    }

    /**
     * Retrieve data from vegetarian input text field
     * @return vegetarianInput data
     */
    public String getVegetarianInput() {
        return vegetarianInput.getText();
    }

    /**
     * Retrieve data from name input text field
     * @return nameInput data
     */
    public String getNameInput() {
        return nameInput.getText();
    }

    /**
     * Retrieve data from address input text field
     * @return addressInput data
     */
    public String getAddressInput() {
        return addressInput.getText();
    }

    /**
     * Retrieve data from food input text field
     * @return foodInput data
     */
    public String getFoodInput() {
        return foodInput.getText();
    }

    /**
     * Retrieve data from number input text field
     * @return numberInput data
     */
    public String getNumberInput() {
        return numberInput.getText();
    }

    /**
     * Retrieve data from edit flight airline input text field
     * @return editFlightAirlineInput data
     */
    public String getEditFlightAirlineInput() {
        return editFlightAirlineInput.getText();
    }

    /**
     * Retrieve data from edit flight etd input text field
     * @return editFlightEtdInput data
     */
    public String getEditFlightEtdInput() {
        return editFlightEtdInput.getText();
    }

    /**
     * Retrieve data from edit flight number input text field
     * @return editFlightNumberInput data
     */
    public String getEditFlightNumberInput() {
        return editFlightNumberInput.getText();
    }

    /**
     * Retrieve data from edit flight eta input text field
     * @return editFlightEtaInput data
     */
    public String getEditFlighEtaInput() {
        return editFlightEtaInput.getText();
    }

    /**
     * Retrieve data from edit flight desination input text field
     * @return editFlightDestinationInput data
     */
    public String getEditFlightDestinationInput() {
        return editFlightDestinationInput.getText();
    }

    /**
     * Retrieve data from remove flight text area label
     * @return removeFlightTextArea data
     */
    public String getRemoveFlightTextArea() {
        return removeFlightTextArea.getText();
    }

    /**
     * Retrieve data from destination input text field
     * @return addFlightDestinationInput data
     */
    public String getDestinationInput() {
        return addFlightDestinationInput.getText();
    }

    /**
     * Retrieve data from add flight etd input text field
     * @return addFlightEtdInput data
     */
    public String getAddFlightEtdInput() {
        return addFlightEtdInput.getText();
    }

    /**
     * Retrieve data from add flight airline input text field
     * @return addFlightAirlineInput data
     */
    public String getAddFlightAirlineInput() {
        return addFlightAirlineInput.getText();
    }

    /**
     * Retrieve data from add flight eta input text field
     * @return addFlightEtaInput data
     */
    public String getAddFlightEtaInput() {
        return addFlightEtaInput.getText();
    }

    /**
     * Retrieve data from number input remove flight text field
     * @retur numberInputRemoveFlight data
     */
    public String getNumberInputRemoveFlight() {
        return numberInputRemoveFlight.getText();
    }



}
