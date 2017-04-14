package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager; // to set look and feel
import javax.swing.border.LineBorder;

import database.DatabaseMgr;
import event.Event;
import reminder.ReminderObj;
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
import java.util.List;
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

import time.*;


public class Home_View {

	private JFrame frame;
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel calPanel;
	private JButton addEventButton;
	//private Display_Add_Event_Screen addEventWindow;
	
    private final int LABEL_WIDTH = 25;
    private final int LABEL_HEIGHT = 10;
    
    private final String THEME_BLUE = "#167ac6";
    //private final String JFRAME_BACKGROUND = ;
	
	
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
        calPanel.setBackground(Color.decode(THEME_BLUE));
        
        topPanel = new JPanel();
        //topPanel.setLayout(new FlowLayout());
        //topPanel.setBackground(Color.RED);
        topPanel.setBounds(85,10, calPanel.getWidth() - 85 - 85, 35); // location(x,y) size(width,height)
        topPanel.setLayout( new BorderLayout() );
        
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS)); //BoxLayout.PAGE_AXIS == top to bottom layout
        rightPanel.setLocation(calPanel.getX() + calPanel.getWidth() + 15, 10);
        rightPanel.setSize(frame.getWidth() - rightPanel.getX() - 10, frame.getHeight() - rightPanel.getY() - 30);
        //rightPanel.setBackground(Color.RED);              
        
        frame.add(calPanel);
        frame.add(topPanel);
        frame.add(rightPanel);      
        
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
        createDayOfWeekLabel("Sunday",Color.decode(THEME_BLUE));
        createDayOfWeekLabel("Monday",Color.decode(THEME_BLUE));
        createDayOfWeekLabel("Tuesday",Color.decode(THEME_BLUE));
        createDayOfWeekLabel("Wednesday",Color.decode(THEME_BLUE));
        createDayOfWeekLabel("Thursday",Color.decode(THEME_BLUE));
        createDayOfWeekLabel("Friday",Color.decode(THEME_BLUE));
        createDayOfWeekLabel("Saturday",Color.decode(THEME_BLUE));
              
        addWhiteBoxesUnderWeekLabels();
        
        // Adding elements to right panel
        addSettingsButton();
        rightPanel.add(Box.createRigidArea(new Dimension(0,10))); // add space
       	displayRemindersForNext7Days(); // displays this addUpcommingRemindersScrollBox();
        rightPanel.add(Box.createRigidArea(new Dimension(0,10))); // add space
        displayEventsForToday();  //displays this addEventsTodayScrollBox();
        
        frame.setVisible(true);
    }
    
    private void createDayOfWeekLabel(String labelText, Color bg) {

        JLabel inLabel = new JLabel(labelText, SwingConstants.CENTER);
        
        inLabel.setFont(new Font("", Font.PLAIN, 16));
        
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
    	int initialDrawingPosition =  Time.getFirstDay();
    	int numberOfDaysInMonth = Time.numberOfDaysInMonth();
    	int currentDay = 1;
    	
    	//Make 43 a constant. We draw 42 boxes because we need up to six rows for the calendar, and 6*7=42
    	for (int i = 1; i < 43; i++) {
            
            if (i >= initialDrawingPosition && i < initialDrawingPosition + numberOfDaysInMonth) {
            	
            	drawBox(currentDay);
            	currentDay++;
            } else {
            	drawBlankBox();
            }
    	}
    }
    
    private void drawBox(int i) {
    	final JButton button = new JButton(Integer.toString(i));
    	button.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
       
        int today = Time.getCurrentDay();
        if (i == today) {
        	String bluecolor = "#a1c4fc";
            button.setBackground(Color.decode(bluecolor));
            button.setBorder( new LineBorder(Color.decode(bluecolor)) );
        } else {
            button.setBackground(Color.WHITE);
            button.setBorder( new LineBorder(Color.white) );
        }

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
    
    private void drawBlankBox() {
    	JButton button = new JButton();
    	button.setMinimumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setMaximumSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        button.setBorder( new LineBorder(Color.decode(THEME_BLUE)) );
        button.setBackground(Color.decode(THEME_BLUE));
        //button.setBorder( new LineBorder(Color.GRAY) );
        //button.setBackground(Color.GRAY);
        button.setOpaque(true); // without this the background color is not shown
        
    	calPanel.add(button);
    }
    
    private void createNextPrvMonthButton(String buttonText, String position) {
    	final int width = 70;
    	final int height = 30;

        final JButton button = new JButton(buttonText);
        
        button.setFont(new Font("", Font.PLAIN, 16));
        
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
        });
        
        topPanel.add(button, position);
    }
    
    private void addMonthYearLabel() {
    	final int width = 30;
    	final int height = 50;
    	
    	//getMonthYear() returns the current month and year as a string like "April, 2017"
    	JLabel label = new JLabel(Time.getMonthYear(),SwingConstants.CENTER);
    	
    	label.setFont(new Font("", Font.PLAIN, 24));
         
        label.setMinimumSize(new Dimension(width, height));
        label.setPreferredSize(new Dimension(width, height));
        label.setMaximumSize(new Dimension(width, height));
	    label.setForeground(Color.BLACK); // text color
		
    	topPanel.add(label,BorderLayout.CENTER);
    }
    
    private void addSettingsButton() {
    	final int width = 80;
    	final int height = 40;
    	final JButton button = new JButton("Settings");
    	
        button.setFont(new Font("", Font.PLAIN, 16));
        
        button.setMinimumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setForeground(Color.WHITE); // text color
        button.setBackground(Color.decode(THEME_BLUE));
        button.setOpaque(true); // without this the background color is not shown
        button.setBorder( new LineBorder(Color.decode(THEME_BLUE),2,false)); // color, thickness, rounded corners
        
        // Got this from SO, but it looks like there is a better way (see comments on accepted answer)
        // http://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });      
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(button);  	
    }
    
    private void addUpcommingRemindersScrollBox(String reminderText) {
    	// Thanks for help with JTextArea SO (switched to JEditorPane tho)
    	// http://stackoverflow.com/questions/10213100/jscrollpane-words-wrap
    	JEditorPane label = new JEditorPane("text/html", "");
    	
    	//label.setText("<b>Monday, April 6 - 5:30pm:<br></b>Time to workout!<br>"
    		//	+ "<br><b>Thursday, April 25 - 10:00am<br></b>Get some homework done.<br>");
    	label.setText(reminderText);
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
    	
    	ReminderBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Everything in BoxLayout must be aligned in the same position.
    	ReminderBoxLabel.setFont(new Font("", Font.PLAIN, 16));   
    	rightPanel.add(ReminderBoxLabel);
    	
    	// Prevent scrollbox from changing size - see issue 34 - https://github.com/AndrewERAU/Calendar/issues/34
    	scrollPane.setMinimumSize(new Dimension(rightPanel.getWidth(),rightPanel.getHeight()/3 + 45));
    	scrollPane.setMaximumSize(new Dimension(rightPanel.getWidth(),rightPanel.getHeight()/3 + 45));
    	scrollPane.setPreferredSize(new Dimension(rightPanel.getWidth(),rightPanel.getHeight()/3 + 45));
    	
    	rightPanel.add(scrollPane);
    }
    
    private void addEventsTodayScrollBox(String events) {
    	// Thanks for help with JTextArea SO (switched to JEditorPane tho)
    	// http://stackoverflow.com/questions/10213100/jscrollpane-words-wrap
    	JEditorPane label = new JEditorPane("text/html", "");
    	
    	//label.setText("<b>Monday, April 6. 9:00am - 12:30pm:<br></b>Biology class<br>"
    	//		+ "<br><b>Thursday, April 25. 5:00am - 7:00am:<br></b>Morning workout.<br>");        // make it look & act like a label
    	label.setText(events);
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
    	
    	// Prevent scrollbox from changing size - see issue 34 - https://github.com/AndrewERAU/Calendar/issues/34
    	scrollPane.setMinimumSize(new Dimension(rightPanel.getWidth(),rightPanel.getHeight()/3 + 45));
    	scrollPane.setMaximumSize(new Dimension(rightPanel.getWidth(),rightPanel.getHeight()/3 + 45));
    	scrollPane.setPreferredSize(new Dimension(rightPanel.getWidth(),rightPanel.getHeight()/3 + 45));
    	
    	rightPanel.add(scrollPane);
    }

    public void displayRemindersForNext7Days() {
    	String reminders = "";
    	
    	DatabaseMgr db = new DatabaseMgr();
    	List<ReminderObj> reminderList = db.retrieveReminders(Time.getCurrentDayInSqlFormat());
    	
    	// These are for debugging.  Remove
    	/*
    	reminderList.add(new ReminderObj("2017-4-11","3:30","my Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	reminderList.add(new ReminderObj("2017-4-22","13:30","my second Event title!"));
    	*/

    	for (ReminderObj reminder : reminderList) {
    		reminders+= formatReminder(reminder);
    	}
    	
    	addUpcommingRemindersScrollBox(reminders);
    }
    
    private String formatReminder(ReminderObj r) {
    	//String outStr = null;
    	
    	return ("<b>"+r.getReminderDayOfWeek()+", "+r.getReminderMonth()+" "+r.getReminderDay()+" - "+r.getReminderTime()+":<br></b>"+r.getReminderEventTitle()+"<br><br>");
    	//<b>Monday, April 6 - 5:30pm:<br></b>Time to workout!<br>"
    			//+ "<br><b>Thursday, April 25 - 10:00am<br></b>Get some homework done.<br>");
    	
    	//return outStr;
    }
    
    public void displayEventsForToday() {
    	String events = "";
    	
    	DatabaseMgr db = new DatabaseMgr();
    	List<Event> eventList = db.retrieveEvents('D',Time.getCurrentDayInSqlFormat());
    	
    	// These are for debugging.  Remove.
    	
//    	eventList.add(new Event("myTitle", // eventTitle
//    			"Meeting to go over plan details.", // eventDescription
//    			"2017-04-12", // eventDate
//    			"12:30:00", // eventStartTime
//    			"13:30:00", // eventEndTime
//    			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
//    			"", // eventInvitees
//    			"Work", // eventTag
//    			"",
//    			"",
//    			"",
//    			""));
  

    	for (Event event : eventList) {
    		events+= event.formatEventSummary();
    	}
    	
    	addEventsTodayScrollBox(events);
    }

}
