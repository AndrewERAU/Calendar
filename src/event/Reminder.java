package event;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder
{
	// This class will handle sending events to server
	private String eventTitle = null;
	private String eventInfo = null;
	private Integer firstAlertTime = null;
    private Integer secondAlertTime = null;
	
	public Reminder()
    {
		eventTitle = "";
		eventInfo = "";
		firstAlertTime = -1;
        secondAlertTime = -1;
        System.out.println("Reminder object was created.");
	}
	
	public String getTitle()
    {
        return this.eventTitle;
	}
    
    public String getInfo()
    {
        return this.eventInfo;
    }
    
    public Integer getFirstAlert()
    {
        return this.firstAlertTime;
    }
    
    public Integer getSecondAlert()
    {
        return this.secondAlertTime;
    }
	
	public void setTitle(String title)
    {
        this.eventTitle = title;
	}
    
    public void setEventInfo(String info)
    {
        this.eventInfo = info;
    }
    
    public void setFirstAlert(Integer alertTime)
    {
        this.firstAlertTime = alertTime;
        System.out.println("Setting first reminder:");
        System.out.println("Converting the inputted alertTime into the amount of seconds until alertTime occurs");
        System.out.println("Setting a Timer class object to countdown from when user indicated they want the reminder to appear");
        System.out.println("When timer reaches zero, reminder will appear on terminal.");
        //Integer delay = alertTime * 1000;
        //Integer period = 1000;
        //timer = new Timer();
        //timer.scheduleAtFixedR
    }
    
    public void setSecondAlert(Integer alertTime)
    {
        this.secondAlertTime = alertTime;
        System.out.println("Setting second reminder:");
        System.out.println("Converting the inputted alertTime into the amount of seconds until alertTime occurs");
        System.out.println("Setting a Timer class object to countdown from when user indicated they want the reminder to appear");
        System.out.println("When timer reaches zero, reminder will appear on terminal.");
    }
}
