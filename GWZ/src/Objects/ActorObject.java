package Objects;

public class ActorObject {
	private int objectNumber;
	private double sizeX;
	private double sizeY;
	private String name;
	
	public static int objectsInstantiated = 0;
	
	public ActorObject(){
		name = "default";
		objectNumber = objectsInstantiated;
		objectsInstantiated++;
		sizeX = 1.0;
		sizeY = 1.0;
	}
	
	// remember to provide security against unreasonable input
	public ActorObject(int x, int y){
		this();
		sizeX = x;
		sizeY = y;
	}
	
	public int getObjectNumber(){
		return objectNumber;
	}
	
	public double getSizeX(){
		return sizeX;
	}
	
	public double getSizeY(){
		return sizeY;
	}
}
