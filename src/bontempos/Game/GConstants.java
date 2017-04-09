package bontempos.Game;

import java.util.ArrayList;

import bontempos.Game.Act.Action;
import bontempos.Game.Act.Countdown;

/**
 * Game Constants Class to support Game objects - Ents, Stage, etc
 */


public class GConstants {

	private static GConstants instance;

	private ArrayList<Action> events;
	private ArrayList<Countdown> timers;

	//private static Game game;



	private GConstants() {
		events = new ArrayList<Action>();
		timers = new ArrayList<Countdown>();
	}
	////////////////////////////////////////////////////  GETTERS
	public static GConstants get() {
		if (instance == null) {
			instance = new GConstants();
		}
		return instance;
	}




	//////////////////////////////////////////////////// SETTERS

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