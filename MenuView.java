import java.awt.*;
import javax.swing.*;


public class MenuView extends JFrame {

    private MenuController controller;
    private Dimension screenSize;
    private JPanel loginPane, createAccountPane;
    private JLabel lblLogin, lblUsername, lblPassword, lblCreateAccount;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton btnLogin, btnCreate, btnCreateAccount, btnBack;
    //declare all swing compenents in global scope for use in getter and setter methods

    public MenuView() {
        this.controller = new MenuController(this);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.loginPane = new JPanel();
        this.createAccountPane = new JPanel();
        this.lblLogin = new JLabel("MIA AIRPORT LOGIN");
        this.lblUsername = new JLabel();
        this.lblPassword = new JLabel();
        this.lblCreateAccount = new JLabel("CREATE ACCOUNT");
        this.usernameInput = new JTextField();
        this.passwordInput = new JPasswordField();

        this.btnLogin = new JButton("Login");
        btnLogin.addActionListener(controller.getLogin()); // clicking the login button runs the method from the controller
        this.btnCreate = new JButton("Create");
        btnCreate.addActionListener(controller.getCreate()); // clicking the create button runs the method from the controller
        this.btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.addActionListener(controller.getCreateAccount()); // clicking the create account button runs the method from the controller
        this.btnBack = new JButton("Back");
        btnBack.addActionListener(controller.getBackAction()); // clicking the back button runs the method from the controller

        createLoginView(); // the main menu, which is what the first thing the user sees upon running main
    }

    /**
     * Returns the username that customer entered in the texfield
     * @return username input
     */
    public JTextField getUsernameInput() {
        return this.usernameInput;
    }

    /**
     * Description: returns the password that customer entered in the texfield
     *
     * @return password input
     */
    public JPasswordField getPasswordInput() {
        return this.passwordInput;
    }

    /**
     * Opens a popup of the which shows a message of user's choice
     * @param isLoginPane, boolean value that shows whether it is the loginpane
     * @message, String that has the error message
     */
    public void showErrorPopup(boolean isLoginPane, String message) {
        if (isLoginPane) {
            JOptionPane.showMessageDialog(loginPane, message, "Warning", 0);
        } else {
            JOptionPane.showMessageDialog(createAccountPane, message, "Warning", 0);
        }
    }


    /**
     * When the admin or customer view is constructed, this menu is no longer needed; makes it invisible
     */
    public void closeView() {
        setVisible(false);
    }

    /**
     * This is the menu the user sees upon running
     */
    public void createLoginView() {
        getContentPane().remove(createAccountPane);
        setVisible(true);
        setResizable(false);
        setBounds((int) ((screenSize.getWidth() - 1024) / 2), (int) ((screenSize.getHeight() - 600) / 2), 550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Airline Reservation - Login");
        loginPane.setBackground(SystemColor.activeCaption);
        loginPane.setForeground(SystemColor.activeCaption);

        setContentPane(loginPane);
        loginPane.setLayout(null);

        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblLogin.setBounds(140, 65, 500, 50);
        loginPane.add(lblLogin);

        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(160, 170, 100, 23);
        lblUsername.setText("Username:");
        loginPane.add(lblUsername);

        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(160, 230, 100, 23);
        lblPassword.setText("Password:");
        loginPane.add(lblPassword);

        usernameInput.setBounds(255, 168, 100, 25);
        loginPane.add(usernameInput);

        passwordInput.setBounds(255, 228, 100, 25);
        loginPane.add(passwordInput);

        btnCreateAccount.setBounds(135, 315, 125, 40);
        loginPane.add(btnCreateAccount);

        btnLogin.setBounds(275, 315, 125, 40);
        loginPane.add(btnLogin);
    }


    /**
     * This is the menu the user sees when they click the create button on the main menu
     */
    public void createAccountView() {
        getContentPane().remove(loginPane);
        setResizable(false);
        setBounds((int) ((screenSize.getWidth() - 1024) / 2), (int) ((screenSize.getHeight() - 600) / 2), 550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Airline Reservation - Create Account");
        createAccountPane.setBackground(SystemColor.activeCaption);
        createAccountPane.setForeground(SystemColor.activeCaption);

        setContentPane(createAccountPane);
        createAccountPane.setLayout(null);

        setVisible(true);

        lblCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblCreateAccount.setBounds(160, 65, 500, 50);
        createAccountPane.add(lblCreateAccount);

        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(155, 170, 150, 23);
        lblUsername.setText("New Username:");
        createAccountPane.add(lblUsername);

        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(155, 230, 150, 23);
        lblPassword.setText("New Password:");
        createAccountPane.add(lblPassword);

        usernameInput.setBounds(280, 168, 100, 25);
        createAccountPane.add(usernameInput);

        passwordInput.setBounds(280, 228, 100, 25);
        createAccountPane.add(passwordInput);

        btnCreate.setBounds(135, 315, 125, 40);
        createAccountPane.add(btnCreate);

        btnBack.setBounds(275, 315, 125, 40);
        createAccountPane.add(btnBack);
    }
}
