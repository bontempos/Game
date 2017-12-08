/**
 * Action is a container, where the name of a method and parameters can be called by a Checker or Countdown object. Actions can be stacked on an ActionList Object.
 *
 *
 * @author       Anderson Sudario
 * @version      1.0
 * 2017
 */


package bontempos.Game.Act;

import java.lang.reflect.Method;


public class Action {

	private static Action instance;
	public boolean echo = false;

	public Object target; // the target object (where action will occur)
	public String method; // the name of the function within the object to invoke [0]
	// function name [1]... for parameters
	public Object[] params;

	public String actionName;
	boolean active = true;
	boolean performed = false;
	boolean autoRemove = true;

	//--------------------------------------------------------------------------------------<   INIT  >

	public Action(Object target, String method, Object... params) {
		this.target = target;
		this.method = method;
		this.params = params;
	}

	public Action(String actionName, Object target, String method, Object... params) {
		this.actionName = actionName;
		this.target = target;
		this.method = method;
		this.params = params;
	}

	public Action(String actionName){
		this.actionName = actionName;
		//Action.perform(actionName);
		//AConstants.get().check(actionName);
	}


	//--------------------------------------------------------------------------------------<   SETTERS  >

	public void setAutoRemove(boolean a) {
		this.autoRemove = a;
	}

	public void setName(String name){
		this.actionName = name;
	}
	
	public void setActive(boolean b){
		this.active = b;
	}
	
	public void setEcho(boolean b){
		this.echo = b;
	}

	//--------------------------------------------------------------------------------------<   GETTERS  >

	public static boolean perform(String actionName) {
		if (instance == null) {
			instance = new Action(actionName);
		}
		return AConstants.get().check(actionName);
	}
	
	
	public boolean isActive(){
		return active;
	}
	
	public String getActionName(){
		return actionName;
	}

	//--------------------------------------------------------------------------------------<   METHODS  >

	public String  eval() {

		Action e = this;

		if (!e.active){
			return actionName + ":!active"; // ignore if not active;
		}
		
		if (e.target == null || e.method == ""){
			AConstants.get().check(e.actionName);
			//System.out.println("ACTION: performing action "+ e.actionName);
			return actionName;
		}
		
		//System.out.println("ACTION: Evaluating action name: "+ actionName);
		Class<?> cls = e.target.getClass(); // get class of target object
		Method[] methods = cls.getMethods(); // get all methods of the class
		// above
		Method toInvoke = null; // this is the method to be invoked
		for (Method method : methods) { // find the method to be
			// invoked inside all
			// methods

			if (!e.method.equals(method.getName())) {
				continue;
			}
			Class<?>[] paramTypes = method.getParameterTypes(); // Method found:
			// test
			// parameters
			// (if this is
			// the case)
			if (e.params == null && paramTypes == null) { // are any params
				// being required by
				// the event?
				// if not, just
				// invoke the method
				// with no params.
				break;
			} else if (e.params == null || paramTypes == null || paramTypes.length != e.params.length) {
				continue;
			}

			// for (int i = 0; i < e.params.length; ++i) {
			// if (!paramTypes[i].isAssignableFrom(e.params[i].getClass())) {
			// continue methodLoop;
			// }
			// }

			toInvoke = method;
		}

		if (toInvoke != null) {
			
			try {
				if(echo) {System.out.println("executing action, method: " + e.method.toString() +
						", parameters: " + e.params.toString() +
						", target: " + e.target.getClass().getName()
				);
				}
				toInvoke.invoke(e.target, e.params); // here methods is invoked.
				performed = true;
				if (e.autoRemove){
					e.active = false;
				}
			} catch (Exception t) {
				//System.out.println(e.method.toString());
				t.printStackTrace();
			}
		}
		return actionName;
	}

}
