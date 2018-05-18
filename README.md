# MTR-Traffic-Regulation-System

Description:  
We want to realise a system to fluidize the traffic inside the MTR by giving in real time the optimal
direction to go in order to find some space in the next train.
This system will simplify the traffic and have people win time, especially at the rush hour. It is made
avoid the situation where you cannot get in the train but there is still space in another car.

# First Step:
(04/04/2018)  
Devlopment of 3 fonctions in python to display the directionnal arrow on the sense hat of the raspberry py

direction_right(sense, color)
direction_left(sense, color)
direction_down(sense, color)

result of direction_right is an arrow pointed on the right

We put as an argument the sense hat and the color of the arrow that we want.

Next step:
Determining the values of the tuple for the color green, orange and red
then create a function to say what to print in each case (empty to full = green to red, + good direction)

# Step Two
(05/04/2018)  

Etablishing the function which prints the rigth direction with this logic: 

|Left|Down|Right|  
|----|----|-----|
|-1|0|1|

I found the good tuple for the colors and put them in a class: colour

Next Step :  
Create the server on my laptop and a client on the display system.
Try to send data an print the good color with the good direction.

# Step Tree
(03/05/2018)

I did the hotspot wifi on one of my Raspberry and fiwed the IP adress. In fact I did't really enabled a router but i fixed the IP addresses on each pi. My hotspot wifi is working with a bridge so if the IP address is already taken then a free random address is given to the pi which is trying to connect. 
To prevent the addresses of my pi to be already taken I chose a middle range adresses
from 172.16.42.58 to 172.16.42.61 (the netmask is 255.255.0.0 for the network on the lab and there is minimum chances that these addresses will be reached one day)

# Step Four
(04/05/2018)

Today I did the processing function of the server. The server will receive an array list of carInformation and process it to have as an output an array list of display Information. I use array list in the case that all the trains do not have the same length. However the train will always stop at the first door on the dock. In that way the system is working

# Step Five
(05/05/2018)

Today I have upgaded my fix ip addresses for my hotspot by implementing a real router instead of bridging the connection between ethernet and wifi. For this I came back on the last version of the py and followed the tutorial of the lab 11.
Then I determined the routing table for all of my Pi, and linked each mac adress to a fix IP.

Then I did the communication part for the Raspberry Pis inside the train. I use TCP protocol on  Java and all pi send it's data to the representative who gather the information on an ArrayList.


I then decided to process the infomation directly on the raspberry pi representative to get the display informations. There is just a few operations and this can avoid using a physical server. Then the data is directly send to the good dock.
However this system only works for a small MTR network because as there is no centralisation on the data there could be issues. with just a few MTR train the maintenance is easily done while in a large network it is more difficult.

Finally, I started working on the HTTP transmission by I enconter problems to enable the server.
But I'm still working on it : show must go on!

Finally I thing I'll use Flask for the HTTP server in this way it'll be easier.
And implement it on the pi and agree with HTTP connection to avoid connection refused due to connection restuctions

# Step Six
(17/05/2018)

After researching more about the HTTP server I found a better idea for the system architechture. Indeed, using Http the sever cannot send data to the client because he doesn't know the IP address. That is, it is not a good idea tu use HTTP because once the train sends the data to the server, the server cannot send it directly to the good station.

To replace this, we will use MQTT. By incrementing the station number at each stop and by including the next station number in the topic of the data published, we do not need to deal with adressing. the only thing needed is to set the good topic name and then the clients on the station will directly get the data from the server.

So today I implemented the broker on my laptop and I started to developp the client who will publish the data.

# Step Seven
(18/05/2018)

I finallised the MQTT clients (publish and subscribe sides) so that the data can be exchanged freely between the train and the server AND the server and the station.

Then I corrected all the minor issues of compatibility between the different parts (the car and the representative, the server and the displays systems on the docks)

I couldn't find an easy option to make the interface between Java and Python so I simply puted my data in a file text in the Java part and I read it in the python one. That way there is no interfacing issue. The file text is overwritten so that there is no space problem. But in a way of making data analisys we could keep a copy of them and concatenate all the data in the same file for further analyse
