package Objects;

public class ActorObject {
	private int objectNumber;
	private double sizeX;
	private double sizeY;
	private String name;
	private int posX;
	private int posY;
	
	public static int objectsInstantiated = 0;
	
	public ActorObject(){
		name = "default";
		objectNumber = objectsInstantiated;
		objectsInstantiated++;
		sizeX = 50.0;
		sizeY = 50.0;
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
	
	public boolean setPosX(int x){
		if(x >= -500 && x <= 500){
			posX = x;
			return true;
		}
		return false;
	}
	
	public boolean setPosY(int y){
		if(y >= -400 && y <= 400){
			posY = y;
			return true;
		}
		return false;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
}
