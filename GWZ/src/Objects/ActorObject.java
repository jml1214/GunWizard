package Objects;

public class ActorObject {
	private int objectNumber;
	private double sizeX;
	private double sizeY;
	private String name;
	private double posX;
	private double posY;
	
	public static int objectsInstantiated = 0;
	
	public ActorObject(){
		name = "default";
		objectNumber = objectsInstantiated;
		objectsInstantiated++;
		sizeX = 50.0;
		sizeY = 50.0;
		posX = 0;
		posY = 0;
	}
	
	public ActorObject(String name){
		this();
		this.name = name;
	}
	// remember to provide security against unreasonable input
	public ActorObject(int x, int y, String name){
		this();
		this.name = name;
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
	
	public boolean setPosX(double x){
		if(x >= -500 && x <= 500){
			posX = x;
			return true;
		}
		return false;
	}
	
	public boolean setPosY(double y){
		if(y >= -400 && y <= 400){
			posY = y;
			return true;
		}
		return false;
	}
	
	public double getPosX(){
		return posX;
	}
	
	public double getPosY(){
		return posY;
	}
}
