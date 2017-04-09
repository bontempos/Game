package bontempos.Game.Act;

import java.lang.reflect.Method;

public class Action {

	
	public Object target; // the target object (where action will occur)
	public String method; // the name of the function within the object to invoke [0]
					// function name [1]... for parameters
	public Object[] params;
	
	
	//PApplet p;
	String label;
	boolean active = true;
	boolean autoRemove = true;
	
	//--------------------------------------------------------------------------------------<   INIT  >

	public Action(Object target, String method, Object... params) {
		this.target = target;
		this.method = method;
		this.params = params;
	}

	
	//--------------------------------------------------------------------------------------<   SETTERS  >

	public void setAutoRemove(boolean a) {
		this.autoRemove = a;
	}
	
	
	//--------------------------------------------------------------------------------------<   METHODS  >

	public void eval() {
		
		Action e = this;

		if (!e.active)
			return; // ignore if not active;

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
				toInvoke.invoke(e.target, e.params); // here methods is invoked.
				if (e.autoRemove)
					e.active = false;
			} catch (Exception t) {
				System.out.println(e.method.toString());
				t.printStackTrace();
			}
		}
	}

}
