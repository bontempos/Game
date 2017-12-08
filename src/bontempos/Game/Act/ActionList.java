/**
 * * Auto creates a checker function to evaluate when next stored actions needs to be performed. 
 * This can be called from a simple Action.perform( evalNext ), where evalNext is a String matching the local variable evalNextActionName
 *
 * @author       Anderson Sudario
 * @version      1.0
 * 2017
 */

package bontempos.Game.Act;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ActionList extends ArrayList<Action> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean echo = false;
	boolean autoClear = false; //TODO this and autoRepeat might be redundant?
	boolean autoRepeat = false;
	boolean evalNext = false;
	boolean reverseList = false;
	boolean complete = false;
	int currentEval = 0;
	int lastEval = 0;
	String evalNextActionName = "evalNext";
	int [] skip; //skip specific action indexes
	int lastSkipped; //currentEval index. when double calls are made, used to make sure lastSkipped is not being mistaken
	Checker ckEvalNext;
	int index;
	String currentEvalActionName = "";

	public ActionList(){
		if(echo) System.out.println("ActionList start");
		index = AConstants.get().addActionList(this);
		ckEvalNext = new Checker(evalNextActionName, this, "evalNext");
	}


	public void update(){
		
		//updated by AConstants every frame.
		
		if(evalNext){
			//System.out.println("there something to do" + currentEval);
			if(!this.isEmpty() && this.size() > currentEval ){
				
				//in case there are indexes to be skipped
				if( skip != null ){
					for(int i : skip){
						if( skip[i] == currentEval ) {
							lastSkipped = currentEval;
							currentEval++;
							return;
						}
					}
				}
				
				
				//make sure we are not skipping anything by mistake. Sometimes the function "callNextAction" can be called twice by mistake. TODO
				
//				if(currentEval > 0){
//					boolean lastActionPerformed = this.get(lastEval).performed; //correct will be (action _isFinished) but maybe impossible.
//					if( !lastActionPerformed ) { 
//						
//						currentEval = lastEval;
//						return;
//					}else{
//						//here is the problem. if there are 2 calls, the previous actions can be performed 1 ms
//						//before next action but still not finished and both actions are performing at same time incorrectly
//					}
//				}

				Action e = this.get( currentEval );
				if(e.isActive()) {
					//System.out.println("ACTION LIST: eval action index "+ currentEval);
					currentEvalActionName = e.eval(); // I AM DOING IT NOW - I want to check when a action is evaluated from INSIDE here
					evalNext = false;
					lastEval = currentEval;
					currentEval++;
					if(currentEval==this.size()){
						if(autoRepeat) reset();
					}
				}
				
			}
			//terminate after last action is performed
			else if(currentEval == this.size()){
				if(!complete){
					complete = true;
					//System.out.println("ACTION LIST: all actions performed. autoClear = " + autoClear);
					reset();
				}
			}
		}
	}


	public void evalNext(){
		//System.out.println("ACTION LIST: evalNext(): action " + currentEval + " of " + (this.size()-1));
		this.evalNext = true;
		ckEvalNext.checked = false;
	}

    
	public void addSet(Action[] actions){
		if(echo) System.out.println("set added all actions to action list");
		this.addAll( Arrays.asList(actions) );
	}

	public void setAutoRepeat(boolean autoRepeat){
		this.autoRepeat = autoRepeat;
	}

	public void setAutoClear(boolean autoClear){
		this.autoClear = autoClear;
	}

	public void setSkip( int [] skip){
		this.skip = skip;
	}

	public void reset(){
		complete = false;
		currentEval = 0;
		evalNext = false;
		if(autoClear) {
			this.clear();
		}else{
			reactiveAllActions();
		}
	}

	public void setEvalName(String name){
		this.evalNextActionName = name;
		ckEvalNext.setActionName(evalNextActionName);
	}
	
	public String getCurrentEvalActionName(){
		return currentEvalActionName;
	}

	public int getIndex(){
		return index;
	}

	void reactiveAllActions(){
		for(Action a : this){
			if(!a.active)a.setActive(true);
			a.performed = false;
		}
	}

	public void reverse(){
		reverseList = !reverseList;
		Collections.reverse(this);
	}

	public void printList(){

		for (int i = 0; i < this.size(); i++) {
			Action e = this.get(i);
			System.out.println("Action "+ i + ": name: "+  e.getActionName() + ", method: " + e.method);
		}

	}
	
	public ArrayList<String> getArrayList(){
		ArrayList<String> as = new ArrayList<String>();
		for (int i = 0; i < this.size(); i++) {
			Action e = this.get(i);
			as.add("Action "+ i + ": name: "+  e.getActionName() + ", method: " + e.method + ", performed: " + e.performed);
		}
		return as;
	}
}
