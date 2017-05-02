package reminder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

public class ScheduleReminderMac {
	// TODO: use this class to schedule mac reminders, or delete it?
	
	final static String ERROR_LOG = "error.txt";
	final static String TMP_CRON_FILE = "tmpmycron";
	final static String REDIRECTED_TO_FILE_NAME = "tmpFile000012";
	
	public int scheduleReminder(String title, String msg) {
		/* This function installs a crontab for the user with the specified reminder
		 * TODO: add two crontabs for user? one that sets the reminder off, and one that deletes the crontab after reminder goes off?
		 * */
		
		//java -cp <path>/reminderJars/MacReminderJar.jar reminder.SendReminderMac Reminder 'Test.'
		/*
		//write out current crontab
		crontab -l > mycron
		// echo new cron into cron file
		echo "00 09 * * 1-5 echo hello" >> mycron
		// install new cron file
		crontab mycron
		rm mycron
		
		*/
		
		new File(TMP_CRON_FILE).delete();
		
		// Get path to jar file
		String reminderJar = new File("reminderJars/MacReminderJar.jar").getAbsolutePath();
		
		String[] cmd1 = new String[]{"crontab","-l"};                    // crontab -l > mycron
		//String[] cmd2 = new String[]{"echo","00 09 * * 1-5 echo hello"}; // echo '00 09 * * 1-5 echo hello' >> mycron
		// mm hh dd month_month day_of_week cmd
		String[] cmd2 = new String[]{"echo","* * * * * " + "java -cp " + reminderJar + " reminder.SendReminderMac " + "'" + title + "' '" + msg + "'"}; 
		// ex) java -cp MacReminderJar.jar reminder.SendReminderMac Reminder 'Time to workout!'
		String[] cmd3 = new String[]{"crontab",TMP_CRON_FILE};           // crontab mycron
		new File(TMP_CRON_FILE).delete();                                // rm mycron
		
		try {
			if (runShellCmd(cmd1,true) != 0) throw new Exception();
			if (runShellCmd(cmd2,true) != 0) throw new Exception();
			if (runShellCmd(cmd3,false) != 0) throw new Exception();
		} catch (Exception e) {
			return -1;
		}
				
		return 0;
	}
	
	
	private int runShellCmd(String[] cmd, boolean redirect) {
		
		// Thx SO for this 
		// http://stackoverflow.com/questions/16238714/runtimes-exec-method-is-not-redirecting-the-output
		try {
			ProcessBuilder builder = new ProcessBuilder(cmd);
			if (redirect) {
				builder.redirectOutput(Redirect.appendTo(new File(TMP_CRON_FILE)));
			} else {
				builder.redirectOutput(new File(REDIRECTED_TO_FILE_NAME));
			}
			builder.redirectError(new File(ERROR_LOG)); // TODO: update this so it appends to error log
			Process p = builder.start(); // may throw IOException
		} catch (Exception e) {
			System.out.println("Error inserting crontab");
			return -1;
		}
		

		return 0;		
	}
	

}
