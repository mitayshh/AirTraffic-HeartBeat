package heartbeat;

import heartbeat.FaultDetectionSystem;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.util.*;
import java.util.Random;


public class WeatherRadar {

	 private TopicConnectionFactory tcf;
	 private TopicConnection tcon;
	 private TopicSession pubSession;
	 private String topicName = "jms/WeatherRadarTopic";
	 private Topic top;
	 private TopicPublisher tPub;
	
//Constructor
	 public WeatherRadar(String username, String password) throws NamingException,JMSException{
		
			
	       InitialContext initialCon = new InitialContext();

	       // Connecting using Connection Factory and Topic
	       tcf = (TopicConnectionFactory)
	                        initialCon.lookup("jms/WeatherRadarConnection");
	       top = (Topic)initialCon.lookup(topicName);
	       
	       // connection using the Factory
	       tcon = tcf.createTopicConnection(username,password);

	       // Topic Sessions using the connection
	       pubSession = tcon.createTopicSession
	                         (false,Session.AUTO_ACKNOWLEDGE);

	       // Create TopicPublisher
	       tPub = pubSession.createPublisher(top);

	       tcon.start();
		 
	 }// Constructor Ends

// Method to close the connection to Topic
public void close() throws JMSException{
    tcon.stop();
}

//Publishing message to Topic
public void writeMsg(String msg) throws JMSException {
    //  Creating a Text Message with the String object
    TextMessage txtMsg = pubSession.createTextMessage(msg);

    //  Publishing the message object to the Topic
    tPub.publish(txtMsg);
}

public static void main(String[] args) 
        throws NamingException,IOException,JMSException, InterruptedException {
String msg, username = null, password = null;
WeatherRadar pub;

//Extracting username and password from compile arguments
if(args.length == 2) {
     username = args[0];
     password = args[1];
} else {
     System.out.println("No parameters");
     System.exit(0);
}

//  Calling the constructor with the credentials
pub = new WeatherRadar(username,password);

//  Declaring a Reader for reading the message from user
BufferedReader br = new BufferedReader
                   (new InputStreamReader(System.in));

//  Random number generator to generate fault randomly
Random rand = new Random();
int num = 0;
while(true) {
	//Delay of 5 seconds
	Thread.sleep(5000);
	num = rand.nextInt((50-1)+1)+1;
	 //msg = br.readLine();
	 //System.out.println(num);
     if(num==24) {
  	   System.out.println("Process Exiting");
           pub.writeMsg("Process Exiting");
           pub.close();
           System.exit(0);
     } else {
  	   //System.out.println("Am Alive");
           pub.writeMsg("Am Alive");
     }
}  //  End of while loop

}  //  End of main()


}
