package Objects;

public class CharacterObject extends ActorObject {
	private int hPoints;
	private int mPoints;
	private int maxHealth;
	private int maxMana;
	private Abilities charAbilities;
	private boolean alive;
	
	final public static int DEF_HEALTH_POINTS = 100;
	final public static int DEF_MANA_POINTS = 50;
	final public static int MAX_TOTAL_HP = 1000;
	final public static int MAX_TOTAL_MANA = 600;
	
	public static void main(String[] args){
		CharacterObject a = new CharacterObject();
	}
		
	public CharacterObject(){
		super();
		hPoints = DEF_HEALTH_POINTS;
		mPoints = DEF_MANA_POINTS;
		maxHealth = DEF_HEALTH_POINTS;
		maxMana = DEF_MANA_POINTS;
		charAbilities = new Abilities();
		alive = true;
	}
	
	public CharacterObject(String name){
		super(name);
		hPoints = DEF_HEALTH_POINTS;
		mPoints = DEF_MANA_POINTS;
		maxHealth = DEF_HEALTH_POINTS;
		maxMana = DEF_MANA_POINTS;
		charAbilities = new Abilities();
		alive = true;
	}
	
	// working on parameterized constructor for CharacterObject
	public CharacterObject(int x, int y, int hp, int mp, int mh, int mm, String name){
		super(x,y, name);
		if(!setMaxHealth(mh)){
			maxHealth = DEF_HEALTH_POINTS;
		}
		if(!setMaxMana(mm)){
			maxMana = DEF_MANA_POINTS;
		}
		if(!setHealth(hp)){
			hPoints = DEF_HEALTH_POINTS;
		}
		if(!setMana(mp)){
			hPoints = DEF_MANA_POINTS;
		}
		charAbilities = new Abilities();
		alive = true;
	}
	
	public int getHealth(){
		return hPoints;
	}
	
	public int getMana(){
		return mPoints;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getMaxMana(){
		return maxMana;
	}
	
	public boolean setHealth(int val){
		if(val >= 0 && val <= maxHealth){
			hPoints = val;
			return true;
		}
		return false;
	}
	
	public boolean setMana(int val){
		if(val >= 0 && val <= maxMana){
			mPoints = val;
			return true;
		}
		return false;
	}
	
	public boolean setMaxHealth(int val){
		if(val >= 0 && val <= MAX_TOTAL_HP){
			maxHealth = val;
			return true;
		}
		return false;
	}
	
	public boolean setMaxMana(int val){
		if(val >= 0 && val <= MAX_TOTAL_MANA){
			maxMana = val;
			return true;
		}
		return false;
	}
	
	public void fillHealth(){
		hPoints = maxHealth;
	}
	
	public void fillMana(){
		mPoints = maxMana;
	}
	
	public String reduceHealth(int val){
		if(val >= hPoints){
			hPoints = 0;
			alive = false;
			return "dead";
		}
		else {
			hPoints -= val;
			return "damaged";
		}
	}
	
	public boolean reduceMana(int val){
		if(val > mPoints){
			return false;
		}
		else {
			mPoints -= val;
			return true;
		}
	}
	
	public Abilities getAbilities(){
		return charAbilities;
	}
	
	public void setAbilities(Abilities newAbilities){
		charAbilities = newAbilities;
	}
}
