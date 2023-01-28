import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MenuController {
    private MenuView menuGUI;
    private ActionListener loginAction, createAction, createAccountAction, backAction;
    // Instantiate the menu to run the non static getters and setters in this classes
    // Instantiate action listeners which are identical to the ones in the view class, allowing communication

    /** Constructor method
     * @param menuView object
     */
    public MenuController(MenuView menuView){
        this.menuGUI = menuView;
        this.loginAction = getLogin();
        this.createAction = getCreate();
        this.createAccountAction = getCreateAccount();
        this.backAction = getBackAction();

    }

    /** Reads from password text file and checks whether the password and username user enter is valid
     * Acheived by storing all username:password combinations and seeing if they match to the user input
     * @return true or false, true if the corresponding username and password match
     */
    public boolean checkValidAccount() throws IOException {
        Set<String> loginDetails = new HashSet<>();
        BufferedReader objReader;
        String strLine;
        boolean accountExists = false;

        objReader = new BufferedReader(new FileReader("passwords.txt"));

        while ((strLine = objReader.readLine()) != null) {
            loginDetails.add(strLine);
        }

        if (loginDetails.contains(menuGUI.getUsernameInput().getText() + ":" + menuGUI.getPasswordInput().getText())) {
            System.out.println();
            accountExists = true;
        }

        return accountExists;
    }

    /** ActionListener method for user to enter login information. If information is valid customer is able to login, otherwise eroor message popsup.
     */
    public ActionListener getLogin(){
        return e -> {
            try{
                if(checkValidAccount()){
                    menuGUI.closeView();
                    new PassengerView(menuGUI.getUsernameInput().getText(), menuGUI.getPasswordInput().getText());
                } else if(menuGUI.getUsernameInput().getText().equals("admin") && menuGUI.getPasswordInput().getText().equals("admin")){
                    menuGUI.closeView();
                    new AdminView();
                } else {
                    menuGUI.showErrorPopup(true, "Username/Password is not found");
                }
            } catch(IOException ioException){
                ioException.printStackTrace();
            }
        };
    }


    /** Description: ActionListener method to create an account. If the account does not exist then the usernmae and password are appended to the passsword text file.
     * Otherise an error message pops up
     */
    public ActionListener getCreate(){
        return e -> {
            try{
                boolean accountExists = checkValidAccount();
                if(!accountExists && !(menuGUI.getUsernameInput().getText().equals("admin")) && !(menuGUI.getPasswordInput().getText().equals("admin"))){
                    FileWriter file = new FileWriter("passwords.txt", true);
                    file.append("\n");
                    file.append(menuGUI.getUsernameInput().getText() + ":" + menuGUI.getPasswordInput().getText());
                    file.close();
                    menuGUI.showErrorPopup(false, "Account Created.");
                }
                else{
                    menuGUI.showErrorPopup(false, "That Username/Password already exists");
                }
            }catch(IOException ioException){
                ioException.printStackTrace();
            }
        };
    }

    /** Description: shifts the view from login view to create account view
     */
    public ActionListener getCreateAccount(){
        return e -> {
            try{
                menuGUI.createAccountView();
            }catch(Exception ioException){
                ioException.printStackTrace();
            }
        };
    }

    /**
     * Description: shifts the view from create account view to login view
     */
    public ActionListener getBackAction(){
        return e -> {
            try{
                menuGUI.createLoginView();
            }catch(Exception ioException){
                ioException.printStackTrace();
            }
        };
    }
}
