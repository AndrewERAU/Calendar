package reminder;

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
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
                       {
            @Override
            public void run()
            {
                System.out.print("\nEvent: Reminder Test 1 First Alert");
                System.out.print("Current time is: ");
                System.out.println(System.currentTimeMillis()/1000);
            }
        }, alertTime * 1000  //converts alert time from milliseconds to seconds
                       );
        //Next step: convert alertTime from inputted as hh:mm:ss to seconds until event occurs
    }
    
    public void setSecondAlert(Integer alertTime)
    {
        this.firstAlertTime = alertTime;
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
                       {
            @Override
            public void run()
            {
                System.out.print("\nEvent: Reminder Test 1 Second Alert");
                System.out.print("Current time is: ");
                System.out.println(System.currentTimeMillis()/1000);
            }
        }, alertTime * 1000  //converts alert time from milliseconds to seconds
                       );
        //Next step: convert alertTime from inputted as hh:mm:ss to seconds until event occurs
    }
}

