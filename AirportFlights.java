/**
 * [ICS4U] Airline Reservation | AirportFlights.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;
// import java arraylist package

public class AirportFlights {
    private ArrayList<Flight> flightsList;

    /**
     * Description: Constructor method
     */
    public AirportFlights() {
        this.flightsList = new ArrayList<Flight>();
    }

    /**
     * Getter method for the list of flights in an Arraylist
     * @return the list of flights
     */
    public ArrayList<Flight> getFlightsList() {
        return this.flightsList;
    }

    /**
     * Description:Getter method that returns the flight at a certain index
     * @param row takes the index of the flight that is wanted
     * @return the flight that is wanted
     */
    public Flight getFlight(int row) {
        return this.flightsList.get(row);
    }

    /**
     * Description: adds a new flight to the list of flights
     * @param flight added flight to the list
     */
    public void addFlight(Flight flight) {
        this.flightsList.add(flight);
    }

    /**
     * Description: removes a flight from the list of flights
     * @param row index/row of the flight that is to be removed
     */
    public void removeFlight(int row) {
        this.flightsList.remove(row - 1);
    }
}
