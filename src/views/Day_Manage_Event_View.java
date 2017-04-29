
package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import database.DatabaseMgr;
import event.Event;
import time.Time;



public class Day_Manage_Event_View  {
		
	private JFrame frame;
	
	private JPanel leftPanel;
	private JPanel rightPanel;

	
	private JTextField eventTag;
	private JTextField eventDate; // TODO, make dates use a date picker. Same for times.
	private JTextField eventStartTime;
	private JTextField eventEndTime;
	private JTextField eventTitle;
	private JTextField eventDescription;
	private JTextField eventLocation;
	private JTextField eventInvitees;
	private JTextField eventReminder1Date;
	private JTextField eventReminder1Time;
	private JTextField eventReminder2Date;
	private JTextField eventReminder2Time;

	
	
	private JLabel titleLabel;
	private JLabel descriptionLabel;
	private JLabel dateLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel locationLabel;
	private JLabel inviteesLabel;
	private JLabel tagLabel;
	private JLabel reminder1DateLabel;
	private JLabel reminder1TimeLabel;
	private JLabel reminder2DateLabel;
	private JLabel reminder2TimeLabel;
	
	private JButton saveEventButton;
	
	JEditorPane label;
	
	//private JPanel panel;
	
	private DatabaseMgr db;
	private Event event;
	
	private String SELECTED_DATE;
	
	private Calendar_View HOME_SCREEN;
	

	public Day_Manage_Event_View(Calendar_View cv, String sql_date_selected) {
		SELECTED_DATE = sql_date_selected;
		HOME_SCREEN = cv;
		initUI();
	}
	
