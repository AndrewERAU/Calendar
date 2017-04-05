package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import views.Add_Event_View;
import views.Event_View;

import javax.swing.JButton;


//import java.awt.FlowLayout;

//import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;


public class Home_View {

	private JFrame frame;
	private JPanel panel;
	private JButton addEventButton;
	//private Display_Add_Event_Screen addEventWindow;
	
	
	public Home_View() {

        initUI();
    }
	   
	public static void main (String[] args) {
		Home_View hs = new Home_View();
		//hs.setVisible(true);
	}
	

    private void initUI() {
    	
    	Calendar_View cal = new Calendar_View();
    	
    	
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
        	  new Add_Event_View();
          }
        });
        
        //frame.getContentPane().setLayout(new FlowLayout());
        panel.add(addEventButton);
        
        
        frame.setVisible(true);
    }
    

}
