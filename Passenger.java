/**
 * [ICS4U] Airline Reservation | Passenger.java
 * Date: January 24th, 2021
 *
 * @author Arjun Menon, James Shappas, Apinash Sivaganesan, Leonardo Lai
 * Teacher: Mr. Ho
 */

public class Passenger {
    private String name;
    private String address;
    private int age;
    private String passportNumber;
    private Boolean isVegetarian;
    //private variable declarations

    /**
     * Description: Constructor method for Passenger class
     *
     * @param name           of the passenger that is seated
     * @param address        of the passenger that is seated
     * @param age            of the passenegr that is seated
     * @param passportNumber of the
     * @param isVegetarian   boolean value that corresponds to the vegetarian status of the passenger
     */
    public Passenger(String name, String address, int age, String passportNumber, boolean isVegetarian) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.passportNumber = passportNumber;
        this.isVegetarian = isVegetarian;
    }

    /**
     * Description:Getter method for the name of the passenger
     *
     * @return the name of the passenger
     */
    public String getName() {
        return this.name;
    }

    /**
     * Description: Getter method for the address of the passenger
     *
     * @return the address of the passenger
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Description:Getter method for the age of the passenger
     *
     * @return the age of the passenger
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Description: Getter method for the passport number of the passenger
     *
     * @return the passport number of the passenger
     */
    public String getPassportNumber() {
        return this.passportNumber;
    }

    /**
     * Description: getter method for the vegetarian status of the passenger
     *
     * @return boolean value that represents the vegetarian status of the passenger
     */
    public boolean getVegetarianStatus() {
        return this.isVegetarian;
    }

    /**
     * Description: Setter method for the name of the passenger
     *
     * @param name of the passenger that is to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description: Setter method for the address of the passenger
     *
     * @param address of the passenger that is to be set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Description: Setter method for the age  of the passenger
     *
     * @param age of the passenger that is to be set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Description: Setter method for the passportnumber of the passenger
     *
     * @param passportNumber of the passenger that is to be set
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * Description: Setter method for the vegetarian status of the passenger
     *
     * @param isVegetarian boolean value that represents the status of the passenger that is to be set
     */
    public void setVegetarianStatus(boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }
}
