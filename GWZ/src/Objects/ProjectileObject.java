package Objects;

public class ProjectileObject extends ActorObject{
	private int orientation;
	private CharacterObject ch;
	
	public ProjectileObject(int orient, CharacterObject ch){
		super();
		setOrient(orient);
		this.ch = ch;
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
}
