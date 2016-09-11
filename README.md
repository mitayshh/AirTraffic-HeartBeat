# AirTraffic-HeartBeat

CONTENTS OF THIS FILE
_____________________
1. Requirements
2. Configuration
3. Deployment
4. Working

REQUIREMENTS
____________
a] MAC with Eclipse installed. (Version Doesn't Matter).
b] Glassfish 3.1 Downloaded and extracted.

CONFIGURATION
_____________
a] Go to the server tab of Eclipse.
If you can't see a server tab. Clik on "Window" tab in the top menu. Click on "Show View" option and select "servers". Server window should be visible now.

b] Now click on "Eclipse" tab from top menu. Click on "Preferences".
Under the column on left side select "Runtime Environments" from "Server" dropdown menu.
Click on "Add" from the menu on right side. 
Select "GlassFish" folder and under that folder select "Glassfish 3.1".
Glassfish should now be downloaded on your system.

c] Now go to "Servers" tab and click add new server.
And select "GlassFish 3.1" from "GlassFish" dropdown menu and click "Finish".

d] Right Click the "GlassFish 3.1" server that just got added from "Servers" tab and click "Start".

e] After GlassFish server has started, go to your browser and type in "localhost:4848" in your address bar.

f] Now that GlassFish server's homepage is up, click on "JMS Resources" from left menu and then click on "Connection Factories".

g] Inside Connection Factories click "New". After a new window opens type in "jms/WeatherRadarConnection" under "Pool Name" and under "Resource Type" select "javax.jms.ConnectionFactory" and click "OK".

h] Now click on "JMS Resources" again from the menu on left and select "Destination Resources".

i] After new window opens, click on "New" button.
Now for "JNDI Name" type "jms/WeatherRadarTopic" and "Physical Name" as "WeatherRadarTopic".
Select "Resource Type" as "javax.jms.Topic" and click "OK".

DEPLOYMENT
__________
To run the system you have to provide two arguments through console before running.

a] Click on "Run" from top menu and click on "Run Configurations".

b] Now click on "Arguments" tab from the menu once new window appears.

c] And under "Program Arguments" type "Username Password" without quotes, and click on Apply.

d] Now you can individually run the "WeatherRadar.java" file and "FaultDetectionSystem" file.


WORKING
_______
a] To simulate real life conditions, an error is introduced in the system using random number generation whenever number "24" is generated randomly.

b] WeatherRadar.java file sends heartbeat every 5 seconds.

c] FaultDetectionSystem.java file waits for 7 seconds, and if doesn't receive heartbeat in 7 seconds it declares that WeatherRadar system is dead.
