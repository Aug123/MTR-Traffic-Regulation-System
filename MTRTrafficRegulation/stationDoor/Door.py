import paho.mqtt.client as mqtt
from sense_hat import SenseHat
import directions

sense=SenseHat()

hostname = "192.168.1.214" # Sandbox broker
port = 1883 # Default port for unencrypted MQTT

class DisplayInfo:
	def __init__(self):
		self.direction = 0
		self.color = (0,255,0)
	def setDirection(self, dir):
		self.direction = dir
	def setColor(self, col):
		self.color = col


topics = "Project/Augustin/2/#" # it will receive only the information relative to its position 1
				# The direction and color

def on_connect(client, userdata, flags, rc):
	# Successful connection is '0'
	print("Connection result: " + str(rc))
	if rc == 0:
		# Subscribe to topics
		client.subscribe(topics)

def on_message(client, userdata, message):
	info=message.payload.decode("utf-8")
	print("Received Info on %s: %s (QoS = %s)" %
		(message.topic, info, str(message.qos)))

	if message.topic=='Project/Augustin/2/Direction' :
		display.setDirection(info)
	else :
		display.setColor(info)

	directions.diplay_good_direction(sense, int(display.direction), display.color)


def on_disconnect(client, userdata, rc):
	if rc != 0:
		print("Disconnected unexpectedly")



# Initialize client instance
client = mqtt.Client()

#initialize the info to display
display=DisplayInfo()

# Bind events to functions
client.on_connect = on_connect
client.on_message = on_message
client.on_disconnect = on_disconnect


# Connect to the specified broker
client.connect(hostname, port, 60)

# Network loop runs in the background to listen to the events
client.loop_forever()
