
public class TrainInformation {

	private int position;
	private int line;
	private int size;
	
	public TrainInformation(int pos, int line, int num) {
		position=pos;
		this.line=line;
		size=num;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void nextStation() {
		position++;
	}
	
	public void todayLine(int num) {
		line=num;
	}
	
}
