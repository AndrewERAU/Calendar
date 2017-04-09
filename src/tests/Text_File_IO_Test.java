import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import Text_File_IO.Text_File_IO;

public class Text_File_IO_Test
{
    //create an empty text file for exporting test
    static String fileContent = "Event Title: My Event\n"
    + "Event Information: I have a new event\n"
    + "Start Date: 2017-03-08\n"
    + "End Date: 2017-03-08\n"
    + "Start Time: 03:00:00\n"
    + "End Time: 04:00:00";
    static File textFile = new File("test.txt");
    static File icsFile = new File("test.ics");
    private static Scanner textFileScanner;
    private static Scanner icsScanner;
    
    public static void main(String args[]) throws IOException
    {
        //assertTrue(textFile.isFile());
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        out.write(fileContent);
        out.close();
        int startTime = (int) System.currentTimeMillis();
        test_import();
        int endTime = (int) System.currentTimeMillis();
        int totalTime = endTime - startTime;
        System.out.println("Finished importing text file in " + totalTime + " milliseconds");
        startTime = (int) System.currentTimeMillis();
        test_export();
        endTime = (int) System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("Finished exporting ics file in " + totalTime + " milliseconds");
        //checkFileEquivalency();
        //assertEquals(textFile, icsFile);
        //textFile.delete();
        //icsFile.delete();
        //assertTrue(!textFile.isFile());  //test that file was deleted
        //assertTrue(!icsFile.isFile());
        System.out.println("Finished text file IO tests.");
    }
    
    public static void test_import()
    {
        Text_File_IO testIO = new Text_File_IO();
        System.out.println("Began importing text file to ics file");
        try
        {
            testIO.IcsImportTextFile("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished importing text file");
    }
    
    public static void test_export() throws IOException
    {
        Text_File_IO testIO = new Text_File_IO();
        System.out.println("Began exporting ics file to text file");
        assertTrue(icsFile.isFile()); //verify that the test ics file exists
        testIO.IcsExportToTextFile("test.ics");
        System.out.println("Finished importing text file");
    }
    
    public static void checkFileEquivalency() throws FileNotFoundException
    {
        textFileScanner = new Scanner(textFile);
        icsScanner = new Scanner(icsFile);
        String textFileContent = "";
        String icsFileContent = "";
        
        textFileContent = textFileScanner.nextLine();
        while(textFileScanner.hasNextLine())
        {
            textFileContent = textFileContent + "\n" + textFileScanner.nextLine();
        }
        
        icsFileContent = icsScanner.nextLine();
        while(icsScanner.hasNextLine())
        {
            icsFileContent = icsFileContent + "\n" + icsScanner.nextLine();
        }
        assertEquals(textFileContent, icsFileContent);
        System.out.println("The text file and the ics file have the same contents.");
    }
    
}
