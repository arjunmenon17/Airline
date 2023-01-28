/**
 * [ICS4U] Airline Reservation | PassengerView.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class PassengerView extends JFrame {

    private PassengerController controller;
    private Dimension screenSize;
    private String username;
    private String password;
    private JPanel homeContentPane, flightContentPane, purchaseTicketContentPane;
    private JLabel airportLbl, flightLbl, ticketLbl, askNameLbl, askAddressLbl, askAgeLbl, askPassportNumLbl;
    private JTextField nameInput, addressInput, ageInput, passportNumInput;
    private DefaultTableModel flightsTableModel, seatingTableModel;
    private JTable flights, seating;
    private JScrollPane fScrollbar, sScrollbar;
    private JButton btnBackToHome, btnBackToFlight, btnPurchaseSeat, btnCancelSeat, btnHelp, btnConfirmBuyTicket;
    private DefaultTableCellRenderer flightCellRenderer, seatCellRenderer;
    // instantiate  private variables incuding Panels, labels, Jtables, buttons, scrollpanes etc.

    /**
     * Description: Constructor method for PassengerView class
     * @param username the username passenger enters to access passenger view components
     * @param password the password passenger/user enters to acces passenger view components
     * @throws IOException
     */
    public PassengerView(String username, String password) throws IOException {
        this.controller = new PassengerController(this);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.username = username;
        this.password = password;

        this.homeContentPane = new JPanel();
        this.flightsTableModel = new DefaultTableModel();
        this.flights = new JTable(flightsTableModel);
        this.fScrollbar = new JScrollPane(flights);
        this.flightCellRenderer = new DefaultTableCellRenderer();
        this.airportLbl = new JLabel("Miami International Airport Flights (MIA)");

        createHomeView(true);

        this.flightContentPane = new JPanel();
        this.seatingTableModel = new DefaultTableModel();
        this.seating = new JTable(seatingTableModel);
        this.sScrollbar = new JScrollPane(seating);
        this.seatCellRenderer = new DefaultTableCellRenderer();
        this.flightLbl = new JLabel();
        this.btnPurchaseSeat = new JButton("Purchase Ticket");
        btnPurchaseSeat.addActionListener(controller.getPurchaseTicket());
        this.btnCancelSeat = new JButton("Cancel Ticket");
        btnCancelSeat.addActionListener(controller.getCancelTicket());
        this.btnHelp = new JButton("Help");
        btnHelp.addActionListener(controller.getHelp());
        this.btnBackToHome = new JButton("Back");
        btnBackToHome.addActionListener(controller.getBackToHome());

        this.purchaseTicketContentPane = new JPanel();
        this.ticketLbl = new JLabel("Purchase Ticket");
        this.askNameLbl = new JLabel("Enter your Full Name: ");
        this.nameInput = new JTextField();
        this.askAddressLbl = new JLabel("Enter your Address: ");
        this.addressInput = new JTextField();
        this.askAgeLbl = new JLabel("Enter your Age: ");
        this.ageInput = new JTextField();
        this.askPassportNumLbl = new JLabel("Enter your Passport #: ");
        this.passportNumInput = new JTextField();
        this.btnConfirmBuyTicket = new JButton("Confirm Purchase");
        btnConfirmBuyTicket.addActionListener(controller.getConfirmBuyTicket());
        this.btnBackToFlight = new JButton("Back");
        btnBackToFlight.addActionListener(controller.getBackToFlight());

    }

    /**
     * Description: Creates customer homeview panel
     * @param isFirstTime checks if its the first time creating the homeview
     * @throws IOException
     */
    public void createHomeView(boolean isFirstTime) throws IOException {
        setResizable(false);
        setTitle("Airline Reservation");
        setBounds((int) ((screenSize.getWidth() - 1024) / 2), (int) ((screenSize.getHeight() - 600) / 2), 1024, 600);
        setVisible(true);
        // the dimensions of the homeview panel and sets it to be visible
        homeContentPane.setBackground(SystemColor.activeCaption);
        homeContentPane.setForeground(SystemColor.activeCaption);
        homeContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        if (!isFirstTime) {
            getContentPane().remove(flightContentPane);
            flightsTableModel.setColumnCount(0);
            flightsTableModel.setRowCount(0);
        }
        // if it is not the first time opening the homeview panel then the previous pane is removed
        setContentPane(homeContentPane);
        homeContentPane.setLayout(null);

        airportLbl.setHorizontalAlignment(SwingConstants.CENTER);
        airportLbl.setFont(new Font("Tahoma", Font.BOLD, 26));
        airportLbl.setBounds(210, 5, 600, 100);
        homeContentPane.add(airportLbl);
        // dimensions and  font for label

        createFlightsTable();
        //invoke create flight table method
    }

    /**
     * Description: creates flight table that provides customer with the current flights available for purchase
     * @throws IOException
     */
    public void createFlightsTable() throws IOException {
        flights.setDefaultEditor(Object.class, null);
        flights.getTableHeader().setReorderingAllowed(false);
        flights.getTableHeader().setResizingAllowed(false);
        flights.setRowHeight(30);
        flights.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        flights.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flights.getTableHeader().setBackground(Color.BLACK);
        flights.getTableHeader().setForeground(Color.WHITE);
        flights.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
        // sets background colours and fonts for the table

        flights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flights.addMouseListener(controller.getFlightSelection());
        // allows customer to click the available options for flights

        flightsTableModel.addColumn("Airline");
        flightsTableModel.addColumn("Destination");
        flightsTableModel.addColumn("ETD");
        flightsTableModel.addColumn("ETA");
        // adds columns to the table

        flights.getColumnModel().getColumn(0).setPreferredWidth(200);
        flights.getColumnModel().getColumn(1).setPreferredWidth(477);
        flights.getColumnModel().getColumn(2).setPreferredWidth(85);
        flights.getColumnModel().getColumn(3).setPreferredWidth(85);
        // sets dimensions of the various columns

        flights.setBounds(75, 100, 850, 400);
        fScrollbar.setBounds(75, 100, 850, 400);
        // dimensions where the table is displayed

        homeContentPane.add(fScrollbar);
        controller.createFlightsList();

        flightCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        flightCellRenderer.setBackground(Color.black);
        flightCellRenderer.setForeground(Color.ORANGE);
        // sets the colours of the flight cells

        for (int i = 0; i < flightsTableModel.getColumnCount(); i++) {
            flights.getColumnModel().getColumn(i).setCellRenderer(flightCellRenderer);
        }
        // for loop that sets cell renderer to each column in the table


    }

    /**
     * Description: adds a new flight as a row in the table
     * @param row the data row that needs to be displayed in the table
     */
    public void addFlightRow(Object[] row) {
        flightsTableModel.addRow(new Object[]{row[0], row[1], row[2], row[3]});
    }

    /**
     * Description: Getter method that gets the flight table
     * @return the flight table
     */
    public JTable getFlightsTable() {
        return flights;
    }

    /**
     * Description: creates the view for the flight selected
     * @throws IOException
     */
    public void createFlightView() throws IOException {
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Flight Seating");
        setBounds((int) ((screenSize.getWidth() - 1024) / 2), (int) ((screenSize.getHeight() - 600) / 2), 875, 600);
        flightContentPane.setBackground(SystemColor.activeCaption);
        flightContentPane.setForeground(SystemColor.activeCaption);
        // sets the background colour and dimensions of the flight view selected

        getContentPane().remove(homeContentPane);
        getContentPane().remove(purchaseTicketContentPane);
        setContentPane(flightContentPane);
        // removes the home content pane and the purchase ticket conytent pane from view

        seatingTableModel.setRowCount(0);
        seatingTableModel.setColumnCount(0);
        flightContentPane.setLayout(null);

        flightLbl.setHorizontalAlignment(SwingConstants.CENTER);
        flightLbl.setFont(new Font("Tahoma", Font.BOLD, 26));
        flightLbl.setBounds(125, 5, 600, 100);
        flightLbl.setText(flights.getValueAt(controller.getFlightNumber(), 0) + " - " + flights.getValueAt(controller.getFlightNumber(), 1) + " Seating");
        flightContentPane.add(flightLbl);
        // sets the dimensions, and font of the label for the flight selected

        createSeatingTable();
        // invoke create seating table method

        btnPurchaseSeat.setBounds(615, 150, 200, 50);
        btnPurchaseSeat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightContentPane.add(btnPurchaseSeat);
        // dimensions and font for the purchase seat button

        btnCancelSeat.setBounds(615, 233, 200, 50);
        btnCancelSeat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightContentPane.add(btnCancelSeat);
        // dimensions and font for the cancel seat button

        btnHelp.setBounds(615, 315, 200, 50);
        btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightContentPane.add(btnHelp);
        // dimensions and font for the Help button

        btnBackToHome.setBounds(615, 400, 200, 50);
        btnBackToHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightContentPane.add(btnBackToHome);
        // dimensions and font for the back button
    }

    /**
     * Description: Creates the seating table that allows passenger to purchase a seat on the flight selected
     * @throws IOException
     */
    public void createSeatingTable() throws IOException {
        seating.setDefaultEditor(Object.class, null);
        seating.getTableHeader().setReorderingAllowed(false);
        seating.getTableHeader().setResizingAllowed(false);
        seating.setRowHeight(30);
        seating.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        seating.setFont(new Font("Tahoma", Font.PLAIN, 16));
        seating.getTableHeader().setBackground(Color.BLUE);
        seating.getTableHeader().setForeground(Color.WHITE);
        seating.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));

        seating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        seating.addMouseListener(controller.getSeatSelection());

        seating.setBounds(75, 100, 500, 400);
        sScrollbar.setBounds(75, 100, 500, 400);
        //sets the dimensions of the seating chart table

        flightContentPane.add(sScrollbar);
        controller.createSeatsList();
        // adds a scrollbar to the flight content pane

        seatCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        seatCellRenderer.setBackground(Color.black);
        seatCellRenderer.setForeground(Color.ORANGE);
        //centers and sets the colour for the cell renderers

        seating.getColumnModel().getColumn(0);

        for (int i = 0; i < seatingTableModel.getColumnCount(); i++) {
            seating.getColumnModel().getColumn(i).setCellRenderer(seatCellRenderer);
        }
    }
    // for loop that sets a celle renderer to each column in the table

    /**
     * Description: Adss the columns title in the form of letter coordinates
     * @param letterCoordinates which correspond to each column
     */
    public void addSeatingColumns(String[] letterCoordinates) {
        for (String letter : letterCoordinates) {
            seatingTableModel.addColumn(letter);
        }
    }

    /**
     * Description: Adds the title for each row in the table in the form of numbers
     * @param row number
     */
    public void addSeatingRow(Object[] row) {
        seatingTableModel.addRow(row);
    }

    /**
     * Description:Getter method that gets the seating table
     * @return the seating table
     */
    public JTable getSeatingTable() {
        return seating;
    }

    /**
     * Description: Creates the purchase ticket view for the passenger to buy a ticket on their desired flight
     * @param row index of the seat that passenegr selected to purchase
     * @param col index of the seat that passenger selected to purchase
     */
    public void createPurchaseTicketView(int row, int col) {
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Purchase Ticket");
        setBounds((int) ((screenSize.getWidth() - 1024) / 2), (int) ((screenSize.getHeight() - 600) / 2), 875, 600);
        purchaseTicketContentPane.setBackground(SystemColor.activeCaption);
        purchaseTicketContentPane.setForeground(SystemColor.activeCaption);
        //sets dimensions of the purchase ticket view pane

        getContentPane().remove(flightContentPane);
        setContentPane(purchaseTicketContentPane);
        // removes the flight content pane from view

        purchaseTicketContentPane.setLayout(null);

        ticketLbl.setHorizontalAlignment(SwingConstants.CENTER);
        ticketLbl.setFont(new Font("Tahoma", Font.BOLD, 26));
        ticketLbl.setBounds(125, 5, 600, 100);
        ticketLbl.setText("Purchase Seat (Row " + row + ", Col " + col + ")");
        // sets the dimensions, font and text of the label

        purchaseTicketContentPane.add(ticketLbl);

        askNameLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        askNameLbl.setBounds(100, 100, 500, 50);
        purchaseTicketContentPane.add(askNameLbl);
        //sets font and dimensions for the ask name label

        nameInput.setBounds(335, 115, 175, 25);
        purchaseTicketContentPane.add(nameInput);
        // sets dimensions for the text field for passengers name

        askAddressLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        askAddressLbl.setBounds(100, 160, 500, 50);
        purchaseTicketContentPane.add(askAddressLbl);
        //sets dimensions for label that asks for passengers address


        addressInput.setBounds(335, 175, 250, 25);
        purchaseTicketContentPane.add(addressInput);
        // sets dimensions for the text field for passengers name

        askAgeLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        askAgeLbl.setBounds(100, 220, 500, 50);
        purchaseTicketContentPane.add(askAgeLbl);
        // sets dimensions and font for label that asks for passengers age

        ageInput.setBounds(335, 235, 60, 25);
        purchaseTicketContentPane.add(ageInput);
        //sets dimension for textfield that asks for users age

        askPassportNumLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        askPassportNumLbl.setBounds(100, 280, 500, 50);
        purchaseTicketContentPane.add(askPassportNumLbl);
        // sets dimensions and font for label that asks for passengers passport number

        passportNumInput.setBounds(335, 295, 150, 25);
        purchaseTicketContentPane.add(passportNumInput);
        // sets dimensions for textfield that asks for passengers passport number

        btnConfirmBuyTicket.setBounds(215, 475, 200, 50);
        btnConfirmBuyTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
        purchaseTicketContentPane.add(btnConfirmBuyTicket);
        //sets dimensions and font for the confirm purchase button

        btnBackToFlight.setBounds(425, 475, 200, 50);
        btnBackToFlight.setFont(new Font("Tahoma", Font.PLAIN, 18));
        purchaseTicketContentPane.add(btnBackToFlight);
        //sets dimensions and font for the back to flight button
    }

    /**
     * Description:Getter method that retrieves the passengers name inputted
     * @return passengers name inputted
     */
    public JTextField getNameInput() {
        return this.nameInput;
    }

    /**
     * Description: Getter method that retrieves the passengers address inputted
     * @return passengers address inputted
     */
    public JTextField getAddressInput() {
        return this.addressInput;
    }

    /**
     * Description: Getter method that retrieves the passengers age inputted
     * @return passengers age inputted
     */
    public JTextField getAgeInput() {
        return this.ageInput;
    }

    /**
     * Description: Getter method that retrieves passengers passport number inputted
     * @return passengers passport number inputted
     */
    public JTextField getPassportNumInput() {
        return this.passportNumInput;
    }

    /**
     * Description: Getter method that retrieves passengers username
     * @return passengers username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Description:Getter method that retrieves passengers password
     * @return passengers password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Description: Shows popup message to customer
     * @param viewPane the view which the popup appears on
     * @param message the message that appears on the popup
     */
    public void showPopup(int viewPane, String message) {
        if (viewPane == 1) {
            JOptionPane.showMessageDialog(homeContentPane, message, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        // if the message needs to appear on the home pane the popup will appear there
        else if (viewPane == 2) {
            JOptionPane.showMessageDialog(flightContentPane, message, "Information Message", JOptionPane.INFORMATION_MESSAGE);
            // if the message needs to appear on the flight content pane the popup will appear there
        } else {
            JOptionPane.showMessageDialog(purchaseTicketContentPane, message, "Warning", JOptionPane.ERROR_MESSAGE);
            //otherwise it will popup on the purchase ticket pane
        }
    }
}
