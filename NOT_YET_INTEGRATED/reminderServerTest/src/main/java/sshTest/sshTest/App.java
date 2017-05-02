package sshTest.sshTest;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Properties;

public class App 
{
   	final static String HOST = "ADD"; // in format "user@127.0.0.1"
   													   //   where user = a valid username on the server
   													   //   127.0.0.1 is replaced by the server's ip or domain
	// minute hour day month dayOfWeek
   	// ex) 9 7 1 5 1 -t 'It works!' --email <email address>  --sms <phone number>
	final static String command = "ADD";
	// ex) "/Users/Bob/Documents/privateKey.pem" NOTE: permissions should be 600 on private Key file
	final static String PRIVATE_KEY_PATH = "";
	final static String PASSWORD = ""; // can be used instead of private key
	final static String ERROR_LOG = "error.txt";
	final static String TMP_CRON_FILE = "tmpmycron";
	final static String REDIRECTED_TO_FILE_NAME = "tmpFile000012";
	
    public static void main( String[] args )
    {
    	try{
    		// some help from here
    		//http://stackoverflow.com/questions/16468475/sending-commands-to-remote-server-through-ssh-by-java-with-jsch
    		JSch jsch=new JSch();
  	    	jsch.addIdentity(PRIVATE_KEY_PATH);
  	    	
  	     	String user=HOST.substring(0, HOST.indexOf('@'));
  	 	    String host=HOST.substring(HOST.indexOf('@')+1);
  	 	    	 
  	 	    Session session=jsch.getSession(user, host, 22);	 	    
  	 	    //session.setPassword(PASSWORD); // uncommment if private key not used
  	 	        
  	 	    Properties config = new Properties();
  	 	    config.put("StrictHostKeyChecking", "no");
  	 	    session.setConfig(config);
  	 	
  	 	    session.connect();
		
  			ChannelExec channel=(ChannelExec) session.openChannel("exec");
	  	 	BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
	  	 	channel.setCommand(command);
	  	 	channel.connect();
	
	  	 	// retrieve output from stdout on server
	  	 	String msg=null;
	  	 	while((msg=in.readLine())!=null){
	  	 	  System.out.println(msg);
	  	 	}
	
	  	 	// done
	  	 	channel.disconnect();
	  	 	session.disconnect();  	 	
	    	} catch (Exception e) {
	    		System.out.println("Server Connection Failed.");
	    	}	
    }	
}
