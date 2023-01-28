/**
 * [ICS4U] Airline Reservation | Flight.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import java.io.*;

public class Flight {
    private String airline;
    private String destination;
    private String etd;
    private String eta;
    private String seatingTxt;
    private String passengerInfoTxt;
    private SeatingPlan seatingChart;
    //instantiation of private variables

    /**
     * Description: Constructor method for Flight class
     * @param airline is the airline that is providing the flight (ie. WestJet, Emirates etc.)
     * @param destination is the destination of the flight
     * @param etd the estimated departure time of the flight
     * @param eta estimated arrival time of the flight
     * @param seatingTxt name of the seating plan text file
     * @param passengerInfoTxt name of the passenger information text file
     * @throws IOException
     */
    public Flight(String airline, String destination, String etd, String eta, String seatingTxt, String passengerInfoTxt) throws IOException {
        this.airline = airline;
        this.destination = destination;
        this.etd = etd;
        this.eta = eta;
        this.seatingTxt = seatingTxt;
        this.passengerInfoTxt = passengerInfoTxt;
        this.seatingChart = initializeSeatingPlan();
        fillSeatingPlan(100.00);
    }

    /**
     * Description: Getter method for the airline
     * @return the airline that is providing the flight ina a String
     */
    public String getAirline() {
        return this.airline;
    }

    /**
     * Description: Getter method for the destination of the flight
     * @return the destination of the flight
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Description: Getter method for the estimated departure time
     * @return the estimated departure time
     */
    public String getEtd() {
        return this.etd;
    }

    /**
     * Description: Getter method for the estimated arrival time
     * @return the estimated arrival time
     */
    public String getEta() {
        return this.eta;
    }

    /**
     * Description: Getter method for the name of the seating plan text file
     * @return the name of the seating plan text file
     */
    public SeatingPlan getSeatingChart() {
        return this.seatingChart;
    }

    /**
     * Description: Setter method for the name of the airline
     * @param airline the airline that is to be set
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Description: Setter method for the destination
     * @param destination the destination that is to be set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Description: Setter method for the estimated deparature time
     * @param etd the estimated departure time that is to be set
     */
    public void setEtd(String etd) {
        this.etd = etd;
    }

    /**
     * Description: Setter method for the estimated arrival time
     * @param eta the estimated arrival time that is to be set
     */
    public void setEta(String eta) {
        this.eta = eta;
    }

    /**
     * Description: Reads from and finds the dimensions of the seating plan
     * @return the seating plan object
     * @throws IOException
     */
    public SeatingPlan initializeSeatingPlan() throws IOException {
        BufferedReader objReader;
        int row = 0;
        int col;
        // variable and buffered reader instantiations

        objReader = new BufferedReader(new FileReader(new File(this.seatingTxt)));
        // Buffered reader object that reads from seating plan textfile

        col = objReader.readLine().length() - 1;
        while (!(objReader.readLine().equals("*-*-*-*"))) {
            row++;
        }
        //reads every line from the text file until it reaches the end of the seating plan
        return new SeatingPlan(row, col);
        //returns seating plan object
    }

    /**
     * Description: finds the location of the walking aisle that splits the two sections of seating
     * @return the index of the aisle
     * @throws IOException
     */
    public int findAisleLocation() throws IOException {
        BufferedReader objReader;
        int col;
        int aisleIndex = 0;
        String sampleLine;
        // variable declarations

        objReader = new BufferedReader(new FileReader(new File(this.seatingTxt)));
        //Buffered reader object

        sampleLine = objReader.readLine();
        col = sampleLine.length();

        for (int i = 0; i < col; i++) {
            if (sampleLine.charAt(i) == ' ') {
                aisleIndex = i;
            }
        }
        //iterates through every character in the first line of the seating plan and finds the index of the open space/aisle

        return aisleIndex;
        //returns the index of the aisle
    }

    /**
     * Description: finds the location that splits the First class from the economy class and returns the index
     * @return the row/index of the split between the cabins or First class and Economy class
     * @throws IOException
     */
    public int findCabinSplitLocation() throws IOException {
        BufferedReader objReader;
        int splitIndex = 0;
        //variable declarations

        objReader = new BufferedReader(new FileReader(new File(this.seatingTxt)));
        // BufferedReader object

        while (!(objReader.readLine()).equals("")) {
            splitIndex++;
        }
        // iterates through every lien in the textfile until there is an empty line which indicates the split

        return splitIndex;
        // returns index of the split location
    }

    /**
     * Description: Fills the Seating plan of the flight and creates objects of the first or economy class and
     * @param basePrice is the initial price of the seat
     * @throws IOException
     */
    public void fillSeatingPlan(double basePrice) throws IOException {
        BufferedReader objReaderSeating;
        BufferedReader objReaderPassenger;
        String seatStrCurrentLine;
        String passStrCurrentLine;
        String[] passInfo;

        int row = 0;
        int col = 0;
        boolean isFirstClass = true;
        //variable,array and buffered reader object initializations


        objReaderSeating = new BufferedReader(new FileReader(new File(this.seatingTxt)));
        objReaderPassenger = new BufferedReader(new FileReader(new File(this.passengerInfoTxt)));
        // create buffered readers objects to read from the seating plan text file

        while (!(seatStrCurrentLine = objReaderSeating.readLine()).equals("*-*-*-*")) {
            // while looops that runs until the endi of the seating plan textfile
            if (seatStrCurrentLine.equals("")) {
                isFirstClass = false;
                //if the the buffered readers reaches an empty line that indicates the seats are no longer in first class
            } else {
                for (char seat : seatStrCurrentLine.toCharArray()) {
                    if (seat == 'O') {
                        if (isFirstClass) {
                            this.seatingChart.setSeat(new First(row, col, basePrice, null, null));
                            // creates a first class seat object for each open seat in first class
                        } else {
                            this.seatingChart.setSeat(new Economy(row, col, basePrice, null, null));
                            //creates an economy class object for every open seat in economy
                        }
                    } else if (seat == 'X') {
                        // for every occupied seat, checks passenegr info file for information at corresponding cell location
                        while ((passStrCurrentLine = objReaderPassenger.readLine()) != null) {
                            passInfo = passStrCurrentLine.split(":");
                            if (passInfo[0].equals(String.valueOf(row + 1)) && passInfo[1].equals(String.valueOf(col + 1))) {
                                String[][] mealInfo = {passInfo[7].split(","), passInfo[8].split(",")};
                                if (isFirstClass) {
                                    this.seatingChart.setSeat(new First(row, col, basePrice, new Passenger(passInfo[2], passInfo[3], Integer.parseInt(passInfo[4]), passInfo[5], Boolean.parseBoolean(passInfo[6])), mealInfo));
                                } else {
                                    this.seatingChart.setSeat(new Economy(row, col, basePrice, new Passenger(passInfo[2], passInfo[3], Integer.parseInt(passInfo[4]), passInfo[5], Boolean.parseBoolean(passInfo[6])), mealInfo));
                                }
                                //creates an object of the first class or economy class(depends on seat location) with corresponding passenger information
                                break;
                            }
                        }
                    } else {
                        col--;
                        //decreases column number
                    }
                    col++;
                    //increments column number
                }
                col = 0;
                //sets column number back to 0
                row++;
                //increments row number
            }
        }
    }
}
