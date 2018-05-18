import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;

public class ServerTrainRepresentative {

    public static void main(String[] args) throws Exception {

	// Create server socket listening on port
	int port = 1234;
	ServerSocket serverSocket = new ServerSocket(port);

	// Declare client socket
	Socket clientSocket;

	// Declare all the information of the train
	// For the prototype the size of the train is 2
	// The line numbers is 1 and the positions is the first one
	TrainInformation train = new TrainInformation(1, 1, 2);

	// declare the variables used for collecting the informations of all car
	CarInformation s = new CarInformation(1);
	MyRandom rnd = new MyRandom();
	ArrayList<CarInformation> list = new ArrayList<CarInformation>();

	while (true) { // Provide service continuously

	    s.setPassengers(rnd.nextInt(30));
	    list.add(s);
	    System.out.println("The CarInfo " + s.getPosition() + " " + s.getPassengers());

	    while (list.size() < train.getSize()) {

		clientSocket = serverSocket.accept();

		ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
		CarInformation c = new CarInformation((int) in.readObject());
		c.setPassengers((int) in.readObject());

		list.add(c);

		in.close();
		clientSocket.close();
	    }

        //Now that we have the informations of all the cars of the train we will process them so that we know what to display 

	    ArrayList<DisplayInformation> result = new ArrayList<DisplayInformation>();

	    for (int i = 0; i < list.size(); i++){
		DisplayInformation dInfo = new DisplayInformation(list.get(i).getPosition());


		// First, we set the color depending on the number of people in the car
		// The car can contain 30 people
		// 0<GREEN<10<ORANGE<20<RED<30
		int n = list.get(i).getPassengers();
		if (n < 10)
		    dInfo.color.setGreen();
		else if (n > 20)
		    dInfo.color.setRed();
		else
		    dInfo.color.setOrange();

		// Then we determine the direction for each door of the train as an
		// integer:-1=left, 0=down, 1=right
		if (i == 0) {
		    if (list.get(i).getPassengers() < list.get(i + 1).getPassengers())
			dInfo.setDirection(0);
		    else
			dInfo.setDirection(1);
		} else if (i == list.size() - 1) {
		    if (list.get(i).getPassengers() < list.get(i - 1).getPassengers())
			dInfo.setDirection(0);
		    else
			dInfo.setDirection(-1);

		} else {
		    if (list.get(i - 1).getPassengers() > list.get(i).getPassengers()
			&& list.get(i).getPassengers() < list.get(i + 1).getPassengers()) {
			dInfo.setDirection(0);

		    } else if (list.get(i - 1).getPassengers() > list.get(i).getPassengers()
			       && list.get(i).getPassengers() > list.get(i + 1).getPassengers()) {
			dInfo.setDirection(1);

		    } else if (list.get(i - 1).getPassengers() < list.get(i).getPassengers()
			       && list.get(i).getPassengers() < list.get(i + 1).getPassengers()) {
			dInfo.setDirection(-1);

		    } else if (list.get(i - 1).getPassengers() < list.get(i).getPassengers()
			       && list.get(i).getPassengers() > list.get(i + 1).getPassengers()) {
			if (list.get(i - 1).getPassengers() < list.get(i + 1).getPassengers())
			    dInfo.setDirection(-1);
			else
			    dInfo.setDirection(1);
		    }
		}
		result.add(dInfo);
	    }

	    //Now put all the informations in a text file
	    //First the train info
	    //Then the car info of each car

	    try{
		File ff=new File("./infos.txt");
		ff.createNewFile();
		FileWriter ffw=new FileWriter(ff);
		
		ffw.write(train.getLine() + "\n" + train.getPosition() + "\n" + train.getSize() + "\n");

		DisplayInformation d;
		while (result.size()!=0){
		    d=result.remove(0);
		    ffw.write(d.getPosition() + "\n" + d.getDirection() + "\n" + d.getColor() + "\n");
		}
		ffw.close();
		
	    } catch (Exception e) {}

	    list.clear();
	    result.clear();
        
        //increments the position of the train.
        train.nextStation();

	    //Now we send the text file wia HTTP to the display part of the system
	    //For this we use a python file and wait for its agreement.
	    Process p = Runtime.getRuntime().exec("python3 ./MQTTSender.py");
	    BufferedReader bufIn =
		new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String pythonOutput = bufIn.readLine();
	    System.out.println(pythonOutput);

	}
    }

}
