package bontempos.Game.Act;

public class Checker implements Comparable<Checker> {

	String actionName; //the name of the event to be listening
	Object target;    //the target object (where action will occur)
	String method;    //the name of the function within the object to invoke [0] function name [1]... for parameters
	Object[] params;
	Action toInvoke = null;
	boolean checked = false;
	boolean active;
	boolean autoRemove;
	int priority = 0;
	String label;


	//--------------------------------------------------------------------------------------<   INIT  >

	public Checker(String actionName, Object target, String method, Object... params) {
		this.actionName = actionName;
		this.target = target;
		this.method = method;
		this.params = params;
		active = true;
		autoRemove = false;
		//System.out.println("checker created. adding to list");
		AConstants.get().addChecker(this);


	};

	public Checker(Action toInvoke){
		this.toInvoke = toInvoke;
		//System.out.println("checker created. adding to list");
		AConstants.get().addChecker(this);
	}

	@Override
	public int compareTo(Checker o) {
		return priority;
	}

	//--------------------------------------------------------------------------------------<   SETTERS  >

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAutoRemove(boolean autoRemove){
		this.autoRemove = autoRemove;
	}

	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public void setActionName(String name){
		this.actionName = name;
	}

	//--------------------------------------------------------------------------------------<   GETTERS  >

	public String getActionName(){
		return actionName;
	}

	public boolean isActive(){
		return active;
	}
	
	public boolean isChecked(){
		return checked;
	}
	//--------------------------------------------------------------------------------------<   METHODS  >

	public void dispatchAction(){
		//System.out.println("CHECK: checker dispatch action: "+ actionName);

		//TODO set if toInvoke == null here
		//System.out.println("CHECK: creating new action to invoke. method: " + method + " in target " + target.getClass().getSimpleName());
		toInvoke = new Action(actionName, target, method, params);

		//toInvoke.setAutoRemove(true);
		AConstants.get().addAction(toInvoke);
		if(autoRemove) {
			remove();
		}
	}

	public void remove(){
		//System.out.println("removing listener " + actionName);
		AConstants.get().checkers.remove(this);
	}




}
