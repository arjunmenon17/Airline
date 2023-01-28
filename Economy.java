/**
 * [ICS4U] Airline Reservation | Economy.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Economy extends Seat {

    /**
     * Description: Constructor method for Economy class
     *
     * @param row       is the position of the passenger in terms of the row
     * @param col       is the position of the passenger in terms of the column
     * @param basePrice is the initial price for passengers seated in economy
     * @param passenger is the passenger thats assigned to the seat in economy
     * @param mealPlan  is a 2D string array that stores the passengers mealplan (food, beverages)
     */
    public Economy(int row, int col, double basePrice, Passenger passenger, String[][] mealPlan) {
        super(row, col, basePrice, passenger, mealPlan);
    }

    /**
     * Description: Calculates the price of the ticket for seats in Economy class
     *
     * @return finalprice of the ticket after all accomodations
     */
    @Override
    public double calcPrice() {
        double finalPrice = basePrice;
        // initial price of the ticket
        if (this.passenger.getAge() < 4 || this.passenger.getAge() > 65) {
            finalPrice += 220;
            // if the  passnger is less than 4 years old or older than 65 they get a discounted price
        } else {
            finalPrice += 300;
            //otherwise they get normal price
        }
        if (this.row < 15) {
            finalPrice += 23;
            // if the row is less than 15 thean the price is incremented by 23 dollars
        } else {
            finalPrice += 11;
            //otherwise it is increased by 11 dollars
        }
        return finalPrice;
        // return final pri
    }

    /**
     * Description: returns a 2D Arraylist(foods, drinks) of all the menu options for both normal passengers and
     * vegetarian passengers seated in Economy class
     */
    @Override
    public ArrayList<ArrayList<String>> calcMenuOptions() {
        ArrayList<ArrayList<String>> menuOptions = new ArrayList<ArrayList<String>>();
        menuOptions.add(new ArrayList<String>(Arrays.asList("Bun", "Pretzel", "Fruit Piece")));
        menuOptions.add(new ArrayList<String>(Arrays.asList("Water", "Orange Juice", "Apple Juice")));
        // adds appetizers and drinks to 2D Arraylist

        if (this.passenger.getVegetarianStatus()) {
            Collections.addAll(menuOptions.get(0), "Lentil Soup", "Pasta", "Salad");
            //if the passenger is vegetarian they are provided with strictly vegetarian options
        } else {
            Collections.addAll(menuOptions.get(0), "Chicken Soup", "Beef Wrap", "Hot Dog");
            // Otherwise they are provided with all options
        }

        if (this.passenger.getAge() > 19) {
            Collections.addAll(menuOptions.get(1), "Heineken", "Budweiser", "Bud Light");
            // if the passenger is older than 19, they are provided with the option to buy alcoholic beverages
        }

        return menuOptions;
        // return the menu options
    }
}
