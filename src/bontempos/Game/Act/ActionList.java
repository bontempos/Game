package bontempos.Game.Act;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ActionList extends ArrayList<Action> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean autoRepeat = false;
	boolean evalNext = false;
	boolean reverseList = false;
	int currentEval = 0;
	String evalNextActionName = "evalNext";
	int [] skip;
	Checker ckEvalNext;
	int index;

	public ActionList(){
		index = AConstants.get().addActionList(this);
		ckEvalNext = new Checker(evalNextActionName, this, "evalNext");
	}


	public void update(){

		if(evalNext){
			if(this.size() > 0 && this.size() > currentEval ){

				if( skip != null ){
					for(int i : skip){
						if( skip[i] == currentEval ) {
							evalNext();
							return;
						}
					}
				}

				Action e = this.get( currentEval );
				if(e.isActive()) {
					//System.out.println("ACTION LIST: eval action index "+ currentEval);
					e.eval();
					evalNext = false;
					currentEval++;
					if(currentEval==this.size()){
						if(autoRepeat) reset();
					}
				}
			}
		}
	}


	public void evalNext(){
		//System.out.println("ACTION LIST: evalNext(): action " + currentEval + " of " + (this.size()-1));
		this.evalNext = true;
		ckEvalNext.checked = false;
	}


	public void add(Action[] actions){
		this.addAll( Arrays.asList(actions) );
	}

	public void setAutoRepeat(boolean autoRepeat){
		this.autoRepeat = autoRepeat;
	}


	public void setSkip( int [] skip){
		this.skip = skip;
	}

	public void reset(){
		currentEval = 0;
		evalNext = false;
		reactiveAllActions();
	}

	public void setEvalName(String name){
		this.evalNextActionName = name;
		ckEvalNext.setActionName(evalNextActionName);
	}


	public int getIndex(){
		return index;
	}

	void reactiveAllActions(){
		for(Action a : this){
			if(!a.active)a.setActive(true);
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
}
