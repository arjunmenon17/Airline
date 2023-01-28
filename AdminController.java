import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.awt.event.*;
import java.io.IOException;

public class AdminController {

    private AdminView AdminGUI;
    // Instantiate the view to use it's getters and setters

    private ActionListener addFlightAction, removeAction, viewTakenSeatsAction,
    removeFlightAction, addPassengerAction, removePassengerAction,
    editFlightAction, confirmAddFlightAction, editAction, backToHomeAction, backToFlightsAction,
    confirmRemoveAction, addAction, viewPassengerAction;
    // Instantiate all actionlisteners for use in constructor and methods
    // Warnings might show as not used, but are needed for actionlistening in the view

    public AdminController(AdminView adminView) {
        // instantiate all actionlisteners which connect to the view
        this.AdminGUI = adminView;
        this.addFlightAction = getAddFlight();
        this.removeFlightAction = getRemove();
        this.addPassengerAction = getAddPassenger();
        this.removePassengerAction = getRemovePassenger();
        this.editFlightAction = getEditFlight();
        this.confirmAddFlightAction = getConfirmAddFlight();
        this.editAction = getEdit();
        this.backToHomeAction = getBackToHome();
        this.confirmRemoveAction = getConfirmRemove();
        this.addAction = getAdd();
        this.viewPassengerAction = getViewPassenger();
    }

    /**
     * Processes behavior for when the add flight is clicked
     * @return user action
     */
    public ActionListener getAddFlight() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI.addFlightView(); // add flight gui
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the edit button is clicked
     * @return user action
     */
    public ActionListener getEdit() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // edit button backend behavior

