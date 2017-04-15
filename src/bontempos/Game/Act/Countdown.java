package bontempos.Game.Act;


public class Countdown {


	public int time;
	public int interval;


	int start;
	int loops;
	boolean active = true;
	boolean stop = true;
	boolean timeout = false;
	boolean autoReset = false;
	boolean autoStop = true;
	boolean repeat = false;
	boolean paused;
	Action toInvoke;

	//--------------------------------------------------------------------------------------<   INIT  >

	public Countdown(){
		AConstants.get().addTimer(this);
	}


	public Countdown(int interval) {
		this.interval = interval;
		AConstants.get().addTimer(this);
	}

	public Countdown(int interval, Action toInvoke) {
		this.interval = interval;
		AConstants.get().addTimer(this);
		this.toInvoke = new Action(toInvoke.target, toInvoke.method, toInvoke.params); // copying
	}

	//--------------------------------------------------------------------------------------<   GETTERS  >

	public int getTime() {
		return time;
	}

	public boolean isRunning(){
		return !stop;
	}

	public boolean timeout(){
		return timeout;
	}

	public int getLoops() {
		return loops;
	}
	
	public boolean isActive(){
		return active;
	}

	//--------------------------------------------------------------------------------------<   SETTERS  >

	public void setActive( boolean b ){
		this.active = b;
	}

	public void setAutoReset(boolean b){
		this.autoReset = b;
	}

	public void setAutoStop(boolean b){
		this.autoStop = b;
	}

	public void setRepeat(boolean b){
		this.repeat = b;
	}

	public void setPaused(boolean b){
		this.paused = b;
	}

	public void setInterval( int interval ) {
		this.interval = interval;
	}

	public void setEvent( Action toInvoke ) {
		this.toInvoke = toInvoke;
	}

	public void setInvokeAutoRemove(boolean b) {
		toInvoke.setAutoRemove(b);
	}

	//--------------------------------------------------------------------------------------<   METHODS  >


	private void checkParent() {
		if(AConstants.papplet == null) System.out.println("WARNING: please initialize ACT before using Countdown or Action classes. Act act = new Act(this)");
	}

	public void start() {
		checkParent();
		start = AConstants.papplet.millis();
		timeout = false;
		stop = false;
	}

	public void reset() {
		timeout = false;
		stop = true;
		if (toInvoke != null ) toInvoke.active = true;
	}


	//--------------------------------------------------------------------------------------<   UPDATES  >


	public void update() {
		if (active){
			if (!stop) {

				if (this.paused)
					start = AConstants.papplet.millis() - time;
				time = AConstants.papplet.millis() - start;

				if (time > interval) {

					/*
					 * TIMEOUT
					 */
					timeout = true;
					loops ++;

					if (toInvoke != null)
						AConstants.get().addAction(toInvoke);

					if (autoStop)
						stop = true;

					if (autoReset)
						reset();

					if (repeat) {
						start();
						if(toInvoke != null) toInvoke.active = true;
					}
				} else {
					timeout = false;
				}
			}
		}
	}

}
