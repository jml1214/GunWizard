package Objects;

public class ProjectileObject extends ActorObject{
	private int orientation;
	private CharacterObject ch;
	private double originY;
	private double originX;
	private double destY;
	private double destX;
	
	public ProjectileObject(int orient, CharacterObject ch, double destiX, double destiY, double origX, double origY){
		super();
		setOrient(orient);
		this.ch = ch;
		destX = destiX;
		destY = destiY;
		originX = origX;
		originY = origY;
	}
	
	public boolean setOrient(int orient){
		if(orient >= 0 && orient <=3){
			orientation = orient;
			return true;
		}
		return false;
	}
	
	public int getOrient(){
		return orientation;
	}
	
	public CharacterObject getChar(){
		return ch;
	}
	
	public double getDestY(){
		return destY;
	}
	
	public double getDestX(){
		return destX;
	}
	public double getOrigY(){
		return originY;
	}
	
	public double getOrigX(){
		return originX;
	}
}