                if (!isInteger(AdminGUI.getEditFlightNumberInput())) { // user entered a string
                    AdminGUI.errorMsg();
                } else if (Integer.parseInt(AdminGUI.getEditFlightNumberInput()) > Integer.parseInt(updateScrollPane()[1])) { // user entered a number which is larger than the amount of flights
                    AdminGUI.errorMsg();
                } else {
                  
                    try {
                        // removes the line that holds the old flight info
                        removeNthLine("flights.txt", Integer.parseInt(AdminGUI.getEditFlightNumberInput()) - 1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                    addFlight(AdminGUI.getEditFlightDestinationInput(), AdminGUI.getEditFlightEtdInput(), AdminGUI.getEditFlightAirlineInput(), AdminGUI.getEditFlightEtdInput());
                    // adds the new flight info

                
                    if (renameFiles("-PassengerInfo.txt")) { // renames passenger file with new information

                        renameFiles("-Seating.txt"); // renames seating file with new information
                      

                    }
                   
                    try {
                        AdminGUI.createHomeView();
                        } catch (Exception x) {
                            x.printStackTrace();
                    }
                }
            }
        };
    }

    /**
     * Processes behavior for when the edit flight button is clicked
     * @return user action
     */
    public ActionListener getEditFlight() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI.editFlightView(); // edit flight gui
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the back button is clicked
     * @return user action
     */
    public ActionListener getBackToHome() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI.createHomeView(); // goes back to main menu back from 1 layer deep
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the confirm button is clicked
     * @return user action
     */
    public ActionListener getConfirmRemove() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check if the user input exceeds the amount of lines in the file
                // if the user enters an input greater than the amount of lines, null will be appended.
                // as a result, only continue if they do not do that
                if (!(Integer.parseInt(AdminGUI.getNumberInputRemoveFlight()) > Integer.parseInt(updateScrollPane()[1]))) {
                    try {
                        getRemoveFlight(AdminGUI.getNumberInputRemoveFlight()); // remove flight processing
                        AdminGUI.createHomeView();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    AdminGUI.errorMsg();
                }
            }
        };
    }

    /**
     * Processes behavior for when the remove button is clicked
     * @return user action
     */
    public ActionListener getRemove() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI.removeFlightView(); // remove flight menu 
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the add passenger button is clicked
     * @return user action
     */
    public ActionListener getAddPassenger() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AdminGUI.addPassengerView(); // add passenger menu gui
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the view button is clicked
     * @return user action
     */
    public ActionListener getViewPassenger() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI.viewPassengerView(); // view passenger gui
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the remove passenger button is clicked
     * @return user action
     */
    public ActionListener getRemovePassenger() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminGUI.removePassengerView(); // remove passenger gui
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Behavior for remove passenger confirm button 
     * @return user action
     */
    public ActionListener getRemovePassengerConfirm() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getRemovePassenger(AdminGUI.getNameInput(), AdminGUI.getNumberInput()); // confirm button processing
                    AdminGUI.createHomeView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the add button is clicked
     * @return user action
     */
    public ActionListener getAdd() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (getAddPassenger(AdminGUI.getNameInput(), AdminGUI.getNumberInput(), AdminGUI.getRowInput(), AdminGUI.getColumnInput(),
                            AdminGUI.getAddressInput(), AdminGUI.getVegetarianInput(), AdminGUI.getFoodInput(), AdminGUI.getDrinksInput(), AdminGUI.getPassportInput(), AdminGUI.getAgeInput())) {
                            // attempt to add a passenger to the user chosen file based on the fields they filled out
                            AdminGUI.createHomeView();
                    } else {
                        // validation error
                        AdminGUI.errorMsg();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    /**
     * Processes behavior for when the confirm add flight button is clicked
     * @return user action
     */
    public ActionListener getConfirmAddFlight() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (addFlight(AdminGUI.getDestinationInput(), AdminGUI.getAddFlightEtdInput(), AdminGUI.getAddFlightAirlineInput(), AdminGUI.getAddFlightEtaInput())) {
                        // attempt to add a flight to the user chosen file based on the fields they filled out
                        AdminGUI.createHomeView();
                    } else { // validation error
                        AdminGUI.errorMsg();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }


    /**
     * Puts all flights into a string at index 0, and puts the total lines in index 1
     * For use in scrollpanes and validation
     * @return an array containing the total lines of flights.txt in index 1 and the scrollpane content in index 0
     */
    public String[] updateScrollPane() {
        BufferedReader reader;
        int linesNum = 1;
        String[] text = {
            "",
            ""
        }; // the array to be returned

        try {
            reader = new BufferedReader(new FileReader("flights.txt"));
            String line = reader.readLine();

            while (line != null) {
                String arr[] = line.split(">");
                if (line.length() > 0) { // append the flight information into the text area box, as one big string.
                    text[0] = text[0] + ("Flight Number: " + linesNum + "<br>Airline: " + arr[0] +
                        "<br>Destination: " + arr[1] + "<br>Arrival Time: " + arr[2] + "<br>Departure Time " + arr[3] + "<br>-------------------------<br>");
                    // append each flights info into string at index 0

                } else {
                    // dont account for blank lines in line calculation, so cancel out operation below
                    linesNum--;
                }
                linesNum++; // if there was a blank line, this cancels it out
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        text[1] = Integer.toString(linesNum); // total lines in the flights file

        return text;

    }


    /**
     *  Finds the filename denoting to the number variable, and then appends a string
     * (with html formatting included) for every line of that file.
     * @param number the number of the flight that is displayed on the panel in view class, used to find the file with it's data
     * @return the text to be put on the scrollpane
     */
    public String updateScrollPanePassengers(String number) {
        String text = "";

        BufferedReader reader2;

        try {
            reader2 = new BufferedReader(new FileReader(findFileName(number))); // the file here is the file containing the data from flight n
            String line = reader2.readLine();

            int linesNumber = 1;
            while (line != null) {
                String arr[] = line.split(":");
                if (line.length() > 0) { //append the flight information into the text area box, as one big string.
                    text = text + ("Passenger Number: " + linesNumber + "<br>Row: " + arr[0] + "<br>Column: " + arr[1] +
                        "<br>Name: " + arr[2] + "<br>-------------------------<br>");
                } // appends passport number, row, column, and name onto the string
                linesNumber++;

                line = reader2.readLine();
            }
            reader2.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            AdminGUI.errorMsg();
        }
        return text;
    }

    /**
     * Given the line in the flights file, this method will find the flight at that location.
     * Reads the file line by line until the breakpoint at the line variable.
     * @param line the line where the flight is
     * @return the flight at line n in the flights file
     */
    public String flightAtN(int line) {

        String stringToFind = "";
        try {
            BufferedReader reader3 = new BufferedReader(new FileReader("flights.txt"));

            stringToFind = reader3.readLine();

            for (int i = 0; i < line - 1; i++) {
                stringToFind = reader3.readLine();
            } // stops reading at the location of the string at line n
            reader3.close();

        } catch (Exception ei) {
            ei.printStackTrace();
        }
        return stringToFind;

    }

    /**
     *  Searches for and returns the string at line 'line' in 'fileName'
     * @param line the line to be searched to
     * @param fileName file to be searched
     * @return the string at line n in the file
     */
    public String seatsAtN(int line, String fileName) {

        String stringToFind = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            stringToFind = reader.readLine();

            for (int i = 0; i < line - 1; i++) {
                stringToFind = reader.readLine();
            } // stops reading at the location of the string at line n
            reader.close();

        } catch (Exception ei) {
            ei.printStackTrace();
        }
        return stringToFind;
    }


    /**
     * When a flight is edited, all of its attributes such as the airline and destination can be changed. 
     * Each file has thoseattributes in the the name. Therefore,
     * when those attributes change so does the file name. Renames old file to it's new name in place.
     * @param type either the seating or passenger file can be renamed
     * @return if the rename was successful
     */
    public boolean renameFiles(String type) {

        boolean success;

        File file = new File(flightAtN(Integer.parseInt(AdminGUI.getEditFlightNumberInput())).split(">")[0] +
            "-" + flightAtN(Integer.parseInt(AdminGUI.getEditFlightNumberInput())).split(">")[1] + type);

        // this goes to line n (denoted my user) in the flights file and gets the flight selected. 
        // By splitting, we can retreive the airline and destination of the old flight which is how the file name is formatted.

        success = file.renameTo(new File(AdminGUI.getEditFlightAirlineInput() + "-" + AdminGUI.getEditFlightDestinationInput() + type));
        // rename the first file with old information to the name of the new file with updated information
        // the first file contains the name of the old file with now old information
        // the second file contains the name of the new file which reflects the new information via getters

        return success;






    }

    /**
     * When selecting flights, the user will be given a list of them in the order they appear in the flights.txt file.
     * The flight number they see will actual be the line in the flights.txt file where that flight is.
     * This goes to that line and returns the info, and formats it appropiately.
     * @param field the flight (line number) to be searched
     * @return the name of the file for the flight at line n
     */
    public String findFileName(String field) {
        String flightID = ""; // string to be returned
        BufferedReader reader2;
        int numOfLines = 1;
        try {
            reader2 = new BufferedReader(new FileReader("flights.txt"));
            String line = reader2.readLine();
            while (line != null) {
                if (numOfLines == Integer.parseInt(field)) {
                    String splitLine[] = line.split(">"); // seperate data
                    flightID = splitLine[0] + "-" + splitLine[1] + "-PassengerInfo.txt";
                    // the file name is the airline and destination, and since they appear first theyre index 0 and 1 respectively
                }
                numOfLines++;
                line = reader2.readLine();
            }
            reader2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return flightID;
    }

    /**
     * When removing passengers or flights, it is imporant that the action reflects in the database.
     * As a result, here we get the file name and search until line n, and then write everything 
     * excepted for the line to be removed to a temp file, and then rename the temp file
     * @param f the file to be searched
     * @param toRemove line n to be removed
     * @throws IOException file not found
     */
    public void removeNthLine(String f, int toRemove) throws IOException {

        File tmp = File.createTempFile("tmp", ""); // temporary file
        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

        for (int i = 0; i < toRemove; i++) {
            //copies file exactly up to the line to remove, into a temporary file
            bw.write(String.format("%s%n", br.readLine()));
        }

        br.readLine(); // skips over the line to be removed, but doesn't write it to anything
        String l;

        while (null != (l = br.readLine())) {
            bw.write(l); // since the line to be removed was skipped, the rest of the file can be written
            bw.write("\n");
        }

        br.close();
        bw.close();

        File oldFile = new File(f);
        if (oldFile.delete()) tmp.renameTo(oldFile); // when the file containing old data is removed, the temp file with the new data be renamed to the original file name
    }

    /**
     * Since we use loops and indexes when modifitying files, the user input needs to be converted to integers.
     * This checks if that is possible, as without this check there would be an error
     * @param input
     * @return
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false; // if the string cannot be parsed that means it is not a number
        }
    }

    /**
     * Modifying the file sometimes has the consequence of leaving white space;
     * this opens the user selected file, and formats it with no white space
     * @param fileName name of file to be cleaned
     */
    public void trimWhiteSpace(String fileName) {
        String inputFileName = fileName;
        String outputFileName = "temp.txt"; // file won't be edited in place, so changes will be in temp file

        // here, we are trimming off the blank lines in the file, left behind from add flight
        try (BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName)); PrintWriter outputFile = new PrintWriter(new FileWriter(outputFileName))) { // the input file is the flight, and outputs to a temp file
            String lineOfText; // line in file
            while ((lineOfText = inputFile.readLine()) != null) { // runs until eof
                lineOfText = lineOfText.trim();
                if (!lineOfText.isEmpty()) {
                    outputFile.println(lineOfText); // puts all nonempty lines (aka not white space) into the temp file
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // delete the temp file and replace with actual file
        File f = new File(fileName);
        f.delete(); // deletes original file which is unformatted

        File file = new File("temp.txt");
        file.renameTo(new File(fileName)); // opens temp file which reflects new changes, and renames to original file to give the effect of in place editing
    }


    /**
     * Takes in the flight number (ie. line number of target string), and searches for the string at that line
     * @param flightNum the flight number we are looking for
     * @return formatted string which is the name of the passenger info file of the chosen flight
     */
    public String searchForFlight(String flightNum) {
        BufferedReader reader;
        int numOfLines = 1;
        try {
            reader = new BufferedReader(new FileReader("flights.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (numOfLines == Integer.parseInt(flightNum)) {
                    String splitLine[] = line.split(">");
                    return splitLine[0] + "-" + splitLine[1] + "-PassengerInfo.txt";
                    // this is a formatted string containing the airline at index 0 and destination at index 1
                }
                numOfLines++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return ""; 
    }

    /**
     *  Validates user input and then adds the user input to the flights.txt file formatted, and 
     * then creates a seat map with all open seats
     * @param destination the destination
     * @param etd estimated time of departure
     * @param airline the airline
     * @param eta estimated time of arrival
     * @return boolean wether or not the operation was done, false means there was a validation error
     */
    public boolean addFlight(String destination, String etd, String airline, String eta) {

        if ((eta.length() == 0) || (!isInteger(eta))) { // validation
            return false;
        } else if (destination.length() == 0) {
            return false;
        } else if ((etd.length() == 0) || (!isInteger(etd))) {
            return false;
        } else if (airline.length() == 0) {
            return false;
        } else if (Integer.parseInt(eta) > 2399 || Integer.parseInt(eta) < 0) { // time is out of range of 24h format
            return false;
        } else if (Integer.parseInt(etd) > 2399 || Integer.parseInt(etd) < 0) { // time is out of range of 24h format
            return false;
        } else if (Integer.parseInt(etd) > Integer.parseInt(eta)) { // departute time can't be after arrival time
            return false;
        } else {

            try {

                char[] etdDigits = etd.toCharArray();
                char[] etaDigits = eta.toCharArray(); // so we can seperate index 1 and 2 with semicolon

                FileWriter file = new FileWriter("flights.txt", true);
                file.append("\n");
                file.append(airline + ">" + destination + ">" + (etdDigits[0]) + (etdDigits[1]) + ":" + (etdDigits[2]) + (etdDigits[3]) + ">" + (etaDigits[0]) + (etaDigits[1]) + ":" + (etaDigits[2]) + (etaDigits[3]));
                file.close();// append flight info 

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            File file = new File(airline + "-" + destination + "-Seating.txt"); //initialize File object and passing path as argument  
            File file2 = new File(airline + "-" + destination + "-PassengerInfo.txt"); //initialize File object and passing path as argument 

            try {
                file.createNewFile(); //creates seating and passenger files
                file2.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                FileWriter fileSeating = new FileWriter(file); // seating file

                // creating the seating plan file, by setting all seats to empty and formatting
                for (int i = 1; i < 31; i++) {
                    if (i == 1) {
                        fileSeating.append("OOO OOO"); // top of file, so no new line
                    } else if (i <= 6 || (i > 7 && i < 30)) {
                        fileSeating.append("\n");
                        fileSeating.append("OOO OOO");
                    } else if (i == 7) {
                        fileSeating.append("\n");  // line 7 is where the class divider would be, so dont put any seats here
                    } else {
                        fileSeating.append("\n");
                        fileSeating.append("*-*-*-*"); // bottom of file, so put divided
                    }
                }
                fileSeating.close();

            } catch (Exception ee) {
                // it is a possibility that we will reach here with file not found ex... 
                ee.printStackTrace();
            }


            return true;
        }
    }

    /**
     * Removes the flight with the flight number of the users choice from the flights.txt file
     * @param flightNumber flight number to be removed
     */
    public void getRemoveFlight(String flightNumber) {

        if (!isInteger(flightNumber)) { // if a string is entered which cannot be converted, the program will crash. prevents that
            AdminGUI.errorMsg();
        } else {

            File oldPassenger = new File (findFileName(flightNumber));
            oldPassenger.delete();

            File oldSeating = new File (findFileName(flightNumber).replace("-PassengerInfo.txt", "-Seating.txt"));
            oldSeating.delete();

            trimWhiteSpace("flights.txt");
            // add flight has the side effect of adding extra white space lines. this method removes any lines which are blank.

            try {
                removeNthLine("flights.txt", Integer.parseInt(flightNumber) - 1);
                // removes the line in the file retetaining to the selection

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            



        }
    }

    /**
     * Finds the flight retaining to the users desired flight with the flight number.
     * Grabs all row:column combinations in the passenger info file, and compares it to the row
     * and column input. If there is a match, that row:column combination is already set in the file
     * and therefore the seat is taken
     * @param row user's desired row
     * @param column user's desired row
     * @param flightNum flight number to be adding to
     * @return boolean if the row:column combination is inside the file of flightNum
     */
    public boolean isSeatTaken(String row, String column, String flightNum) {

        String file = "";
        BufferedReader reader2;
        int numOfLines = 1;
        try {
            reader2 = new BufferedReader(new FileReader("flights.txt"));
            String line = reader2.readLine();
            int flight = Integer.parseInt(flightNum);
            while (line != null) {
                if (numOfLines == flight) { // when were at the intended line
                    String splitLine[] = line.split(">");
                    file = splitLine[0] + "-" + splitLine[1] + "-PassengerInfo.txt"; // passenger info file for the flight
                }
                numOfLines++;
                line = reader2.readLine();
            }
            reader2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Set < String > values = new HashSet < String > ();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if (line.split(":").length > 1) { // check that there is a passenger here
                    values.add(line.split(":")[0] + line.split(":")[1]); // saves the seat information of each passengerinto the set
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (values.contains(row + ":" + column)) { // if the user input matches one of the combinations from a different passenger, that seat is taken
            return true;
        }
        return false;
    }

    /**
     * Takes in the user input from the text fields, and validates. Then, creates the passenger 
     * and seating information files, appends the customer's information to the flight, updates the row to the now taken seat,
     * and appends the new seat to the file in place. 
     * @param name passenger name
     * @param flightNum line number in flights.txt which we will be using
     * @param row seat row
     * @param column seat column
     * @param address passenger address
     * @param vegetarian passenger vegetarian status
     * @param food passenger food
     * @param drinks passenger drinks
     * @returns true with all operations done if valid input, false if not.
     */
    public boolean getAddPassenger(String name, String flightNum, String row, String column, String address,
        String vegetarian, String food, String drinks, String passportNumber, String age) {

        if (name.length() == 0 || drinks.length() == 0 || flightNum.length() == 0) { // validation
            return false;
        } else if (!(vegetarian.equals("true") || vegetarian.equals("false"))) {
            return false;
        } else if (!isInteger(row) || !isInteger(column)) {
            return false;
        } else if (drinks.split(",").length == 0 || food.split(",").length == 0) {
            return false;
        } else if (Integer.parseInt(column) > 6) {
            return false;
        } else if (isSeatTaken(row, column, flightNum)) {
            return false;
        } else {

            String flightIDPassenger = "";
            String flightIDSeating = "";

            BufferedReader reader2;
            int numOfLines = 1;
            try {
                reader2 = new BufferedReader(new FileReader("flights.txt"));
                String line = reader2.readLine();
                while (line != null) {

                    if (numOfLines == Integer.parseInt(flightNum)) {
                        String splitLine[] = line.split(">");
                        flightIDPassenger = splitLine[0] + "-" + splitLine[1] + "-PassengerInfo.txt"; // the passenger info file name for the flight
                        flightIDSeating = splitLine[0] + "-" + splitLine[1] + "-Seating.txt"; // the seating info file name for the flight
                    }
                    numOfLines++;
                    line = reader2.readLine();
                }
                reader2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter(flightIDPassenger, true);
                file.append("\n"); // append the customer's information at the bottom of the passenger info for that flight
                file.append(row + ":" + column + ":" + name + ":" + address + ":" + age + ":" + passportNumber + ":" + vegetarian + ":" + food + ":" + drinks);
                file.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String rowOfSeats = seatsAtN(Integer.parseInt(row), flightIDSeating); // saves the old seats from the row before adding

            char[] seats = rowOfSeats.toCharArray();

            // marks the seat as taken in the chosen column and row
            if (Integer.parseInt(column) > 3) {
                seats[Integer.parseInt(column)] = 'X'; // this is to account for the space in the middle of the aisle for walking
            } else {
                seats[Integer.parseInt(column) - 1] = 'X'; 
            }

            String newSeats = new String(seats); // represents the updated seats

            Path path = Paths.get(flightIDSeating); // the path to the seating file

            try {
                List < String > fileContent = new ArrayList < > (Files.readAllLines(path, StandardCharsets.UTF_8)); // saves the file's content in the array list
                for (int i = 0; i < fileContent.size(); i++) { // searches line by line until the old seats are found
                    if (fileContent.get(i).equals(rowOfSeats)) {
                        fileContent.set(i, newSeats); // when the old row is found at i, replace it with the new row
                        break;
                    }
                }
                Files.write(path, fileContent, StandardCharsets.UTF_8);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Searches for the customer's name that is passed through and removes their data 
     * from passanger file and sets their seat from closed to open
     * @param nameToRemove string of passenger being removed
     * @param flightNum line number (flight number) where the customer is located
     */
    public void getRemovePassenger(String nameToRemove, String flightNum) {

        String flightID = ""; // the file name of the passenger info database
        String flightIDSeats = ""; // the file name of the seating database 
        BufferedReader reader;
        int numOfLines = 1;
        try {
            reader = new BufferedReader(new FileReader("flights.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (numOfLines == Integer.parseInt(flightNum)) { // if the input is correct, the name they selected will be on line n (flightnum)

                    String splitLine[] = line.split(">"); // puts flight information in array

                    flightID = splitLine[0] + "-" + splitLine[1] + "-PassengerInfo.txt"; // set the name of the passenger file with the airline and destination for the flight number
                    flightIDSeats = splitLine[0] + "-" + splitLine[1] + "-Seating.txt"; // set the name of the seats file with the airline and destination for the flight number

                }
                numOfLines++; // updates to the line of the file we're on, until it mataches with the user's parameter
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace(); // file not found
        }

        String lineFound = ""; // holds the line of the passenger to be removed's info

        try {
            Scanner scan = new Scanner(new File(flightID));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] dataAtN = line.split(":"); // passengers information

                if (dataAtN[2].equals(nameToRemove)) {
                    lineFound = line; // the passengers info
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

        String[] seatPosition = {};
        try {

            File temp = new File("temp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp)); // writing to temp file
            String removeID = lineFound; // this is the line number of the file
            String currentLine;

            BufferedReader br = new BufferedReader(new FileReader(new File(flightID)));

            while ((currentLine = br.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(removeID)) {

                    seatPosition = trimmedLine.split(":"); // save the passengers info
                    currentLine = ""; // set the target line to blank

                }
                bw.write(currentLine + System.getProperty("line.separator")); // output the current line to file
            }

            bw.close();
            br.close();

            OutputStream os = new FileOutputStream(flightID); // file output to the passenger file
            Files.copy(Paths.get("temp.txt"), os); // copying contents of temp file to original file, and delete temp file
            os.close();
            temp.delete();
        } catch (Exception w) {
            w.printStackTrace();
        }

        String rowOfSeats = seatsAtN(Integer.parseInt(AdminGUI.getNumberInput()), flightIDSeats); // saves the old seats from the row before adding

        char[] seats = rowOfSeats.toCharArray(); // to be able to mutate any letter in the string

        if (Integer.parseInt(seatPosition[1]) > 3) { // index 1 is the column
            seats[Integer.parseInt(seatPosition[1])] = 'O'; // this is to account for the space in the middle of the aisle for walking
        } else {
            seats[Integer.parseInt(seatPosition[1]) - 1] = 'O';
        }

        String newSeats = new String(seats); // represents the updated seats

        Path path = Paths.get(flightIDSeats); // the path to the seating file

        try {
            List < String > fileContent = new ArrayList < > (Files.readAllLines(path, StandardCharsets.UTF_8)); // saves the file's content in the array list
            for (int i = 0; i < fileContent.size(); i++) { // searches line by line until the old seats are found
                if (fileContent.get(i).equals(rowOfSeats)) {
                    fileContent.set(i, newSeats); // when the old row is found at i, replace it with the new row
                    break;
                }
            }
            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

}
