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
	
	public void update(){
		AConstants.get().updateLists();
	}

}
