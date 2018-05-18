
public class Color {

	private int red;
	private int green;
	private int blue;

	public Color() {
		red = 0;
		blue = 0;
		green = 0;
	}
	
	public void turnOff() {
		red = 0;
		blue = 0;
		green = 0;
	}

	public void setRed() {
		red = 255;
		blue = 0;
		green = 0;
	}
	
	public void setGreen() {
		red = 0;
		blue = 0;
		green = 255;
	}
	
	public void setOrange() {
		red = 255;
		blue = 0;
		green = 128;
	}

	public String toString() {
		return "" + red +","+ green +"," + blue;
	}

}

