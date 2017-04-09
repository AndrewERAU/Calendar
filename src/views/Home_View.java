package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager; // to set look and feel
import javax.swing.border.LineBorder;

import views.Add_Event_View;
import views.Event_View;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;

//import java.awt.FlowLayout;

//import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


public class Home_View {

	private JFrame frame;
	//private JPanel panel;
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel calPanel;
	private JButton addEventButton;
	//private Display_Add_Event_Screen addEventWindow;
	
    final int LABEL_WIDTH = 25;
    final int LABEL_HEIGHT = 10;
	
	
	public Home_View() {
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		// exit application, log  or ignore exception
			System.out.println("setting look and feel failed.");
		}

        initUI();
    }
	   
	public static void main (String[] args) {
		Home_View hs = new Home_View();
		//hs.setVisible(true);
	}
	

    private void initUI() {
    	
    	//Calendar_View cal = new Calendar_View();
    	// TODO: Remove calendar code in other file
    	
    	
    	frame = new JFrame();
        frame.setTitle("Planner");
        frame.setSize(1000, 640); // width, height    
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Show in middle of screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits app instead of just closing window
        frame.getContentPane().setLayout(null);
        
        calPanel = new JPanel();
        calPanel.setLayout(new GridLayout(7,7,2,2)); // 7 rows, 7 cols, hgap = 2, vgap = 2
        calPanel.setLocation(10, 55);
        calPanel.setSize(700, 550);
        
        topPanel = new JPanel();
        //topPanel.setLayout(new FlowLayout());
        //topPanel.setBackground(Color.RED);
        topPanel.setBounds(85,10, calPanel.getWidth() - 85 - 85, 35); // location(x,y) size(width,height)
        topPanel.setLayout( new BorderLayout() );
        
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS)); //BoxLayout.PAGE_AXIS == top to bottom layout
        //rightPanel.setLayout(new GridBagLayout());
        rightPanel.setLocation(calPanel.getX() + calPanel.getWidth() + 15, 10);
        rightPanel.setSize(frame.getWidth() - rightPanel.getX() - 10, frame.getHeight() - rightPanel.getY() - 30);
        //rightPanel.setBackground(Color.RED);
        
        
        calPanel.setBackground(Color.decode("#167ac6"));
        
        frame.add(calPanel);
        frame.add(topPanel);
        frame.add(rightPanel);
        
        //panel.add(optionsPanel);
        //panel.add(calPanel);
      
        //frame.add(panel);
        
        //frame.getContentPane ().add (panel, BorderLayout.WEST);
        
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
        //panel.add(addEventButton); // TODO: uncomment this once calendar is done
        
        // Start calendar code -------------------------------------------------------------------------------------------------------------------

        // Adding elements to top panel
        createNextPrvMonthButton("<",BorderLayout.WEST);
        addMonthYearLabel();
        createNextPrvMonthButton(">",BorderLayout.EAST);
        
        // Adding elemennts to main calendar panel
        createDayOfWeekLabel("Sunday",Color.decode("#167ac6"));
        createDayOfWeekLabel("Monday",Color.decode("#167ac6"));
        createDayOfWeekLabel("Tuesday",Color.decode("#167ac6"));
        createDayOfWeekLabel("Wednesday",Color.decode("#167ac6"));
        createDayOfWeekLabel("Thursday",Color.decode("#167ac6"));
        createDayOfWeekLabel("Friday",Color.decode("#167ac6"));
        createDayOfWeekLabel("Saturday",Color.decode("#167ac6"));
              
        addWhiteBoxesUnderWeekLabels();
        
        // Adding elements to right panel
        addSettingsButton();
        rightPanel.add(Box.createRigidArea(new Dimension(0,10))); // add space
        addUpcommingRemindersScrollBox();
        rightPanel.add(Box.createRigidArea(new Dimension(0,10))); // add space
        addEventsTodayScrollBox();
        
        
        
        frame.setVisible(true);
    }
    
    private void createDayOfWeekLabel(String labelText, Color bg) {

        JLabel inLabel = new JLabel(labelText, SwingConstants.CENTER);
        
        inLabel.setFont(new Font("", Font.PLAIN, 16));
        
      //sundayLabel.setSize(new Dimension(50,50));
        inLabel.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        inLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        inLabel.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        inLabel.setForeground(Color.white); // text color
        inLabel.setBackground(bg);
        inLabel.setOpaque(true); // without this the background color is not shown
        
        calPanel.add(inLabel);
    }
    
    private void addWhiteBoxesUnderWeekLabels() {
    	// INFO: The "white boxes" are buttons that represent days of the month
    	
    	for (int i = 0; i < 42; i++) {
        	JButton button = new JButton(Integer.toString(i));
        	button.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            button.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            button.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            button.setBorder( new LineBorder(Color.white) );
            button.setBackground(Color.WHITE);
            button.setOpaque(true); // without this the background color is not shown
            
            // Perform action when button is pressed
            button.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                // display new add event window
            	  new Add_Event_View();
              }
            });
            
            // Got this from SO, but it looks like there is a better way (see comments on accepted answer)
            // http://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBorder( new LineBorder(Color.BLUE,2,false)); // color, thickness, rounded corners
                	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                	button.setBorder( new LineBorder(Color.white));
                }
            });
        	
        	calPanel.add(button);
    	}

    }
    
    private void createNextPrvMonthButton(String buttonText, String position) {
    	final int width = 70;
    	final int height = 30;

        JButton button = new JButton(buttonText);
        
        button.setFont(new Font("", Font.PLAIN, 16));
        
        //button.setSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setMinimumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setForeground(Color.BLACK); // text color
        button.setBackground(Color.WHITE);
        button.setOpaque(true); // without this the background color is not shown
        button.setBorder( new LineBorder(Color.BLACK,2,false)); // color, thickness, rounded corners
        
        // Got this from SO, but it looks like there is a better way (see comments on accepted answer)
        // http://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
/*
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	button.setBorder( new LineBorder(Color.BLACK,2,false)); // color, thickness, rounded corners
            }*/
        });
        
        topPanel.add(button, position);
    }
    
    private void addMonthYearLabel() {
    	final int width = 30;
    	final int height = 50;
    	JLabel label = new JLabel("April, 2017",SwingConstants.CENTER);
    	
    	label.setFont(new Font("", Font.PLAIN, 24));
         
        //sundayLabel.setSize(new Dimension(50,50));
        label.setMinimumSize(new Dimension(width, height));
        label.setPreferredSize(new Dimension(width, height));
        label.setMaximumSize(new Dimension(width, height));
	    //label.setForeground(Color.white); // text color
	    //label.setBackground(bg);
	    // label.setOpaque(true); // without this the background color is not shown
        
		
    	topPanel.add(label,BorderLayout.CENTER);
    }
    
    private void addSettingsButton() {
    	final int width = 80;
    	final int height = 40;
    	JButton button = new JButton("Settings");
    	
        button.setFont(new Font("", Font.PLAIN, 16));
        
        //button.setSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setMinimumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setForeground(Color.WHITE); // text color
        button.setBackground(Color.decode("#167ac6")); // TODO: replace this hex number everywhere with a const str called blueColor
        button.setOpaque(true); // without this the background color is not shown
        button.setBorder( new LineBorder(Color.decode("#167ac6"),2,false)); // color, thickness, rounded corners
        
        // Got this from SO, but it looks like there is a better way (see comments on accepted answer)
        // http://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
/*
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	button.setBorder( new LineBorder(Color.BLACK,2,false)); // color, thickness, rounded corners
            }*/
        });
        
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(button);  	
    }
    
    private void addUpcommingRemindersScrollBox() {
    	// Thanks for help with JTextArea SO (switched to JEditorPane tho)
    	// http://stackoverflow.com/questions/10213100/jscrollpane-words-wrap
JEditorPane label = new JEditorPane("text/html", "");
    	
    	label.setText("<b>Monday, April 6 - 5:30pm:<br></b>Time to workout!<br>"
    			+ "<br><b>Thursday, April 25 - 10:00am<br></b>Get some homework done.<br>");
        // make it look & act like a label
    	//label.setWrapStyleWord(true);
    	//label.setLineWrap(true);
    	label.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    	label.setFont((new JLabel()).getFont());
    	label.setEditable(false);
    	label.setFocusable(false);
    	label.setOpaque(false);
    	JScrollPane scrollPane = new JScrollPane(label,
    			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	// Label
    	JLabel ReminderBoxLabel = new JLabel("Upcomming Reminders:",SwingConstants.CENTER);
    	//ReminderBoxLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    	//label.setFont(new Font("", Font.PLAIN, 24));     
        //sundayLabel.setSize(new Dimension(50,50));
        //label.setMinimumSize(new Dimension(width, height));
        //label.setPreferredSize(new Dimension(width, height));
        //label.setMaximumSize(new Dimension(width, height));
	    //label.setForeground(Color.white); // text color
	    //label.setBackground(bg);
	    //label.setOpaque(true); // without this the background color is not shown
    	
    	ReminderBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	ReminderBoxLabel.setFont(new Font("", Font.PLAIN, 16));   
    	rightPanel.add(ReminderBoxLabel); // TODO: When I add this, the settings button above it gets shifted left
    	
    	rightPanel.add(scrollPane);
    }
    
    private void addEventsTodayScrollBox() {
    	// Thanks for help with JTextArea SO (switched to JEditorPane tho)
    	// http://stackoverflow.com/questions/10213100/jscrollpane-words-wrap
    	JEditorPane label = new JEditorPane("text/html", "");
    	
    	label.setText("<b>Monday, April 6. 9:00am - 12:30pm:<br></b>Biology class<br>"
    			+ "<br><b>Thursday, April 25. 5:00am - 7:00am:<br></b>Morning workout.<br>");        // make it look & act like a label
    	//label.setWrapStyleWord(true);
    	//label.setLineWrap(true);
    	label.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    	label.setFont((new JLabel()).getFont());
    	label.setEditable(false);
    	label.setFocusable(false);
    	label.setOpaque(false);
    	JScrollPane scrollPane = new JScrollPane(label,
    			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	JLabel EventsBoxLabel = new JLabel("Today's Events:");
    	EventsBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	EventsBoxLabel.setFont(new Font("", Font.PLAIN, 16)); 
    	rightPanel.add(EventsBoxLabel); // TODO: When I add this, the settings button above it gets shifted left 	
    	
    	rightPanel.add(scrollPane);
    }

    

}
