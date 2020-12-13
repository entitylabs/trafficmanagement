package traffic.management.model;

public class Position {

	private int xCoordinate;
	private int yCoordinate;
	
	
	public Position(int xCoordinate, int yCoordinate) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}


	public int getxCoordinate() {
		return xCoordinate;
	}


	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}


	public int getyCoordinate() {
		return yCoordinate;
	}


	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}


	@Override
	public String toString() {
		return "[X=" + xCoordinate + ",Y=" + yCoordinate + "]";
	}
	
	
	

}
