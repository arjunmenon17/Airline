/**
 * [ICS4U] Airline Reservation | SeatingPlan.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

public class SeatingPlan {
    private Seat[][] seatingPlan;
    // private 2D array for seatingplan

    /**
     * Description:Constructor method for SeatingPlan class
     *
     * @param rows the number of rows in the seating plan
     * @param cols the number of columns in the seating plan
     */
    public SeatingPlan(int rows, int cols) {
        this.seatingPlan = new Seat[rows][cols];
    }

    /**
     * Description: getter method that gets the seating plan
     *
     * @return the seating plan
     */
    public Seat[][] getSeatingPlan() {
        return this.seatingPlan;
    }

    /**
     * Description: getter method that gets the specific seat
     *
     * @param row the index of the row for the seat
     * @param col the index of the column for the seat
     * @return the position of the seat
     */
    public Seat getSeat(int row, int col) {
        return this.seatingPlan[row][col];
    }

    /**
     * Description: Setter method that sets the seat
     *
     * @param seat object
     */
    public void setSeat(Seat seat) {
        this.seatingPlan[seat.getRow()][seat.getCol()] = seat;
    }

    /**
     * Description: checks if the seat is not occupied by a passenger
     *
     * @param row number/index of the seat that is checked
     * @param col number/column os the seat that is checked
     * @return a boolean value that corresponds to whether the seat is empty
     */
    public boolean isSeatEmpty(int row, int col) {
        return this.seatingPlan[row][col].passenger == null;
    }
}
