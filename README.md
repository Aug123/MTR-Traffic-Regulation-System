# MTR-Traffic-Regulation-System

### Information on the code
Each part of the system has his own code in a dedicated folder  
The part of the train is in Java  
The part of the station is in python

## Description:  
We want to realize a system to fluidize the traffic inside the MTR by giving in real time the optimal
direction to go in order to find some space in the next train.
This system will simplify the traffic and have people win time, especially at the rush hour. It is made to avoid the situation where you miss the train even though there is still space in it.

## First Step:
(04/04/2018)  
Development of tree functions in python to display the directional arrow with the corresponding color on the sense hat of the raspberry pi.

direction_right(sense, color)
direction_left(sense, color)
direction_down(sense, color)

result of direction_right is an arrow pointed on the right

We put as an argument the sense hat and the color of the arrow that we want.

## Step Two
(05/04/2018)  

Establishing the function which prints the right direction with this logic: 

|Left|Down|Right|  
|----|----|-----|
|-1|0|1|

I found the good tuple for the colors and put them in a class: color
red 255,0,0
orange 255,128,0
green 0,255,0


## Step Tree
(03/05/2018)

I did the WIFI router on one of my Raspberry and fixed the IP addresses. In fact I didn't really enabled a router but I fixed the IP addresses on each pi. My hotspot wifi is working with a bridge so if the IP address is already taken then a free random address is given to the pi which is trying to connect. 
To prevent the addresses of my pi to be already taken I chose a middle range addresses
from 172.16.42.58 to 172.16.42.61 (the netmask is 255.255.0.0 for the network on the lab and there is minimum chances that these addresses will be reached one day)

## Step Four
(04/05/2018)

Today I did the processing function of the server. The server will receive a  list of the information (the number of people in each car) and process it to have as an output an list of display information for the station part. 

## Step Five
(05/05/2018)

Today I have upgraded my fixed IP addresses for my router WIFI by implementing a real router instead of bridging the connection between ethernet and wifi. For this I came back on the previous version of the pi and followed the tutorial of the lab 11.
Then I determined the routing table for all of my Pi, and linked each mac address to a fix IP address.

Then I did the communication part for the Raspberry Pis inside the train. I use TCP protocol on  Java and all pi send it's data to the representative who gather the informations.


I then decided to process the information directly on the raspberry pi representative to get the display informations. There are just a few operations to realize for the prototype so that will have a minimal impact on the performances. For the final project depending on the size of the MTR (size of the trains…) we could decide to process all on the representative or to centralize everything on the server.

Finally, I started to work on the HTTP transmission but I faced problems to enable the server.
But I'm still working on it : show must go on!

Finally I thing I'll use Flask for the HTTP server in this way it'll be easier.
And implement it on the pi and agree with HTTP connection to avoid connection refused due to connection restrictions

## Step Six
(17/05/2018)

After researching more about the HTTP server I found a better idea for the system architecture. Indeed, by using HTTP the sever cannot send data to the display systems because he doesn't know their IP address. That is, it is not a good idea to use HTTP because once the train sends the data to the server, the server cannot send it directly to the good station.

To replace this, I will use MQTT. By incrementing the station number at each stop and by including the next station number in the topic of the data published, we do not need to deal with addressing. The only thing needed is to set the good topic name and then the clients on the station will directly get the data from the server.

So today I implemented the broker on my laptop (with mosquitto) and I started to develop the client who will publish the data.

## Step Seven
(18/05/2018)

I finalized the MQTT clients (publish and subscribe sides) so that the data can be exchanged freely between the train and the server AND the server and the station.

Then I corrected all the minor issues of compatibility between the different parts (the car and the representative, the server and the displays systems on the docks)

I couldn't find an easy option to make the interface between Java and Python so I simply put my data in a file text in the Java part and I read it in the python one. That way there is no interfacing issue. The file text is overwritten so that there is no space problem. But in a way of making data analysis we could keep a copy of them and concatenate all the data in the same file for further analyze
