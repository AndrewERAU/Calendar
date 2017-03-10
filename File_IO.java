package Text_File_IO;

import java.io.*;
import java.util.Scanner;

public class Text_File_IO
{
    private Scanner scanner;
    
    public void IcsImportTextFile(String importFileName) throws IOException //pass a text file in as an argument to be converted into an ics file
    {
        FileReader in = null;
        FileWriter out = null;
        //import event class into file to help create a new event based on input
        //To Do: create exception handler for invalid inputs
        //To Do: read size of input file and verify that it does not go beyond maximum specified file size in requirements
        
        String outputFileName = importFileName.replace("txt", "ics");  //change file extension
        try
        {
            in = new FileReader(importFileName);
            out = new FileWriter(outputFileName);
            scanner = new Scanner(in);
            String eventTag = "";
            
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if(line.indexOf(":") != -1)  //separating string using expected format of text file
                {
                    String [] strArray = line.split(":");
                    eventTag = strArray[0];
                    switch (eventTag)
                    {
                        case "Title":
                            //newEvent.setTitle(strArray[1]);
                            break;
                        case "Event Information":
                            //newEvent.setEventInfo(strArray[1]);
                            break;
                            //case "Location":
                            //newEvent.setLocation(strArray[1]);
                            //	break;
                        case "Start Date":
                            //newEvent.setStartDate(strArray[1]);
                            break;
                        case "End Date":
                            //newEvent.setEndDate(strArray[1]);
                            break;
                        case "Start Time":
                            //newEvent.setStartTime(strArray[1]);
                            break;
                        case "End Time":
                            //newEvent.setEndTime(strArray[1]);
                            break;
                        default:
                            System.out.println("ERROR:" + strArray[0] + " is not a valid event detail.");
                            //create more cases once all event fields have been agreed upon
                    }
                }
            }
            //print event details found in file to terminal (or JFrame)
            
            //copy text into ics file
            //Next Step: read in entire calendar and print calendar
            int index;
            while((index = in.read()) != -1)
            {
                out.write(index);
            }
        }
        finally
        {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    
    //method for exporting ics files to text files
    public void IcsExportToTextFile(String IcsFileName) throws IOException
    {
        FileReader in = null;
        FileWriter out = null;
        String outputFileName = IcsFileName.replace("ics", "txt");
        
        try
        {
            in = new FileReader(IcsFileName);
            out = new FileWriter(outputFileName);
            
            int index;
            while((index = in.read()) != -1)
            {
                out.write(index);
            }
        }
        finally
        {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        
        
    }
    
}