	private void initUI() {
    	frame = new JFrame();
        frame.setTitle("Add Event");
        frame.setSize(900, 650);     
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Show in middle of screen
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes window w/o exiting app
        
        //panel = new JPanel();
        //panel.setLayout(new FlowLayout()); // TODO: Update Add Event Screen - USE GridBagLayout
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS)); //BoxLayout.PAGE_AXIS == top to bottom layout
        //rightPanel.setLocation(450, 10);
        //rightPanel.setSize(frame.getWidth()/2 - 10,frame.getHeight()  - 30);
        //rightPanel.setBackground(Color.RED);    
        
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS)); //BoxLayout.PAGE_AXIS == top to bottom layout
        //leftPanel.setLocation(450, 100);
        //leftPanel.setSize(this.getWidth()/2 - 10,this.getHeight()  - 30);
        //leftPanel.setSize(10,10);
        //leftPanel.setPreferredSize(new Dimension(10, 100));
        //leftPanel.setBackground(Color.BLUE);
        //rightPanel.setBackground(Color.RED);              
        
        frame.setLayout(new GridLayout(1,2));
        frame.add(leftPanel);
        frame.add(rightPanel);
       
        
        titleLabel = new JLabel("Event Title");
        eventTitle = new JTextField();
        eventTitle.setPreferredSize(new Dimension(200,20));
        
        descriptionLabel = new JLabel("Description");
        eventDescription = new JTextField();
        eventDescription.setPreferredSize(new Dimension(240,120));
        
        dateLabel = new JLabel("Event Date");
        eventDate = new JTextField();
        eventDate.setPreferredSize(new Dimension(200, 20));
        
        startTimeLabel = new JLabel("Start Time");
        eventStartTime = new JTextField();
        eventStartTime.setPreferredSize(new Dimension(200,20));
        		
        endTimeLabel = new JLabel("End Time");
        eventEndTime = new JTextField();
        eventEndTime.setPreferredSize(new Dimension(200,20));
        
        locationLabel = new JLabel("Location");
        eventLocation = new JTextField();
        eventLocation.setPreferredSize(new Dimension(200,20));
        
        inviteesLabel = new JLabel("Invitees");
        eventInvitees = new JTextField();
        eventInvitees.setPreferredSize(new Dimension(200,20));
        
        tagLabel = new JLabel("Tag");
        eventTag = new JTextField();
        eventTag.setPreferredSize(new Dimension(200,20));
        
        reminder1DateLabel = new JLabel("Reminder 1 Date");
        eventReminder1Date = new JTextField();
        eventReminder1Date.setPreferredSize(new Dimension(200,20));
        
        reminder1TimeLabel = new JLabel("Reminder 1 Time");
        eventReminder1Time = new JTextField();
        eventReminder1Time.setPreferredSize(new Dimension(200,20));
        
        reminder2DateLabel = new JLabel("Reminder 2 Date");
        eventReminder2Date = new JTextField();
        eventReminder2Date.setPreferredSize(new Dimension(200,20));
        
        reminder2TimeLabel = new JLabel("Reminder 2 Time");
        eventReminder2Time = new JTextField();
        eventReminder2Time.setPreferredSize(new Dimension(200,20));
        
        saveEventButton = new JButton("Save Event");
        
        saveEventButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        saveEventButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              // Save event to database
        	  // TODO: Add any reminders if they are listed done?
        	  // TODO: updated event format and constructor, fix this
        	  event = new Event(eventTitle.getText(),
        			  eventDescription.getText(),
        			  eventDate.getText(),
        			  eventStartTime.getText(),
        			  eventEndTime.getText(),
        			  eventLocation.getText(),
        			  eventInvitees.getText(),
        			  eventTag.getText(),
        			  eventReminder1Date.getText(),
        			  eventReminder1Time.getText(),
        			  eventReminder1Date.getText(),
        			  eventReminder1Time.getText()
//        			  eventReminder1Date.getText(),
//        			  eventReminder1Time.getText(),
//        			  eventReminder1Date.getText(),
//        			  eventReminder1Time.getText()
        			  );
        	  System.out.print(event.getEventTitle());
        	  System.out.print(event.getEventDate());
        	  db = new DatabaseMgr();
        	  db.insertEvent(event);
        	  db.close();
        	  HOME_SCREEN.refreshTodaysEventsView();
        	  refreshEventsOnDayView(); // refreshes events list in left pane on Day_Manage_Event_View
          }
        });
        
        JButton modifyEventButton = new JButton("Modify Event");      
        modifyEventButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              // Save event to database
        	  // TODO: Add any reminders if they are listed done?
        	  // TODO: updated event format and constructor, fix this
        	  event = new Event(eventTitle.getText(),
        			  eventDescription.getText(),
        			  eventDate.getText(),
        			  eventStartTime.getText(),
        			  eventEndTime.getText(),
        			  eventLocation.getText(),
        			  eventInvitees.getText(),
        			  eventTag.getText(),
        			  eventReminder1Date.getText(),
        			  eventReminder1Time.getText(),
        			  eventReminder1Date.getText(),
        			  eventReminder1Time.getText()
//        			  eventReminder1Date.getText(),
//        			  eventReminder1Time.getText(),
//        			  eventReminder1Date.getText(),
//        			  eventReminder1Time.getText()
        			  );
        	  
        	  db = new DatabaseMgr();
        	  db.updateEvent(event);
        	  db.close();   	  
        	  HOME_SCREEN.refreshTodaysEventsView();
        	  refreshEventsOnDayView(); // refreshes events list in left pane on Day_Manage_Event_View
          }
        });
        
        JButton deleteEventButton = new JButton("Delete Event");        
        saveEventButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              // Save event to database
        	  // TODO: Add any reminders if they are listed done?
        	  // TODO: updated event format and constructor, fix this
        	  event = new Event(eventTitle.getText(),
        			  eventDescription.getText(),
        			  eventDate.getText(),
        			  eventStartTime.getText(),
        			  eventEndTime.getText(),
        			  eventLocation.getText(),
        			  eventInvitees.getText(),
        			  eventTag.getText(),
        			  eventReminder1Date.getText(),
        			  eventReminder1Time.getText(),
        			  eventReminder1Date.getText(),
        			  eventReminder1Time.getText()
//        			  eventReminder1Date.getText(),
//        			  eventReminder1Time.getText(),
//        			  eventReminder1Date.getText(),
//        			  eventReminder1Time.getText()
        			  );
        	  
        	  db = new DatabaseMgr();
        	  //db.removeEvent(event);
        	  db.close();
        	  HOME_SCREEN.refreshTodaysEventsView();
        	  refreshEventsOnDayView(); // refreshes events list in left pane on Day_Manage_Event_View
        	  
          }
        });
        
     
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        rightPanel.add(titleLabel);
        rightPanel.add(eventTitle);
        
        rightPanel.add(descriptionLabel);
        rightPanel.add(eventDescription);
        
        rightPanel.add(dateLabel);
        rightPanel.add(eventDate);
        
        rightPanel.add(startTimeLabel);
        rightPanel.add(eventStartTime);
        
        rightPanel.add(endTimeLabel);
        rightPanel.add(eventEndTime);
        
        rightPanel.add(locationLabel);
        rightPanel.add(eventLocation);
        
        rightPanel.add(inviteesLabel);
        rightPanel.add(eventInvitees);
        
        rightPanel.add(tagLabel);
        rightPanel.add(eventTag);
        
        //rightPanel.add(reminder1Label);
        //rightPanel.add(eventReminder1);
        
        //rightPanel.add(reminder2Label);
        //rightPanel.add(eventReminder2);
        
        rightPanel.add(reminder1DateLabel);
        rightPanel.add(eventReminder1Date);
        
        rightPanel.add(reminder1TimeLabel);
        rightPanel.add(eventReminder1Time);
        
        rightPanel.add(reminder2DateLabel);
        rightPanel.add(eventReminder2Date);
        
        rightPanel.add(reminder2TimeLabel);
        rightPanel.add(eventReminder2Time);
        
        rightPanel.add(saveEventButton);
        rightPanel.add(modifyEventButton);
        rightPanel.add(deleteEventButton);
        
        
        displayEventsForToday();
        

        //getContentPane ().add (panel, BorderLayout.LINE_START); // must come after add(panel);
	    frame.setVisible(true);
	}
	
	   private void addEventsTodayScrollBox(String events) {
       	// Thanks for help with JTextArea SO (switched to JEditorPane tho)
       	// http://stackoverflow.com/questions/10213100/jscrollpane-words-wrap
       	label = new JEditorPane("text/html", "");
       	
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
                   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       	
       	JLabel EventsBoxLabel = new JLabel("Today's Events:");
       	EventsBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
       	EventsBoxLabel.setFont(new Font("", Font.PLAIN, 16)); 
       	leftPanel.add(EventsBoxLabel); // TODO: When I add this, the settings button above it gets shifted left 	
       	
       	// Prevent scrollbox from changing size - see issue 34 - https://github.com/AndrewERAU/Calendar/issues/34
//       	scrollPane.setMinimumSize(new Dimension(leftPanel.getWidth(),leftPanel.getHeight()));
//       	scrollPane.setMaximumSize(new Dimension(leftPanel.getWidth(),leftPanel.getHeight()));
//       	scrollPane.setPreferredSize(new Dimension(leftPanel.getWidth(),leftPanel.getHeight()));
       	//scrollPane.setMinimumSize(new Dimension(10,10));
       	//scrollPane.setMaximumSize(new Dimension(10,10));
       	//scrollPane.setPreferredSize(new Dimension(10,10));
       	//scrollPane.setLocation(10,10);
       	
       	leftPanel.add(scrollPane);
       }
	   
	   public void displayEventsForToday() {
	    	String events = "";
	    	
	    	DatabaseMgr db = new DatabaseMgr();
	    	List<Event> eventList = db.retrieveEvents('D',SELECTED_DATE);
	    	
	    	// These are for debugging.  Remove.
	    	
//	    	eventList.add(new Event("myTitle", // eventTitle
//	    			"Meeting to go over plan details.", // eventDescription
//	    			"2017-04-12", // eventDate
//	    			"12:30:00", // eventStartTime
//	    			"13:30:00", // eventEndTime
//	    			"3500 Deer Creek Rd, Palo Alto, CA 94304", // eventLocation
//	    			"", // eventInvitees
//	    			"Work", // eventTag
//	    			"",
//	    			"",
//	    			"",
//	    			""));
	  

	    	for (Event event : eventList) {
	    		events+= event.formatEvent();
	    	}
	    	
	    	addEventsTodayScrollBox(events);
	    }
	
	    
	    private void refreshEventsOnDayView() {

	    	String events="";
	    	DatabaseMgr db = new DatabaseMgr();
	    	List<Event> eventList = db.retrieveEvents('D',SELECTED_DATE);
	    	for (Event event : eventList) {
	    		events+= event.formatEvent();
	    	}
	    	label.setText(events);
	    }
	        	

}