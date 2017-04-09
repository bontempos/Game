package bontempos.Game.Act;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * Action Constants Class to support Countdown and Action classes
 */


public class AConstants {

	private static AConstants instance;

	private ArrayList<Action> events;
	private ArrayList<Countdown> timers;
	static PApplet papplet;

	//private static Game game;

	

	private AConstants() {
		events = new ArrayList<Action>();
		timers = new ArrayList<Countdown>();
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


	//////////////////////////////////////////////////// SETTERS
	
	public static void setPApplet( PApplet p){
		papplet = p;
	}

	public void addTimer(Countdown t){
		timers.add(t);
		System.out.println("timer added");
	}

	public void addEvent(Action e){
		events.add(e);
	}


	//////////////////////////////////////////////////// UPDATE	

	public void updateLists(){
		for(Countdown t: timers) t.update();
		for(Action e: events) e.eval();
	}

	public void removeInactiveEvents(){

	}

}