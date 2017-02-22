package display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


//import java.awt.FlowLayout;

//import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import display.Display_Event;
import display.Display_Add_Event_Screen;

public class Home_Screen {
	private JFrame frame;
	private JPanel panel;
	private JButton addEventButton;
	//private Display_Add_Event_Screen addEventWindow;
	
	public Home_Screen() {

        initUI();
    }
	   
	public static void main (String[] args) {
		Home_Screen hs = new Home_Screen();
		//hs.setVisible(true);
	}
	

    private void initUI() {
    	frame = new JFrame();
        frame.setTitle("Planner");
        frame.setSize(900, 600);     
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Show in middle of screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits app instead of just closing window
        
        panel = new JPanel();
        
        frame.add(panel);
        
        frame.getContentPane ().add (panel, BorderLayout.WEST);
        
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            // display new add event window
        	  new Display_Add_Event_Screen();
          }
        });
        
        //frame.getContentPane().setLayout(new FlowLayout());
        panel.add(addEventButton);
        
        
        frame.setVisible(true);
    }
    

}
