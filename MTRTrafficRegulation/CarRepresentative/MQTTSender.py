import paho.mqtt.publish as publish

hostname = "192.168.1.214" # Sandbox broker
port = 1883 # Default port for unencrypted MQTT
topic = "Project/Augustin" # '/' as delimiter for sub-topics


# define the class to send the informations to the broker
def publisher(position, direction, color):
	msgs = [(topic + "/" + str(position) + "/" + "Direction", direction, 1, False),
		(topic + "/" + str(position) + "/" + "Color", color, 1, False)]

	publish.multiple(msgs, hostname=hostname, port=port)

# get all the informations from the file text
file=open("infos.txt", "r")

#info on the train
#in the future, the application will be elaborated such as the number of the next station in included in the topic published 
#That way, there is no need of adressing because the stations will just subscribe to their topic number 
#Therefore there will be an automatic display
trainLine=file.readline()
trainPosition=file.readline()
trainSize=file.readline()

#information of each door to be printed

for i in range(int(trainSize)):
	position=int(file.readline())
	direction=file.readline()
	color=file.readline()
	#Send all the data to the broker
	publisher(position, direction, color)
file.close()

print ("Sending done")

