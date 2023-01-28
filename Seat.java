/**
 * [ICS4U] Airline Reservation | Seat.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;

public abstract class Seat {
    protected int row;
    protected int col;
    protected double basePrice;
    protected Passenger passenger;
    protected String[][] mealPlan;
    // instantiation of protected variables

    /**
     * Description: Constructor method for the Seat class
     * @param row index of the seat
     * @param col index of the column
     * @param basePrice of the seat
     * @param passenger the passenger that is assigned to the seat
     * @param mealPlan of the passenger assigned to the seat
     */
    protected Seat(int row, int col, double basePrice, Passenger passenger, String[][] mealPlan) {
        this.row = row;
        this.col = col;
        this.basePrice = basePrice;
        this.passenger = passenger;
        this.mealPlan = mealPlan;
    }

    /** Description: geter method that gets the index of the row
     * @return the index of the row
     */
    public int getRow() {
        return this.row;
    }

    /** Description: geter method that gets the index column
     * @return the index of the column
     */
    public int getCol() {
        return this.col;
    }

    /** Description: getter method that gets base price of the plane ticket
     * @return the base price
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    /** Description: Getter method that gets the passenger assigned to the seat
     * @return the passenger object assigned to the seat
     */
    public Passenger getPassenger() {
        return this.passenger;
    }


    /**Description: getter method that gets the mealplan of the passenger assigned to the seat
     * @return the mealplan of the passenger both food and drinks in 2D array
     */
    public String[][] getMealPlan() {
        return this.mealPlan;
    }

    /**Description: setter method that sets the row of the seat
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**Description: setter method that sets the col of the seat
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**Description: setter method that sets the base price of the seat
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**Description: setter method that sets the passeneger object for the seat
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**Description: setter method that sets the meal plan for the passenger of the seat
     */
    public void setMealPlan(String[][] mealPlan) {
        this.mealPlan = mealPlan;
    }

    /**
     * Description:abstract method that calculates the price of the seat
     * @return the price of the seat
     */
    public abstract double calcPrice();

    /**
     * Description: abstract method that returns the menu options in a 2D Arraylist(food,drinks)
     * @return 2D Arraylist with menu options
     */
    public abstract ArrayList<ArrayList<String>> calcMenuOptions();

}
