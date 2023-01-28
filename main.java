
import java.awt.*;


public class main{
    // runs the MenuView 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable (){
            // to make it thread safe 
            public void run(){
                MenuView main = new MenuView();
            }
        });
    }
}
