package display;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Home_Screen extends JFrame {
	
	public Home_Screen() {

        initUI();
    }
	   
	public static void main (String[] args) {
		Home_Screen hs = new Home_Screen();
		hs.setVisible(true);
	}
	

	    private void initUI() {
	        setTitle("Planner");
	        setSize(900, 600);     
	        setResizable(false);
	        setLocationRelativeTo(null); // Show in middle of screen
	        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits app instead of just closing window
	    }

}
