//Author: Nicholas DiPinto
//Import ICS File Test
//Test driver to test import ics files from text files

package test.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import event.Event;

public class Ics_Import_Test
{
	private void createFile() {
	    System.out.println("Starting ics file import test");
	    System.out.println("Creating text file with event content");
	    //create a new text file containing the following information:
	    //Event Title: My Event
	    //Event Information: I have a new event
	    //Start Date: 2017-03-08
	    //End Date: 2017-03-08
	    //Start Time: 03:00:00
	    //End Time: 04:00:00
	    //close the file
	    System.out.println("Closing file");
	}
    
    @Test
    public void test()
    {
    	createFile();
        System.out.println("Begin reading file");
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
}
