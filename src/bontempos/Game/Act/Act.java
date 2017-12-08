/**
 * Act class register the "pre" loop function from processing and updates all related classes to actions, countdowns, checkers in this package.
 *
 *
 * @author       Anderson Sudario
 * @version      1.0
 * 2017
 */

package bontempos.Game.Act;

import java.util.ArrayList;
import processing.core.PApplet;


public class Act {

	public Act(PApplet p){
		System.out.println("Act start");
		p.registerMethod("pre", this);
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
	
	public void pre(){		
		AConstants.get().updateLists();
	}

}
