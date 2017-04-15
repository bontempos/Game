package bontempos.Game.Act;

import java.util.ArrayList;
import processing.core.PApplet;


public class Act {

	public Act(PApplet p) {
		AConstants.setPApplet(p);
	}
	
	public ArrayList<Countdown> getTimers(){
		return AConstants.get().getTimers();
	}

	public ArrayList<Action> getActions(){
		return AConstants.get().getActions();
	}
	
	
	public ArrayList<Checker> getCheckers(){
		return AConstants.get().getCheckers();
	}
	
	public ArrayList<ActionList> getActionLists(){
		return AConstants.get().getActionLists();
	}
	
	public void update(){		
		AConstants.get().updateLists();
	}

}
