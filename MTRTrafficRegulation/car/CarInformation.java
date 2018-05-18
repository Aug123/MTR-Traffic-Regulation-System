
public class CarInformation {
	
	private int position;
	public int passengers;
	
	public CarInformation(int pos){
		position=pos;
		passengers=0;
	}
	
	public void setPassengers(int num) {
		passengers=num;
	}
	
	public int getPassengers() {
		return passengers;
	}
	
	public int getPosition() {
		return position;
	}
}
