//Author: Nicholas DiPinto
//Text File IO Test
//Test for importing and exporting text files 
package tests;

import static org.junit.Assert.*;
import java.io.File;
import file_io.File_IO;
import org.junit.Test;
import event.Event;

public class Text_File_Tester
{
    //create a new text file containing the following information:
    //Event Title: My Event
    //Event Information: I have a new event
    //Start Date: 2017-03-08
    //End Date: 2017-03-08
    //Start Time: 03:00:00
    //End Time: 04:00:00
   
    //create an empty text file for exporting test
    
    @Test
    public void test_import()
    {
        System.out.println("Starting ics file import test");
        System.out.println("Begin reading text file");
        //call assert to validate that ics file does not exist yet
        //start timer to measure time to read file
        //call IcsFileImportTextFile() function
        //stop timer to get final time to import text file
        //call assert to validate that ics file exists now
        System.out.println("Finished importing text file");
        //calculate total time to import file
        //calculate size of text file using scanner
        //print size of text file
    }
    
    public void test_export()
    {
        System.out.println("Begin reading ics file");
        //call assert to verify that ics file exists
        //call assert to verify that the text file exists
        //start timer to measure time to export file
        //call IcsExportToTextFile() function
        //stop timer to get final time to export ics file
        //call assert to validate that information in ics file matches information in text file by comparing two strings each one containing one of the file's contents
        System.out.println("Finished importing text file");
        //calculate total time to export file
        //calculate size of ics file using scanner
        //print size of ics file
    }
}
