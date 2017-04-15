package bontempos.Game.Act;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Action Constants Class to support Countdown and Action classes
 */


public class AConstants {

	private static AConstants instance;

	ArrayList<Action> events;
	ArrayList<Countdown> timers;
	ArrayList<Checker> checkers;
	ArrayList<ActionList> actionLists;
	static PApplet papplet;

	//private static Game game;

	

	private AConstants() {
		events = new ArrayList<Action>();
		timers = new ArrayList<Countdown>();
		checkers = new ArrayList<Checker>();
		actionLists = new ArrayList<ActionList>();
	}
	////////////////////////////////////////////////////  GETTERS
	public static AConstants get() {
		if (instance == null) {
			instance = new AConstants();
		}
		return instance;
	}

	public ArrayList<Countdown> getTimers(){
		return timers;
	}
	
	public ArrayList<Action> getActions(){
		return events;
	}
	
	public ArrayList<Checker> getCheckers(){
		return checkers;
	}
	
	public ArrayList<ActionList> getActionLists(){
		return actionLists;
	}


	//////////////////////////////////////////////////// SETTERS
	
	public static void setPApplet( PApplet p){
		papplet = p;
	}

	public int addTimer(Countdown t){
		timers.add(t);
		//System.out.println("timer added");
		return timers.size();
	}

	public int addAction(Action e){
		events.add(e);
		//System.out.println("action added");
		return events.size();
	}
	
	public int addChecker(Checker c){
		checkers.add(c);
		//System.out.println("checker added");
		return checkers.size();
	}
	
	public int addActionList(ActionList l){
		actionLists.add(l);
		//System.out.println("action list added");
		return actionLists.size();
	}


	//////////////////////////////////////////////////// UPDATE	

	public void updateLists(){
		if(timers.size()>0){
			for(int i = timers.size()-1; i >= 0; i--){
				Countdown t = timers.get(i);
				t.update();
			}
		}
	
		if(events.size()>0){
			for(int i = events.size()-1; i >= 0; i--){
				Action e = events.get(i);
				if(e.isActive()) {
					e.eval();
				}else{
					events.remove(e);
				}
			}
		}
		
		if(actionLists.size()>0){
			for(int i = actionLists.size()-1; i>=0; i--){
				ActionList L = actionLists.get(i);
				L.update();
			}
		}
		
		
	}

	//////////////////////////////////////////////////// METHODS		
	
	public boolean check(String actionName){
		Collections.sort(checkers);
		//System.out.println("checking if action: "+ actionName + " exists in checking list");
		for(Checker c : checkers){
			if(c.actionName.equals(actionName)){
				if(c.isActive() && !c.isChecked()){
					c.checked = true;
					c.dispatchAction();
					return true;
				}
			}
			
		}
		return false;
	}
}