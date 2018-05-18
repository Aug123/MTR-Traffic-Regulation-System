
public class DisplayInformation {
	
	private int position;
	public Color color;
	public int direction;
	
	public DisplayInformation(int pos) {
		position=pos;
		color=new Color();
		direction=0;
	}
	
	public int getPosition() {
		return position;
	}	
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void setColor(Color c) {
		color=c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setDirection(int n) {
		direction=n;
	}

	public int getDirection() {
		return direction;
	}
	
	public void printInfo() {
		System.out.println(position + " " + direction + " " + color.toString());
	}

    public String infoToString(){
        return position + " " + direction + " " + color.toString();
    }

	
	
	
}
