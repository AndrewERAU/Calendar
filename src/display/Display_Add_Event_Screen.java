package display;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import database.DatabaseMgr;


public class Display_Add_Event_Screen extends JFrame {
	
	private JTextField eventTag;
	private JTextField eventDate; // TODO, make dates use a date picker. Same for times.
	private JTextField eventStartTime;
	private JTextField eventEndTime;
	private JTextField eventTitle;
	private JTextField eventDescription;
	private JTextField eventLocation;
	private JTextField eventInvitees;
	
	private JLabel titleLabel;
	private JLabel descriptionLabel;
	private JLabel dateLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JLabel locationLabel;
	private JLabel inviteesLabel;
	private JLabel tagLabel;
	
	private JButton saveEventButton;
	
	private JPanel panel;
	
	private DatabaseMgr db;
	
	public Display_Add_Event_Screen() {
		initUI();
	}
	
	private void initUI() {
        setTitle("Add Event");
        setSize(400, 500);     
        setResizable(false);
        setLocationRelativeTo(null); // Show in middle of screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes window w/o exiting app
        
        panel = new JPanel();
        //panel.setLayout(new FlowLayout()); // TODO: USE GridBagLayout
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        
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
        
        saveEventButton = new JButton("Save Event");
        saveEventButton.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              // Save event to database
        	  // TODO: Add any reminders if they are listed
        	  db = new DatabaseMgr();
        	  db.insertEvent(eventTitle.getText(),
        			  eventDescription.getText(),
        			  eventDate.getText(),
        			  eventStartTime.getText(),
        			  eventEndTime.getText(),
        			  eventLocation.getText(),
        			  eventInvitees.getText(),
        			  eventTag.getText());
        	  db.close();
        	  
          }
        });
        
        
        panel.add(titleLabel);
        panel.add(eventTitle);
        
        panel.add(descriptionLabel);
        panel.add(eventDescription);
        
        panel.add(dateLabel);
        panel.add(eventDate);
        
        panel.add(startTimeLabel);
        panel.add(eventStartTime);
        
        panel.add(endTimeLabel);
        panel.add(eventEndTime);
        
        panel.add(locationLabel);
        panel.add(eventLocation);
        
        panel.add(inviteesLabel);
        panel.add(eventInvitees);
        
        panel.add(tagLabel);
        panel.add(eventTag);
        
        panel.add(saveEventButton);
        
        
        
        add(panel);
        //getContentPane ().add (panel, BorderLayout.LINE_START); // must come after add(panel);
	    setVisible(true);
	}
	
	
	
	
}
