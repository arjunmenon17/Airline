/**
 * [ICS4U] Airline Reservation | PassengerController.java
 * Date: January 24th, 2022
 *
 * @author Arjun Menon, Apinash Sivaganeshan, James Shapas, Leonardo Lai
 * Teacher: Mr. Ho
 */

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class PassengerController {
    private final PassengerView passengerGUI;
    private final AirportFlights airportFlights;
    private final MouseListener flightSelectionAction;
    private Flight selectedFlight;
    private final ActionListener purchaseTicketAction, confirmTicketBuyAction, cancelTicketAction, helpAction, backHomeAction, backToFlightAction;
    private Boolean cellClicked, actionBtnClicked;
    private int flightNumber;
    private final Set<String> purchaseHistory;

    /**
     * Constructor method for PassengerController that initializes all ActionListener objects
     * @param passengerView PassengerGUI instance
     */
    public PassengerController(PassengerView passengerView) {
        this.passengerGUI = passengerView;
        this.airportFlights = new AirportFlights();
        this.cellClicked = false;
        this.actionBtnClicked = false;
        this.flightSelectionAction = getFlightSelection();
        this.backHomeAction = getBackToHome();
        this.backToFlightAction = getBackToFlight();
        this.purchaseTicketAction = getPurchaseTicket();
        this.confirmTicketBuyAction = getConfirmBuyTicket();
        this.purchaseHistory = new HashSet<>();
        this.cancelTicketAction = getCancelTicket();
        this.helpAction = getHelp();
        this.flightNumber = 0;
    }

    /**
     * This getter method returns the index of the flight selected by the user
     * @return the index of the flight selected
     */
    public int getFlightNumber() {
        return this.flightNumber;
    }

    /**
     * This method determines the actual row index of the selected seat in the Jtable
     * @return Actual integer row index
     * @throws IOException
     */
    public int getSeatRow() throws IOException {
        int row = passengerGUI.getSeatingTable().getSelectedRow();
        if (row > selectedFlight.findCabinSplitLocation()) {
            row--;
        }

        return row;
    }

    /**
     * This method determines the actual column index of the selected seat in the Jtable
     * @return Actual integer column index
     * @throws IOException
     */
    public int getSeatCol() throws IOException {
        int col = passengerGUI.getSeatingTable().getSelectedColumn() - 1;
        if (col > selectedFlight.findAisleLocation()) {
            col--;
        }

        return col;
    }

    /**
     * This method reads from a text file and updates the corresponding object classes accordingly
     * @param txtFile String value of the text file to be read
     * @throws IOException
     */
    public void importFromTxt(String txtFile) throws IOException {
        BufferedReader objReader;
        String strCurrentLine;

        String[] flightInfo;
        String flightSeatingTxt;
        String flightPassInfoTxt;

        String[] previouslyPurchased;
        String[] seatsOnFlight;

        if (txtFile.equals("flights")) {
            objReader = new BufferedReader(new FileReader(new File("flights.txt")));

            // Clears the current flights arrayList
            airportFlights.getFlightsList().clear();
            while ((strCurrentLine = objReader.readLine()) != null) {
                flightInfo = strCurrentLine.split(">");
                flightSeatingTxt = flightInfo[0] + "-" + flightInfo[1] + "-Seating.txt";
                flightPassInfoTxt = flightInfo[0] + "-" + flightInfo[1] + "-PassengerInfo.txt";

                //Adds new flight to the flights list is airportFlights
                airportFlights.addFlight(new Flight(flightInfo[0], flightInfo[1], flightInfo[2], flightInfo[3], flightSeatingTxt, flightPassInfoTxt));
            }
        } else if (txtFile.equals("seating")) {
            // Calls fillSeatingPlan to fill the seatingPlan array
            selectedFlight.fillSeatingPlan(100);
        } else {
            objReader = new BufferedReader(new FileReader(new File("purchaseHistory.txt")));
            while ((strCurrentLine = objReader.readLine()) != null) {
                previouslyPurchased = strCurrentLine.split(":");

                // Checks if a customer has made any previous ticket purchases
                if (previouslyPurchased.length > 2) {

                    // Creates array of all purchases made for a specific flight
                    for (int i = 2; i < previouslyPurchased.length; i++) {
                        seatsOnFlight = previouslyPurchased[i].split(">");

                        // Checks if the purchases made are on the currently selected flight
                        if ((selectedFlight.getAirline() + "-" + selectedFlight.getDestination()).equals(seatsOnFlight[0])) {
                            for (int j = 1; j < seatsOnFlight.length; j++) {
                                purchaseHistory.add(seatsOnFlight[j]);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method uses the objects in the program to write to and update the text files
     * @param txtFile String of the file to be wrote to
     * @param row row of data that was changed
     * @param col column of data that was changed
     * @param append boolean that dictates whether
     * @throws IOException
     */
    public void exportToTxt(String txtFile, int row, int col, Boolean append) throws IOException {
        FileWriter fileWriter;
        File file;
        BufferedReader objReader;
        String strNewLine = "";
        String strCurrentLine;
        List<String> rows;
        Passenger newPassenger;
        boolean aisleFound = false;
        int counter = 0;

        if (txtFile.equals("seating")) {
            fileWriter = new FileWriter(selectedFlight.getAirline() + "-" + selectedFlight.getDestination() + "-Seating.txt", true);
            file = new File(selectedFlight.getAirline() + "-" + selectedFlight.getDestination() + "-Seating.txt");

            // Determines the new row after the customer has purchased a seat
            for (int j = 0; j < selectedFlight.getSeatingChart().getSeatingPlan()[0].length; j++) {
                if (selectedFlight.findAisleLocation() == j && !aisleFound) {
                    strNewLine += " ";
                    j--;
                    aisleFound = true;
                } else if (selectedFlight.getSeatingChart().getSeat(row, j).passenger == null) {
                    strNewLine += "O";
                } else {
                    strNewLine += "X";
                }
            }

            // Replaces the old row with the new row in the file
            rows = Files.readAllLines(file.toPath());
            rows.set(row, strNewLine);
            Files.write(file.toPath(), rows);


            fileWriter = new FileWriter(selectedFlight.getAirline() + "-" + selectedFlight.getDestination() + "-PassengerInfo.txt", true);

            // If method call requires new passenger information to be appended in the PassengerInfo file, this method is run
            if (append) {
                newPassenger = selectedFlight.getSeatingChart().getSeat(row, col).passenger;
                strNewLine = (row + 1) + ":" + (col + 1) + ":" + newPassenger.getName() + ":" + newPassenger.getAddress() + ":" + newPassenger.getAge() + ":" + newPassenger.getPassportNumber() + ":false:Meal1,Meal2,Meal3:Drink1,Drink2";

                fileWriter.append("\n");
                fileWriter.append(strNewLine);
            }
            fileWriter.close();

        } else {
            fileWriter = new FileWriter("purchaseHistory.txt", true);
            file = new File("purchaseHistory.txt");

            objReader = new BufferedReader(new FileReader(new File("purchaseHistory.txt")));
            strNewLine = passengerGUI.getUsername() + ":" + passengerGUI.getPassword();

            while ((strCurrentLine = objReader.readLine()) != null) {
                // Checks if the user's login details match the line in the purchaseHistory file
                if (strCurrentLine.split(":")[0].equals(passengerGUI.getUsername()) && strCurrentLine.split(":")[1].equals(passengerGUI.getPassword())) {
                    //Checks if any purchases have been made by the user
                    if (purchaseHistory.size() > 0) {
                        strNewLine += ":" + selectedFlight.getAirline() + "-" + selectedFlight.getDestination();
                        // Adds each purchase the user has made to the new line to be added to the the text file
                        for (String purchasedTicket : purchaseHistory) {
                            strNewLine += ">" + purchasedTicket;
                        }
                    }
                    break;
                }
                counter++;
            }

            // Replaces the old row with the new row in the file
            rows = Files.readAllLines(file.toPath());
            rows.set(counter, strNewLine);
            Files.write(file.toPath(), rows);

        }
    }

    /**
     * Provides the GUI with an array of data that represents the information for each flight
     * @throws IOException
     */
    public void createFlightsList() throws IOException {
        Object[] row = new Object[4];
        Flight flight;

        importFromTxt("flights");

        // Calls GUI method to add flight row to JTable for each flight
        for (int i = 0; i < airportFlights.getFlightsList().size(); i++) {
            flight = airportFlights.getFlight(i);
            row[0] = flight.getAirline();
            row[1] = flight.getDestination();
            row[2] = flight.getEtd();
            row[3] = flight.getEta();
            passengerGUI.addFlightRow(row);
        }
    }

    /**
     * Provides the GUI with an array of data that represents the information for the seating plan on a specific flight
     * @throws IOException
     */
    public void createSeatsList() throws IOException {
        Object[] rowArray;
        ArrayList<String> row = new ArrayList<String>();
        int columns;
        int rows;
        String[] letterCoordinates;
        String alphabet = " ABCDEFGHIJKLMNO";
        boolean aislePassed = false;
        boolean cabinSplitPassed = false;

        importFromTxt("seating");
        importFromTxt("purchases");

        rows = selectedFlight.getSeatingChart().getSeatingPlan().length;
        columns = selectedFlight.getSeatingChart().getSeatingPlan()[0].length;

        // Provides letter labels for the columns
        letterCoordinates = new String[columns + 2];
        for (int i = 0; i < columns + 2; i++) {
            if (i == selectedFlight.findAisleLocation() + 1) {
                letterCoordinates[i] = " ";
                aislePassed = true;
            } else {
                if (aislePassed) {
                    letterCoordinates[i] = String.valueOf(alphabet.charAt(i - 1));
                } else {
                    letterCoordinates[i] = String.valueOf(alphabet.charAt(i));
                }
            }
        }
        passengerGUI.addSeatingColumns(letterCoordinates);
        aislePassed = false;

        for (int i = 0; i < rows; i++) {
            // Reduces the row number after the blank line that signifies the difference between the two cabins has been crossed
            if (i == selectedFlight.findCabinSplitLocation() && !cabinSplitPassed) {
                row.add(null);
                cabinSplitPassed = true;
                i--;
            } else {
                //Determines the data row that should be displayed in the seating chart GUI
                row.add(String.valueOf(i + 1));
                for (int j = 0; j < columns; j++) {
                    if (j == selectedFlight.findAisleLocation() && !aislePassed) {
                        row.add(" ");
                        aislePassed = true;
                        j--;
                    } else if (!(selectedFlight.getSeatingChart().isSeatEmpty(i, j)) && purchaseHistory.contains(i + "," + j)) {
                        row.add("P");
                    } else if (selectedFlight.getSeatingChart().isSeatEmpty(i, j)) {
                        row.add("O");
                    } else {
                        row.add("X");
                    }
                }
            }
            rowArray = row.toArray();
            passengerGUI.addSeatingRow(rowArray);
            aislePassed = false;
            row.clear();
        }
    }

    /**
     * Creates the back to home button that re-creates the home view
     * @return ActionListener object for the back to home button button
     */
    public ActionListener getBackToHome() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    passengerGUI.createHomeView(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Creates the back to flight button that re-creates the flight seating plan view
     * @return ActionListener object for the back to flight seating plan view button
     */
    public ActionListener getBackToFlight() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    passengerGUI.createFlightView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Determines which flight is selected and creates its flight view (the seating plan)
     * @return ActionListener object for the get flight selection button
     */
    public MouseListener getFlightSelection() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = passengerGUI.getFlightsTable().getSelectedRow();
                if (row != -1) {
                    selectedFlight = airportFlights.getFlight(row);
                    try {
                        flightNumber = row;
                        passengerGUI.createFlightView();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        };
    }

    /**
     * Determines which seat is selected and checks whether any other function buttons have also been selected
     * @return ActionListener object for the get seat selection button
     */
    public MouseListener getSeatSelection() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    String cellValue;
                    if (getSeatRow() != selectedFlight.findCabinSplitLocation() && getSeatCol() != selectedFlight.findAisleLocation() && passengerGUI.getSeatingTable().getSelectedColumn() != 0) {
                        cellValue = (String) passengerGUI.getSeatingTable().getValueAt(passengerGUI.getSeatingTable().getSelectedRow(), passengerGUI.getSeatingTable().getSelectedColumn());

                        // Ensures the seat selected is not "X"
                        if (cellValue.equals("O") || cellValue.equals("P")) {
                            cellClicked = true;
                            if (actionBtnClicked) {
                                cellClicked = false;
                                actionBtnClicked = false;

                                // Creates the purchase ticket view if purchase button is selected and the seat is empty
                                if (cellValue.equals("O")) {
                                    passengerGUI.createPurchaseTicketView(getSeatRow() + 1, getSeatCol() + 1);
                                }
                            }
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
    }

    /**
     * Creates the purchase ticket view if a cell is also clicked
     * @return ActionListener object for the purchase ticket button
     */
    public ActionListener getPurchaseTicket() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cellValue;
                    cellValue = (String) passengerGUI.getSeatingTable().getValueAt(passengerGUI.getSeatingTable().getSelectedRow(), passengerGUI.getSeatingTable().getSelectedColumn());

                    if (cellValue != null) {
                        // Checks if the cell is empty and can be purchased
                        if (cellValue.equals("O")) {
                            actionBtnClicked = true;
                            if (cellClicked) {
                                actionBtnClicked = false;
                                cellClicked = false;

                                // Creates the purchase ticket view
                                passengerGUI.createPurchaseTicketView(getSeatRow() + 1, getSeatCol() + 1);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * This method verifies if they have entered valid data and processes the purchase
     * @return ActionListener object for the confirm purchase button
     */
    public ActionListener getConfirmBuyTicket() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Passenger newPassenger;

                    importFromTxt("seating");
                    importFromTxt("purchases");

                    // Checks if the user has entered valid data in text entry fields
                    if (passengerGUI.getNameInput().getText() != null && passengerGUI.getAddressInput().getText() != null && passengerGUI.getAgeInput().getText() != null && passengerGUI.getPassportNumInput().getText() != null) {
                        if (Integer.parseInt(passengerGUI.getAgeInput().getText()) > 0 && Integer.parseInt(passengerGUI.getAgeInput().getText()) < 130) {
                            newPassenger = new Passenger(passengerGUI.getNameInput().getText(), passengerGUI.getAddressInput().getText(), Integer.parseInt(passengerGUI.getAgeInput().getText()), passengerGUI.getPassportNumInput().getText(), false);

                            // Adds a seat with the given passenger (and its information) to the seating plan object
                            if (getSeatRow() > selectedFlight.findCabinSplitLocation()) {
                                selectedFlight.getSeatingChart().setSeat(new Economy(getSeatRow(), getSeatCol(), 100, newPassenger, null));
                            } else {
                                selectedFlight.getSeatingChart().setSeat(new First(getSeatRow(), getSeatCol(), 100, newPassenger, null));
                            }
                            purchaseHistory.add(getSeatRow() + "," + getSeatCol());
                        } else {
                            passengerGUI.showPopup(3, "The age you have entered is not a valid age");
                        }

                        exportToTxt("seating", getSeatRow(), getSeatCol(), true);
                        exportToTxt("purchases", -1, -1, null);

                        passengerGUI.showPopup(3, "Your ticket has been purchased. The price was: $" + selectedFlight.getSeatingChart().getSeat(getSeatRow(), getSeatCol()).calcPrice() + ".");
                        passengerGUI.createFlightView();
                    } else {
                        passengerGUI.showPopup(3, "Please make sure all entry fields are filled");
                    }


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (NumberFormatException numberFormatException) {
                    passengerGUI.showPopup(3, "The age you have entered is not a valid integer");
                }
            }
        };
    }

    /**
     * This method processes the cancellation of a previously purchased ticket
     * @return ActionListener object for the cancel ticket button
     */
    public ActionListener getCancelTicket() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cellValue;
                    cellValue = (String) passengerGUI.getSeatingTable().getValueAt(passengerGUI.getSeatingTable().getSelectedRow(), passengerGUI.getSeatingTable().getSelectedColumn());

                    if (cellValue != null) {
                        // Checks whether the cell selected represents an already purchased seat
                        if (cellValue.equals("P")) {
                            actionBtnClicked = true;
                            if (cellClicked) {
                                actionBtnClicked = false;
                                cellClicked = false;

                                importFromTxt("seating");
                                importFromTxt("purchases");

                                // Removes the passenger from the selected seat and set the passenger attribute for the seat to null
                                if (getSeatRow() > selectedFlight.findCabinSplitLocation()) {
                                    selectedFlight.getSeatingChart().setSeat(new Economy(getSeatRow(), getSeatCol(), 100, null, null));
                                } else {
                                    selectedFlight.getSeatingChart().setSeat(new First(getSeatRow(), getSeatCol(), 100, null, null));
                                }

                                // Removes the seat to be cancelled from the purchase history hashset
                                purchaseHistory.remove(getSeatRow() + "," + getSeatCol());

                                System.out.println(purchaseHistory.size());

                                exportToTxt("purchases", -1, -1, null);
                                exportToTxt("seating", getSeatRow(), getSeatCol(), false);

                                passengerGUI.showPopup(3, "You have cancelled your reservation of this seat.");
                                passengerGUI.createFlightView();
                            }
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
    }

    /**
     * This method creates the text to be displayed in the help button popup
     * @return ActionListener object for the get help button
     */
    public ActionListener getHelp() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passengerGUI.showPopup(2, "To cancel or purchase a ticket, select on any empty cell in the seating plan" + '\n' +
                        "and the button for the action you would like to perform. If you would like cancel a" + '\n' +
                        "ticket, ensure the cell is marked with a P, which indicates it is a seat you have already" + '\n' +
                        "purchased. Seats above the blank horizontal row represent First class seats, while" + '\n' +
                        "those below it represent Economy class seats.");
            }
        };
    }
}
