package Objects;
import java.util.ArrayList;

public class Abilities {
	public static void main(String[] args){
		ArrayList<ArrayList<Boolean>> tester = new ArrayList<ArrayList<Boolean>>(5);
		tester.ensureCapacity(5);
		tester.add(0, new ArrayList<Boolean>());
		System.out.println(tester.size());
	}
	
	private ArrayList<ArrayList<Boolean>> abilityList;
	
	public Abilities(){
		abilityList = new ArrayList<ArrayList<Boolean>>(5);
		for(int i = 0; i < 5; i++){
			abilityList.add(i, new ArrayList<Boolean>(3));
			for(int k = 0; k < 10; k++){
				abilityList.get(i).add(k, false);
			}
		}
	}
	
	public boolean unlockAbility(String ability, int score){
		if(score > 9 || score < 0){
			return false;
		}
		
		if(ability.equals("GA")){
			if(score == 0){
				abilityList.get(0).set(score, true);
				return true;
			}
			else if (abilityList.get(0).get(score-1) == true){
				abilityList.get(0).set(score, true);
				return true;
			}
			else return false;
		}
		
		else if(ability.equals("MA")){
			if(score == 0){
				abilityList.get(1).set(score, true);
				return true;
			}
			else if (abilityList.get(1).get(score-1) == true){
				abilityList.get(1).set(score, true);
				return true;
			}
			else return false;
		}
		
		else if(ability.equals("RS")){
			if(score == 0){
				abilityList.get(2).set(score, true);
				return true;
			}
			else if (abilityList.get(2).get(score-1) == true){
				abilityList.get(2).set(score, true);
				return true;
			}
			else return false;
		}
		
		else if(ability.equals("HP")){
			if(score == 0){
				abilityList.get(3).set(score, true);
				return true;
			}
			else if (abilityList.get(3).get(score-1) == true){
				abilityList.get(3).set(score, true);
				return true;
			}
			else return false;
		}
		
		else if(ability.equals("MP")){
			if(score == 0){
				abilityList.get(4).set(score, true);
				return true;
			}
			else if (abilityList.get(4).get(score-1) == true){
				abilityList.get(4).set(score, true);
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public String getAbilityUnlock(String ability, int score){
		if(ability.equals("GA")){
			if (abilityList.get(0).get(score) == true){
				return "unlocked";
			}
			else return "locked";
		}
		if(ability.equals("MA")){
			if (abilityList.get(1).get(score) == true){
				return "unlocked";
			}
			else return "locked";
		}
		if(ability.equals("RS")){
			if (abilityList.get(2).get(score) == true){
				return "unlocked";
			}
			else return "locked";
		}
		if(ability.equals("HP")){
			if (abilityList.get(3).get(score) == true){
				return "unlocked";
			}
			else return "locked";
		}
		if(ability.equals("MP")){
			if (abilityList.get(4).get(score) == true){
				return "unlocked";
			}
			else return "locked";
		}
		else return "nil";
	}
}
