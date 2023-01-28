/**
 * [ICS4U] Airline Reservation | First.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class First extends Seat {

    /**
     * Description: Constructor method for First class
     * @param row of the passenger seat
     * @param col colum of the passenger seat
     * @param basePrice initial price the of the passengers seat in first class
     * @param passenger object of passenger class of passenger in first class
     * @param mealPlan of the passenger in firstclass
     */
    public First(int row, int col, double basePrice, Passenger passenger, String[][] mealPlan) {
        super(row, col, basePrice, passenger, mealPlan);
    }

    /**
     * Description: Calculates thr price of the seats in first class with specific accomodations(ie. age)
     * @return the price of the ticket in first class
     */
    @Override
    public double calcPrice() {
        double finalPrice = this.basePrice * 1.682;

        if (this.passenger.getAge() < 4 || this.passenger.getAge() > 65) {
            finalPrice += 220;
        } else {
            finalPrice += 300;
        }
        // if the passenger is younger than 4 years old or older than 65 years old they get a discount otheriwse they
        //don't

        if (this.col == 0 || this.col == 4) {
            finalPrice += 50;
        }
        // if th column is a window seat or aisle seat an additional 50 dollars is charged
        return finalPrice;
        //returns final price of the seat
    }

    /**
     * Description: returns a 2D Arraylist of menu options for passengers seated in First class
     *@return the menuoptions for the passenger
     */
    @Override
    public ArrayList<ArrayList<String>> calcMenuOptions() {
        ArrayList<ArrayList<String>> menuOptions = new ArrayList<ArrayList<String>>();
        menuOptions.add(new ArrayList<String>(Arrays.asList("Bun", "Pretzel", "Fruit Piece")));
        menuOptions.add(new ArrayList<String>(Arrays.asList("Water", "Orange Juice", "Apple Juice")));
        // basic menu optios of appetizers and drinks that are provided to all passengers

        if (this.passenger.getVegetarianStatus()) {
            Collections.addAll(menuOptions.get(0), "Lentil Soup", "Sweet & Sour Soup", "Cream of Mushroom Soup",
                    "Pasta", "Salad", "Veggie Fried Rice", "Macaroni & Cheese", "Chickpea Salad Sandwich",
                    "Cheese Pizza");
            //displays vegetarian menu options that are exclusive to First class passengers that are vegetarian
        } else {
            Collections.addAll(menuOptions.get(0), "Chicken Soup", "Crab Soup", "Egg drop Soup", "Beef Wrap", "Hot Dog",
                    "Beef Fried Rice", "Chicken Fried Rice", "BLT", "Fish and Chips", "Caviar", "Curry Chicken",
                    "Garlic Shrimp", "Pepperoni Pizza");
            //  displays normal menu options that are exclusive to First class passengers
        }

        if (this.passenger.getAge() > 19) {
            Collections.addAll(menuOptions.get(1), "Heineken", "Budweiser", "Bud Light", "Vodka", "Whiskey",
                    "Champagne", "Rum", "Brandy", "Cocktail");
            // alcoholic options are displayed if passenger is older than 19. Exclusive alcoholic beverages to passengers
            //in First class are also provided
        }

        return menuOptions;
        // returns menu options
    }
}
