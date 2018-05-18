import java.io.*;
import java.net.*;
import java.util.*;


public class ClientCar {

	public static void main(String[] args) throws Exception {

        //set the informations of the car
        int position=2;

        MyRandom rnd=new MyRandom();
        
        while (true){

		    // Bind the socket to the server with the appropriate port
		    Socket socket = new Socket("192.168.1.1", 1234);
		    // Setup I/O streams
		    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            CarInformation s=new CarInformation(position);
		    
            s.setPassengers(rnd.nextInt(30));
            
            System.out.println("The CarInfo " + s.getPosition() + "  " + s.getPassengers());

		    out.writeObject(s.getPosition());
            out.writeObject(s.getPassengers());
		    out.flush();
    
    		Thread.sleep(2000);
            
        }
	}

}
